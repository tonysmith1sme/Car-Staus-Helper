package com.huawei.carstatushelper.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener;
import android.hardware.bydauto.doorlock.BYDAutoDoorLockDevice;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.engine.AbsBYDAutoEngineListener;
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
import android.os.Bundle;
import android.widget.Toast;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.bean.CarStatusInfo;
import com.huawei.carstatushelper.byd_helper.BYDAutoStatisticDeviceHelper;
import com.huawei.carstatushelper.util.BydApi29Helper;
import com.huawei.carstatushelper.util.StringUtil;

/**
 * google android databinding 最佳实践
 */
public class CarStatusDetailActivity extends BackEnableBaseActivity {

    private ViewDataBinding binding;
    private CarStatusInfo carStatusInfo;
    private BYDAutoStatisticDevice statisticDevice;
    private BYDAutoBodyworkDevice bodyworkDevice;
    private BYDAutoEngineDevice engineDevice;
    private BYDAutoEnergyDevice energyDevice;
    private BYDAutoTyreDevice tyreDevice;
    private BYDAutoDoorLockDevice doorLockDevice;
    private BYDAutoSpeedDevice speedDevice;
    private BYDAutoInstrumentDevice instrumentDevice;
    private BYDAutoSettingDevice settingDevice;
    private BYDAutoGearboxDevice gearboxDevice;

    @Override
    public CharSequence setPageTitle() {
        return "车况详情";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_car_status_detail);
        carStatusInfo = new CarStatusInfo();
        binding.setVariable(R.id.data, carStatusInfo);

