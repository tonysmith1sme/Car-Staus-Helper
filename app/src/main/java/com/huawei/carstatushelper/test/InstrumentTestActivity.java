package com.huawei.carstatushelper.test;

import android.content.pm.PackageManager;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.huawei.carstatushelper.BR;
import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.databinding.ActivityInstrumentTestBinding;
import com.huawei.carstatushelper.util.BydManifest;
import com.socks.library.KLog;

import java.util.Arrays;

public class InstrumentTestActivity extends AppCompatActivity implements View.OnClickListener {
    private BYDAutoInstrumentDevice instrumentDevice;
    private com.huawei.carstatushelper.databinding.ActivityInstrumentTestBinding binding;
    private InstrumentDataHolder dataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInstrumentTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.setVariable(BR.listener, this);
        dataHolder = new InstrumentDataHolder();
        refreshData();
    }

    void refreshData() {
        binding.setVariable(BR.data, dataHolder);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.request_permission_btn:
                String[] permission = {BydManifest.permission.BYDAUTO_INSTRUMENT_GET, BydManifest.permission.BYDAUTO_INSTRUMENT_SET, BydManifest.permission.BYDAUTO_INSTRUMENT_COMMON};
                boolean granted = true;
                for (String per : permission) {
                    if (ContextCompat.checkSelfPermission(this, per) != PackageManager.PERMISSION_GRANTED) {
                        granted = false;
                        break;
                    }
                }
                if (!granted) {
                    ActivityCompat.requestPermissions(this, permission, 0);
                    return;
                }
                Toast.makeText(this, "权限授予成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.register_btn:
                instrumentDevice = BYDAutoInstrumentDevice.getInstance(this);
                instrumentDevice.registerListener(instrumentListener);
                break;
            case R.id.unregister_btn:
                instrumentDevice.unregisterListener(instrumentListener);
                break;
            default:
                break;
        }
    }

    private final AbsBYDAutoInstrumentListener instrumentListener = new AbsBYDAutoInstrumentListener() {
        @Override
        public void onMalfunctionInfoChanged(int typeName, int hasMalfunction) {
            super.onMalfunctionInfoChanged(typeName, hasMalfunction);
        }

        @Override
        public void onMalfunctionInfoChanged2(int typeName, int hasMalfunction) {
            super.onMalfunctionInfoChanged2(typeName, hasMalfunction);
        }

        @Override
        public void onBacklightModeStateChanged(int backlightMode, int state) {
            super.onBacklightModeStateChanged(backlightMode, state);
        }

        @Override
        public void onBacklightBrightnessChanged(int brightness) {
            super.onBacklightBrightnessChanged(brightness);
        }

        @Override
        public void onUnitChanged(int unitName, int unitValue) {
            super.onUnitChanged(unitName, unitValue);
        }

        @Override
        public void onMaintenanceInfoChanged(int typeName, int infoValue) {
            super.onMaintenanceInfoChanged(typeName, infoValue);
        }

        @Override
        public void onMusicInfoResultChanged(int result) {
            super.onMusicInfoResultChanged(result);
        }

        @Override
        public void onCallInfoResultChanged(int result) {
            super.onCallInfoResultChanged(result);
        }

        @Override
        public void onRadioInfoResultChanged(int result) {
            super.onRadioInfoResultChanged(result);
        }

        @Override
        public void onAlarmBuzzleStateChange(int state) {
            super.onAlarmBuzzleStateChange(state);
        }

        @Override
        public void onPowerOnErrInfoChanged(int err) {
            super.onPowerOnErrInfoChanged(err);
        }

        @Override
        public void onPowerOffErrInfoChanged(int err) {
            super.onPowerOffErrInfoChanged(err);
        }

        @Override
        public void onRemoteDrivingReminderChanged(int value) {
            super.onRemoteDrivingReminderChanged(value);
        }

        @Override
        public void onKeyDetectionReminderChanged(int value) {
            super.onKeyDetectionReminderChanged(value);
        }

        @Override
        public void onAverageSpeedChanged(int value) {
            super.onAverageSpeedChanged(value);
        }

        @Override
        public void onExternalChargingPowerChanged(double value) {
            super.onExternalChargingPowerChanged(value);
            dataHolder.setExternalChargingPower(value + "");
            refreshData();
        }

        @Override
        public void onInstrumentScreenTypeChanged(int value) {
            super.onInstrumentScreenTypeChanged(value);
        }

        @Override
        public void onNaviDestinationCommandChanged(int command) {
            super.onNaviDestinationCommandChanged(command);
        }

        @Override
        public void onRoadNameCheckStateChanged(int state) {
            super.onRoadNameCheckStateChanged(state);
        }

        @Override
        public void onTextInfoChanged(int value) {
            super.onTextInfoChanged(value);
        }

        @Override
        public void onModuleStateChanged(int module, int state) {
            super.onModuleStateChanged(module, state);
        }

        final int[] doorState = new int[8];

        @Override
        public void onDoorStateChanged(int area, int state) {
            super.onDoorStateChanged(area, state);
            doorState[area] = state;
            dataHolder.setDoorState(Arrays.toString(doorState));
            refreshData();
        }

        final int[] safetyBeltStatus = new int[8];

        @Override
        public void onSafetyBeltStatusChanged(int area, int state) {
            super.onSafetyBeltStatusChanged(area, state);
            safetyBeltStatus[area] = state;
            dataHolder.setSafetyBeltStatus(Arrays.toString(safetyBeltStatus));
            refreshData();
        }

        final int[] wheelColor = new int[8];

        @Override
        public void onWheelColorChanged(int position, int state) {
            super.onWheelColorChanged(position, state);
            wheelColor[position] = state;
            dataHolder.setWheelColor(Arrays.toString(wheelColor));
            refreshData();
        }

        final int[] wheelTemperatureColor = new int[8];

        @Override
        public void onWheelTemperatureColorChanged(int position, int state) {
            super.onWheelTemperatureColorChanged(position, state);
            wheelTemperatureColor[position] = state;
            dataHolder.setWheelTemperatureColor(Arrays.toString(wheelTemperatureColor));
            refreshData();
        }

        final int[] wheelPressure = new int[8];

        @Override
        public void onWheelPressureChanged(int position, int value) {
            super.onWheelPressureChanged(position, value);
            wheelPressure[position] = value;
            dataHolder.setWheelPressure(Arrays.toString(wheelPressure));
            refreshData();
        }

        final int[] wheelTemperature = new int[8];

        @Override
        public void onWheelTemperatureChanged(int position, int value) {
            super.onWheelTemperatureChanged(position, value);
            wheelTemperature[position] = value;
        }

        @Override
        public void onDeviationStateChanged(int state) {
            super.onDeviationStateChanged(state);
        }

        @Override
        public void onGapDetectionChanged(int state) {
            super.onGapDetectionChanged(state);
        }

        @Override
        public void onLaneLineStateChanged(int state) {
            super.onLaneLineStateChanged(state);
            dataHolder.setLaneLineState(state + "");
            refreshData();
        }

        @Override
        public void onTimeIntervalStateChanged(int state) {
            super.onTimeIntervalStateChanged(state);
        }

        @Override
        public void onEnergyFeedbackChanged(int state) {
            super.onEnergyFeedbackChanged(state);
        }

        @Override
        public void onTextColorChanged(int state) {
            super.onTextColorChanged(state);
        }

        @Override
        public void onSpacingStateChanged(int state) {
            super.onSpacingStateChanged(state);
        }

        @Override
        public void onSoundTypeChanged(int state) {
            super.onSoundTypeChanged(state);
        }

        @Override
        public void onAccCruisingSpeedChanged(int state) {
            super.onAccCruisingSpeedChanged(state);
            dataHolder.setAccCruisingSpeed(state + "");
            refreshData();
        }

        @Override
        public void onAccCruisingSpeedColorChanged(int state) {
            super.onAccCruisingSpeedColorChanged(state);
        }

        @Override
        public void onPCWAlarmInstructionChanged(int state) {
            super.onPCWAlarmInstructionChanged(state);
        }

        @Override
        public void onLaneLineColorChanged(int state) {
            super.onLaneLineColorChanged(state);
            dataHolder.setLaneLineColor(state + "");
            refreshData();
        }

        @Override
        public void onTotalMileageChanged(int state) {
            super.onTotalMileageChanged(state);
        }

        @Override
        public void onMileageUnitChanged(int state) {
            super.onMileageUnitChanged(state);
        }

        @Override
        public void onLast50KmPowerConsumeChanged(double state) {
            super.onLast50KmPowerConsumeChanged(state);
        }

        @Override
        public void onSpeedUnitChanged(int state) {
            super.onSpeedUnitChanged(state);
        }

        @Override
        public void onBatteryPercentChanged(int state) {
            super.onBatteryPercentChanged(state);
        }

        @Override
        public void onExternalChargePowerChanged(double state) {
            super.onExternalChargePowerChanged(state);
            dataHolder.setExternalChargingPower(state + "");
            refreshData();
        }

        @Override
        public void onTravelTimeChanged(double state) {
            super.onTravelTimeChanged(state);
        }

        @Override
        public void onPowerUnitChanged(int state) {
            super.onPowerUnitChanged(state);
        }

        @Override
        public void onAirHeatOilDisplayChanged(int state) {
            super.onAirHeatOilDisplayChanged(state);
        }

        @Override
        public void onChargeDisplayChanged(int value) {
            super.onChargeDisplayChanged(value);
        }

        @Override
        public void onChargePercentChanged(int value) {
            super.onChargePercentChanged(value);
        }

        @Override
        public void onChargePowerChanged(double value) {
            super.onChargePowerChanged(value);
        }

        @Override
        public void onChargeNoticeChanged(int value) {
            super.onChargeNoticeChanged(value);
        }

        @Override
        public void onChargeRestTimeChanged(int[] resetTime) {
            super.onChargeRestTimeChanged(resetTime);
        }

        @Override
        public void onExpectChargeStateChanged(int value) {
            super.onExpectChargeStateChanged(value);
        }

        @Override
        public void onExpectChargeDisplayChanged(int value) {
            super.onExpectChargeDisplayChanged(value);
        }

        @Override
        public void onACCIndicateLightStateChanged(int state) {
            super.onACCIndicateLightStateChanged(state);
        }

        @Override
        public void onACCIndicateLightColorChanged(int color) {
            super.onACCIndicateLightColorChanged(color);
        }

        @Override
        public void onAccCruisingSpeedValueChanged(int value) {
            super.onAccCruisingSpeedValueChanged(value);
        }

        @Override
        public void onOilLevelAlarmIndicatorChanged(int value) {
            super.onOilLevelAlarmIndicatorChanged(value);
        }

        @Override
        public void onOilLevelAlarmIndicatorColorChanged(int value) {
            super.onOilLevelAlarmIndicatorColorChanged(value);
        }

        @Override
        public void onOutCarTemperatureChanged(int value) {
            super.onOutCarTemperatureChanged(value);
            dataHolder.setOutCarTemperature(value + "");
            refreshData();
        }

        @Override
        public void onLinkErrKeyTimeChanged(int flag, int value) {
            super.onLinkErrKeyTimeChanged(flag, value);
        }

        @Override
        public void onSRSFaultWarningLightChanged(int value) {
            super.onSRSFaultWarningLightChanged(value);
        }

        @Override
        public void onSRSFaultWarningLightColorChanged(int value) {
            super.onSRSFaultWarningLightColorChanged(value);
        }

        @Override
        public void onABSFaultWarningLightChanged(int value) {
            super.onABSFaultWarningLightChanged(value);
        }

        @Override
        public void onABSFaultWarningLightColorChanged(int value) {
            super.onABSFaultWarningLightColorChanged(value);
        }

        @Override
        public void onBrakeSysFaultLightStateChanged(int value) {
            super.onBrakeSysFaultLightStateChanged(value);
        }

        @Override
        public void onBrakeSysFaultLightColorChanged(int value) {
            super.onBrakeSysFaultLightColorChanged(value);
        }

        @Override
        public void onCoolantTempHighWarnLightStateChanged(int value) {
            super.onCoolantTempHighWarnLightStateChanged(value);
        }

        @Override
        public void onCoolantTempHighWarnLightColorChanged(int value) {
            super.onCoolantTempHighWarnLightColorChanged(value);
        }

        @Override
        public void onELECParkingStateChanged(int value) {
            super.onELECParkingStateChanged(value);
        }

        @Override
        public void onELECParkingColorChanged(int value) {
            super.onELECParkingColorChanged(value);
        }

        @Override
        public void onEngineFailWarnLightStateChanged(int value) {
            super.onEngineFailWarnLightStateChanged(value);
        }

        @Override
        public void onEngineFailWarnLightColorChanged(int value) {
            super.onEngineFailWarnLightColorChanged(value);
        }

        @Override
        public void onESPFailWarnLightStateChanged(int value) {
            super.onESPFailWarnLightStateChanged(value);
        }

        @Override
        public void onESPFailWarnLightColorChanged(int value) {
            super.onESPFailWarnLightColorChanged(value);
        }

        @Override
        public void onGPFIndicatorStateChanged(int value) {
            super.onGPFIndicatorStateChanged(value);
        }

        @Override
        public void onGPFIndicatorColorChanged(int value) {
            super.onGPFIndicatorColorChanged(value);
        }

        @Override
        public void onLowFuelWarnLightStateChanged(int value) {
            super.onLowFuelWarnLightStateChanged(value);
        }

        @Override
        public void onLowFuelWarnLightColorChanged(int value) {
            super.onLowFuelWarnLightColorChanged(value);
        }

        @Override
        public void onPressureWarnLightStateChanged(int value) {
            super.onPressureWarnLightStateChanged(value);
        }

        @Override
        public void onPressureWarnLightColorChanged(int value) {
            super.onPressureWarnLightColorChanged(value);
        }

        @Override
        public void onMainAlarmIndicatorStateChanged(int value) {
            super.onMainAlarmIndicatorStateChanged(value);
        }

        @Override
        public void onMainAlarmIndicatorColorChanged(int value) {
            super.onMainAlarmIndicatorColorChanged(value);
        }

        @Override
        public void onPressureSupplySysFailWarnLightStateChanged(int value) {
            super.onPressureSupplySysFailWarnLightStateChanged(value);
        }

        @Override
        public void onPressureSupplySysFailWarnLightColorChanged(int value) {
            super.onPressureSupplySysFailWarnLightColorChanged(value);
        }

        @Override
        public void onSmartKeySysWarnLightStateChanged(int value) {
            super.onSmartKeySysWarnLightStateChanged(value);
        }

        @Override
        public void onSmartKeySysWarnLightColorChanged(int value) {
            super.onSmartKeySysWarnLightColorChanged(value);
        }

        @Override
        public void onSteeringSYSFailWarnLightStateChanged(int value) {
            super.onSteeringSYSFailWarnLightStateChanged(value);
        }

        @Override
        public void onSteeringSYSFailWarnLightColorChanged(int value) {
            super.onSteeringSYSFailWarnLightColorChanged(value);
        }

        @Override
        public void onTyrePressureSYSFailWarnLightStateChanged(int value) {
            super.onTyrePressureSYSFailWarnLightStateChanged(value);
        }

        @Override
        public void onTyrePressureSYSFailWarnLightColorChanged(int value) {
            super.onTyrePressureSYSFailWarnLightColorChanged(value);
        }

        @Override
        public void onHeadlampFailWarnLightStateChanged(int value) {
            super.onHeadlampFailWarnLightStateChanged(value);
        }

        @Override
        public void onHeadlampFailWarnLightColorChanged(int value) {
            super.onHeadlampFailWarnLightColorChanged(value);
        }

        @Override
        public void onCruiseCtrlIndicatorStateChanged(int value) {
            super.onCruiseCtrlIndicatorStateChanged(value);
            dataHolder.setCruiseCtrlIndicatorState(value + "");
            refreshData();
        }

        @Override
        public void onCruiseCtrlIndicatorColorChanged(int value) {
            super.onCruiseCtrlIndicatorColorChanged(value);
        }

        @Override
        public void onDishargeIndicatorStateChanged(int value) {
            super.onDishargeIndicatorStateChanged(value);
        }

        @Override
        public void onDischargeIndicatorColorChanged(int value) {
            super.onDischargeIndicatorColorChanged(value);
        }

        @Override
        public void onDrivePowerLimitIndicatorStateChanged(int value) {
            super.onDrivePowerLimitIndicatorStateChanged(value);
        }

        @Override
        public void onDrivePowerLimitIndicatorColorChanged(int value) {
            super.onDrivePowerLimitIndicatorColorChanged(value);
        }

        @Override
        public void onECOIndicatorStateChanged(int value) {
            super.onECOIndicatorStateChanged(value);
        }

        @Override
        public void onECOIndicatorColorChanged(int value) {
            super.onECOIndicatorColorChanged(value);
        }

        @Override
        public void onEVIndicatorStateChanged(int value) {
            super.onEVIndicatorStateChanged(value);
        }

        @Override
        public void onEVIndicatorColorChanged(int value) {
            super.onEVIndicatorColorChanged(value);
        }

        @Override
        public void onHEVIndicatorStateChanged(int value) {
            super.onHEVIndicatorStateChanged(value);
        }

        @Override
        public void onHEVIndicatorColorChanged(int value) {
            super.onHEVIndicatorColorChanged(value);
        }

        @Override
        public void onLowPowerBatteryWarnLightStateChanged(int value) {
            super.onLowPowerBatteryWarnLightStateChanged(value);
        }

        @Override
        public void onLowPowerBatteryWarnLightColorChanged(int value) {
            super.onLowPowerBatteryWarnLightColorChanged(value);
        }

        @Override
        public void onOKIndicatorStateChanged(int value) {
            super.onOKIndicatorStateChanged(value);
            dataHolder.setOKIndicatorState(value + "");
            refreshData();
        }

        @Override
        public void onOKIndicatorColorChanged(int value) {
            super.onOKIndicatorColorChanged(value);
        }

        @Override
        public void onPowerBatteryChargeConnectIndicatorStateChanged(int value) {
            super.onPowerBatteryChargeConnectIndicatorStateChanged(value);
        }

        @Override
        public void onPowerBatteryChargeConnectIndicatorColorChanged(int value) {
            super.onPowerBatteryChargeConnectIndicatorColorChanged(value);
        }

        @Override
        public void onPowerBatteryHeatWarnLightStateChanged(int value) {
            super.onPowerBatteryHeatWarnLightStateChanged(value);
        }

        @Override
        public void onPowerBatteryHeatWarnLightColorChanged(int value) {
            super.onPowerBatteryHeatWarnLightColorChanged(value);
        }

        @Override
        public void onPowerBatFailWarnLightStateChanged(int value) {
            super.onPowerBatFailWarnLightStateChanged(value);
        }

        @Override
        public void onPowerBatFailWarnLightColorChanged(int value) {
            super.onPowerBatFailWarnLightColorChanged(value);
        }

        @Override
        public void onPowerSysFailWarnLightStateChanged(int value) {
            super.onPowerSysFailWarnLightStateChanged(value);
        }

        @Override
        public void onPowerSysFailWarnLightColorChanged(int value) {
            super.onPowerSysFailWarnLightColorChanged(value);
        }

        @Override
        public void onSportIndicatorStateChanged(int value) {
            super.onSportIndicatorStateChanged(value);
        }

        @Override
        public void onSportIndicatorColorChanged(int value) {
            super.onSportIndicatorColorChanged(value);
        }

        @Override
        public void onDischargeUiStateChanged(int value) {
            super.onDischargeUiStateChanged(value);
        }

        @Override
        public void onDischargeModeChanged(int mode) {
            super.onDischargeModeChanged(mode);
        }

        @Override
        public void onDischargeElecEnergyChanged(double value) {
            super.onDischargeElecEnergyChanged(value);
        }

        @Override
        public void onDirectionInfoChanged(int value) {
            super.onDirectionInfoChanged(value);
            dataHolder.setDirectionInfo(value + "");
            refreshData();
        }

        @Override
        public void onTyrePressureCarTypeChanged(int type) {
            super.onTyrePressureCarTypeChanged(type);
        }

        @Override
        public void onMileageValidFlagChanged(int value) {
            super.onMileageValidFlagChanged(value);
        }

        @Override
        public void onDashboardAlarmStateChanged(int value) {
            super.onDashboardAlarmStateChanged(value);
        }

        @Override
        public void onCurrentJourneyDriveMileageChanged(double value) {
            super.onCurrentJourneyDriveMileageChanged(value);
            dataHolder.setCurrentJourneyDriveMileage(value + "");
            refreshData();
        }

        @Override
        public void onCurrentJourneyDriveTimeChanged(double value) {
            super.onCurrentJourneyDriveTimeChanged(value);
            dataHolder.setCurrentJourneyDriveTime(value + "");
            refreshData();
        }

        @Override
        public void onCurrentDriveInterFaceChanged(int state) {
            super.onCurrentDriveInterFaceChanged(state);
        }

        @Override
        public void onOdometerDisplayChanged(int state) {
            super.onOdometerDisplayChanged(state);
        }

        @Override
        public void onAppCountdownHourChanged(int value) {
            super.onAppCountdownHourChanged(value);
        }

        @Override
        public void onAppCountdownMinuteChanged(int value) {
            super.onAppCountdownMinuteChanged(value);
        }

        @Override
        public void onViewStatusChanged(int state) {
            super.onViewStatusChanged(state);
        }

        @Override
        public void onFirstMenuChanged(int value) {
            super.onFirstMenuChanged(value);
        }

        @Override
        public void onSecondMenuChanged(int value) {
            super.onSecondMenuChanged(value);
        }

        @Override
        public void onAirHeatingOilWarnChanged(int value) {
            super.onAirHeatingOilWarnChanged(value);
        }

        @Override
        public void onChargeAppTimeOptionChanged(int state) {
            super.onChargeAppTimeOptionChanged(state);
        }

        @Override
        public void on2in1AccDistanceChanged(int value) {
            super.on2in1AccDistanceChanged(value);
        }

        @Override
        public void on2in1AccTextPromptChanged(int value) {
            super.on2in1AccTextPromptChanged(value);
        }

        @Override
        public void on2in1BodyPositionChanged(int value) {
            super.on2in1BodyPositionChanged(value);
        }

        @Override
        public void onLineValueChanged(int flag, int value) {
            super.onLineValueChanged(flag, value);
            dataHolder.setLineValue(flag + " " + value);
            refreshData();
        }

        @Override
        public void on2in1AccWorkInterfaceChanged(int value) {
            super.on2in1AccWorkInterfaceChanged(value);
        }

        @Override
        public void on2in1AccTimeDistanceChanged(int value) {
            super.on2in1AccTimeDistanceChanged(value);
        }

        @Override
        public void onSoundFreqChanged(int state) {
            super.onSoundFreqChanged(state);
        }

        @Override
        public void on2in1FaultSmallLightIndicatorChanged(int value) {
            super.on2in1FaultSmallLightIndicatorChanged(value);
        }

        @Override
        public void on2in1FaultSmallLightIndicatorColorChanged(int value) {
            super.on2in1FaultSmallLightIndicatorColorChanged(value);
        }

        @Override
        public void on2in1FaultFrontFogLightIndicatorChanged(int value) {
            super.on2in1FaultFrontFogLightIndicatorChanged(value);
        }

        @Override
        public void on2in1FaultFrontFogLightIndicatorColorChanged(int value) {
            super.on2in1FaultFrontFogLightIndicatorColorChanged(value);
        }

        @Override
        public void on2in1FaultGrassIndicatorChanged(int value) {
            super.on2in1FaultGrassIndicatorChanged(value);
        }

        @Override
        public void on2in1FaultGrassIndicatorColorChanged(int value) {
            super.on2in1FaultGrassIndicatorColorChanged(value);
        }

        @Override
        public void on2in1MenuStateChanged(int state) {
            super.on2in1MenuStateChanged(state);
        }

        @Override
        public void onAppointmentHourChanged(int value) {
            super.onAppointmentHourChanged(value);
        }

        @Override
        public void onAppointmentMinuteChanged(int value) {
            super.onAppointmentMinuteChanged(value);
        }

        @Override
        public void onInstrumentViewChanged(int value) {
            super.onInstrumentViewChanged(value);
        }

        @Override
        public void onFaultMuddyIndicatorChanged(int value) {
            super.onFaultMuddyIndicatorChanged(value);
        }

        @Override
        public void onFaultMuddyIndicatorColorChanged(int value) {
            super.onFaultMuddyIndicatorColorChanged(value);
        }

        @Override
        public void onFaultNormalIndicatorChanged(int value) {
            super.onFaultNormalIndicatorChanged(value);
        }

        @Override
        public void onFaultNormalIndicatorColorChanged(int value) {
            super.onFaultNormalIndicatorColorChanged(value);
        }

        @Override
        public void onFaultOilLifeDetectIndicatorChanged(int value) {
            super.onFaultOilLifeDetectIndicatorChanged(value);
        }

        @Override
        public void onFaultOilLifeDetectIndicatorColorChanged(int value) {
            super.onFaultOilLifeDetectIndicatorColorChanged(value);
        }

        @Override
        public void onFaultSandIndicatorChanged(int value) {
            super.onFaultSandIndicatorChanged(value);
        }

        @Override
        public void onFaultSandIndicatorColorChanged(int value) {
            super.onFaultSandIndicatorColorChanged(value);
        }

        @Override
        public void on50KmEneryConsumptionDisplayStateChanged(int value) {
            super.on50KmEneryConsumptionDisplayStateChanged(value);
        }

        @Override
        public void onAverageEneryConsumptionDisplayStateChanged(int value) {
            super.onAverageEneryConsumptionDisplayStateChanged(value);
        }

        @Override
        public void onAverageFuelConsumptionDisplayStateChanged(int value) {
            super.onAverageFuelConsumptionDisplayStateChanged(value);
        }

        @Override
        public void onFuelConsumptionDisplayStateChanged(int value) {
            super.onFuelConsumptionDisplayStateChanged(value);
        }

        @Override
        public void onInstantFuelConsumptionDisplayStateChanged(int value) {
            super.onInstantFuelConsumptionDisplayStateChanged(value);
        }

        @Override
        public void onInstantFuelConsumptionUnitChanged(int value) {
            super.onInstantFuelConsumptionUnitChanged(value);
        }

        @Override
        public void onWaterTempMeterPercentChanged(double value) {
            super.onWaterTempMeterPercentChanged(value);
            dataHolder.setWaterTempMeterPercent(value+"");
            refreshData();
        }

        @Override
        public void onAirHeatingDisplayStateChanged(int value) {
            super.onAirHeatingDisplayStateChanged(value);
        }

        @Override
        public void onDirectTypePressDisplayStateChanged(int value) {
            super.onDirectTypePressDisplayStateChanged(value);
        }

        @Override
        public void on50KmFuelConsumptionDisplayStateChanged(int value) {
            super.on50KmFuelConsumptionDisplayStateChanged(value);
        }

        @Override
        public void onEnergyDisplayChanged(int status) {
            super.onEnergyDisplayChanged(status);
        }

        @Override
        public void onFaultIndicatorChanged(int indicatorType, int value) {
            super.onFaultIndicatorChanged(indicatorType, value);
        }

        @Override
        public void onFaultIndicatorColorChanged(int indicatorType, int value) {
            super.onFaultIndicatorColorChanged(indicatorType, value);
        }

        @Override
        public void onFuelLowAlarmChanged(int value) {
            super.onFuelLowAlarmChanged(value);
        }

        @Override
        public void onError(int errCode, String errMessage) {
            super.onError(errCode, errMessage);
        }

        @Override
        public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
            super.onDataEventChanged(eventType, eventValue);
            String builder = eventValue.intValue + "," + eventValue.floatValue + "," + eventValue.doubleValue + "," + Arrays.toString(eventValue.intArrayValue) + "," + Arrays.toString(eventValue.floatArrayValue) + "," + Arrays.toString(eventValue.bufferDataValue);
            KLog.e("onDataEventChanged 仪表信息" + eventType + " ,data = [" + builder + "]");
        }
    };
}