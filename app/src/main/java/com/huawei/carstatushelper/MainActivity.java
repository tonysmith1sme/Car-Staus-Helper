package com.huawei.carstatushelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.charging.AbsBYDAutoChargingListener;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.speed.AbsBYDAutoSpeedListener;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.hardware.bydauto.tyre.AbsBYDAutoTyreListener;
import android.hardware.bydauto.tyre.BYDAutoTyreDevice;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import com.huawei.byd_sdk_29.Api29Helper;
import com.huawei.carstatushelper.activity.AboutActivity;
import com.huawei.carstatushelper.activity.RadarFloatingSettingActivity;
import com.huawei.carstatushelper.activity.SettingsActivity;
import com.huawei.carstatushelper.databinding.ActivityMainBinding;
import com.huawei.carstatushelper.databinding.ActivityMainLandBinding;
import com.huawei.carstatushelper.databinding.ActivityMainLandMultiBinding;
import com.huawei.carstatushelper.receiver.BootCompleteService;
import com.huawei.carstatushelper.util.PermissionUtils;
import com.huawei.carstatushelper.util.StringUtil;
import com.huawei.carstatushelper.view.CarSpeedView;
import com.huawei.carstatushelper.view.EnginePowerView;
import com.huawei.carstatushelper.view.EngineSpeedView;
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

    private boolean initSuccess;

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
        initSuccess = true;
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
        engineSpeedEsv = binding.engineSpeedEsv;
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
    }

    private void initMainLandView(ActivityMainLandBinding binding) {
        engineSpeedEsv = binding.engineSpeedEsv;
        enginePowerEpv = binding.enginePowerEpv;
        carSpeedCsv = binding.carSpeedCsv;

        temperaturePlusBtn = binding.temperaturePlusBtn;
        temperatureSubBtn = binding.temperatureSubBtn;
        windLevelPlusBtn = binding.windLevelPlusBtn;
        windLevelSubBtn = binding.windLevelSubBtn;

        currentGearboxLevelTv = binding.currentGearboxLevelTv;

        energyModeTv = binding.energyModeTv;
        operationModeTv = binding.operationModeTv;
        totalMileageTv = binding.totalMileageTv;
        totalHevMileageTv = binding.totalHevMileageTv;
        totalFuelCostTv = binding.totalFuelCostTv;
        totalElecCostTv = binding.totalElecCostTv;
        drivingTimeTv = binding.drivingTimeTv;
        lastFuelConPhmTv = binding.lastFuelConPhmTv;
        lastElecConPhmTv = binding.lastElecConPhmTv;

        totalEvMileageTv = binding.totalEvMileageTv;
        carSpeedTv = binding.carSpeedTv;
        engineSpeedTv = binding.engineSpeedTv;

        textTv = binding.textTv;
        powerMileageTv = binding.powerMileageTv;
        fuelMileageTv = binding.fuelMileageTv;
        fuelPercentPb = binding.fuelPercentPb;
        fuelPbTv = binding.fuelPbTv;
        elecPercentPb = binding.elecPercentPb;
        elecPbTv = binding.elecPbTv;
        enginePowerTv = binding.enginePowerTv;
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
        register();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregister();
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
        bodyworkDevice = BYDAutoBodyworkDevice.getInstance(this);
        statisticDevice = BYDAutoStatisticDevice.getInstance(this);
        speedDevice = BYDAutoSpeedDevice.getInstance(this);
        energyDevice = BYDAutoEnergyDevice.getInstance(this);
        engineDevice = BYDAutoEngineDevice.getInstance(this);
        bydAutoAcDevice = BYDAutoAcDevice.getInstance(this);
        gearboxDevice = BYDAutoGearboxDevice.getInstance(this);
        chargingDevice = BYDAutoChargingDevice.getInstance(this);
        tyreDevice = BYDAutoTyreDevice.getInstance(this);
    }

    private void register() {
        if (!initSuccess) {
            return;
        }
        statisticDevice.registerListener(absBYDAutoStatisticListener);
        speedDevice.registerListener(absBYDAutoSpeedListener);
        energyDevice.registerListener(absBYDAutoEnergyListener);
        bydAutoAcDevice.registerListener(absBYDAutoAcListener);
        gearboxDevice.registerListener(absBYDAutoGearboxListener);
        chargingDevice.registerListener(absBYDAutoChargingListener);
        tyreDevice.registerListener(absBYDAutoTyreListener);
//        oliCostHelper.start();
    }

    private void unregister() {
        if (!initSuccess) {
            return;
        }
        statisticDevice.unregisterListener(absBYDAutoStatisticListener);
        speedDevice.unregisterListener(absBYDAutoSpeedListener);
        energyDevice.unregisterListener(absBYDAutoEnergyListener);
        bydAutoAcDevice.unregisterListener(absBYDAutoAcListener);
        gearboxDevice.unregisterListener(absBYDAutoGearboxListener);
        chargingDevice.unregisterListener(absBYDAutoChargingListener);
        tyreDevice.unregisterListener(absBYDAutoTyreListener);
//        oliCostHelper.stop();
    }

    private AbsBYDAutoTyreListener absBYDAutoTyreListener = new AbsBYDAutoTyreListener() {
        /**
         * 胎压变化
         * @param area
         * @param value
         */
        @Override
        public void onTyrePressureValueChanged(int area, int value) {
            super.onTyrePressureValueChanged(area, value);
//            KLog.e("TyrePressure area = " + area + " ,value = " + value);
            if (tyrePreLeftFrontTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT) {
                tyrePreLeftFrontTv.setText(value + "kPa");
            }
            if (tyrePreRightFrontTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT) {
                tyrePreRightFrontTv.setText(value + "kPa");
            }
            if (tyrePreLeftRearTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR) {
                tyrePreLeftRearTv.setText(value + "kPa");
            }
            if (tyrePreRightRearTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR) {
                tyrePreRightRearTv.setText(value + "kPa");
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
            chargingPowerTv.setText(value + "");
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
            chargingRestTimeTv.setText((hour > 24 ? 0 : hour) + "h " + (min > 60 ? 0 : min) + "分");
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
            currentWindLevelTv.setText("" + level);
        }

        /**
         * 监听各区域温度的变化
         * @param area
         * @param value
         */
        @Override
        public void onTemperatureChanged(int area, int value) {
            super.onTemperatureChanged(area, value);
            KLog.e("各区域温度：" + area + " " + value);
            if (currentTemperatureTv != null) {
                currentTemperatureTv.setText("区域" + area + "，" + value);
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
            String energyModeName = StringUtil.getEnergyModeName(energyMode);
//            KLog.e("当前能耗模式：" + energyMode + " " + energyModeName);
            if (energyModeTv == null) {
                return;
            }
            energyModeTv.setText(energyModeName);
        }

        /**
         * 监听整车运行模式（经济模式，运动模式）
         * @param operationMode
         */
        @Override
        public void onOperationModeChanged(int operationMode) {
            super.onOperationModeChanged(operationMode);
            String operationModeName = StringUtil.getOperationModeName(operationMode);
//            KLog.e("当前行车模式：" + operationMode + " " + operationModeName);
            if (operationModeTv == null) {
                return;
            }
            operationModeTv.setText(operationModeName);
        }
    };

    private final AbsBYDAutoStatisticListener absBYDAutoStatisticListener = new AbsBYDAutoStatisticListener() {
        /**
         * 水温
         *
         * @param value
         */
//        @Override
        public void onWaterTemperatureChanged(int value) {
            if (waterTemperatureTv != null) {
                waterTemperatureTv.setText(String.valueOf(value > 0 ? value : 0));
            }
        }

        /**
         * 瞬时电耗
         *
         * @param value
         */
//        @Override
        public void onInstantElecConChanged(double value) {
            if (instantElecConTv != null) {
                instantElecConTv.setText(String.valueOf(format.format(value > 0 ? value : 0)));
            }
        }

        /**
         * 瞬时油耗
         *
         * @param value
         */
//        @Override
        public void onInstantFuelConChanged(double value) {
            if (instantFuelConTv != null) {
                instantFuelConTv.setText(String.valueOf(format.format(value)));
            }
        }

        /**
         * 监听总里程变化
         * @param totalMileageValue
         */
        @Override
        public void onTotalMileageValueChanged(int totalMileageValue) {
            super.onTotalMileageValueChanged(totalMileageValue);
//            KLog.e("总行驶里程：" + totalMileageValue + " km");
            if (totalMileageTv != null) {
                totalMileageTv.setText(totalMileageValue + " km");
            }
            int hevMileageValue = Api29Helper.getHEVMileageValue(statisticDevice);
            if (totalHevMileageTv != null) {
                totalHevMileageTv.setText(hevMileageValue);
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
            String cost = elec_cost + "kwh" + fuel_cost + "L";
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
//            KLog.e("总燃油消耗：" + value + " L");
            if (totalFuelCostTv == null) {
                return;
            }
            totalFuelCostTv.setText(format.format(value) + "L");
        }

        /**
         * 监听电消耗总量的变化
         * @param value
         */
        @Override
        public void onTotalElecConChanged(double value) {
            super.onTotalElecConChanged(value);
//            KLog.e("总电量消耗：" + value + "KWH");
            if (totalElecCostTv == null) {
                return;
            }
            totalElecCostTv.setText(format.format(value) + "KWH");
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
            lastFuelConPhmTv.setText(format.format(value) + "L/100KM");
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
            lastElecConPhmTv.setText(format.format(value) + "KWH/100KM");
        }

        /**
         * 监听累计平均电耗变化
         * @param value
         */
        @Override
        public void onTotalElecConPHMChanged(double value) {
            super.onTotalElecConPHMChanged(value);
//            String s = format.format(value) + "(监听值)";
            totalElecConPHM = value;
//            binding.totalElecConPhmTv.setText(s);
        }

        /**
         * 监听电续航里程变化
         * @param value
         */
        @Override
        public void onElecDrivingRangeChanged(int value) {
            super.onElecDrivingRangeChanged(value);
            KLog.e("电量续航里程：" + value + "km");
            updateElecDrivingRange(value);
        }


        /**
         * 监听燃油续航里程变化
         * @param value
         */
        @Override
        public void onFuelDrivingRangeChanged(int value) {
            super.onFuelDrivingRangeChanged(value);
            KLog.e("燃油续航里程：" + value + "km");
            updateFuelDrivingRange(value);
        }

        /**
         * 监听燃油百分比变化
         * @param fuelPercentageValue
         */
        @Override
        public void onFuelPercentageChanged(int fuelPercentageValue) {
            super.onFuelPercentageChanged(fuelPercentageValue);
            KLog.e("燃油续航百分比：" + fuelPercentageValue);
            updateFuelPercent(fuelPercentageValue);
        }

        /**
         * 监听电量百分比变化
         * @param value
         */
        @Override
        public void onElecPercentageChanged(double value) {
            super.onElecPercentageChanged(value);
            updateElecPercent(value);
        }

        /**
         * 监听钥匙电池电量变化
         * @param value
         */
        @Override
        public void onKeyBatteryLevelChanged(int value) {
            super.onKeyBatteryLevelChanged(value);
        }

        /**
         * 监听EV里程的变化
         * @param value
         */
        @Override
        public void onEVMileageValueChanged(int value) {
            super.onEVMileageValueChanged(value);
            if (totalEvMileageTv == null) {
                return;
            }
            totalEvMileageTv.setText(value + "km");
        }
    };

    private void updateEngineSpeedData() {
        if (engineDevice == null) {
            return;
        }
        int engine_speed = Api29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_SPEED);
        int engine_speed_gb = Api29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_SPEED_GB);

        int engine_speed_warning = Api29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_SPEED_WARNING);

        if (engine_speed > 0 && engine_speed <= 8000) {
            updateEngineSpeedUI(engine_speed);
        } else if (engine_speed_gb > 0 && engine_speed_gb <= 8000) {
            updateEngineSpeedUI(engine_speed_gb);
        }

        int front_motor_speed = Api29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_FRONT_MOTOR_SPEED);
        int front_motor_torque = Api29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_FRONT_MOTOR_TORQUE);

        int rear_motor_speed = Api29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_REAR_MOTOR_SPEED);
        int rear_motor_torque = Api29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_REAR_MOTOR_TORQUE);

        if (engineSpeedGbTv != null) {
            engineSpeedGbTv.setText(engine_speed_gb + "rpm");
        }
        if (engineSpeedWarningTv != null) {
            engineSpeedWarningTv.setText((engine_speed_warning > 1 ? engine_speed_warning : 0) + "rpm");
        }
        if (frontMotorSpeedTv != null) {
            frontMotorSpeedTv.setText(Math.abs(front_motor_speed) + "rpm");
        }
        if (frontMotorTorqueTv != null) {
            frontMotorTorqueTv.setText(getValidTorqueValue(front_motor_torque) + "Nm");
        }
        if (rearMotorSpeedTv != null) {
            rearMotorSpeedTv.setText(Math.abs(rear_motor_speed) + "rpm");
        }
        if (rearMotorTorqueTv != null) {
            rearMotorTorqueTv.setText(getValidTorqueValue(rear_motor_torque) + "Nm");
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


    private final AbsBYDAutoSpeedListener absBYDAutoSpeedListener = new AbsBYDAutoSpeedListener() {
        /**
         * 监听车速变化[0-282]km/h
         * @param currentSpeed
         */
        @Override
        public void onSpeedChanged(double currentSpeed) {
            super.onSpeedChanged(currentSpeed);
//            KLog.e("当前车速：" + currentSpeed);
            if (carSpeedTv != null) {
                carSpeedTv.setText(format.format(currentSpeed) + "km/h");
            }
            carSpeedCsv.setVelocity(((int) currentSpeed));

            //行车模式
            updateEnginePower();

            updateEngineSpeedData();

            if (drivingTimeTv != null && statisticDevice != null) {
                double travelTime = Api29Helper.getTravelTime(statisticDevice, 1);
                drivingTimeTv.setText(String.valueOf(travelTime));
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
            youMengTv.setText(value + "%");

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
            shaCheTv.setText(value + "%");
        }

    };

    private void updateEngineSpeedUI(int engineSpeed) {
        if (engineSpeed > 8000 || engineSpeed < 0) {
            engineSpeed = 0;
        }
        if (engineSpeedTv != null) {
            engineSpeedTv.setText(engineSpeed + " rpm");
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
                return;
            }
            String autoVIN = bodyworkDevice.getAutoVIN();
            textTv.setText(autoVIN);
        } else {
            textTv.setText("*****************");
        }
    }

    /**
     * 初始化界面数据
     */
    private void initAutoData() {
        if (!initSuccess) {
            return;
        }
        //6.1.2 车架号
        //LGXC76C44N0131100,LGXC76C44N0131101
        if (showVinCb != null) {
            boolean checked = showVinCb.isChecked();
            showVin(checked);
        }
        if (autoModelNameTv != null) {
            String autoModelName = StringUtil.getAutoModelName(bodyworkDevice);
            autoModelNameTv.setText(autoModelName);
        }
        if (autoTypeTv != null) {
            autoTypeTv.setText(Api29Helper.getAutoType(bodyworkDevice) + "");
        }

        //发动机名称
        if (engineCodeTv != null) {
            engineCodeTv.setText(engineDevice.getEngineCode() + "");
        }
        //变速箱名称
        if (gearboxCodeTv != null) {
            gearboxCodeTv.setText(gearboxDevice.getGearboxCode() + "");
        }
        //变速箱类型
        if (gearboxTypeTv != null) {
            gearboxTypeTv.setText(StringUtil.getGearboxTypeName(gearboxDevice.getGearboxType()));
        }

        //总里程
        int totalMileageValue = statisticDevice.getTotalMileageValue();
        absBYDAutoStatisticListener.onTotalMileageValueChanged(totalMileageValue);

        //总EV里程
        int evMileageValue = statisticDevice.getEVMileageValue();
        absBYDAutoStatisticListener.onEVMileageValueChanged(evMileageValue);

        //车速
        double currentSpeed = speedDevice.getCurrentSpeed();
        absBYDAutoSpeedListener.onSpeedChanged(currentSpeed);

        //能耗模式
        int energyMode = energyDevice.getEnergyMode();
        absBYDAutoEnergyListener.onEnergyModeChanged(energyMode);

        //驾驶模式
        int operationMode = energyDevice.getOperationMode();
        absBYDAutoEnergyListener.onOperationModeChanged(operationMode);

        //功率
        updateEnginePower();

        //发动机转速
        updateEngineSpeedData();

        //累计燃油消耗
        double totalFuelConValue = statisticDevice.getTotalFuelConValue();
        absBYDAutoStatisticListener.onTotalFuelConChanged(totalFuelConValue);

        //累计电量消耗
        double totalElecConValue = statisticDevice.getTotalElecConValue();
        absBYDAutoStatisticListener.onTotalElecConChanged(totalElecConValue);

        //当前档位
        int gearboxAutoModeType = gearboxDevice.getGearboxAutoModeType();
        absBYDAutoGearboxListener.onGearboxAutoModeTypeChanged(gearboxAutoModeType);

        //总行驶时间
        double drivingTimeValue = statisticDevice.getDrivingTimeValue();
        absBYDAutoStatisticListener.onDrivingTimeChanged(drivingTimeValue);

        //最近50km电耗
        double lastElecConPHMValue = statisticDevice.getLastElecConPHMValue();
        absBYDAutoStatisticListener.onLastElecConPHMChanged(lastElecConPHMValue);
        //最近50km油耗
        double lastFuelConPHMValue = statisticDevice.getLastFuelConPHMValue();
        absBYDAutoStatisticListener.onLastFuelConPHMChanged(lastFuelConPHMValue);
        //累计平均电耗
        double totalElecConPHMValue = statisticDevice.getTotalElecConPHMValue();
        absBYDAutoStatisticListener.onTotalElecConPHMChanged(totalElecConPHMValue);
        //累计平均油耗
        double totalFuelConPHMValue = statisticDevice.getTotalFuelConPHMValue();
        absBYDAutoStatisticListener.onTotalFuelConPHMChanged(totalFuelConPHMValue);

        //剩余电量百分比
        updateElecPercent(statisticDevice.getElecPercentageValue());
        //剩余电续航里程
        updateElecDrivingRange(statisticDevice.getElecDrivingRangeValue());
        //剩余燃油百分比
        updateFuelPercent(statisticDevice.getFuelPercentageValue());
        //剩余燃油续航里程
        updateFuelDrivingRange(statisticDevice.getFuelDrivingRangeValue());

        //水温
        if (waterTemperatureTv != null) {
            waterTemperatureTv.setText(String.valueOf(Api29Helper.getWaterTemperature(statisticDevice)));
        }
        //瞬时电耗
        if (instantElecConTv != null) {
            instantElecConTv.setText(String.valueOf(Api29Helper.getInstantElecConValue(statisticDevice)));
        }
        //瞬时油耗
        if (instantFuelConTv != null) {
            instantFuelConTv.setText(String.valueOf(Api29Helper.getInstantFuelConValue(statisticDevice)));
        }

        //当前风量
        int acWindLevel = bydAutoAcDevice.getAcWindLevel();
        absBYDAutoAcListener.onAcWindLevelChanged(acWindLevel);

        //充电功率与剩余时长
        if (chargingPowerTv != null) {
            chargingPowerTv.setText(format.format(chargingDevice.getChargingPower()) + "");
        }
        if (chargingRestTimeTv != null) {
            int[] time = chargingDevice.getChargingRestTime();
            if (time.length == 2) {
                chargingRestTimeTv.setText((time[0] > 24 ? 0 : time[0]) + "h" + (time[1] > 60 ? 0 : time[1]) + "min");
            }
        }
        //胎压
        if (tyrePreLeftFrontTv != null) {
            int value = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT);
            tyrePreLeftFrontTv.setText(value + "kPa");
        }
        if (tyrePreRightFrontTv != null) {
            int value = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT);
            tyrePreRightFrontTv.setText(value + "kPa");
        }
        if (tyrePreLeftRearTv != null) {
            int value = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR);
            tyrePreLeftRearTv.setText(value + "kPa");
        }
        if (tyrePreRightRearTv != null) {
            int value = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR);
            tyrePreRightRearTv.setText(value + "kPa");
        }
    }

    /**
     * 电量续航里程
     *
     * @param value
     */
    private void updateElecDrivingRange(int value) {
        if (powerMileageTv == null) {
            return;
        }
        powerMileageTv.setText(value + "km");
    }

    /**
     * 燃油续航里程
     *
     * @param value
     */
    private void updateFuelDrivingRange(int value) {
        if (fuelMileageTv == null) {
            return;
        }
        fuelMileageTv.setText(value + "km");
    }

    /**
     * 燃油百分比
     *
     * @param fuelPercentageValue
     */
    private void updateFuelPercent(int fuelPercentageValue) {
        if (fuelPercentPb != null) {
            fuelPercentPb.setMax(100);
            fuelPercentPb.setProgress(fuelPercentageValue);
        }
        if (fuelPbTv != null) {
            fuelPbTv.setText(fuelPercentageValue + "%");
        }
    }

    /**
     * 电量百分比
     *
     * @param value
     */
    private void updateElecPercent(double value) {
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
        safeSetText(elecPbTv, format.format(ret) + "%");
    }


    private void updateEnginePower() {
        int enginePower = engineDevice.getEnginePower();
//        KLog.e("当前功率：" + enginePower + " kw");
        if (enginePowerTv != null) {
            enginePowerTv.setText(enginePower + " kw");
        }
        if (enginePowerEpv != null) {
            enginePowerEpv.setVelocity(enginePower);
        }

        updateEnergyCost();
    }

    private void updateEnergyCost() {
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
        if (bydAutoAcDevice != null) {
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