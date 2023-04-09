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
}
