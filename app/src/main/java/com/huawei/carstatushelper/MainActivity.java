package com.huawei.carstatushelper;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.charging.AbsBYDAutoChargingListener;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.speed.AbsBYDAutoSpeedListener;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.hardware.bydauto.tyre.AbsBYDAutoTyreListener;
import android.hardware.bydauto.tyre.BYDAutoTyreDevice;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.huawei.carstatushelper.activity.AboutActivity;
import com.huawei.carstatushelper.activity.SettingsActivity;
import com.huawei.carstatushelper.byd_helper.BYDAutoAcDeviceHelper;
import com.huawei.carstatushelper.byd_helper.BYDAutoStatisticDeviceHelper;
import com.huawei.carstatushelper.byd_helper.ChargingDeviceHelper;
import com.huawei.carstatushelper.databinding.ActivityMainBinding;
import com.huawei.carstatushelper.databinding.ActivityMainLandBinding;
import com.huawei.carstatushelper.databinding.ActivityMainLandMultiBinding;
import com.huawei.carstatushelper.receiver.BootCompleteService;
import com.huawei.carstatushelper.service.FloatingService;
import com.huawei.carstatushelper.util.BydApi29Helper;
import com.huawei.carstatushelper.util.BydManifest;
import com.huawei.carstatushelper.util.PermissionUtils;
import com.huawei.carstatushelper.util.StringUtil;
import com.huawei.carstatushelper.view.CarSpeedView;
import com.huawei.carstatushelper.view.EnginePowerView;
import com.huawei.carstatushelper.view.EngineSpeedView;
import com.huawei.carstatushelper.view.MotorSpeedView;
import com.huawei.carstatushelper.view.RearMotorSpeedView;
import com.socks.library.KLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final DecimalFormat format = new DecimalFormat("##0.0##");

    private BYDAutoEngineDevice engineDevice;
    private BYDAutoSpeedDevice speedDevice;
    private BYDAutoStatisticDevice statisticDevice;
    private BYDAutoEnergyDevice energyDevice;
    private BYDAutoGearboxDevice gearboxDevice;
    private BYDAutoAcDevice bydAutoAcDevice;
    private BYDAutoChargingDevice chargingDevice;
    private BYDAutoTyreDevice tyreDevice;
    private BYDAutoBodyworkDevice bodyworkDevice;
    private BYDAutoSettingDevice settingDevice;
    private BYDAutoInstrumentDevice instrumentDevice;
    /**
     * 累计平均电耗
     */
    private double totalElecConPHM;
    /**
     * 累计平均油耗
     */
    private double totalFuelConPHM;
    /**
     * 发动机转速
     */
    private EngineSpeedView engineSpeedEsv;
    /**
     * 前电机转速
     */
    private MotorSpeedView frontMotorSpeedMsv;
    /**
     * 车速表
     */
    private CarSpeedView carSpeedCsv;
    /**
     * 功率表
     */
    private EnginePowerView enginePowerEpv;

    private ProgressBar youMengPb;
    private ProgressBar shaChePb;
    private LinearLayout shaCheGroupLayout;
    private int init_totalMileageValue;
    private int init_evMileageValue;
    private int init_hevMileageValue;
    private double init_totalElecConValue;
    private double init_totalFuelConValue;
    private double init_latest_electric_price;
    private double init_latest_fuel_price;
    /**
     * 后电机转速
     */
    private RearMotorSpeedView rearMotorSpeedMsv;
    private SharedPreferences mPreferences;
    private boolean supportTyreValue;
    private TextView mapPlaceHolderTv;
    private TextView controlModeStatusTv, acOnOffStatusTv, compressorStatusTv, defrostModeStatusTv, cycleModeStatusTv, ventilateStatusTv;
    private com.huawei.carstatushelper.databinding.ActivityMainLandMultiBinding activityMainLandMultiBinding;
    private com.huawei.carstatushelper.databinding.ActivityMainLandBinding activityMainLandBinding;
    private com.huawei.carstatushelper.databinding.ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.app_name) + BuildConfig.VERSION_NAME);

        loadContentView();

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        loadCurrentTripData();

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        boolean need = PermissionUtils.needRequestPermission(this, SplashActivity.BYD_PERMISSIONS);
        if (need) {
            Toast.makeText(this, "车辆权限不足，无法获取车辆数据", Toast.LENGTH_SHORT).show();
            return;
        }
        initDevice();
        initAutoData();
    }

    private void loadCurrentTripData() {
        String json = mPreferences.getString(BootCompleteService.KEY_INIT_DRIVER_DATA, null);
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONObject initDriverData = new JSONObject(json);
                init_totalMileageValue = initDriverData.getInt("totalMileageValue");
                init_evMileageValue = initDriverData.getInt("evMileageValue");
                init_hevMileageValue = initDriverData.getInt("hevMileageValue");
                init_totalElecConValue = initDriverData.getDouble("totalElecConValue");
                init_totalFuelConValue = initDriverData.getDouble("totalFuelConValue");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean show_sha_che_group_layout = mPreferences.getBoolean("show_sha_che_group_layout", getResources().getBoolean(R.bool.default_show_sha_che_group_layout_value));
        if (shaCheGroupLayout != null) {
            shaCheGroupLayout.setVisibility(show_sha_che_group_layout ? View.VISIBLE : View.GONE);
        }
        boolean power_speed_enable = mPreferences.getBoolean("power_speed_enable", getResources().getBoolean(R.bool.default_power_speed_enable_value));
        if (enginePowerEpv != null) {
            enginePowerEpv.setVisibility(power_speed_enable ? View.VISIBLE : View.GONE);
        }
        boolean car_speed_enable = mPreferences.getBoolean("car_speed_enable", getResources().getBoolean(R.bool.default_car_speed_enable_value));
        if (carSpeedCsv != null) {
            carSpeedCsv.setVisibility(car_speed_enable ? View.VISIBLE : View.GONE);
        }
        boolean rear_motor_speed_enable = mPreferences.getBoolean("rear_motor_speed_enable", getResources().getBoolean(R.bool.default_rear_motor_speed_enable_value));
        if (rearMotorSpeedMsv != null) {
            rearMotorSpeedMsv.setVisibility(rear_motor_speed_enable ? View.VISIBLE : View.GONE);
        }
        boolean map_place_holder_enable = mPreferences.getBoolean("map_place_holder_enable", getResources().getBoolean(R.bool.default_map_place_holder_enable_value));
        if (mapPlaceHolderTv != null) {
            mapPlaceHolderTv.setVisibility(map_place_holder_enable ? View.VISIBLE : View.GONE);
        }
        init_latest_fuel_price = Double.parseDouble(mPreferences.getString("latest_fuel_price", getString(R.string.default_latest_fuel_price)));
        init_latest_electric_price = Double.parseDouble(mPreferences.getString("latest_electric_price", getString(R.string.default_latest_electric_price)));
        if (statisticDevice != null) {
            absBYDAutoStatisticListener.onTotalMileageValueChanged(statisticDevice.getTotalMileageValue());
        }
    }

    private DataHolder dataHolder;

    private void refreshUI() {
        if (activityMainBinding != null) {
            activityMainBinding.setVariable(BR.data, dataHolder);
        }
        if (activityMainLandBinding != null) {
            activityMainLandBinding.setVariable(BR.data, dataHolder);
        }
        if (activityMainLandMultiBinding != null) {
            activityMainLandMultiBinding.setVariable(BR.data, dataHolder);
        }
    }

    private void loadContentView() {
        int orientation = getResources().getConfiguration().orientation;
        View rootView;
        dataHolder = new DataHolder();
        if (isInMultiWindowMode()) {
            activityMainLandMultiBinding = ActivityMainLandMultiBinding.inflate(getLayoutInflater());
            activityMainLandMultiBinding.setVariable(BR.listener, this);
            activityMainLandMultiBinding.setVariable(BR.data, dataHolder);
            rootView = activityMainLandMultiBinding.getRoot();
            initMainLandMultiView(activityMainLandMultiBinding);
        } else {
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                activityMainLandBinding = ActivityMainLandBinding.inflate(getLayoutInflater());
                activityMainLandBinding.setVariable(BR.listener, this);
                activityMainLandBinding.setVariable(BR.data, dataHolder);
                rootView = activityMainLandBinding.getRoot();
                initMainLandView(activityMainLandBinding);
            } else {
                activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
                activityMainBinding.setVariable(BR.listener, this);
                activityMainBinding.setVariable(BR.data, dataHolder);
                rootView = activityMainBinding.getRoot();
                initMainView(activityMainBinding);
            }
        }
        setContentView(rootView);
    }

    private void initMainView(ActivityMainBinding binding) {
        engineSpeedEsv = binding.engineSpeedEsv;
        frontMotorSpeedMsv = binding.motorSpeedMsv;
        enginePowerEpv = binding.enginePowerEpv;
        carSpeedCsv = binding.carSpeedCsv;
        rearMotorSpeedMsv = binding.rearMotorSpeedMsv;
        mapPlaceHolderTv = binding.mapPlaceHolderTv;
        controlModeStatusTv = binding.controlModeStatusTv;
        acOnOffStatusTv = binding.acOnOffStatusTv;
        compressorStatusTv = binding.compressorStatusTv;
        defrostModeStatusTv = binding.defrostModeStatusTv;
        cycleModeStatusTv = binding.cycleModeStatusTv;
        ventilateStatusTv = binding.ventilateStatusTv;
    }

    private void initMainLandView(ActivityMainLandBinding binding) {
        engineSpeedEsv = binding.engineSpeedEsv;
        frontMotorSpeedMsv = binding.motorSpeedMsv;
        carSpeedCsv = binding.carSpeedCsv;
        enginePowerEpv = binding.enginePowerEpv;
        rearMotorSpeedMsv = binding.rearMotorSpeedMsv;
        shaCheGroupLayout = binding.shaCheGroupLayout;
        youMengPb = binding.youMengPb;
        shaChePb = binding.shaChePb;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean enable = preferences.getBoolean("show_sha_che_group_layout", false);
        shaCheGroupLayout.setVisibility(enable ? View.VISIBLE : View.GONE);
        controlModeStatusTv = binding.controlModeStatusTv;
        acOnOffStatusTv = binding.acOnOffStatusTv;
        compressorStatusTv = binding.compressorStatusTv;
        defrostModeStatusTv = binding.defrostModeStatusTv;
        cycleModeStatusTv = binding.cycleModeStatusTv;
        ventilateStatusTv = binding.ventilateStatusTv;
    }

    private void initMainLandMultiView(ActivityMainLandMultiBinding binding) {
        engineSpeedEsv = binding.engineSpeedEsv;
        frontMotorSpeedMsv = binding.motorSpeedMsv;
        carSpeedCsv = binding.carSpeedCsv;
        youMengPb = binding.youMengPb;
        shaChePb = binding.shaChePb;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (statisticDevice == null) {
            return;
        }
        statisticDevice.unregisterListener(absBYDAutoStatisticListener);
        bodyworkDevice.unregisterListener(absBYDAutoBodyworkListener);
        speedDevice.unregisterListener(absBYDAutoSpeedListener);
        energyDevice.unregisterListener(absBYDAutoEnergyListener);
        bydAutoAcDevice.unregisterListener(absBYDAutoAcListener);
        gearboxDevice.unregisterListener(absBYDAutoGearboxListener);
        chargingDevice.unregisterListener(absBYDAutoChargingListener);
        tyreDevice.unregisterListener(absBYDAutoTyreListener);
        settingDevice.unregisterListener(absBYDAutoSettingListener);
        instrumentDevice.unregisterListener(instrumentListener);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initAutoData();
    }

    private void initDevice() {
        if (ContextCompat.checkSelfPermission(this, BydManifest.permission.BYDAUTO_BODYWORK_GET) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "权限不足", Toast.LENGTH_SHORT).show();
            return;
        }
        bodyworkDevice = BYDAutoBodyworkDevice.getInstance(this);
        statisticDevice = BYDAutoStatisticDevice.getInstance(this);
        speedDevice = BYDAutoSpeedDevice.getInstance(this);
        energyDevice = BYDAutoEnergyDevice.getInstance(this);
        engineDevice = BYDAutoEngineDevice.getInstance(this);
        bydAutoAcDevice = BYDAutoAcDevice.getInstance(this);
        gearboxDevice = BYDAutoGearboxDevice.getInstance(this);
        chargingDevice = BYDAutoChargingDevice.getInstance(this);
        tyreDevice = BYDAutoTyreDevice.getInstance(this);
        settingDevice = BYDAutoSettingDevice.getInstance(this);
        instrumentDevice = BYDAutoInstrumentDevice.getInstance(this);

        statisticDevice.registerListener(absBYDAutoStatisticListener);
        bodyworkDevice.registerListener(absBYDAutoBodyworkListener);
        speedDevice.registerListener(absBYDAutoSpeedListener);
        energyDevice.registerListener(absBYDAutoEnergyListener);
        bydAutoAcDevice.registerListener(absBYDAutoAcListener);
        gearboxDevice.registerListener(absBYDAutoGearboxListener);
        chargingDevice.registerListener(absBYDAutoChargingListener);
        tyreDevice.registerListener(absBYDAutoTyreListener);
        settingDevice.registerListener(absBYDAutoSettingListener);
        instrumentDevice.registerListener(instrumentListener);
    }

    private final AbsBYDAutoSettingListener absBYDAutoSettingListener = new AbsBYDAutoSettingListener() {
        /**
         * 能量回馈强度
         * @param level
         */
        @Override
        public void onEnergyFeedbackStrengthChanged(int level) {
            super.onEnergyFeedbackStrengthChanged(level);
            if (level == BYDAutoSettingDevice.SET_DR_ENERGY_FB_STANDARD) {
                dataHolder.setEnergyFeedback("标准");
            } else if (level == BYDAutoSettingDevice.SET_DR_ENERGY_FB_LARGE) {
                dataHolder.setEnergyFeedback("较大");
            }
            refreshUI();
        }
    };

    private final AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener = new AbsBYDAutoBodyworkListener() {
        /**
         * 电源档位
         * @param level
         */
        @Override
        public void onPowerLevelChanged(int level) {
            super.onPowerLevelChanged(level);
            dataHolder.setPowerLevel(StringUtil.getPowerLevelName(level));
            refreshUI();
        }

    };

    private final AbsBYDAutoTyreListener absBYDAutoTyreListener = new AbsBYDAutoTyreListener() {
        /**
         *
         * @param area
         * @param state
         */
        @Override
        public void onTyrePressureStateChanged(int area, int state) {
            super.onTyrePressureStateChanged(area, state);
            if (supportTyreValue) {
                return;
            }
            String tyreStateName = StringUtil.getTyreStateName(state);
            if (area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT) {
                dataHolder.setTyrePreLeftFront(tyreStateName);
            }
            if (area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT) {
                dataHolder.setTyrePreRightFront(tyreStateName);
            }
            if (area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR) {
                dataHolder.setTyrePreLeftRear(tyreStateName);
            }
            if (area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR) {
                dataHolder.setTyrePreRightRear(tyreStateName);
            }
            refreshUI();
        }

        /**
         * 胎压值变化（只有胎压报警的车型无此数据）
         * @param area
         * @param value
         */
        @Override
        public void onTyrePressureValueChanged(int area, int value) {
            super.onTyrePressureValueChanged(area, value);
            if (!supportTyreValue) {
                return;
            }
            if (area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT) {
                dataHolder.setTyrePreLeftFront(value + "kPa");
            }
            if (area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT) {
                dataHolder.setTyrePreRightFront(value + "kPa");
            }
            if (area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR) {
                dataHolder.setTyrePreLeftRear(value + "kPa");
            }
            if (area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR) {
                dataHolder.setTyrePreRightRear(value + "kPa");
            }
            refreshUI();
        }
    };

    private final AbsBYDAutoGearboxListener absBYDAutoGearboxListener = new AbsBYDAutoGearboxListener() {
        /**
         * 监听自动变速箱档位变化
         * @param level
         */
        @Override
        public void onGearboxAutoModeTypeChanged(int level) {
            super.onGearboxAutoModeTypeChanged(level);
            dataHolder.setCurrentGearboxLevel(StringUtil.getGearboxLevelName(level));
            refreshUI();
        }
    };

    private final AbsBYDAutoChargingListener absBYDAutoChargingListener = new AbsBYDAutoChargingListener() {
        /**
         * 充电功率变化监听
         *
         * @param value
         */
//        @Override
//        public void onChargingPowerChanged(double value) {
//            super.onChargingPowerChanged(value);
//            dataHolder.setChargePower(format.format(value));
//            refreshUI();
//        }

        /**
         * 获取充满电剩余时间
         *
         * @param hour
         * @param min
         */
        @Override
        public void onChargingRestTimeChanged(int hour, int min) {
            super.onChargingRestTimeChanged(hour, min);
            dataHolder.setChargeRestHour(hour + "");
            dataHolder.setChargeRestMinute(min + "");
            refreshUI();
        }

        @Override
        public void onChargingGunStateChanged(int state) {
            super.onChargingGunStateChanged(state);
            dataHolder.setChargeGunConnectState(state + "");
            refreshUI();
        }

        @Override
        public void onChargerStateChanged(int state) {
            super.onChargerStateChanged(state);
            dataHolder.setChargerState(state + "");
            refreshUI();
        }
    };

    private final AbsBYDAutoAcListener absBYDAutoAcListener = new AbsBYDAutoAcListener() {
        /**
         * 监听风量档位的变化
         * @param level
         */
        @Override
        public void onAcWindLevelChanged(int level) {
            super.onAcWindLevelChanged(level);
            dataHolder.setCurrentWindLevel(level + "");
            refreshUI();
        }

        /**
         * 监听各区域温度的变化
         * @param area
         * @param value
         */
        @Override
        public void onTemperatureChanged(int area, int value) {
            super.onTemperatureChanged(area, value);
            if (area == BYDAutoAcDevice.AC_TEMPERATURE_MAIN) {
                dataHolder.setCurrentTemperature(value + "");
                refreshUI();
            }
        }

        /**
         * 手动/自动
         * @param mode
         */
        @Override
        public void onAcCtrlModeChanged(int mode) {
            super.onAcCtrlModeChanged(mode);
            if (controlModeStatusTv != null) {
                if (mode == BYDAutoAcDevice.AC_CTRLMODE_AUTO) {
                    controlModeStatusTv.setSelected(true);
                } else if (mode == BYDAutoAcDevice.AC_CTRLMODE_MANUAL) {
                    controlModeStatusTv.setSelected(false);
                }
            }
        }

        @Override
        public void onAcStarted() {
            super.onAcStarted();
            if (acOnOffStatusTv != null) {
                acOnOffStatusTv.setSelected(true);
            }
        }

        @Override
        public void onAcStoped() {
            super.onAcStoped();
            if (acOnOffStatusTv != null) {
                acOnOffStatusTv.setSelected(false);
            }
        }

        /**
         * 内/外循环
         * @param mode
         */
        @Override
        public void onAcCycleModeChanged(int mode) {
            super.onAcCycleModeChanged(mode);
            if (cycleModeStatusTv != null) {
                if (mode == BYDAutoAcDevice.AC_CYCLEMODE_OUTLOOP) {
                    cycleModeStatusTv.setSelected(false);
                } else if (mode == BYDAutoAcDevice.AC_CYCLEMODE_INLOOP) {
                    cycleModeStatusTv.setSelected(true);
                }
            }
        }

        /**
         * 压缩机状态改变
         * @param mode
         */
        @Override
        public void onAcCompressorModeChanged(int mode) {
            super.onAcCompressorModeChanged(mode);
            if (compressorStatusTv != null) {
                if (mode == BYDAutoAcDevice.AC_COMPRESSOR_OFF) {
                    compressorStatusTv.setSelected(false);
                } else if (mode == BYDAutoAcDevice.AC_COMPRESSOR_ON) {
                    compressorStatusTv.setSelected(true);
                }
            }
        }

        /**
         * 除霜
         * @param area
         * @param state
         */
        @Override
        public void onAcDefrostStateChanged(int area, int state) {
            super.onAcDefrostStateChanged(area, state);
            if (area == BYDAutoAcDevice.AC_DEFROST_AREA_FRONT) {
                if (defrostModeStatusTv != null) {
                    if (state == BYDAutoAcDevice.AC_DEFROST_STATE_OFF) {
                        defrostModeStatusTv.setSelected(false);
                    } else if (state == BYDAutoAcDevice.AC_DEFROST_STATE_ON) {
                        defrostModeStatusTv.setSelected(true);
                    }
                }
            } else if (area == BYDAutoAcDevice.AC_DEFROST_AREA_REAR) {
                if (state == BYDAutoAcDevice.AC_DEFROST_STATE_OFF) {

                } else if (state == BYDAutoAcDevice.AC_DEFROST_STATE_ON) {

                }
            }
        }

        /**
         * 通风
         * @param state
         */
        @Override
        public void onAcVentilationStateChanged(int state) {
            super.onAcVentilationStateChanged(state);
            if (ventilateStatusTv != null) {
                if (state == BYDAutoAcDevice.AC_VENTILATION_STATE_OFF) {
                    ventilateStatusTv.setSelected(false);
                } else if (state == BYDAutoAcDevice.AC_VENTILATION_STATE_ON) {
                    ventilateStatusTv.setSelected(true);
                }
            }
        }
    };

    private final AbsBYDAutoEnergyListener absBYDAutoEnergyListener = new AbsBYDAutoEnergyListener() {
        /**
         * 监听能耗模式（EV/强制EV/HEV）
         * @param energyMode
         */
        @Override
        public void onEnergyModeChanged(int energyMode) {
            super.onEnergyModeChanged(energyMode);
            dataHolder.setEnergyMode(StringUtil.getEnergyModeName(energyMode));
            refreshUI();

            updateEngineSpeedData();

//            if (engineSpeedEsv != null) {
//                if (energyMode == BYDAutoEnergyDevice.ENERGY_MODE_EV || energyMode == BYDAutoEnergyDevice.ENERGY_MODE_FORCE_EV) {
//                    engineSpeedEsv.setVisibility(View.GONE);
//                } else {
//                    engineSpeedEsv.setVisibility(View.VISIBLE);
//                }
//            }
        }

        /**
         * 监听整车运行模式（经济模式，运动模式）
         * @param operationMode
         */
        @Override
        public void onOperationModeChanged(int operationMode) {
            super.onOperationModeChanged(operationMode);
            dataHolder.setOperationMode(StringUtil.getOperationModeName(operationMode));
            refreshUI();
        }

        /**
         * 原地踩油门发电功率
         *
         * @param value
         */
        @Override
        public void onPowerGenerationValueChanged(int value) {
            super.onPowerGenerationValueChanged(value);
            updateEnginePower();
//            updateEngineSpeedData();
        }
    };

    private final AbsBYDAutoStatisticListener absBYDAutoStatisticListener = new AbsBYDAutoStatisticListener() {

        /**
         * 监听总里程变化
         * @param totalMileageValue
         */
        @Override
        public void onTotalMileageValueChanged(int totalMileageValue) {
            super.onTotalMileageValueChanged(totalMileageValue);
            //总里程
            dataHolder.setTotalMileage(totalMileageValue + "");
            refreshUI();
            BYDAutoStatisticDeviceHelper statisticDeviceHelper = BYDAutoStatisticDeviceHelper.getInstance(statisticDevice);
            //里程1
//            if (customMileage1Tv != null) {
//                customMileage1Tv.setText();
//            }
            dataHolder.setCustomMileage1(format.format(statisticDeviceHelper.getMileageNumber(0)));

            //里程2
//            if (customMileage2Tv != null) {
//                customMileage2Tv.setText();
//            }
            dataHolder.setCustomMileage2(format.format(statisticDeviceHelper.getMileageNumber(1)));
            //总HEV里程
            int hevMileageValue = statisticDeviceHelper.getHEVMileageValue();
            dataHolder.setTotalHevMileage(hevMileageValue + "");
            refreshUI();
            //总EV里程
            int evMileageValue = statisticDevice.getEVMileageValue();
            //更新单次行程能耗数据
            double totalElecConValue = statisticDevice.getTotalElecConValue();
            double totalFuelConValue = statisticDevice.getTotalFuelConValue();

            //本次行程总里程
            int total_mileage = totalMileageValue - init_totalMileageValue;
            //本次行程ev里程
            int ev_mileage = evMileageValue - init_evMileageValue;
            //本次行程hev里程
            int hev_mileage = hevMileageValue - init_hevMileageValue;
//            if (currentTravelMileageTv != null) {
//                currentTravelMileageTv.setText(String.valueOf(total_mileage));
//            }
            dataHolder.setCurrentTravelMileage(total_mileage + "");
            //本次行程电耗
            double elec_cost = totalElecConValue - init_totalElecConValue;
            //同步车机负一屏计算方式，油发电不计算费用
            if (elec_cost <= 0) {
                elec_cost = 0;
            }
            //本次行程油耗
            double fuel_cost = totalFuelConValue - init_totalFuelConValue;
            //本次行程能耗(电耗+油耗)
            String cost = format.format(elec_cost) + "KWH+" + format.format(fuel_cost) + "L";
//            if (currentTravelEnergyCostTv != null) {
//                currentTravelEnergyCostTv.setText(cost);
//            }
            dataHolder.setCurrentTravelEnergyCost(cost);
            //本次行程花费
            double yuan = elec_cost * init_latest_electric_price + fuel_cost * init_latest_fuel_price;
//            if (currentTravelYuanCostTv != null) {
//                currentTravelYuanCostTv.setText(format.format(yuan));
//            }
            dataHolder.setCurrentTravelYuanCost(format.format(yuan));
            //本次行程平均电耗(kwh/100km)
//            if (currentTravelElecCostTv != null) {
            if (ev_mileage != 0) {
//                    currentTravelElecCostTv.setText(format.format(elec_cost * 100 / ev_mileage));
                dataHolder.setCurrentTravelElecCost(format.format(elec_cost * 100 / ev_mileage));
            } else {
//                    currentTravelElecCostTv.setText("0");
                dataHolder.setCurrentTravelElecCost(0 + "");
            }
//            }

            //本次行程平均油耗
//            if (currentTravelFuelCostTv != null) {
            if (hev_mileage != 0) {
//                    currentTravelFuelCostTv.setText();
                dataHolder.setCurrentTravelFuelCost(format.format(fuel_cost * 100 / hev_mileage));
            } else {
//                    currentTravelFuelCostTv.setText("0");
                dataHolder.setCurrentTravelFuelCost("0");
            }
//            }
            //本次行程花费的钱相当于多少电
            double valueKwh = yuan / init_latest_electric_price;//多少kwh
            //本次行程花费的钱相当于多少油
            double valueL = yuan / init_latest_fuel_price;//多少L
            double c = 0;
            double d = 0;
            if (total_mileage != 0) {
                //本次行程综合电耗
                d = valueKwh * 100.0f / total_mileage;//kwh/100km
                //本次行程综合油耗
                c = valueL * 100.0f / total_mileage;//L/100km
            }
//            if (currentComprehensiveElecCostTv != null) {
//                currentComprehensiveElecCostTv.setText(format.format(d));
//            }
            dataHolder.setCurrentComprehensiveElecCost(format.format(d));
//            if (currentComprehensiveFuelCostTv != null) {
//                currentComprehensiveFuelCostTv.setText(format.format(c));
//            }
            dataHolder.setCurrentComprehensiveFuelCost(format.format(c));
            refreshUI();
        }

        /**
         * 监听燃油消耗总量变化
         * @param value
         */
        @Override
        public void onTotalFuelConChanged(double value) {
            super.onTotalFuelConChanged(value);
//            if (totalFuelCostTv != null) {
//                totalFuelCostTv.setText(format.format(value));
//            }
            dataHolder.setTotalFuelConPhm(format.format(value));
            refreshUI();
        }

        /**
         * 监听电消耗总量的变化
         * @param value
         */
        @Override
        public void onTotalElecConChanged(double value) {
            super.onTotalElecConChanged(value);
//            if (totalElecCostTv != null) {
//                totalElecCostTv.setText(format.format(value));
//            }
            dataHolder.setTotalElecCost(format.format(value));
            refreshUI();
        }

        /**
         * 监听最近百公里油耗变化
         * @param value {0,51.1}L/100KM
         */
        @Override
        public void onLastFuelConPHMChanged(double value) {
            super.onLastFuelConPHMChanged(value);
//            if (lastFuelConPhmTv != null) {
//                lastFuelConPhmTv.setText(format.format(value));
//            }
            dataHolder.setLastFuelConPhm(format.format(value));
            refreshUI();
        }

        /**
         * 监听累计平均油耗变化
         * @param value
         */
        @Override
        public void onTotalFuelConPHMChanged(double value) {
            super.onTotalFuelConPHMChanged(value);
            totalFuelConPHM = value;
        }


        /**
         * 监听最近百公里电耗变化
         * @param value {-99.9,99.9}KWH/100KM
         */
        @Override
        public void onLastElecConPHMChanged(double value) {
            super.onLastElecConPHMChanged(value);
//            if (lastElecConPhmTv != null) {
//                lastElecConPhmTv.setText(format.format(value));
//            }
            dataHolder.setLastElecConPhm(format.format(value));
            refreshUI();
        }

        /**
         * 监听累计平均电耗变化
         * @param value
         */
        @Override
        public void onTotalElecConPHMChanged(double value) {
            super.onTotalElecConPHMChanged(value);
            totalElecConPHM = value;
        }

        /**
         * 监听电续航里程变化
         * @param value
         */
        @Override
        public void onElecDrivingRangeChanged(int value) {
            super.onElecDrivingRangeChanged(value);
            dataHolder.setPowerMileage(value + "");

            BYDAutoStatisticDeviceHelper statisticDeviceHelper = BYDAutoStatisticDeviceHelper.getInstance(statisticDevice);
            dataHolder.setLowestBatterVoltage(format.format(statisticDeviceHelper.getSTATISTIC_LOWEST_BATTERY_VOLTAGE() * 1.0f / 1000));
            dataHolder.setHighestBatterVoltage(format.format(statisticDeviceHelper.getSTATISTIC_HIGHEST_BATTERY_VOLTAGE() * 1.0f / 1000));
            dataHolder.setLowestBatterTemp(String.valueOf(statisticDeviceHelper.getLOWEST_BATTERY_TEMP()));
            dataHolder.setHighestBatterTemp(String.valueOf(statisticDeviceHelper.getHIGHEST_BATTERY_TEMP()));
            dataHolder.setAverageBatterTemp(String.valueOf(statisticDeviceHelper.getAVERAGE_BATTERY_TEMP()));

            ChargingDeviceHelper helper = ChargingDeviceHelper.getInstance(chargingDevice);
            dataHolder.setChargePower(helper.getPower() + "");
            dataHolder.setChargeVolt(helper.getVoltage() + "");
            dataHolder.setChargeCurrent(helper.getCurrent() + "");
            dataHolder.setChargeRestMinute(helper.getMinute() + "");
            dataHolder.setChargeRestHour(helper.getHour() + "");
            dataHolder.setBatteryDeviceState(helper.getBatteryState() + "");
            dataHolder.setChargeGunConnectState(helper.getGunConnect() + "");
            dataHolder.setChargerState(helper.getChargerConnect() + "");

            refreshUI();
        }

        /**
         * 监听燃油续航里程变化
         * @param value
         */
        @Override
        public void onFuelDrivingRangeChanged(int value) {
            super.onFuelDrivingRangeChanged(value);
            if (value >= 2046) {
                dataHolder.setFuelMileage("--");
            } else {
                dataHolder.setText(value + "");
            }
            refreshUI();
        }

        /**
         * 监听燃油百分比变化
         * @param fuelPercentageValue
         */
        @Override
        public void onFuelPercentageChanged(int fuelPercentageValue) {
            super.onFuelPercentageChanged(fuelPercentageValue);
            dataHolder.setFuelPb(fuelPercentageValue + "");
            dataHolder.setFuelPercent(fuelPercentageValue);
            refreshUI();
        }

        /**
         * 监听电量百分比变化
         * @param value
         */
        @Override
        public void onElecPercentageChanged(double value) {
            super.onElecPercentageChanged(value);
            double ret;
            if (value <= 1) {
                ret = value * 100;
            } else {
                ret = value;
            }
            dataHolder.setElecPb((int) ret + "");
            dataHolder.setElecPercent(((int) ret));
            refreshUI();
        }

        @Override
        public void onEVMileageValueChanged(int value) {
            super.onEVMileageValueChanged(value);
            dataHolder.setTotalEvMileage(value + "");
            refreshUI();
        }

        @Keep
        @Override
        public void onHEVMileageValueChanged(int value) {
            KLog.e("onHEVMileageValueChanged = " + value);
            dataHolder.setTotalHevMileage(value + "");
            refreshUI();
        }

        /**
         * 瞬时电耗
         *
         * @param value
         */
        @Keep
        @Override
        public void onInstantElecConChanged(double value) {
            updateInstantElecCon();
        }

        /**
         * 瞬时油耗
         *
         * @param value
         */
        @Keep
        @Override
        public void onInstantFuelConChanged(double value) {
            updateInstantFuelCon();
        }

    };

    private void updateFrontMotorSpeed() {
        int front_motor_speed = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_FRONT_MOTOR_SPEED);
        if (front_motor_speed == 1) {
            front_motor_speed = 0;
        }
        dataHolder.setFrontMotorSpeed(Math.abs(front_motor_speed) + "");
        refreshUI();
        if (frontMotorSpeedMsv != null) {
            frontMotorSpeedMsv.setVelocity(Math.abs(front_motor_speed));
        }
        int rear_motor_speed = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_REAR_MOTOR_SPEED);
        if (rearMotorSpeedMsv != null) {
            rearMotorSpeedMsv.setVelocity(Math.abs(rear_motor_speed));
        }
    }

    /**
     * onEnergyModeChanged()
     * onPowerGenerationValueChanged()
     * onSpeedChanged()
     * initAutoData()
     */
    private void updateEngineSpeedData() {
        if (engineDevice == null) {
            return;
        }
        int engine_speed = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_SPEED);
        int engine_speed_gb = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_SPEED_GB);
        int engine_speed_warning = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_SPEED_WARNING);
        int engine_speed_result;
        if (engine_speed_gb > 0 && engine_speed_gb <= 8000) {
            engine_speed_result = engine_speed_gb;
        } else if (engine_speed > 0 && engine_speed <= 8000) {
            engine_speed_result = engine_speed;
        } else {
            engine_speed_result = 0;
        }
        dataHolder.setEngineSpeed(engine_speed_result + "");
        if (engineSpeedEsv != null) {
            engineSpeedEsv.setVelocity(engine_speed_result);
        }

        int front_motor_speed = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_FRONT_MOTOR_SPEED);
        int front_motor_torque = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_FRONT_MOTOR_TORQUE);

        int rear_motor_speed = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_REAR_MOTOR_SPEED);
        int rear_motor_torque = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_REAR_MOTOR_TORQUE);

        if (engine_speed_gb == 1) {
            engine_speed_gb = 0;
        }
        if (engine_speed_gb >= 8000) {
            engine_speed_gb = 0;
        }
        dataHolder.setEngineSpeedGb(engine_speed_gb + "");
        if (engine_speed_warning == 1) {
            engine_speed_warning = 0;
        }
        if (engine_speed_warning >= 8000) {
            engine_speed_warning = 0;
        }
        dataHolder.setEngineSpeedWarning(engine_speed_warning + "");
        if (front_motor_speed == 1) {
            front_motor_speed = 0;
        }
        if (front_motor_speed >= 8000) {
            front_motor_speed = 0;
        }
        dataHolder.setFrontMotorSpeed(Math.abs(front_motor_speed) + "");
        dataHolder.setFrontMotorTorque(getValidTorqueValue(front_motor_torque) + "");
        if (rear_motor_speed == 1) {
            rear_motor_speed = 0;
        }
        if (rear_motor_speed >= 8000) {
            rear_motor_speed = 0;
        }
        dataHolder.setRearMotorSpeed(Math.abs(rear_motor_speed) + "");
        dataHolder.setRearMotorTorque(getValidTorqueValue(rear_motor_torque) + "");
        refreshUI();
    }

    private int getValidTorqueValue(int motor_torque) {
        if (motor_torque <= 0) {
            return 0;
        }
        if (motor_torque >= 65535) {
            return 0;
        }
        return motor_torque;
    }

    private final AbsBYDAutoSpeedListener absBYDAutoSpeedListener = new AbsBYDAutoSpeedListener() {
        double currentSpeed;

        /**
         * 监听车速变化[0-282]km/h
         * @param currentSpeed
         */
        @Override
        public void onSpeedChanged(double currentSpeed) {
            super.onSpeedChanged(currentSpeed);
            this.currentSpeed = currentSpeed;
            dataHolder.setCarSpeed(format.format(currentSpeed));
            refreshUI();
            if (carSpeedCsv != null) {
                carSpeedCsv.setVelocity(((int) currentSpeed));
            }
            updateEngineSpeedData();
            updateEnginePower();
            updateFrontMotorSpeed();
        }

        /**
         * 监听油门深度变化[0-100]%
         * @param value
         */
        @Override
        public void onAccelerateDeepnessChanged(int value) {
            super.onAccelerateDeepnessChanged(value);

            updateEngineSpeedData();

            if (youMengPb != null) {
                youMengPb.setProgress(value);
            }
            dataHolder.setYouMeng(value + "");
            refreshUI();
        }

        /**
         * 监听制动深度变化[0-100]%
         * @param value
         */
        @Override
        public void onBrakeDeepnessChanged(int value) {
            super.onBrakeDeepnessChanged(value);
            if (shaChePb != null) {
                shaChePb.setProgress(value);
            }
            dataHolder.setShaChe(value + "");
            refreshUI();
        }

    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about_us) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (item.getItemId() == R.id.version_update) {
            Uri uri = Uri.parse("https://pan.baidu.com/s/1HH9eXbn2hWWwIhCYNwGyJg?pwd=gaqe");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else if (item.getItemId() == R.id.float_show_new) {
            startService(new Intent(this, FloatingService.class));
        } else if (item.getItemId() == R.id.settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
//        else if (item.getItemId() == R.id.fuel_consumption_statistics) {
//            startActivity(new Intent(this, FuelConsumptionStatisticsActivity.class));
//        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 瞬时电耗
     */
    private void updateInstantElecCon() {
        double instantElecConValue = BYDAutoStatisticDeviceHelper.getInstance(statisticDevice).getInstantElecConValue();
        if (instantElecConValue == -1 || instantElecConValue > 100) {
            instantElecConValue = 0;
        }
        dataHolder.setInstantElecCon(format.format(instantElecConValue));
        refreshUI();
    }

    /**
     * 瞬时油耗
     */
    private void updateInstantFuelCon() {
        dataHolder.setInstantFuelCon(format.format(BYDAutoStatisticDeviceHelper.getInstance(statisticDevice).getInstantFuelConValue()));
        refreshUI();
    }

    /**
     * 初始化界面数据
     */
    private void initAutoData() {
        //车型代号
        dataHolder.setAutoType(BydApi29Helper.getAutoType(bodyworkDevice));
        refreshUI();
        //电源档位
        absBYDAutoBodyworkListener.onPowerLevelChanged(bodyworkDevice.getPowerLevel());

        //变速箱名称
        dataHolder.setGearboxCode(gearboxDevice.getGearboxCode());
        refreshUI();
        //变速箱类型
        dataHolder.setGearboxType(StringUtil.getGearboxTypeName(gearboxDevice.getGearboxType()));
        refreshUI();

        //总里程
        absBYDAutoStatisticListener.onTotalMileageValueChanged(statisticDevice.getTotalMileageValue());
        //总EV里程
        absBYDAutoStatisticListener.onEVMileageValueChanged(statisticDevice.getEVMileageValue());
        //车速
        absBYDAutoSpeedListener.onSpeedChanged(speedDevice.getCurrentSpeed());
        //能耗模式
        absBYDAutoEnergyListener.onEnergyModeChanged(energyDevice.getEnergyMode());
        //驾驶模式
        absBYDAutoEnergyListener.onOperationModeChanged(energyDevice.getOperationMode());
        //功率
        updateEnginePower();
        //发动机转速
        updateEngineSpeedData();
        //累计燃油消耗
        absBYDAutoStatisticListener.onTotalFuelConChanged(statisticDevice.getTotalFuelConValue());
        //累计电量消耗
        absBYDAutoStatisticListener.onTotalElecConChanged(statisticDevice.getTotalElecConValue());
        //当前档位
        absBYDAutoGearboxListener.onGearboxAutoModeTypeChanged(gearboxDevice.getGearboxAutoModeType());
        //最近50km电耗
        absBYDAutoStatisticListener.onLastElecConPHMChanged(statisticDevice.getLastElecConPHMValue());
        //最近50km油耗
        absBYDAutoStatisticListener.onLastFuelConPHMChanged(statisticDevice.getLastFuelConPHMValue());
        //累计平均电耗
        absBYDAutoStatisticListener.onTotalElecConPHMChanged(statisticDevice.getTotalElecConPHMValue());
        //累计平均油耗
        absBYDAutoStatisticListener.onTotalFuelConPHMChanged(statisticDevice.getTotalFuelConPHMValue());
        //剩余电量百分比
        absBYDAutoStatisticListener.onElecPercentageChanged(statisticDevice.getElecPercentageValue());
        //剩余电续航里程
        absBYDAutoStatisticListener.onElecDrivingRangeChanged(statisticDevice.getElecDrivingRangeValue());
        //剩余燃油百分比
        absBYDAutoStatisticListener.onFuelPercentageChanged(statisticDevice.getFuelPercentageValue());
        //剩余燃油续航里程
        absBYDAutoStatisticListener.onFuelDrivingRangeChanged(statisticDevice.getFuelDrivingRangeValue());
        //瞬时电耗
        updateInstantElecCon();
        //瞬时油耗
        updateInstantFuelCon();
        //获取各区域空调温度
        int temprature = bydAutoAcDevice.getTemprature(BYDAutoAcDevice.AC_TEMPERATURE_MAIN);
        int temprature1 = bydAutoAcDevice.getTemprature(BYDAutoAcDevice.AC_TEMPERATURE_REAR);
        int temprature2 = bydAutoAcDevice.getTemprature(BYDAutoAcDevice.AC_TEMPERATURE_OUT);
        absBYDAutoAcListener.onTemperatureChanged(BYDAutoAcDevice.AC_TEMPERATURE_MAIN, temprature);
        absBYDAutoAcListener.onTemperatureChanged(BYDAutoAcDevice.AC_TEMPERATURE_REAR, temprature1);
        absBYDAutoAcListener.onTemperatureChanged(BYDAutoAcDevice.AC_TEMPERATURE_OUT, temprature2);
        //当前风量
        absBYDAutoAcListener.onAcWindLevelChanged(bydAutoAcDevice.getAcWindLevel());
        //充电功率与剩余时长
        absBYDAutoChargingListener.onChargingPowerChanged(chargingDevice.getChargingPower());
        int[] time = chargingDevice.getChargingRestTime();
        absBYDAutoChargingListener.onChargingRestTimeChanged(time[0], time[1]);
        //胎压
        supportTyreValue = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT) != 0;
        if (supportTyreValue) {
            absBYDAutoTyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT, tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT));
            absBYDAutoTyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT, tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT));
            absBYDAutoTyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR, tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR));
            absBYDAutoTyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR, tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR));
        } else {
            absBYDAutoTyreListener.onTyrePressureStateChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT, tyreDevice.getTyrePressureState(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT));
            absBYDAutoTyreListener.onTyrePressureStateChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT, tyreDevice.getTyrePressureState(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT));
            absBYDAutoTyreListener.onTyrePressureStateChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR, tyreDevice.getTyrePressureState(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR));
            absBYDAutoTyreListener.onTyrePressureStateChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR, tyreDevice.getTyrePressureState(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR));
        }

        //能量回馈强度
        absBYDAutoSettingListener.onEnergyFeedbackStrengthChanged(settingDevice.getEnergyFeedback());

        //外接充电总量
        instrumentListener.onExternalChargingPowerChanged(instrumentDevice.getExternalChargingPower());

        //空调的手动、与自动控制方式
        absBYDAutoAcListener.onAcCtrlModeChanged(bydAutoAcDevice.getAcControlMode());
        //获取空调开启状态
        int acStartState = bydAutoAcDevice.getAcStartState();
        if (acStartState == BYDAutoAcDevice.AC_POWER_ON) {
            absBYDAutoAcListener.onAcStarted();
        } else if (acStartState == BYDAutoAcDevice.AC_POWER_OFF) {
            absBYDAutoAcListener.onAcStoped();
        }
        //获取空调内外循环
        absBYDAutoAcListener.onAcCycleModeChanged(bydAutoAcDevice.getAcCycleMode());
    }

    private void updateEnginePower() {
        int enginePower = engineDevice.getEnginePower();
        dataHolder.setEnginePower(enginePower + "");
        if (enginePowerEpv != null) {
            enginePowerEpv.setVelocity(enginePower);
        }
        double totalElecConPHMValue = statisticDevice.getTotalElecConPHMValue();
        double totalFuelConPHMValue = statisticDevice.getTotalFuelConPHMValue();

        String elec_listener_and_cacu = format.format(totalElecConPHM == 0 ? totalElecConPHMValue : totalElecConPHM);
        String fuel_listener_and_cacu = format.format(totalFuelConPHM == 0 ? totalFuelConPHMValue : totalFuelConPHM);

        dataHolder.setTotalElecConPhm(elec_listener_and_cacu);
        dataHolder.setTotalFuelConPhm(fuel_listener_and_cacu);
        refreshUI();
    }

    public static final String KEY_PAUSE_CURRENT_MILEAGE_DATA = "key_pause_current_mileage_data";

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        if (vId == R.id.ac_compressor_btn) {
            int acCompressorMode = bydAutoAcDevice.getAcCompressorMode();
            if (acCompressorMode == BYDAutoAcDevice.AC_COMPRESSOR_OFF) {
                BYDAutoAcDeviceHelper.getInstance(bydAutoAcDevice).setAcCompressorMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_COMPRESSOR_ON);
            } else if (acCompressorMode == BYDAutoAcDevice.AC_COMPRESSOR_ON) {
                BYDAutoAcDeviceHelper.getInstance(bydAutoAcDevice).setAcCompressorMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_COMPRESSOR_OFF);
            }
            return;
        }
        if (vId == R.id.ac_on_off_btn) {
            int acStartState = bydAutoAcDevice.getAcStartState();
            if (acStartState == BYDAutoAcDevice.AC_POWER_ON) {
                bydAutoAcDevice.stop(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY);
            } else if (acStartState == BYDAutoAcDevice.AC_POWER_OFF) {
                bydAutoAcDevice.start(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY);
            }
            return;
        }
        if (vId == R.id.ac_cycle_mode_btn) {
            int acCycleMode = bydAutoAcDevice.getAcCycleMode();
            if (acCycleMode == BYDAutoAcDevice.AC_CYCLEMODE_OUTLOOP) {
                bydAutoAcDevice.setAcCycleMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_CYCLEMODE_INLOOP);
            } else if (acCycleMode == BYDAutoAcDevice.AC_CYCLEMODE_INLOOP) {
                bydAutoAcDevice.setAcCycleMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_CYCLEMODE_OUTLOOP);
            }
            return;
        }
        if (vId == R.id.reset_current_mileage_btn) {
            //保存当前里程、电量、油量信息
            BootCompleteService.saveCurrentTripData(this);
            //加载最新里程、电量、油量信息
            loadCurrentTripData();
            //更新界面本次行程信息
            absBYDAutoStatisticListener.onTotalMileageValueChanged(statisticDevice.getTotalMileageValue());
            return;
        }
        if (vId == R.id.pause_current_mileage_btn) {
            if (mPreferences != null) {
                mPreferences.edit().putBoolean(KEY_PAUSE_CURRENT_MILEAGE_DATA, true).apply();
                Toast.makeText(this, "下次启动车辆本次行程数据将保留", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (vId == R.id.energy_feedback_btn) {
            int energyFeedback = settingDevice.getEnergyFeedback();
            if (energyFeedback == BYDAutoSettingDevice.SET_DR_ENERGY_FB_STANDARD) {
                settingDevice.setEnergyFeedback(BYDAutoSettingDevice.SET_DR_ENERGY_FB_LARGE);
                dataHolder.setEnergyFeedback("较大");
            } else if (energyFeedback == BYDAutoSettingDevice.SET_DR_ENERGY_FB_LARGE) {
                settingDevice.setEnergyFeedback(BYDAutoSettingDevice.SET_DR_ENERGY_FB_STANDARD);
                dataHolder.setEnergyFeedback("标准");
            }
            refreshUI();
            return;
        }
        if (vId == R.id.ac_control_mode_btn) {
            int acControlMode = bydAutoAcDevice.getAcControlMode();
            if (acControlMode == BYDAutoAcDevice.AC_CTRLMODE_AUTO) {
                bydAutoAcDevice.setAcControlMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_CTRLMODE_MANUAL);
            } else if (acControlMode == BYDAutoAcDevice.AC_CTRLMODE_MANUAL) {
                bydAutoAcDevice.setAcControlMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_CTRLMODE_AUTO);
            }
            return;
        }
        if (vId == R.id.temperature_plus_btn) {
            int temprature = bydAutoAcDevice.getTemprature(BYDAutoAcDevice.AC_TEMPERATURE_MAIN);
            if (temprature == BYDAutoAcDevice.AC_TEMP_IN_CELSIUS_MAX) {
                return;
            }
            bydAutoAcDevice.setAcTemperature(BYDAutoAcDevice.AC_TEMPERATURE_MAIN, temprature + 1, BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_TEMPERATURE_UNIT_OC);
        } else if (vId == R.id.temperature_sub_btn) {
            int temprature = bydAutoAcDevice.getTemprature(BYDAutoAcDevice.AC_TEMPERATURE_MAIN);
            if (temprature == BYDAutoAcDevice.AC_TEMP_IN_CELSIUS_MIN) {
                return;
            }
            bydAutoAcDevice.setAcTemperature(BYDAutoAcDevice.AC_TEMPERATURE_MAIN, temprature - 1, BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_TEMPERATURE_UNIT_OC);
        } else if (vId == R.id.wind_level_plus_btn) {
            int acWindLevel = bydAutoAcDevice.getAcWindLevel();
            if (acWindLevel == BYDAutoAcDevice.AC_WINDLEVEL_7) {
                return;
            }
            bydAutoAcDevice.setAcWindLevel(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, acWindLevel + 1);
        } else if (vId == R.id.wind_level_sub_btn) {
            int acWindLevel = bydAutoAcDevice.getAcWindLevel();
            if (acWindLevel - 1 == BYDAutoAcDevice.AC_WINDLEVEL_0) {
                bydAutoAcDevice.stopRearAc(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY);
                int ret = bydAutoAcDevice.stop(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY);
                return;
            }
            bydAutoAcDevice.setAcWindLevel(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, acWindLevel - 1);
        } else if (vId == R.id.defrost_mode_btn) {
            int state = bydAutoAcDevice.getAcDefrostState(BYDAutoAcDevice.AC_DEFROST_AREA_FRONT);
            if (state == BYDAutoAcDevice.AC_DEFROST_STATE_OFF) {
                bydAutoAcDevice.setAcDefrostState(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_DEFROST_AREA_FRONT, BYDAutoAcDevice.AC_DEFROST_STATE_ON);
            } else if (state == BYDAutoAcDevice.AC_DEFROST_STATE_ON) {
                bydAutoAcDevice.setAcDefrostState(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_DEFROST_AREA_FRONT, BYDAutoAcDevice.AC_DEFROST_STATE_OFF);
            }
        } else if (vId == R.id.ventilate_mode_btn) {
            int state = bydAutoAcDevice.getAcVentilationState();
            if (state == BYDAutoAcDevice.AC_VENTILATION_STATE_OFF) {
                bydAutoAcDevice.setAcVentilationState(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_VENTILATION_STATE_ON);
            } else if (state == BYDAutoAcDevice.AC_VENTILATION_STATE_ON) {
                bydAutoAcDevice.setAcVentilationState(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_VENTILATION_STATE_OFF);
            }
        }
    }

    private final AbsBYDAutoInstrumentListener instrumentListener = new AbsBYDAutoInstrumentListener() {

        @Override
        public void onExternalChargingPowerChanged(double value) {
            super.onExternalChargingPowerChanged(value);
            dataHolder.setExternalChargingPower(MainActivity.format.format(value));
            refreshUI();
        }
    };
}