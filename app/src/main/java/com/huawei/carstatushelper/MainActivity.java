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
import com.huawei.carstatushelper.activity.SettingsActivity;
import com.huawei.carstatushelper.byd_helper.BYDAutoAcDeviceHelper;
import com.huawei.carstatushelper.byd_helper.BYDAutoInstrumentDeviceHelper;
import com.huawei.carstatushelper.byd_helper.BYDAutoStatisticDeviceHelper;
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
    /**
     * 累计燃油消耗
     */
    private TextView totalFuelCostTv;
    /**
     * 累计电量消耗
     */
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
    /**
     * 发动机功率
     */
    private TextView enginePowerTv;
    /**
     * 累计平均电耗
     */
    private TextView totalElecConPhmTv;
    /**
     * 累计平均油耗
     */
    private TextView totalFuelConPhmTv;
    /**
     * 车架号
     */
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
    /**
     * 本次行程平均电耗
     */
    private TextView currentTravelElecCostTv;
    /**
     * 本次行程平均油耗
     */
    private TextView currentTravelFuelCostTv;
    /**
     * 本次行程里程
     */
    private TextView currentTravelMileageTv;
    /**
     * 本次行程电耗+油耗
     */
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
    /**
     * 本次行程花费
     */
    private TextView currentTravelYuanCostTv;
    private TextView customMileage1Tv;
    private TextView customMileage2Tv;
    /**
     * 蓄电池电压
     */
    private TextView smallBatteryVoltageTv;
    /**
     * 电源档位
     */
    private TextView powerLevelTv;
    private BYDAutoSettingDevice settingDevice;
    private Button energyFeedbackBtn;
    private TextView batterSocTv;
    private TextView lowestBatterTempTv;
    private TextView highestBatterTempTv;
    private TextView averageBatterTempTv;
    /**
     * 后电机转速
     */
    private RearMotorSpeedView rearMotorSpeedMsv;
    private SharedPreferences mPreferences;
    private boolean supportTyreValue;
    private TextView mapPlaceHolderTv;
    private BYDAutoInstrumentDevice instrumentDevice;
    private TextView waterTemperaturePercentTv;
    /**
     * 外接充电总量
     */
    private TextView externalChargingPowerTv;
    private Button acControlModeBtn;
    private Button resetMileage1Btn;
    private Button resetMileage2Btn;
    /**
     * 暂停行程
     */
    private Button pauseCurrentMileageBtn;
    private Button acOnOffBtn;
    private Button acCycleModeBtn;
    private Button acCompressorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.app_name) + BuildConfig.VERSION_NAME);

        loadContentView();

        initBtnListener();

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
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
        if (acControlModeBtn != null) {
            acControlModeBtn.setOnClickListener(this);
        }
        if (resetMileage1Btn != null) {
            resetMileage1Btn.setOnLongClickListener(onLongClickListener);
        }
        if (resetMileage2Btn != null) {
            resetMileage2Btn.setOnLongClickListener(onLongClickListener);
        }
        if (pauseCurrentMileageBtn != null) {
            pauseCurrentMileageBtn.setOnClickListener(this);
        }
        if (acOnOffBtn != null) {
            acOnOffBtn.setOnClickListener(this);
        }
        if (acCycleModeBtn != null) {
            acCycleModeBtn.setOnClickListener(this);
        }
        if (acCompressorBtn != null) {
            acCompressorBtn.setOnClickListener(this);
        }
    }

    private final View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            switch (view.getId()) {
                case R.id.reset_mileage1_btn:
                    if (instrumentDevice != null) {
                        BYDAutoInstrumentDeviceHelper instrumentDeviceHelper = BYDAutoInstrumentDeviceHelper.getInstance(instrumentDevice);
                        instrumentDeviceHelper.set(BYDAutoFeatureIds.INSTRUMENT_CLEAR_MILEAGE1_SET, 0);
                    }
                    break;
                case R.id.reset_mileage2_btn:
                    if (instrumentDevice != null) {
                        BYDAutoInstrumentDeviceHelper instrumentDeviceHelper = BYDAutoInstrumentDeviceHelper.getInstance(instrumentDevice);
                        instrumentDeviceHelper.set(BYDAutoFeatureIds.INSTRUMENT_CLEAR_MILEAGE2_SET, 0);
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    };

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
        frontMotorSpeedMsv = binding.motorSpeedMsv;
        enginePowerEpv = binding.enginePowerEpv;
        carSpeedCsv = binding.carSpeedCsv;
        rearMotorSpeedMsv = binding.rearMotorSpeedMsv;
//        rearMotorSpeedMsv.setHeader(" x1k rpm(后电机)");

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

        currentTravelMileageTv = binding.currentTravelMileageTv;
        currentTravelEnergyCostTv = binding.currentTravelEnergyCostTv;
        currentTravelYuanCostTv = binding.currentTravelYuanCostTv;
        currentTravelElecCostTv = binding.currentTravelElecCostTv;
        currentTravelFuelCostTv = binding.currentTravelFuelCostTv;

        smallBatteryVoltageTv = binding.smallBatteryVoltageTv;
        powerLevelTv = binding.powerLevelTv;
        energyFeedbackBtn = binding.energyFeedbackBtn;

        batterSocTv = binding.batterSocTv;
        lowestBatterTempTv = binding.lowestBatterTempTv;
        highestBatterTempTv = binding.highestBatterTempTv;
        averageBatterTempTv = binding.averageBatterTempTv;

        mapPlaceHolderTv = binding.mapPlaceHolderTv;
        waterTemperaturePercentTv = binding.waterTemperaturePercentTv;

        externalChargingPowerTv = binding.externalChargingPowerTv;

        acControlModeBtn = binding.acControlModeBtn;

        resetMileage1Btn = binding.resetMileage1Btn;
        resetMileage2Btn = binding.resetMileage2Btn;

        pauseCurrentMileageBtn = binding.pauseCurrentMileageBtn;

        acOnOffBtn = binding.acOnOffBtn;
        acCycleModeBtn = binding.acCycleModeBtn;
        acCompressorBtn = binding.acCompressorBtn;
    }

    private void initMainLandView(ActivityMainLandBinding binding) {
        engineSpeedEsv = binding.engineSpeedEsv;
        frontMotorSpeedMsv = binding.motorSpeedMsv;
        carSpeedCsv = binding.carSpeedCsv;
        enginePowerEpv = binding.enginePowerEpv;
        rearMotorSpeedMsv = binding.rearMotorSpeedMsv;

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

        tyrePreLeftFrontTv = binding.tyrePreLeftFrontTv;
        tyrePreRightFrontTv = binding.tyrePreRightFrontTv;
        tyrePreLeftRearTv = binding.tyrePreLeftRearTv;
        tyrePreRightRearTv = binding.tyrePreRightRearTv;

        defrostModeBtn = binding.defrostModeBtn;
        ventilateModeBtn = binding.ventilateModeBtn;

        currentTemperatureTv = binding.currentTemperatureTv;
        currentWindLevelTv = binding.currentWindLevelTv;

        currentTravelMileageTv = binding.currentTravelMileageTv;
        currentTravelEnergyCostTv = binding.currentTravelEnergyCostTv;
        currentTravelYuanCostTv = binding.currentTravelYuanCostTv;
        currentTravelElecCostTv = binding.currentTravelElecCostTv;
        currentTravelFuelCostTv = binding.currentTravelFuelCostTv;

        energyFeedbackBtn = binding.energyFeedbackBtn;

        externalChargingPowerTv = binding.externalChargingPowerTv;

        acControlModeBtn = binding.acControlModeBtn;
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
    protected void onDestroy() {
        super.onDestroy();
        if (statisticDevice == null) {
            return;
        }
        statisticDevice.unregisterListener(absBYDAutoStatisticListener);
        bodyworkDevice.unregisterListener(absBYDAutoBodyworkListener);
        speedDevice.unregisterListener(absBYDAutoSpeedListener);
        energyDevice.unregisterListener(absBYDAutoEnergyListener);
//        engineDevice.unregisterListener(absBYDAutoEngineListener);
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
        instrumentDevice = BYDAutoInstrumentDevice.getInstance(this);

        statisticDevice.registerListener(absBYDAutoStatisticListener);
        bodyworkDevice.registerListener(absBYDAutoBodyworkListener);
        speedDevice.registerListener(absBYDAutoSpeedListener);
        energyDevice.registerListener(absBYDAutoEnergyListener);
//        engineDevice.registerListener(absBYDAutoEngineListener);
        bydAutoAcDevice.registerListener(absBYDAutoAcListener);
        gearboxDevice.registerListener(absBYDAutoGearboxListener);
        chargingDevice.registerListener(absBYDAutoChargingListener);
        tyreDevice.registerListener(absBYDAutoTyreListener);
        settingDevice.registerListener(absBYDAutoSettingListener);
        instrumentDevice.registerListener(instrumentListener);
    }

//    private final AbsBYDAutoEngineListener absBYDAutoEngineListener = new AbsBYDAutoEngineListener() {
//        /**
//         * 监听发动机转速变化
//         * @param value
//         */
//        @Override
//        public void onEngineSpeedChanged(int value) {
//            super.onEngineSpeedChanged(value);
//            if (value > 0 && value <= 8000) {
//                updateEngineSpeedUI(value);
//            } else {
//                int engine_speed_gb = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_SPEED_GB);
//                updateEngineSpeedUI(engine_speed_gb);
//            }
//        }
//    };

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
            if (tyrePreLeftFrontTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT) {
                tyrePreLeftFrontTv.setText(tyreStateName);
            }
            if (tyrePreRightFrontTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT) {
                tyrePreRightFrontTv.setText(tyreStateName);
            }
            if (tyrePreLeftRearTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR) {
                tyrePreLeftRearTv.setText(tyreStateName);
            }
            if (tyrePreRightRearTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR) {
                tyrePreRightRearTv.setText(tyreStateName);
            }
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
            if (tyrePreLeftFrontTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT) {
                tyrePreLeftFrontTv.setText(String.valueOf(value) + "kPa");
            }
            if (tyrePreRightFrontTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT) {
                tyrePreRightFrontTv.setText(String.valueOf(value) + "kPa");
            }
            if (tyrePreLeftRearTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR) {
                tyrePreLeftRearTv.setText(String.valueOf(value) + "kPa");
            }
            if (tyrePreRightRearTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR) {
                tyrePreRightRearTv.setText(String.valueOf(value) + "kPa");
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
            if (area == BYDAutoAcDevice.AC_TEMPERATURE_MAIN) {
                if (currentTemperatureTv != null) {
                    currentTemperatureTv.setText(String.valueOf(value));
                }
            }
        }

        @Override
        public void onAcCtrlModeChanged(int mode) {
            super.onAcCtrlModeChanged(mode);
            if (acControlModeBtn != null) {
                if (mode == BYDAutoAcDevice.AC_CTRLMODE_AUTO) {
                    acControlModeBtn.setText("自动");
                } else if (mode == BYDAutoAcDevice.AC_CTRLMODE_MANUAL) {
                    acControlModeBtn.setText("手动");
                } else {
                    acControlModeBtn.setText("mode:" + mode);
                }
            }
        }

        @Override
        public void onAcStarted() {
            super.onAcStarted();
            if (acOnOffBtn != null) {
                acOnOffBtn.setText("开启");
            }
        }

        @Override
        public void onAcStoped() {
            super.onAcStoped();
            if (acOnOffBtn != null) {
                acOnOffBtn.setText("关闭");
            }
        }

        @Override
        public void onAcCycleModeChanged(int mode) {
            super.onAcCycleModeChanged(mode);
            if (acCycleModeBtn != null) {
                if (mode == BYDAutoAcDevice.AC_CYCLEMODE_OUTLOOP) {
                    acCycleModeBtn.setText("外循环");
                } else if (mode == BYDAutoAcDevice.AC_CYCLEMODE_INLOOP) {
                    acCycleModeBtn.setText("内循环");
                } else {
                    acCycleModeBtn.setText("mode:" + mode);
                }
            }
        }

        @Override
        public void onAcCompressorModeChanged(int mode) {
            super.onAcCompressorModeChanged(mode);
            if (acCompressorBtn != null) {
                if (mode == BYDAutoAcDevice.AC_COMPRESSOR_OFF) {
                    acCompressorBtn.setTextColor(getColor(android.R.color.secondary_text_dark));
                } else if (mode == BYDAutoAcDevice.AC_COMPRESSOR_ON) {
                    acCompressorBtn.setTextColor(getColor(android.R.color.secondary_text_light));
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
            if (energyModeTv != null) {
                energyModeTv.setText(StringUtil.getEnergyModeName(energyMode));
            }

//            updateEngineSpeedData();

            if (engineSpeedEsv != null) {
                if (energyMode == BYDAutoEnergyDevice.ENERGY_MODE_EV || energyMode == BYDAutoEnergyDevice.ENERGY_MODE_FORCE_EV) {
                    engineSpeedEsv.setVisibility(View.GONE);
                } else {
                    engineSpeedEsv.setVisibility(View.VISIBLE);
                }
            }
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

        /**
         * 原地踩油门发电状态
         *
         * @param mode
         */
        @Override
        public void onPowerGenerationStateChanged(int mode) {
            super.onPowerGenerationStateChanged(mode);
            //发电中
            if (mode == BYDAutoEnergyDevice.ENERGY_POWER_GENERATING) {

            } else {

            }
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
            updateEngineSpeedData();
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
            BYDAutoStatisticDeviceHelper statisticDeviceHelper = BYDAutoStatisticDeviceHelper.getInstance(statisticDevice);
            //里程1
            if (customMileage1Tv != null) {
                customMileage1Tv.setText(format.format(statisticDeviceHelper.getMileageNumber(0)));
            }

            //里程2
            if (customMileage2Tv != null) {
                customMileage2Tv.setText(format.format(statisticDeviceHelper.getMileageNumber(1)));
            }
            //总EV里程
//            if (totalEvMileageTv != null) {
//                totalEvMileageTv.setText(String.valueOf(statisticDevice.getEVMileageValue()));
//            }
            //总HEV里程
            int hevMileageValue = statisticDeviceHelper.getHEVMileageValue();
            if (totalHevMileageTv != null) {
                totalHevMileageTv.setText(String.valueOf(hevMileageValue));
            }
            //更新单次行程能耗数据
            int evMileageValue = statisticDevice.getEVMileageValue();
            double totalElecConValue = statisticDevice.getTotalElecConValue();
            double totalFuelConValue = statisticDevice.getTotalFuelConValue();

            //本次行程总里程
            int total_mileage = totalMileageValue - init_totalMileageValue;
            if (currentTravelMileageTv != null) {
                currentTravelMileageTv.setText(String.valueOf(total_mileage));
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
            String cost = format.format(elec_cost) + "KWH+" + format.format(fuel_cost) + "L";
            if (currentTravelEnergyCostTv != null) {
                currentTravelEnergyCostTv.setText(cost);
            }
            //本次行程花费
            if (currentTravelYuanCostTv != null) {
                double yuan = elec_cost * init_latest_electric_price + fuel_cost * init_latest_fuel_price;
                currentTravelYuanCostTv.setText(format.format(yuan));
            }
            //本次行程平均电耗(kwh/100km)
            if (currentTravelElecCostTv != null && ev_mileage != 0) {
                currentTravelElecCostTv.setText(format.format(elec_cost * 100 / ev_mileage));
            }
            //本次行程平均油耗
            if (currentTravelFuelCostTv != null && hev_mileage != 0) {
                currentTravelFuelCostTv.setText(format.format(fuel_cost * 100 / hev_mileage));
            }
            updateWaterTemperature();
        }

        /**
         * 监听燃油消耗总量变化
         * @param value
         */
        @Override
        public void onTotalFuelConChanged(double value) {
            super.onTotalFuelConChanged(value);
            if (totalFuelCostTv != null) {
                totalFuelCostTv.setText(format.format(value));
            }
        }

        /**
         * 监听电消耗总量的变化
         * @param value
         */
        @Override
        public void onTotalElecConChanged(double value) {
            super.onTotalElecConChanged(value);
            if (totalElecCostTv != null) {
                totalElecCostTv.setText(format.format(value));
            }
        }

        /**
         * 监听最近百公里油耗变化
         * @param value {0,51.1}L/100KM
         */
        @Override
        public void onLastFuelConPHMChanged(double value) {
            super.onLastFuelConPHMChanged(value);
            if (lastFuelConPhmTv != null) {
                lastFuelConPhmTv.setText(format.format(value));
            }
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
            if (lastElecConPhmTv != null) {
                lastElecConPhmTv.setText(format.format(value));
            }
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
            if (value >= 2046) {
                fuelMileageTv.setText("--");
            } else {
                fuelMileageTv.setText(String.valueOf(value));
            }
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
            if (totalEvMileageTv != null) {
                totalEvMileageTv.setText(String.valueOf(value));
            }
        }

        public void onHEVMileageValueChanged(int value) {
            KLog.e("onHEVMileageValueChanged = " + value);
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
            KLog.e("onWaterTemperatureChanged = " + value);
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

        /**
         * 总行驶时间
         * @param value
         */
        @Override
        public void onDrivingTimeChanged(double value) {
            super.onDrivingTimeChanged(value);
            updateDrivingTime();
        }
    };

    private void updateFrontMotorSpeed() {
        int front_motor_speed = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_FRONT_MOTOR_SPEED);
        if (front_motor_speed == 1) {
            front_motor_speed = 0;
        }
        if (frontMotorSpeedTv != null) {
            frontMotorSpeedTv.setText(String.valueOf(Math.abs(front_motor_speed)));
        }
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
        if (engine_speed_gb > 0 && engine_speed_gb <= 8000) {
            updateEngineSpeedUI(engine_speed_gb);
        } else if (engine_speed > 0 && engine_speed <= 8000) {
            updateEngineSpeedUI(engine_speed);
        } else {
            updateEngineSpeedUI(0);
        }

        int front_motor_speed = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_FRONT_MOTOR_SPEED);
        int front_motor_torque = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_FRONT_MOTOR_TORQUE);

        int rear_motor_speed = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_REAR_MOTOR_SPEED);
        int rear_motor_torque = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_REAR_MOTOR_TORQUE);

        if (engineSpeedGbTv != null) {
            if (engine_speed_gb == 1) {
                engine_speed_gb = 0;
            }
            if (engine_speed_gb >= 8000) {
                engine_speed_gb = 0;
            }
            engineSpeedGbTv.setText(String.valueOf(engine_speed_gb));
        }
        if (engineSpeedWarningTv != null) {
            if (engine_speed_warning == 1) {
                engine_speed_warning = 0;
            }
            if (engine_speed_warning >= 8000) {
                engine_speed_warning = 0;
            }
            engineSpeedWarningTv.setText(String.valueOf((engine_speed_warning)));
        }
        if (frontMotorSpeedTv != null) {
            if (front_motor_speed == 1) {
                front_motor_speed = 0;
            }
            if (front_motor_speed >= 8000) {
                front_motor_speed = 0;
            }
            frontMotorSpeedTv.setText(String.valueOf(Math.abs(front_motor_speed)));
        }
        if (frontMotorTorqueTv != null) {
            frontMotorTorqueTv.setText(String.valueOf(getValidTorqueValue(front_motor_torque)));
        }
        if (rearMotorSpeedTv != null) {
            if (rear_motor_speed == 1) {
                rear_motor_speed = 0;
            }
            if (rear_motor_speed >= 8000) {
                rear_motor_speed = 0;
            }
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
            double travelTime = BYDAutoStatisticDeviceHelper.getInstance(statisticDevice).getTravelTime(1);
            drivingTimeTv.setText(format.format(travelTime));
        }
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
            if (carSpeedTv != null) {
                carSpeedTv.setText(format.format(currentSpeed));
            }
            if (carSpeedCsv != null) {
                carSpeedCsv.setVelocity(((int) currentSpeed));
            }
            updateEngineSpeedData();
            updateEnginePower();
            updateFrontMotorSpeed();
            updateWaterTemperature();

            BYDAutoStatisticDeviceHelper statisticDeviceHelper = BYDAutoStatisticDeviceHelper.getInstance(statisticDevice);
            if (batterSocTv != null) {
                batterSocTv.setText(String.valueOf(statisticDeviceHelper.getESTIMATE_SOC_V1()));
            }
            if (lowestBatterTempTv != null) {
                lowestBatterTempTv.setText(String.valueOf(statisticDeviceHelper.getLOWEST_BATTERY_TEMP()));
            }
            if (highestBatterTempTv != null) {
                highestBatterTempTv.setText(String.valueOf(statisticDeviceHelper.getHIGHEST_BATTERY_TEMP()));
            }
            if (averageBatterTempTv != null) {
                averageBatterTempTv.setText(String.valueOf(statisticDeviceHelper.getAVERAGE_BATTERY_TEMP()));
            }
        }

        /**
         * 监听油门深度变化[0-100]%
         * @param value
         */
        @Override
        public void onAccelerateDeepnessChanged(int value) {
            super.onAccelerateDeepnessChanged(value);

//            updateEngineSpeedData();

            if (youMengPb != null && youMengTv != null) {
                youMengPb.setProgress(value);
                youMengTv.setText(String.valueOf(value));
            }
        }

        /**
         * 监听制动深度变化[0-100]%
         * @param value
         */
        @Override
        public void onBrakeDeepnessChanged(int value) {
            super.onBrakeDeepnessChanged(value);
            if (shaChePb != null || shaCheTv != null) {
                shaChePb.setProgress(value);
                shaCheTv.setText(String.valueOf(value));
            }
        }

    };

    private void updateEngineSpeedUI(int engineSpeed) {
        if (engineSpeed >= 8191) {
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
            Uri uri = Uri.parse("https://pan.baidu.com/s/1HH9eXbn2hWWwIhCYNwGyJg?pwd=gaqe");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
//        else if (item.getItemId() == R.id.float_show) {
//            startService(new Intent(this, EngineSpeedFloatingService.class));
//        }
        else if (item.getItemId() == R.id.settings) {
            startActivity(new Intent(this, SettingsActivity.class));
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
            int waterTemperature = BYDAutoStatisticDeviceHelper.getInstance(statisticDevice).getWaterTemperature();
            if (waterTemperature == BYDAutoStatisticDeviceHelper.INVALID_DATA_1) {
                waterTemperatureTv.setText("--");
            } else {
                waterTemperatureTv.setText(String.valueOf(waterTemperature));
            }
        }
    }

    /**
     * 瞬时电耗
     */
    private void updateInstantElecCon() {
        if (instantElecConTv != null) {
            double instantElecConValue = BYDAutoStatisticDeviceHelper.getInstance(statisticDevice).getInstantElecConValue();
            if (instantElecConValue == -1) {
                instantElecConValue = 0;
            }
            instantElecConTv.setText(format.format(instantElecConValue));
        }
    }

    /**
     * 瞬时油耗
     */
    private void updateInstantFuelCon() {
        if (instantFuelConTv != null) {
            instantFuelConTv.setText(format.format(BYDAutoStatisticDeviceHelper.getInstance(statisticDevice).getInstantFuelConValue()));
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
        //车型代号
        if (autoTypeTv != null) {
            autoTypeTv.setText(BydApi29Helper.getAutoType(bodyworkDevice));
        }
        //蓄电池？待验证
        updateBatteryPower();
        //电源档位
        absBYDAutoBodyworkListener.onPowerLevelChanged(bodyworkDevice.getPowerLevel());

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
            if (bydAutoAcDevice != null) {
                int acStartState = bydAutoAcDevice.getAcStartState();
                if (acStartState == BYDAutoAcDevice.AC_POWER_ON) {
                    bydAutoAcDevice.stop(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY);
                } else if (acStartState == BYDAutoAcDevice.AC_POWER_OFF) {
                    bydAutoAcDevice.start(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY);
                }
            }
            return;
        }
        if (vId == R.id.ac_cycle_mode_btn) {
            if (bydAutoAcDevice != null) {
                int acCycleMode = bydAutoAcDevice.getAcCycleMode();
                if (acCycleMode == BYDAutoAcDevice.AC_CYCLEMODE_OUTLOOP) {
                    bydAutoAcDevice.setAcCycleMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_CYCLEMODE_INLOOP);
                } else if (acCycleMode == BYDAutoAcDevice.AC_CYCLEMODE_INLOOP) {
                    bydAutoAcDevice.setAcCycleMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_CYCLEMODE_OUTLOOP);
                }
            }
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
            return;
        }
        if (bydAutoAcDevice != null) {
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
                if (acWindLevel == BYDAutoAcDevice.AC_WINDLEVEL_0) {
                    bydAutoAcDevice.stopRearAc(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY);
                    int ret = bydAutoAcDevice.stop(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY);
                    Toast.makeText(this, getCommandRet(ret), Toast.LENGTH_SHORT).show();
                    return;
                }
                bydAutoAcDevice.setAcWindLevel(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, acWindLevel - 1);
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

    private final AbsBYDAutoInstrumentListener instrumentListener = new AbsBYDAutoInstrumentListener() {

        /**
         * 水温计百分比变化(0-100)
         * @param percent
         */
        public void onWaterTempMeterPercentChanged(double percent) {
            if (waterTemperaturePercentTv != null) {
                waterTemperaturePercentTv.setText(format.format(percent));
            }
        }

        @Override
        public void onMalfunctionInfoChanged(int typeName, int hasMalfunction) {
            super.onMalfunctionInfoChanged(typeName, hasMalfunction);
            //BYDAutoFeatureIds.INSTRUMENT_HIGH_WATER_TEMPERATURE
            if (typeName == BYDAutoInstrumentDevice.MALFUNCTION_HIGH_WATER_TEMPERATURE) {

            }
        }

        @Override
        public void onMaintenanceInfoChanged(int typeName, int infoValue) {
            super.onMaintenanceInfoChanged(typeName, infoValue);
        }

        @Override
        public void onAlarmBuzzleStateChange(int state) {
            super.onAlarmBuzzleStateChange(state);
        }

        @Override
        public void onExternalChargingPowerChanged(double value) {
            super.onExternalChargingPowerChanged(value);
            String va = MainActivity.format.format(value);
            if (externalChargingPowerTv != null) {
                externalChargingPowerTv.setText(va);
            }
        }
    };
}