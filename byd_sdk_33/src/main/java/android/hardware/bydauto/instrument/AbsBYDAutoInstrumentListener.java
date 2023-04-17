//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.instrument;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoInstrumentListener implements IBYDAutoListener {
    private static final boolean DEBUG = true;
    protected static final String TAG = "AbsBYDAutoInstrumentListener";

    public void onMalfunctionInfoChanged(int typeName, int hasMalfunction) {
    }

    public void onMalfunctionInfoChanged2(int typeName, int hasMalfunction) {
    }

    public void onBacklightModeStateChanged(int backlightMode, int state) {
    }

    public void onBacklightBrightnessChanged(int brightness) {
    }

    public void onUnitChanged(int unitName, int unitValue) {
    }

    public void onMaintenanceInfoChanged(int typeName, int infoValue) {
    }

    public void onMusicInfoResultChanged(int result) {
    }

    public void onCallInfoResultChanged(int result) {
    }

    public void onRadioInfoResultChanged(int result) {
    }

    public void onAlarmBuzzleStateChange(int state) {
    }

    public void onPowerOnErrInfoChanged(int err) {
    }

    public void onPowerOffErrInfoChanged(int err) {
    }

    public void onRemoteDrivingReminderChanged(int value) {
    }

    public void onKeyDetectionReminderChanged(int value) {
    }

    public void onAverageSpeedChanged(int value) {
    }

    public void onExternalChargingPowerChanged(double value) {
    }

    public void onInstrumentScreenTypeChanged(int value) {
    }

    public void onNaviDestinationCommandChanged(int command) {
    }

    public void onRoadNameCheckStateChanged(int state) {
    }

    public void onTextInfoChanged(int value) {
    }

    public void onModuleStateChanged(int module, int state) {
    }

    public void onDoorStateChanged(int area, int state) {
    }

    public void onSafetyBeltStatusChanged(int area, int state) {
    }

    public void onWheelColorChanged(int position, int state) {
    }

    public void onWheelTemperatureColorChanged(int position, int state) {
    }

    public void onWheelPressureChanged(int position, int value) {
    }

    public void onWheelTemperatureChanged(int position, int value) {
    }

    public void onDeviationStateChanged(int state) {
    }

    public void onGapDetectionChanged(int state) {
    }

    public void onLaneLineStateChanged(int state) {
    }

    public void onTimeIntervalStateChanged(int state) {
    }

    public void onEnergyFeedbackChanged(int state) {
    }

    public void onTextColorChanged(int state) {
    }

    public void onSpacingStateChanged(int state) {
    }

    public void onSoundTypeChanged(int state) {
    }

    public void onAccCruisingSpeedChanged(int state) {
    }

    public void onAccCruisingSpeedColorChanged(int state) {
    }

    public void onPCWAlarmInstructionChanged(int state) {
    }

    public void onLaneLineColorChanged(int state) {
    }

    public void onTotalMileageChanged(int state) {
    }

    public void onMileageUnitChanged(int state) {
    }

    public void onLast50KmPowerConsumeChanged(double state) {
    }

    public void onSpeedUnitChanged(int state) {
    }

    public void onBatteryPercentChanged(int state) {
    }

    public void onExternalChargePowerChanged(double state) {
    }

    public void onTravelTimeChanged(double state) {
    }

    public void onPowerUnitChanged(int state) {
    }

    public void onAirHeatOilDisplayChanged(int state) {
    }

    public void onChargeDisplayChanged(int value) {
    }

    public void onChargePercentChanged(int value) {
    }

    public void onChargePowerChanged(double value) {
    }

    public void onChargeNoticeChanged(int value) {
    }

    public void onChargeRestTimeChanged(int[] resetTime) {
    }

    public void onExpectChargeStateChanged(int value) {
    }

    public void onExpectChargeDisplayChanged(int value) {
    }

    public void onACCIndicateLightStateChanged(int state) {
    }

    public void onACCIndicateLightColorChanged(int color) {
    }

    public void onAccCruisingSpeedValueChanged(int value) {
    }

    public void onOilLevelAlarmIndicatorChanged(int value) {
    }

    public void onOilLevelAlarmIndicatorColorChanged(int value) {
    }

    public void onOutCarTemperatureChanged(int value) {
    }

    public void onLinkErrKeyTimeChanged(int flag, int value) {
    }

    public void onSRSFaultWarningLightChanged(int value) {
    }

    public void onSRSFaultWarningLightColorChanged(int value) {
    }

    public void onABSFaultWarningLightChanged(int value) {
    }

    public void onABSFaultWarningLightColorChanged(int value) {
    }

    public void onBrakeSysFaultLightStateChanged(int value) {
    }

    public void onBrakeSysFaultLightColorChanged(int value) {
    }

    public void onCoolantTempHighWarnLightStateChanged(int value) {
    }

    public void onCoolantTempHighWarnLightColorChanged(int value) {
    }

    public void onELECParkingStateChanged(int value) {
    }

    public void onELECParkingColorChanged(int value) {
    }

    public void onEngineFailWarnLightStateChanged(int value) {
    }

    public void onEngineFailWarnLightColorChanged(int value) {
    }

    public void onESPFailWarnLightStateChanged(int value) {
    }

    public void onESPFailWarnLightColorChanged(int value) {
    }

    public void onGPFIndicatorStateChanged(int value) {
    }

    public void onGPFIndicatorColorChanged(int value) {
    }

    public void onLowFuelWarnLightStateChanged(int value) {
    }

    public void onLowFuelWarnLightColorChanged(int value) {
    }

    public void onPressureWarnLightStateChanged(int value) {
    }

    public void onPressureWarnLightColorChanged(int value) {
    }

    public void onMainAlarmIndicatorStateChanged(int value) {
    }

    public void onMainAlarmIndicatorColorChanged(int value) {
    }

    public void onPressureSupplySysFailWarnLightStateChanged(int value) {
    }

    public void onPressureSupplySysFailWarnLightColorChanged(int value) {
    }

    public void onSmartKeySysWarnLightStateChanged(int value) {
    }

    public void onSmartKeySysWarnLightColorChanged(int value) {
    }

    public void onSteeringSYSFailWarnLightStateChanged(int value) {
    }

    public void onSteeringSYSFailWarnLightColorChanged(int value) {
    }

    public void onTyrePressureSYSFailWarnLightStateChanged(int value) {
    }

    public void onTyrePressureSYSFailWarnLightColorChanged(int value) {
    }

    public void onHeadlampFailWarnLightStateChanged(int value) {
    }

    public void onHeadlampFailWarnLightColorChanged(int value) {
    }

    public void onCruiseCtrlIndicatorStateChanged(int value) {
    }

    public void onCruiseCtrlIndicatorColorChanged(int value) {
    }

    public void onDishargeIndicatorStateChanged(int value) {
    }

    public void onDischargeIndicatorColorChanged(int value) {
    }

    public void onDrivePowerLimitIndicatorStateChanged(int value) {
    }

    public void onDrivePowerLimitIndicatorColorChanged(int value) {
    }

    public void onECOIndicatorStateChanged(int value) {
    }

    public void onECOIndicatorColorChanged(int value) {
    }

    public void onEVIndicatorStateChanged(int value) {
    }

    public void onEVIndicatorColorChanged(int value) {
    }

    public void onHEVIndicatorStateChanged(int value) {
    }

    public void onHEVIndicatorColorChanged(int value) {
    }

    public void onLowPowerBatteryWarnLightStateChanged(int value) {
    }

    public void onLowPowerBatteryWarnLightColorChanged(int value) {
    }

    public void onOKIndicatorStateChanged(int value) {
    }

    public void onOKIndicatorColorChanged(int value) {
    }

    public void onPowerBatteryChargeConnectIndicatorStateChanged(int value) {
    }

    public void onPowerBatteryChargeConnectIndicatorColorChanged(int value) {
    }

    public void onPowerBatteryHeatWarnLightStateChanged(int value) {
    }

    public void onPowerBatteryHeatWarnLightColorChanged(int value) {
    }

    public void onPowerBatFailWarnLightStateChanged(int value) {
    }

    public void onPowerBatFailWarnLightColorChanged(int value) {
    }

    public void onPowerSysFailWarnLightStateChanged(int value) {
    }

    public void onPowerSysFailWarnLightColorChanged(int value) {
    }

    public void onSportIndicatorStateChanged(int value) {
    }

    public void onSportIndicatorColorChanged(int value) {
    }

    public void onDischargeUiStateChanged(int value) {
    }

    public void onDischargeModeChanged(int mode) {
    }

    public void onDischargeElecEnergyChanged(double value) {
    }

    public void onDirectionInfoChanged(int value) {
    }

    public void onTyrePressureCarTypeChanged(int type) {
    }

    public void onMileageValidFlagChanged(int value) {
    }

    public void onDashboardAlarmStateChanged(int value) {
    }

    public void onCurrentJourneyDriveMileageChanged(double value) {
    }

    public void onCurrentJourneyDriveTimeChanged(double value) {
    }

    public void onCurrentDriveInterFaceChanged(int state) {
    }

    public void onOdometerDisplayChanged(int state) {
    }

    public void onAppCountdownHourChanged(int value) {
    }

    public void onAppCountdownMinuteChanged(int value) {
    }

    public void onViewStatusChanged(int state) {
    }

    public void onFirstMenuChanged(int value) {
    }

    public void onSecondMenuChanged(int value) {
    }

    public void onAirHeatingOilWarnChanged(int value) {
    }

    public void onChargeAppTimeOptionChanged(int state) {
    }

    public void on2in1AccDistanceChanged(int value) {
    }

    public void on2in1AccTextPromptChanged(int value) {
    }

    public void on2in1BodyPositionChanged(int value) {
    }

    public void onLineValueChanged(int flag, int value) {
    }

    public void on2in1AccWorkInterfaceChanged(int value) {
    }

    public void on2in1AccTimeDistanceChanged(int value) {
    }

    public void onSoundFreqChanged(int state) {
    }

    public void on2in1FaultSmallLightIndicatorChanged(int value) {
    }

    public void on2in1FaultSmallLightIndicatorColorChanged(int value) {
    }

    public void on2in1FaultFrontFogLightIndicatorChanged(int value) {
    }

    public void on2in1FaultFrontFogLightIndicatorColorChanged(int value) {
    }

    public void on2in1FaultGrassIndicatorChanged(int value) {
    }

    public void on2in1FaultGrassIndicatorColorChanged(int value) {
    }

    public void on2in1MenuStateChanged(int state) {
    }

    public void onAppointmentHourChanged(int value) {
    }

    public void onAppointmentMinuteChanged(int value) {
    }

    public void onInstrumentViewChanged(int value) {
    }

    public void onFaultMuddyIndicatorChanged(int value) {
    }

    public void onFaultMuddyIndicatorColorChanged(int value) {
    }

    public void onFaultNormalIndicatorChanged(int value) {
    }

    public void onFaultNormalIndicatorColorChanged(int value) {
    }

    public void onFaultOilLifeDetectIndicatorChanged(int value) {
    }

    public void onFaultOilLifeDetectIndicatorColorChanged(int value) {
    }

    public void onFaultSandIndicatorChanged(int value) {
    }

    public void onFaultSandIndicatorColorChanged(int value) {
    }

    public void on50KmEneryConsumptionDisplayStateChanged(int value) {
    }

    public void onAverageEneryConsumptionDisplayStateChanged(int value) {
    }

    public void onAverageFuelConsumptionDisplayStateChanged(int value) {
    }

    public void onFuelConsumptionDisplayStateChanged(int value) {
    }

    public void onInstantFuelConsumptionDisplayStateChanged(int value) {
    }

    public void onInstantFuelConsumptionUnitChanged(int value) {
    }

    public void onWaterTempMeterPercentChanged(double value) {
    }

    public void onAirHeatingDisplayStateChanged(int value) {
    }

    public void onDirectTypePressDisplayStateChanged(int value) {
    }

    public void on50KmFuelConsumptionDisplayStateChanged(int value) {
    }

    public void onEnergyDisplayChanged(int status) {
    }

    public void onFaultIndicatorChanged(int indicatorType, int value) {
    }

    public void onFaultIndicatorColorChanged(int indicatorType, int value) {
    }

    public void onFuelLowAlarmChanged(int value) {
    }

    private InstrumentMessageData parse(Object obj) {
        return null;
    }

    @Keep
    private class InstrumentMessageData {
        private InstrumentMessageData() {
        }
    }


    public void onError(int errCode, String errMessage) {
        throw new RuntimeException("Stub!");
    }

    public final void onDataChanged(IBYDAutoEvent event) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        throw new RuntimeException("Stub!");
    }
}