        if (getBaseContext().checkSelfPermission(Manifest.permission.BYDAUTO_BODYWORK_GET) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "权限不足", Toast.LENGTH_SHORT).show();
            return;
        }

        statisticDevice = BYDAutoStatisticDevice.getInstance(this);
        bodyworkDevice = BYDAutoBodyworkDevice.getInstance(this);
        engineDevice = BYDAutoEngineDevice.getInstance(this);
        energyDevice = BYDAutoEnergyDevice.getInstance(this);
        tyreDevice = BYDAutoTyreDevice.getInstance(this);
        doorLockDevice = BYDAutoDoorLockDevice.getInstance(this);
        speedDevice = BYDAutoSpeedDevice.getInstance(this);
        instrumentDevice = BYDAutoInstrumentDevice.getInstance(this);
        settingDevice = BYDAutoSettingDevice.getInstance(this);
        gearboxDevice = BYDAutoGearboxDevice.getInstance(this);

        statisticDevice.registerListener(statisticListener);
        bodyworkDevice.registerListener(bodyworkListener);
        engineDevice.registerListener(engineListener);
        energyDevice.registerListener(energyListener);
        tyreDevice.registerListener(tyreListener);
        doorLockDevice.registerListener(doorLockListener);
        speedDevice.registerListener(speedListener);
        instrumentDevice.registerListener(instrumentListener);
        settingDevice.registerListener(settingListener);
        gearboxDevice.registerListener(gearboxListener);

        initDeviceData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (statisticDevice == null) {
            return;
        }
        statisticDevice.unregisterListener(statisticListener);
        bodyworkDevice.unregisterListener(bodyworkListener);
        engineDevice.unregisterListener(engineListener);
        energyDevice.unregisterListener(energyListener);
        tyreDevice.unregisterListener(tyreListener);
        doorLockDevice.unregisterListener(doorLockListener);
        speedDevice.unregisterListener(speedListener);
        instrumentDevice.unregisterListener(instrumentListener);
        settingDevice.unregisterListener(settingListener);
        gearboxDevice.unregisterListener(gearboxListener);
    }

    private void initDeviceData() {
        String autoVIN = bodyworkDevice.getAutoVIN();//车架号
        String autoType = BydApi29Helper.getAutoType(bodyworkDevice);//车型代号
        String gearboxCode = gearboxDevice.getGearboxCode();//变速箱名称
        int gearboxType = gearboxDevice.getGearboxType();//变速箱类型
        String gearboxTypeName = StringUtil.getGearboxTypeName(gearboxType);
        int gearboxAutoModeType = gearboxDevice.getGearboxAutoModeType();//变速箱挡位

        int totalMileageValue = statisticDevice.getTotalMileageValue();
        BYDAutoStatisticDeviceHelper statisticDeviceHelper = BYDAutoStatisticDeviceHelper.getInstance(statisticDevice);
        double mileage1Number = statisticDeviceHelper.getMileageNumber(0);
        double mileage2Number = statisticDeviceHelper.getMileageNumber(1);
        int evMileageValue = statisticDevice.getEVMileageValue();
        int hevMileageValue = statisticDeviceHelper.getHEVMileageValue();
        if (hevMileageValue == 0) {
            hevMileageValue = totalMileageValue - evMileageValue;
        }
        double drivingTimeValue = statisticDevice.getDrivingTimeValue();

        int fuelPercentageValue = statisticDevice.getFuelPercentageValue();
        int fuelDrivingRangeValue = statisticDevice.getFuelDrivingRangeValue();
        double elecPercentageValue = statisticDevice.getElecPercentageValue();
        int elecDrivingRangeValue = statisticDevice.getElecDrivingRangeValue();

        int tyrePressureValueLeftFront = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT);
        int tyrePressureValueRightFront = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT);
        int tyrePressureValueLeftRear = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR);
        int tyrePressureValueRightRear = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR);

        double totalElecConValue = statisticDevice.getTotalElecConValue();
        double totalFuelConValue = statisticDevice.getTotalFuelConValue();
        double instantFuelConValue = statisticDeviceHelper.getInstantFuelConValue();
        double instantElecConValue = statisticDeviceHelper.getInstantElecConValue();

        double lastElecConPHMValue = statisticDevice.getLastElecConPHMValue();
        double lastFuelConPHMValue = statisticDevice.getLastFuelConPHMValue();
        double totalElecConPHMValue = statisticDevice.getTotalElecConPHMValue();
        double totalFuelConPHMValue = statisticDevice.getTotalFuelConPHMValue();

        carStatusInfo.vin = autoVIN;
        binding.setVariable(R.id.data, carStatusInfo);
    }

    private final AbsBYDAutoStatisticListener statisticListener = new AbsBYDAutoStatisticListener() {
        /**
         * 监听总里程变化
         * @param value
         */
        @Override
        public void onTotalMileageValueChanged(int value) {
            super.onTotalMileageValueChanged(value);
        }

        /**
         * 监听燃油消耗总量变化
         * @param value
         */
        @Override
        public void onTotalFuelConChanged(double value) {
            super.onTotalFuelConChanged(value);
        }

        /**
         * 监听电消耗总量的变化
         * @param value
         */
        @Override
        public void onTotalElecConChanged(double value) {
            super.onTotalElecConChanged(value);
        }

        /**
         * 总行驶时间
         * @param value
         */
        @Override
        public void onDrivingTimeChanged(double value) {
            super.onDrivingTimeChanged(value);
        }

        /**
         * 监听最近百公里油耗变化
         * @param value
         */
        @Override
        public void onLastFuelConPHMChanged(double value) {
            super.onLastFuelConPHMChanged(value);
        }

        /**
         * 监听累计平均油耗变化
         * @param value
         */
        @Override
        public void onTotalFuelConPHMChanged(double value) {
            super.onTotalFuelConPHMChanged(value);
        }

        /**
         * 监听最近百公里电耗变化
         * @param value
         */
        @Override
        public void onLastElecConPHMChanged(double value) {
            super.onLastElecConPHMChanged(value);
        }

        /**
         * 监听累计平均电耗变化
         * @param value
         */
        @Override
        public void onTotalElecConPHMChanged(double value) {
            super.onTotalElecConPHMChanged(value);
        }

        /**
         * 监听电续航里程变化
         * @param value
         */
        @Override
        public void onElecDrivingRangeChanged(int value) {
            super.onElecDrivingRangeChanged(value);
        }

        /**
         * 监听燃油续航里程变化
         * @param value
         */
        @Override
        public void onFuelDrivingRangeChanged(int value) {
            super.onFuelDrivingRangeChanged(value);
        }

        /**
         * 监听燃油百分比变化
         * @param value
         */
        @Override
        public void onFuelPercentageChanged(int value) {
            super.onFuelPercentageChanged(value);
        }

        /**
         * 监听电量百分比变化
         * @param value
         */
        @Override
        public void onElecPercentageChanged(double value) {
            super.onElecPercentageChanged(value);
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
        }

        public void onHEVMileageValueChanged(int value) {

        }

        public void onWaterTemperatureChanged(int value) {

        }

        public void onInstantElecConChanged(double value) {

        }

        public void onInstantFuelConChanged(double value) {

        }
    };

    private final AbsBYDAutoBodyworkListener bodyworkListener = new AbsBYDAutoBodyworkListener() {
        @Override
        public void onWindowStateChanged(int area, int state) {
            super.onWindowStateChanged(area, state);
        }

        @Override
        public void onDoorStateChanged(int area, int state) {
            super.onDoorStateChanged(area, state);
        }

        @Override
        public void onAutoSystemStateChanged(int state) {
            super.onAutoSystemStateChanged(state);
        }

        @Override
        public void onSteeringWheelValueChanged(int type, double value) {
            super.onSteeringWheelValueChanged(type, value);
        }

        @Override
        public void onPowerLevelChanged(int level) {
            super.onPowerLevelChanged(level);
        }

        @Override
        public void onBatteryVoltageLevelChanged(int level) {
            super.onBatteryVoltageLevelChanged(level);
        }

        @Override
        public void onMoonRoofConfigChanged(int config) {
            super.onMoonRoofConfigChanged(config);
        }

        @Override
        public void onFuelElecLowPowerChanged(int state) {
            super.onFuelElecLowPowerChanged(state);
        }

        @Override
        public void onAlarmStateChanged(int state) {
            super.onAlarmStateChanged(state);
        }

        @Override
        public void onWindowOpenPercentChanged(int area, int percent) {
            super.onWindowOpenPercentChanged(area, percent);
        }
    };


    private final AbsBYDAutoEngineListener engineListener = new AbsBYDAutoEngineListener() {
        @Override
        public void onEngineSpeedChanged(int value) {
            super.onEngineSpeedChanged(value);
        }

        @Override
        public void onEngineCoolantLevelChanged(int state) {
            super.onEngineCoolantLevelChanged(state);
        }

        @Override
        public void onOilLevelChanged(int value) {
            super.onOilLevelChanged(value);
        }
    };

    private final AbsBYDAutoEnergyListener energyListener = new AbsBYDAutoEnergyListener() {
        @Override
        public void onEnergyModeChanged(int mode) {
            super.onEnergyModeChanged(mode);
        }

        @Override
        public void onOperationModeChanged(int mode) {
            super.onOperationModeChanged(mode);
        }

        @Override
        public void onPowerGenerationStateChanged(int mode) {
            super.onPowerGenerationStateChanged(mode);
        }

        @Override
        public void onPowerGenerationValueChanged(int value) {
            super.onPowerGenerationValueChanged(value);
        }

        @Override
        public void onRoadSurfaceChanged(int type) {
            super.onRoadSurfaceChanged(type);
        }
    };

    private final AbsBYDAutoTyreListener tyreListener = new AbsBYDAutoTyreListener() {
        @Override
        public void onTyreSystemStateChanged(int state) {
            super.onTyreSystemStateChanged(state);
        }

        @Override
        public void onTyreTemperatureStateChanged(int state) {
            super.onTyreTemperatureStateChanged(state);
        }

        @Override
        public void onTyreBatteryStateChanged(int state) {
            super.onTyreBatteryStateChanged(state);
        }

        @Override
        public void onTyreAirLeakStateChanged(int area, int state) {
            super.onTyreAirLeakStateChanged(area, state);
        }

        @Override
        public void onTyreSignalStateChanged(int area, int state) {
            super.onTyreSignalStateChanged(area, state);
        }

        @Override
        public void onTyrePressureStateChanged(int area, int state) {
            super.onTyrePressureStateChanged(area, state);
        }

        @Override
        public void onTyrePressureValueChanged(int area, int value) {
            super.onTyrePressureValueChanged(area, value);
        }
    };

    private final AbsBYDAutoDoorLockListener doorLockListener = new AbsBYDAutoDoorLockListener() {
        @Override
        public void onDoorLockStatusChanged(int area, int state) {
            super.onDoorLockStatusChanged(area, state);
        }
    };

    private final AbsBYDAutoSpeedListener speedListener = new AbsBYDAutoSpeedListener() {
        @Override
        public void onSpeedChanged(double value) {
            super.onSpeedChanged(value);
        }

        @Override
        public void onAccelerateDeepnessChanged(int value) {
            super.onAccelerateDeepnessChanged(value);
        }

        @Override
        public void onBrakeDeepnessChanged(int value) {
            super.onBrakeDeepnessChanged(value);
        }
    };

    private final AbsBYDAutoInstrumentListener instrumentListener = new AbsBYDAutoInstrumentListener() {
        @Override
        public void onMalfunctionInfoChanged(int typeName, int hasMalfunction) {
            super.onMalfunctionInfoChanged(typeName, hasMalfunction);
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
        }
    };

    private final AbsBYDAutoSettingListener settingListener = new AbsBYDAutoSettingListener() {
        @Override
        public void onACBTWindSwitchChanged(int state) {
            super.onACBTWindSwitchChanged(state);
        }

        @Override
        public void onACTunnelCycleSwitchChanged(int state) {
            super.onACTunnelCycleSwitchChanged(state);
        }

        @Override
        public void onACPauseCycleSwitchChanged(int state) {
            super.onACPauseCycleSwitchChanged(state);
        }

        @Override
        public void onACAutoAirModeChanged(int state) {
            super.onACAutoAirModeChanged(state);
        }

        @Override
        public void onEnergyFeedbackStrengthChanged(int level) {
            super.onEnergyFeedbackStrengthChanged(level);
        }

        @Override
        public void onSOCTargetRangeChanged(int state) {
            super.onSOCTargetRangeChanged(state);
        }

        @Override
        public void onChargingPortSwitchChanged(int state) {
            super.onChargingPortSwitchChanged(state);
        }

        @Override
        public void onSteerAssisModeChanged(int state) {
            super.onSteerAssisModeChanged(state);
        }

        @Override
        public void onRearViewMirrorFlipSwitchChanged(int state) {
            super.onRearViewMirrorFlipSwitchChanged(state);
        }

        @Override
        public void onDriverSeatAutoReturnSwitchChanged(int state) {
            super.onDriverSeatAutoReturnSwitchChanged(state);
        }

        @Override
        public void onSteerPositionAutoReturnSwitchChanged(int state) {
            super.onSteerPositionAutoReturnSwitchChanged(state);
        }

        @Override
        public void onPM25PowerSwitchChanged(int state) {
            super.onPM25PowerSwitchChanged(state);
        }

        @Override
        public void onPM25SwitchCheckChanged(int state) {
            super.onPM25SwitchCheckChanged(state);
        }

        @Override
        public void onPM25TimeCheckChanged(int state) {
            super.onPM25TimeCheckChanged(state);
        }

        @Override
        public void onControlWindowSwitchChanged(int state) {
            super.onControlWindowSwitchChanged(state);
        }

        @Override
        public void onLockCarRiseWindowChanged(int state) {
            super.onLockCarRiseWindowChanged(state);
        }

        @Override
        public void onBackHomeLightDelayValueChanged(int value) {
            super.onBackHomeLightDelayValueChanged(value);
        }

        @Override
        public void onLeftHomeLightDelayValueChanged(int value) {
            super.onLeftHomeLightDelayValueChanged(value);
        }

        @Override
        public void onLockOffDoorChanged(int state) {
            super.onLockOffDoorChanged(state);
        }

        @Override
        public void onRemoteControlUpwindowStateChanged(int state) {
            super.onRemoteControlUpwindowStateChanged(state);
        }

        @Override
        public void onMicroSwitchLockWindowStateChanged(int state) {
            super.onMicroSwitchLockWindowStateChanged(state);
        }

        @Override
        public void onRearAcOnlineStateChanged(int state) {
            super.onRearAcOnlineStateChanged(state);
        }

        @Override
        public void onBackDoorElectricModeChanged(int mode) {
            super.onBackDoorElectricModeChanged(mode);
        }

        @Override
        public void onFeatureChanged(String feature, int ifHas) {
            super.onFeatureChanged(feature, ifHas);
        }

        @Override
        public void onOverspeedLockStateChanged(int state) {
            super.onOverspeedLockStateChanged(state);
        }

        @Override
        public void onLanguageChanged(int value) {
            super.onLanguageChanged(value);
        }

        @Override
        public void onSafeWarnStateChanged(int state) {
            super.onSafeWarnStateChanged(state);
        }

        @Override
        public void onMaintainRemindStateChanged(int state) {
            super.onMaintainRemindStateChanged(state);
        }

        @Override
        public void onMicroSwitchUnlockWindowStateChanged(int state) {
            super.onMicroSwitchUnlockWindowStateChanged(state);
        }

        @Override
        public void onAutoExternalRearMirrorFollowUpSwitchChanged(int state) {
            super.onAutoExternalRearMirrorFollowUpSwitchChanged(state);
        }
    };

    private final AbsBYDAutoGearboxListener gearboxListener = new AbsBYDAutoGearboxListener() {
        @Override
        public void onGearboxAutoModeTypeChanged(int level) {
            super.onGearboxAutoModeTypeChanged(level);
        }

        @Override
        public void onGearboxManualModeLevelChanged(int level) {
            super.onGearboxManualModeLevelChanged(level);
        }

        @Override
        public void onBrakeFluidLevelChanged(int level) {
            super.onBrakeFluidLevelChanged(level);
        }

        @Override
        public void onParkBrakeSwitchChanged(int level) {
            super.onParkBrakeSwitchChanged(level);
        }

        @Override
        public void onBrakePedalStateChanged(int level) {
            super.onBrakePedalStateChanged(level);
        }
    };
}