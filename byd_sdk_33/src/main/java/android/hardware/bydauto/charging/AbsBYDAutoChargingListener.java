//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.charging;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoChargingListener implements IBYDAutoListener {
    public AbsBYDAutoChargingListener() {
        throw new RuntimeException("Stub!");
    }

    public void onChargerFaultStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onChargerWorkStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onChargingCapacityChanged(double value) {
        throw new RuntimeException("Stub!");
    }

    public void onChargingTypeChanged(int type) {
        throw new RuntimeException("Stub!");
    }

    public void onChargingRestTimeChanged(int hour, int min) {
        throw new RuntimeException("Stub!");
    }

    public void onChargingCapStateChanged(int type, int state) {
        throw new RuntimeException("Stub!");
    }

    public void onChargingPortLockRebackStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onDischargeRequestStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onChargerStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onChargingGunStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onChargingPowerChanged(double value) {
        throw new RuntimeException("Stub!");
    }

    public void onBatteryManagementDeviceStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onChargingScheduleEnableStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onChargingScheduleStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onChargingGunNotInsertedStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onChargingScheduleTimeChanged(int hour, int min) {
        throw new RuntimeException("Stub!");
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
    //-------------------sdk 29--------------------------
    public void onChargingStateChanged(int state) {
    }

    public void onChargingModeChanged(int mode) {
    }

    public void onChargingTimerInfoChanged(ChargingTimerInfo timerInfo) {
    }

    public void onWirelessChargingSwitchStateChanged(int state) {
    }

    public void onWirelessChargingOnline5sStateChanged(int state) {
    }

    public void onSmartChargingStateChanged(int state) {
    }

    public void onDischargeStateChanged(int type, int state) {
    }

    public void onDisChargeWarningStateChanged(int state) {
    }

    public void onFeatureChanged(String feature, int ifHas) {
    }

    public void onWirlessChargingStateChanged(int state) {
    }

    public void onChargeTempCtlStateChanged(int state) {
    }

    public void onBatteryTypeChanged(int type) {
    }

    public void onChargeStopSwitchStateChanged(int state) {
    }

    public void onChargeStopCapacityStateChanged(int state) {
    }

    public void onWeatherAndTimeRequestChanged(int state) {
    }

    public void onCarDischargeStateChanged(int state) {
    }

    public void onCarDischargeLowWarnChanged(int state) {
    }

    public void onVtovDischargeConnectStateChanged(int value) {
    }

    public void onVtovDischargeLimitValChanged(int value) {
    }

    public void onVtovDischargeLowestValChanged(int value) {
    }

    public void onVtovDischargeQuantityChanged(double value) {
    }

    public void onCapStateChanged(int value) {
    }

    public void onSocSaveSwitchChanged(int value) {
    }

    private ChargingMessageData parse(Object obj) {
        return null;
    }

    @Keep
    private class ChargingMessageData {
        private ChargingMessageData() {
        }
    }

}
