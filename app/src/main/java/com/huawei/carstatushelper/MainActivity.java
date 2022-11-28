package com.huawei.carstatushelper;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.carstatushelper.activity.AboutActivity;
import com.huawei.carstatushelper.activity.RadarFloatingSettingActivity;
import com.huawei.carstatushelper.activity.SettingsActivity;
import com.huawei.carstatushelper.databinding.ActivityMainBinding;
import com.huawei.carstatushelper.databinding.ActivityMainLandBinding;
import com.huawei.carstatushelper.databinding.ActivityMainLandMultiBinding;
import com.huawei.carstatushelper.receiver.BootCompleteService;
import com.huawei.carstatushelper.util.BydApi29Helper;
import com.huawei.carstatushelper.util.PermissionUtils;
import com.huawei.carstatushelper.util.StringUtil;
import com.huawei.carstatushelper.view.CarSpeedView;
import com.huawei.carstatushelper.view.EnginePowerView;
import com.huawei.carstatushelper.view.EngineSpeedView;
import com.huawei.carstatushelper.view.MotorSpeedView;
import com.socks.library.KLog;
import com.ziwenl.floatingwindowdemo.FloatingWindowService;

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

    private double totalElecConPHM;//累计平均电耗
    private double totalFuelConPHM;//累计平均油耗

    private EngineSpeedView engineSpeedEsv;
    private EnginePowerView enginePowerEpv;
    private CarSpeedView carSpeedCsv;

    private View temperaturePlusBtn;
    private View temperatureSubBtn;
    private View windLevelPlusBtn;
    private View windLevelSubBtn;

    private TextView currentGearboxLevelTv;
    private TextView currentWindLevelTv;
    private TextView currentTemperatureTv;

    private TextView energyModeTv;
    private TextView operationModeTv;

    private TextView totalMileageTv;
    private TextView totalHevMileageTv;
    private TextView totalFuelCostTv;
    private TextView totalElecCostTv;
    private TextView drivingTimeTv;
    private TextView lastFuelConPhmTv;
    private TextView lastElecConPhmTv;

    private TextView powerMileageTv;
    private TextView fuelMileageTv;
    private ProgressBar fuelPercentPb;
    private TextView fuelPbTv;
    private ProgressBar elecPercentPb;
    private TextView elecPbTv;
    private TextView enginePowerTv;
    private TextView totalElecConPhmTv;
    private TextView totalFuelConPhmTv;
    private TextView textTv;

    private TextView totalEvMileageTv;
    private TextView carSpeedTv;
    private TextView engineSpeedTv;

    private ProgressBar youMengPb;
    private TextView youMengTv;
    private ProgressBar shaChePb;
    private TextView shaCheTv;
    private BYDAutoChargingDevice chargingDevice;
    private TextView chargingPowerTv;
    private TextView chargingRestTimeTv;
    private TextView autoModelNameTv;
    private TextView engineCodeTv;
    private TextView gearboxCodeTv;
    private Button defrostModeBtn;
    private Button ventilateModeBtn;
    private TextView waterTemperatureTv;
    private TextView instantElecConTv;
    private TextView instantFuelConTv;
    private LinearLayout shaCheGroupLayout;
    private TextView gearboxTypeTv;
    private TextView engineSpeedGbTv;
    private TextView engineSpeedWarningTv;
    private TextView frontMotorSpeedTv;
    private TextView rearMotorSpeedTv;
    private TextView frontMotorTorqueTv;
    private TextView rearMotorTorqueTv;
    private BYDAutoTyreDevice tyreDevice;
    private TextView tyrePreLeftFrontTv;
    private TextView tyrePreRightFrontTv;
    private TextView tyrePreLeftRearTv;
    private TextView tyrePreRightRearTv;
    private TextView autoTypeTv;
    private TextView currentTravelElecCostTv;
    private TextView currentTravelFuelCostTv;
    private TextView currentTravelMileageTv;
    private TextView currentTravelEnergyCostTv;
    private BYDAutoBodyworkDevice bodyworkDevice;
    private CheckBox showVinCb;
    private int init_totalMileageValue;
    private int init_evMileageValue;
    private int init_hevMileageValue;
    private double init_totalElecConValue;
    private double init_totalFuelConValue;
    private double init_latest_electric_price;
    private double init_latest_fuel_price;
    private TextView currentTravelYuanCostTv;
    private TextView customMileage1Tv;
    private TextView customMileage2Tv;
    private TextView smallBatteryVoltageTv;
    private TextView powerLevelTv;
    private MotorSpeedView motorSpeedMsv;
    private BYDAutoSettingDevice settingDevice;
    private Button energyFeedbackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.app_name) + BuildConfig.VERSION_NAME);

        loadContentView();

        initBtnListener();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String json = preferences.getString(BootCompleteService.KEY_INIT_DRIVER_DATA, null);
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONObject initDriverData = new JSONObject(json);
                init_totalMileageValue = initDriverData.getInt("totalMileageValue");
                init_evMileageValue = initDriverData.getInt("evMileageValue");
                init_hevMileageValue = initDriverData.getInt("hevMileageValue");
                init_totalElecConValue = initDriverData.getDouble("totalElecConValue");
                init_totalFuelConValue = initDriverData.getDouble("totalFuelConValue");
                init_latest_electric_price = initDriverData.getDouble("latest_electric_price");
                init_latest_fuel_price = initDriverData.getDouble("latest_fuel_price");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        boolean need = PermissionUtils.needRequestPermission(this, SplashActivity.BYD_PERMISSIONS);
        if (need) {
            Toast.makeText(this, "车辆权限不足，无法获取车辆数据", Toast.LENGTH_SHORT).show();
            return;
        }
        initDevice();
        initAutoData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (shaCheGroupLayout != null) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean show_sha_che_group_layout = preferences.getBoolean("show_sha_che_group_layout", false);
            shaCheGroupLayout.setVisibility(show_sha_che_group_layout ? View.VISIBLE : View.GONE);
        }
    }

    private void initBtnListener() {
        if (temperaturePlusBtn != null) {
            temperaturePlusBtn.setOnClickListener(this);
        }
        if (temperatureSubBtn != null) {
            temperatureSubBtn.setOnClickListener(this);
        }
        if (windLevelPlusBtn != null) {
            windLevelPlusBtn.setOnClickListener(this);
        }
        if (windLevelSubBtn != null) {
            windLevelSubBtn.setOnClickListener(this);
        }
        if (defrostModeBtn != null) {
            defrostModeBtn.setOnClickListener(this);
        }
        if (ventilateModeBtn != null) {
            ventilateModeBtn.setOnClickListener(this);
        }
        if (energyFeedbackBtn != null) {
            energyFeedbackBtn.setOnClickListener(this);
        }
    }

    private void loadContentView() {
        int orientation = getResources().getConfiguration().orientation;
        View rootView;
        if (isInMultiWindowMode()) {
            ActivityMainLandMultiBinding binding = ActivityMainLandMultiBinding.inflate(getLayoutInflater());
            rootView = binding.getRoot();
            initMainLandMultiView(binding);
        } else {
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                ActivityMainLandBinding binding = ActivityMainLandBinding.inflate(getLayoutInflater());
                rootView = binding.getRoot();
                initMainLandView(binding);
            } else {
                ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
                rootView = binding.getRoot();
                initMainView(binding);
            }
        }
        setContentView(rootView);
    }

    private void initMainView(ActivityMainBinding binding) {
        engineSpeedEsv = binding.engineSpeedEsv;//发动机转速
        motorSpeedMsv = binding.motorSpeedMsv;//前电机转速
        enginePowerEpv = binding.enginePowerEpv;
        carSpeedCsv = binding.carSpeedCsv;

        temperaturePlusBtn = binding.temperaturePlusBtn;
        temperatureSubBtn = binding.temperatureSubBtn;
        windLevelPlusBtn = binding.windLevelPlusBtn;
        windLevelSubBtn = binding.windLevelSubBtn;

        currentGearboxLevelTv = binding.currentGearboxLevelTv;
        currentTemperatureTv = binding.currentTemperatureTv;
        currentWindLevelTv = binding.currentWindLevelTv;

        energyModeTv = binding.energyModeTv;
        operationModeTv = binding.operationModeTv;

        totalMileageTv = binding.totalMileageTv;
        customMileage1Tv = binding.customMileage1Tv;
        customMileage2Tv = binding.customMileage2Tv;
        totalHevMileageTv = binding.totalHevMileageTv;

        totalFuelCostTv = binding.totalFuelCostTv;
        totalElecCostTv = binding.totalElecCostTv;
        drivingTimeTv = binding.drivingTimeTv;
        lastFuelConPhmTv = binding.lastFuelConPhmTv;
        lastElecConPhmTv = binding.lastElecConPhmTv;

        totalEvMileageTv = binding.totalEvMileageTv;
        carSpeedTv = binding.carSpeedTv;
        engineSpeedTv = binding.engineSpeedTv;
        engineSpeedGbTv = binding.engineSpeedGbTv;
        engineSpeedWarningTv = binding.engineSpeedWarningTv;
        frontMotorSpeedTv = binding.frontMotorSpeedTv;
        frontMotorTorqueTv = binding.frontMotorTorqueTv;
        rearMotorSpeedTv = binding.rearMotorSpeedTv;
        rearMotorTorqueTv = binding.rearMotorTorqueTv;

        textTv = binding.textTv;
        showVinCb = binding.showVinCb;
        showVinCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showVin(b);
            }
        });
        autoModelNameTv = binding.autoModelNameTv;
        engineCodeTv = binding.engineCodeTv;
        gearboxCodeTv = binding.gearboxCodeTv;
        gearboxTypeTv = binding.gearboxTypeTv;

        powerMileageTv = binding.powerMileageTv;
        fuelMileageTv = binding.fuelMileageTv;
        fuelPercentPb = binding.fuelPercentPb;
        fuelPbTv = binding.fuelPbTv;
        elecPercentPb = binding.elecPercentPb;
        elecPbTv = binding.elecPbTv;
        enginePowerTv = binding.enginePowerTv;
        totalElecConPhmTv = binding.totalElecConPhmTv;
        totalFuelConPhmTv = binding.totalFuelConPhmTv;

        chargingPowerTv = binding.chargingPowerTv;
        chargingRestTimeTv = binding.chargingRestTimeTv;

        defrostModeBtn = binding.defrostModeBtn;
        ventilateModeBtn = binding.ventilateModeBtn;

        waterTemperatureTv = binding.waterTemperatureTv;
        instantElecConTv = binding.instantElecConTv;
        instantFuelConTv = binding.instantFuelConTv;

        tyrePreLeftFrontTv = binding.tyrePreLeftFrontTv;
        tyrePreRightFrontTv = binding.tyrePreRightFrontTv;
        tyrePreLeftRearTv = binding.tyrePreLeftRearTv;
        tyrePreRightRearTv = binding.tyrePreRightRearTv;

        autoTypeTv = binding.autoTypeTv;

        currentTravelMileageTv = binding.currentTravelMileageTv;//本次行程里程
        currentTravelEnergyCostTv = binding.currentTravelEnergyCostTv;//本次行程电耗+油耗
        currentTravelYuanCostTv = binding.currentTravelYuanCostTv;//本次行程花费
        currentTravelElecCostTv = binding.currentTravelElecCostTv;//本次行程平均电耗
        currentTravelFuelCostTv = binding.currentTravelFuelCostTv;//本次行程平均油耗

        smallBatteryVoltageTv = binding.smallBatteryVoltageTv;//蓄电池电压
        powerLevelTv = binding.powerLevelTv;//电源档位
        energyFeedbackBtn = binding.energyFeedbackBtn;
    }

    private void initMainLandView(ActivityMainLandBinding binding) {
        engineSpeedEsv = binding.engineSpeedEsv;
//        carSpeedCsv = binding.carSpeedCsv;
        motorSpeedMsv = binding.motorSpeedMsv;

        temperaturePlusBtn = binding.temperaturePlusBtn;
        temperatureSubBtn = binding.temperatureSubBtn;
        windLevelPlusBtn = binding.windLevelPlusBtn;
        windLevelSubBtn = binding.windLevelSubBtn;

        totalMileageTv = binding.totalMileageTv;
        totalHevMileageTv = binding.totalHevMileageTv;
        totalFuelCostTv = binding.totalFuelCostTv;
        totalElecCostTv = binding.totalElecCostTv;
        lastFuelConPhmTv = binding.lastFuelConPhmTv;
        lastElecConPhmTv = binding.lastElecConPhmTv;

        totalEvMileageTv = binding.totalEvMileageTv;

        powerMileageTv = binding.powerMileageTv;
        fuelMileageTv = binding.fuelMileageTv;
        fuelPercentPb = binding.fuelPercentPb;
        fuelPbTv = binding.fuelPbTv;
        elecPercentPb = binding.elecPercentPb;
        elecPbTv = binding.elecPbTv;
        totalElecConPhmTv = binding.totalElecConPhmTv;
        totalFuelConPhmTv = binding.totalFuelConPhmTv;

        shaCheGroupLayout = binding.shaCheGroupLayout;
        youMengPb = binding.youMengPb;
        youMengTv = binding.youMengTv;
        shaChePb = binding.shaChePb;
        shaCheTv = binding.shaCheTv;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean enable = preferences.getBoolean("show_sha_che_group_layout", false);
        shaCheGroupLayout.setVisibility(enable ? View.VISIBLE : View.GONE);
    }

    private void initMainLandMultiView(ActivityMainLandMultiBinding binding) {
        engineSpeedEsv = binding.engineSpeedEsv;
        enginePowerEpv = binding.enginePowerEpv;
        carSpeedCsv = binding.carSpeedCsv;

        youMengPb = binding.youMengPb;
        youMengTv = binding.youMengTv;
        shaChePb = binding.shaChePb;
        shaCheTv = binding.shaCheTv;

        fuelPbTv = binding.fuelPbTv;//油量百分比
        fuelMileageTv = binding.fuelMileageTv;//油续航

        elecPbTv = binding.elecPbTv;//电百分比
        powerMileageTv = binding.powerMileageTv;//电续航
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (statisticDevice == null) {
            return;
        }
        statisticDevice.registerListener(absBYDAutoStatisticListener);
        bodyworkDevice.registerListener(absBYDAutoBodyworkListener);
        speedDevice.registerListener(absBYDAutoSpeedListener);
        energyDevice.registerListener(absBYDAutoEnergyListener);
        bydAutoAcDevice.registerListener(absBYDAutoAcListener);
        gearboxDevice.registerListener(absBYDAutoGearboxListener);
        chargingDevice.registerListener(absBYDAutoChargingListener);
        tyreDevice.registerListener(absBYDAutoTyreListener);
        settingDevice.registerListener(absBYDAutoSettingListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
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
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initAutoData();
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        KLog.e("当前分屏模式：" + isInMultiWindowMode);
        //分屏模式
    }

    private void initDevice() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BYDAUTO_BODYWORK_GET) != PackageManager.PERMISSION_GRANTED) {
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
    }

    private final AbsBYDAutoSettingListener absBYDAutoSettingListener = new AbsBYDAutoSettingListener() {
        /**
         * 能量回馈强度
         * @param level
         */
        @Override
        public void onEnergyFeedbackStrengthChanged(int level) {
            super.onEnergyFeedbackStrengthChanged(level);
            if (energyFeedbackBtn == null) {
                return;
            }
            if (level == BYDAutoSettingDevice.SET_DR_ENERGY_FB_STANDARD) {
                energyFeedbackBtn.setText("标准");
            } else if (level == BYDAutoSettingDevice.SET_DR_ENERGY_FB_LARGE) {
                energyFeedbackBtn.setText("较大");
            }
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
            if (powerLevelTv != null) {
                powerLevelTv.setText(StringUtil.getPowerLevelName(level));
            }
        }

        public void onBatteryPowerChanged(int value) {
            updateBatteryPower();
        }
    };

    private void updateBatteryPower() {
        int batteryPowerValue = BydApi29Helper.getBatteryPowerValue(bodyworkDevice);
        if (smallBatteryVoltageTv != null) {
            smallBatteryVoltageTv.setText(String.valueOf(batteryPowerValue));
        }
    }

    private final AbsBYDAutoTyreListener absBYDAutoTyreListener = new AbsBYDAutoTyreListener() {
        /**
         * 胎压变化
         * @param area
         * @param value
         */
        @Override
        public void onTyrePressureValueChanged(int area, int value) {
            super.onTyrePressureValueChanged(area, value);
            if (tyrePreLeftFrontTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT) {
                tyrePreLeftFrontTv.setText(String.valueOf(value));
            }
            if (tyrePreRightFrontTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT) {
                tyrePreRightFrontTv.setText(String.valueOf(value));
            }
            if (tyrePreLeftRearTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR) {
                tyrePreLeftRearTv.setText(String.valueOf(value));
            }
            if (tyrePreRightRearTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR) {
                tyrePreRightRearTv.setText(String.valueOf(value));
            }
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
            if (currentGearboxLevelTv == null) {
                return;
            }
            currentGearboxLevelTv.setText(StringUtil.getGearboxLevelName(level));
        }
    };

    private final AbsBYDAutoChargingListener absBYDAutoChargingListener = new AbsBYDAutoChargingListener() {
        /**
         * 充电功率变化监听
         *
         * @param value
         */
        @Override
        public void onChargingPowerChanged(double value) {
            super.onChargingPowerChanged(value);
            if (chargingPowerTv == null) {
                return;
            }
            chargingPowerTv.setText(format.format(value));
        }

        /**
         * 获取充满电剩余时间
         *
         * @param hour
         * @param min
         */
        @Override
        public void onChargingRestTimeChanged(int hour, int min) {
            super.onChargingRestTimeChanged(hour, min);
            if (chargingRestTimeTv == null) {
                return;
            }
            chargingRestTimeTv.setText((hour != 255 ? hour : 0) + "h" + (min > 60 ? 0 : min) + "min");
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
            if (currentWindLevelTv == null) {
                return;
            }
            currentWindLevelTv.setText(String.valueOf(level));
        }

        /**
         * 监听各区域温度的变化
         * @param area
         * @param value
         */
        @Override
        public void onTemperatureChanged(int area, int value) {
            super.onTemperatureChanged(area, value);
            if (currentTemperatureTv != null) {
                currentTemperatureTv.setText("区域:" + StringUtil.getAcTemperatureAreaName(area) + "，" + value);
            }
        }
    };

    private final AbsBYDAutoEnergyListener absBYDAutoEnergyListener = new AbsBYDAutoEnergyListener() {
        /**
         * 监听整车工作模式（EV/强制EV/HEV）
         * @param energyMode
         */
        @Override
        public void onEnergyModeChanged(int energyMode) {
            super.onEnergyModeChanged(energyMode);
            if (energyModeTv == null) {
                return;
            }
            energyModeTv.setText(StringUtil.getEnergyModeName(energyMode));
        }

        /**
         * 监听整车运行模式（经济模式，运动模式）
         * @param operationMode
         */
        @Override
        public void onOperationModeChanged(int operationMode) {
            super.onOperationModeChanged(operationMode);
            if (operationModeTv == null) {
                return;
            }
            operationModeTv.setText(StringUtil.getOperationModeName(operationMode));
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
            if (totalMileageTv != null) {
                totalMileageTv.setText(String.valueOf(totalMileageValue));
            }
            //里程1
            if (customMileage1Tv != null) {
                customMileage1Tv.setText(String.valueOf(BydApi29Helper.getMileageNumber(statisticDevice, 0)));
            }

            //里程2
            if (customMileage2Tv != null) {
                customMileage2Tv.setText(String.valueOf(BydApi29Helper.getMileageNumber(statisticDevice, 1)));
            }
            //总EV里程
            if (totalEvMileageTv != null) {
                totalEvMileageTv.setText(String.valueOf(statisticDevice.getEVMileageValue()));
            }
            //总HEV里程
            int hevMileageValue = BydApi29Helper.getHEVMileageValue(statisticDevice);
            if (totalHevMileageTv != null) {
                totalHevMileageTv.setText(String.valueOf(hevMileageValue));
            }
            //更新单次行程能耗数据
            int evMileageValue = statisticDevice.getEVMileageValue();
            double totalElecConValue = statisticDevice.getTotalElecConValue();
            double totalFuelConValue = statisticDevice.getTotalFuelConValue();

            //本次行程总里程
            if (currentTravelMileageTv != null) {
                currentTravelMileageTv.setText(String.valueOf(totalMileageValue - init_totalMileageValue));
            }
            //本次行程ev里程
            int ev_mileage = evMileageValue - init_evMileageValue;
            //本次行程hev里程
            int hev_mileage = hevMileageValue - init_hevMileageValue;
            //本次行程电耗
            double elec_cost = totalElecConValue - init_totalElecConValue;
            //本次行程油耗
            double fuel_cost = totalFuelConValue - init_totalFuelConValue;
            //本次行程能耗(电耗+油耗)
            String cost = elec_cost + "Kwh+" + fuel_cost + "L";
            if (currentTravelEnergyCostTv != null) {
                currentTravelEnergyCostTv.setText(cost);
            }
            //本次行程花费
            if (currentTravelYuanCostTv != null) {
                double yuan = elec_cost * init_latest_electric_price + fuel_cost * init_latest_fuel_price;
                currentTravelYuanCostTv.setText(format.format(yuan));
            }
            //本次行程平均电耗(kwh/100km)
            if (currentTravelElecCostTv != null) {
                currentTravelElecCostTv.setText(format.format(elec_cost * 100 / ev_mileage));
            }
            //本次行程平均油耗
            if (currentTravelFuelCostTv != null) {
                currentTravelFuelCostTv.setText(format.format(fuel_cost * 100 / hev_mileage));
            }
        }

        /**
         * 监听燃油消耗总量变化
         * @param value
         */
        @Override
        public void onTotalFuelConChanged(double value) {
            super.onTotalFuelConChanged(value);
            if (totalFuelCostTv == null) {
                return;
            }
            totalFuelCostTv.setText(format.format(value));
        }

        /**
         * 监听电消耗总量的变化
         * @param value
         */
        @Override
        public void onTotalElecConChanged(double value) {
            super.onTotalElecConChanged(value);
            if (totalElecCostTv == null) {
                return;
            }
            totalElecCostTv.setText(format.format(value));
        }

        /**
         * 监听最近百公里油耗变化
         * @param value {0,51.1}L/100KM
         */
        @Override
        public void onLastFuelConPHMChanged(double value) {
            super.onLastFuelConPHMChanged(value);
            if (lastFuelConPhmTv == null) {
                return;
            }
            lastFuelConPhmTv.setText(format.format(value));
        }

        /**
         * 监听累计平均油耗变化
         * @param value
         */
        @Override
        public void onTotalFuelConPHMChanged(double value) {
            super.onTotalFuelConPHMChanged(value);
//            String s = format.format(value) + "(监听值)";
            totalFuelConPHM = value;
//            binding.totalFuelConPhmTv.setText(s);
        }


        /**
         * 监听最近百公里电耗变化
         * @param value {-99.9,99.9}KWH/100KM
         */
        @Override
        public void onLastElecConPHMChanged(double value) {
            super.onLastElecConPHMChanged(value);
            if (lastElecConPhmTv == null) {
                return;
            }
            lastElecConPhmTv.setText(format.format(value));
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
            if (powerMileageTv == null) {
                return;
            }
            powerMileageTv.setText(String.valueOf(value));
        }


        /**
         * 监听燃油续航里程变化
         * @param value
         */
        @Override
        public void onFuelDrivingRangeChanged(int value) {
            super.onFuelDrivingRangeChanged(value);
            if (fuelMileageTv == null) {
                return;
            }
            fuelMileageTv.setText(String.valueOf(value));
        }

        /**
         * 监听燃油百分比变化
         * @param fuelPercentageValue
         */
        @Override
        public void onFuelPercentageChanged(int fuelPercentageValue) {
            super.onFuelPercentageChanged(fuelPercentageValue);
            if (fuelPercentPb != null) {
                fuelPercentPb.setMax(100);
                fuelPercentPb.setProgress(fuelPercentageValue);
            }
            if (fuelPbTv != null) {
                fuelPbTv.setText(String.valueOf(fuelPercentageValue));
            }
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
//        KLog.e("电量续航百分比：" + ret);
            if (elecPercentPb != null) {
                elecPercentPb.setMax(100);
                elecPercentPb.setProgress(((int) ret));
            }
            safeSetText(elecPbTv, String.valueOf((int) ret));
        }

        /**
         * 监听钥匙电池电量变化
         * @param value
         */
        @Override
        public void onKeyBatteryLevelChanged(int value) {
            super.onKeyBatteryLevelChanged(value);
        }

        @Override
        public void onEVMileageValueChanged(int value) {
            super.onEVMileageValueChanged(value);
            if (totalEvMileageTv == null) {
                return;
            }
            totalEvMileageTv.setText(String.valueOf(value));
        }

        public void onHEVMileageValueChanged(int value) {
            if (totalHevMileageTv != null) {
                totalHevMileageTv.setText(String.valueOf(value));
            }
        }

        /**
         * 水温
         *
         * @param value
         */
        public void onWaterTemperatureChanged(int value) {
            updateWaterTemperature();
        }

        /**
         * 瞬时电耗
         *
         * @param value
         */
        public void onInstantElecConChanged(double value) {
            updateInstantElecCon();
        }

        /**
         * 瞬时油耗
         *
         * @param value
         */
        public void onInstantFuelConChanged(double value) {
            updateInstantFuelCon();
        }

    };

    private void updateEngineSpeedData() {
        if (engineDevice == null) {
            return;
        }
        int engine_speed = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_SPEED);
        int engine_speed_gb = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_SPEED_GB);
        int engine_speed_warning = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_SPEED_WARNING);
        if (engine_speed_gb > 0 && engine_speed_gb <= 8000) {
            updateEngineSpeedUI(engine_speed_gb);
        } else if (engine_speed > 0 && engine_speed <= 8000) {
            updateEngineSpeedUI(engine_speed);
        }

        int front_motor_speed = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_FRONT_MOTOR_SPEED);
        int front_motor_torque = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_FRONT_MOTOR_TORQUE);

        int rear_motor_speed = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_REAR_MOTOR_SPEED);
        int rear_motor_torque = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_REAR_MOTOR_TORQUE);

        if (engineSpeedGbTv != null) {
            engineSpeedGbTv.setText(String.valueOf(engine_speed_gb));
        }
        if (engineSpeedWarningTv != null) {
            engineSpeedWarningTv.setText(String.valueOf((engine_speed_warning > 1 ? engine_speed_warning : 0)));
        }
        if (frontMotorSpeedTv != null) {
            frontMotorSpeedTv.setText(String.valueOf(Math.abs(front_motor_speed)));
        }
        if (frontMotorTorqueTv != null) {
            frontMotorTorqueTv.setText(String.valueOf(getValidTorqueValue(front_motor_torque)));
        }
        if (rearMotorSpeedTv != null) {
            rearMotorSpeedTv.setText(String.valueOf(Math.abs(rear_motor_speed)));
        }
        if (rearMotorTorqueTv != null) {
            rearMotorTorqueTv.setText(String.valueOf(getValidTorqueValue(rear_motor_torque)));
        }
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

    private void updateDrivingTime() {
        if (drivingTimeTv != null && statisticDevice != null) {
            double travelTime = BydApi29Helper.getTravelTime(statisticDevice, 1);
            drivingTimeTv.setText(String.valueOf(travelTime));
        }
    }


    private final AbsBYDAutoSpeedListener absBYDAutoSpeedListener = new AbsBYDAutoSpeedListener() {
        /**
         * 监听车速变化[0-282]km/h
         * @param currentSpeed
         */
        @Override
        public void onSpeedChanged(double currentSpeed) {
            super.onSpeedChanged(currentSpeed);
            if (carSpeedTv != null) {
                carSpeedTv.setText(format.format(currentSpeed));
            }
            if (carSpeedCsv != null) {
                carSpeedCsv.setVelocity(((int) currentSpeed));
            }

            //行车模式
            updateEnginePower();

            updateEngineSpeedData();

            updateDrivingTime();

            //更新前电机轩速表
            if (motorSpeedMsv != null) {
                int front_motor_speed = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_FRONT_MOTOR_SPEED);
                motorSpeedMsv.setVelocity(front_motor_speed);
            }
        }

        /**
         * 监听油门深度变化[0-100]%
         * @param value
         */
        @Override
        public void onAccelerateDeepnessChanged(int value) {
            super.onAccelerateDeepnessChanged(value);
            if (youMengPb == null || youMengTv == null) {
                return;
            }
            youMengPb.setProgress(value);
            youMengTv.setText(String.valueOf(value));

            updateEngineSpeedData();
        }

        /**
         * 监听制动深度变化[0-100]%
         * @param value
         */
        @Override
        public void onBrakeDeepnessChanged(int value) {
            super.onBrakeDeepnessChanged(value);
            if (shaChePb == null || shaCheTv == null) {
                return;
            }
            shaChePb.setProgress(value);
            shaCheTv.setText(String.valueOf(value));
        }

    };

    private void updateEngineSpeedUI(int engineSpeed) {
        if (engineSpeed > 8000 || engineSpeed < 0) {
            engineSpeed = 0;
        }
        if (engineSpeedTv != null) {
            engineSpeedTv.setText(String.valueOf(engineSpeed));
        }
        if (engineSpeedEsv != null) {
            engineSpeedEsv.setVelocity(engineSpeed);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about_us) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (item.getItemId() == R.id.version_update) {
            Uri uri = Uri.parse("https://gitee.com/liyiwei1032/car-staus-helper");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else if (item.getItemId() == R.id.float_show) {
            startService(new Intent(this, FloatingWindowService.class));
        } else if (item.getItemId() == R.id.settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (item.getItemId() == R.id.radar_distance) {
            startActivity(new Intent(this, RadarFloatingSettingActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    private void showVin(boolean show) {
        if (textTv == null) {
            return;
        }
        if (show) {
            if (bodyworkDevice == null) {
                textTv.setText("00000000000000000");
                return;
            }
            String autoVIN = bodyworkDevice.getAutoVIN();
            textTv.setText(autoVIN);
        } else {
            textTv.setText("***********************");
        }
    }

    /**
     * 发动机水温
     */
    private void updateWaterTemperature() {
        if (waterTemperatureTv != null) {
            waterTemperatureTv.setText(BydApi29Helper.getWaterTemperature(statisticDevice));
        }
    }

    /**
     * 瞬时电耗
     */
    private void updateInstantElecCon() {
        if (instantElecConTv != null) {
            instantElecConTv.setText(BydApi29Helper.getInstantElecConValue(statisticDevice));
        }
    }

    /**
     * 瞬时油耗
     */
    private void updateInstantFuelCon() {
        if (instantFuelConTv != null) {
            instantFuelConTv.setText(BydApi29Helper.getInstantFuelConValue(statisticDevice));
        }
    }

    /**
     * 初始化界面数据
     */
    private void initAutoData() {
        if (bodyworkDevice == null) {
            return;
        }
        //6.1.2 车架号
        //LGXC76C44N0131100,LGXC76C44N0131101
        if (showVinCb != null) {
            showVin(showVinCb.isChecked());
        }
        //车型名称
        if (autoModelNameTv != null) {
            autoModelNameTv.setText(StringUtil.getAutoModelName(bodyworkDevice));
        }
        //车型代号
        if (autoTypeTv != null) {
            autoTypeTv.setText(BydApi29Helper.getAutoType(bodyworkDevice));
        }
        //蓄电池？待验证
        updateBatteryPower();
        //电源档位
        absBYDAutoBodyworkListener.onPowerLevelChanged(bodyworkDevice.getPowerLevel());

        //发动机名称
        if (engineCodeTv != null) {
            engineCodeTv.setText(engineDevice.getEngineCode());
        }
        //变速箱名称
        if (gearboxCodeTv != null) {
            gearboxCodeTv.setText(gearboxDevice.getGearboxCode());
        }
        //变速箱类型
        if (gearboxTypeTv != null) {
            gearboxTypeTv.setText(StringUtil.getGearboxTypeName(gearboxDevice.getGearboxType()));
        }

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
        //总行驶时间
        updateDrivingTime();
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
        //发动机水温
        updateWaterTemperature();
        //瞬时电耗
        updateInstantElecCon();
        //瞬时油耗
        updateInstantFuelCon();
        //当前风量
        absBYDAutoAcListener.onAcWindLevelChanged(bydAutoAcDevice.getAcWindLevel());
        //充电功率与剩余时长
        absBYDAutoChargingListener.onChargingPowerChanged(chargingDevice.getChargingPower());
        int[] time = chargingDevice.getChargingRestTime();
        absBYDAutoChargingListener.onChargingRestTimeChanged(time[0], time[1]);
        //胎压
        absBYDAutoTyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT, tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT));
        absBYDAutoTyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT, tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT));
        absBYDAutoTyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR, tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR));
        absBYDAutoTyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR, tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR));
    }

    private void updateEnginePower() {
        int enginePower = engineDevice.getEnginePower();
        if (enginePowerTv != null) {
            enginePowerTv.setText(String.valueOf(enginePower));
        }
        if (enginePowerEpv != null) {
            enginePowerEpv.setVelocity(enginePower);
        }
        if (statisticDevice == null) {
            return;
        }
        double totalElecConPHMValue = statisticDevice.getTotalElecConPHMValue();
        double totalFuelConPHMValue = statisticDevice.getTotalFuelConPHMValue();

//        //燃油消耗总量
//        double totalFuelConValue = statisticDevice.getTotalFuelConValue();
//        //电消耗总量
//        double totalElecConValue = statisticDevice.getTotalElecConValue();

        String elec_listener_and_cacu = format.format(totalElecConPHM == 0 ? totalElecConPHMValue : totalElecConPHM);
        String fuel_listener_and_cacu = format.format(totalFuelConPHM == 0 ? totalFuelConPHMValue : totalFuelConPHM);

        if (totalElecConPhmTv != null) {
            totalElecConPhmTv.setText(elec_listener_and_cacu);
        }
        if (totalFuelConPhmTv != null) {
            totalFuelConPhmTv.setText(fuel_listener_and_cacu);
        }
    }

    private boolean isLandLayoutShow() {
        Resources resources = getResources();
        if (resources == null) {
            return false;
        }
        Configuration configuration = resources.getConfiguration();
        if (configuration == null) {
            return false;
        }
        return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        if (vId == R.id.energy_feedback_btn) {
            if (settingDevice == null || energyFeedbackBtn == null) {
                return;
            }
            int energyFeedback = settingDevice.getEnergyFeedback();
            if (energyFeedback == BYDAutoSettingDevice.SET_DR_ENERGY_FB_STANDARD) {
                settingDevice.setEnergyFeedback(BYDAutoSettingDevice.SET_DR_ENERGY_FB_LARGE);
                energyFeedbackBtn.setText("较大");
            } else if (energyFeedback == BYDAutoSettingDevice.SET_DR_ENERGY_FB_LARGE) {
                settingDevice.setEnergyFeedback(BYDAutoSettingDevice.SET_DR_ENERGY_FB_STANDARD);
                energyFeedbackBtn.setText("标准");
            } else {
                Toast.makeText(this, "error feedback mode:" + energyFeedback, Toast.LENGTH_SHORT).show();
            }
        } else if (bydAutoAcDevice != null) {
            if (vId == R.id.temperature_plus_btn) {
                int temprature = bydAutoAcDevice.getTemprature(BYDAutoAcDevice.AC_TEMPERATURE_MAIN);
                if (temprature == BYDAutoAcDevice.AC_TEMP_IN_CELSIUS_MAX) {
                    return;
                }
                bydAutoAcDevice.setAcTemperature(BYDAutoAcDevice.AC_TEMPERATURE_MAIN_DEPUTY, temprature + 1, BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_TEMPERATURE_UNIT_OC);
                if (isLandLayoutShow()) {
                    Toast.makeText(this, "当前温度：" + (temprature + 1), Toast.LENGTH_SHORT).show();
                }
            } else if (vId == R.id.temperature_sub_btn) {
                int temprature = bydAutoAcDevice.getTemprature(BYDAutoAcDevice.AC_TEMPERATURE_MAIN);
                if (temprature == BYDAutoAcDevice.AC_TEMP_IN_CELSIUS_MIN) {
                    return;
                }
                bydAutoAcDevice.setAcTemperature(BYDAutoAcDevice.AC_TEMPERATURE_MAIN_DEPUTY, temprature - 1, BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_TEMPERATURE_UNIT_OC);
                if (isLandLayoutShow()) {
                    Toast.makeText(this, "当前温度：" + (temprature - 1), Toast.LENGTH_SHORT).show();
                }
            } else if (vId == R.id.wind_level_plus_btn) {
                int acWindLevel = bydAutoAcDevice.getAcWindLevel();
                if (acWindLevel == BYDAutoAcDevice.AC_WINDLEVEL_7) {
                    return;
                }
                bydAutoAcDevice.setAcWindLevel(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, acWindLevel + 1);
                if (isLandLayoutShow()) {
                    Toast.makeText(this, "当前风量：" + (acWindLevel + 1), Toast.LENGTH_SHORT).show();
                }
            } else if (vId == R.id.wind_level_sub_btn) {
                int acWindLevel = bydAutoAcDevice.getAcWindLevel();
                if (acWindLevel == BYDAutoAcDevice.AC_WINDLEVEL_0) {
                    bydAutoAcDevice.stopRearAc(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY);
                    int ret = bydAutoAcDevice.stop(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY);
                    Toast.makeText(this, getCommandRet(ret), Toast.LENGTH_SHORT).show();
                    return;
                }
                bydAutoAcDevice.setAcWindLevel(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, acWindLevel - 1);
                if (isLandLayoutShow()) {
                    Toast.makeText(this, "当前风量：" + (acWindLevel - 1), Toast.LENGTH_SHORT).show();
                }
            } else if (vId == R.id.defrost_mode_btn) {
                int state = bydAutoAcDevice.getAcDefrostState(BYDAutoAcDevice.AC_DEFROST_AREA_FRONT);
                if (state == BYDAutoAcDevice.AC_DEFROST_STATE_OFF) {
                    bydAutoAcDevice.setAcDefrostState(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_DEFROST_AREA_FRONT, BYDAutoAcDevice.AC_DEFROST_STATE_ON);
                } else if (state == BYDAutoAcDevice.AC_DEFROST_STATE_ON) {
                    bydAutoAcDevice.setAcDefrostState(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_DEFROST_AREA_FRONT, BYDAutoAcDevice.AC_DEFROST_STATE_OFF);
                } else {

                }
            } else if (vId == R.id.ventilate_mode_btn) {
                int state = bydAutoAcDevice.getAcVentilationState();
                if (state == BYDAutoAcDevice.AC_VENTILATION_STATE_OFF) {
                    bydAutoAcDevice.setAcCycleMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_CYCLEMODE_OUTLOOP);
                    bydAutoAcDevice.setAcVentilationState(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_VENTILATION_STATE_ON);
                } else if (state == BYDAutoAcDevice.AC_VENTILATION_STATE_ON) {
                    bydAutoAcDevice.setAcCycleMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_CYCLEMODE_INLOOP);
                    bydAutoAcDevice.setAcVentilationState(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_VENTILATION_STATE_OFF);
                } else {

                }
            }
        }
    }

    private void safeSetText(TextView target, CharSequence text) {
        if (target == null) {
            return;
        }
        target.setText(text);
    }

    private static String getCommandRet(int ret) {
        if (ret == 0) {
            return "success";
        }
        return "failed";
    }
}