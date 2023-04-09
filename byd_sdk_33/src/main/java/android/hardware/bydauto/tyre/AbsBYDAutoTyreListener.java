//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.tyre;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoTyreListener implements IBYDAutoListener {
    public AbsBYDAutoTyreListener() {
        throw new RuntimeException("Stub!");
    }

    public void onTyreSystemStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onTyreTemperatureStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onTyreBatteryStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onTyreAirLeakStateChanged(int area, int state) {
        throw new RuntimeException("Stub!");
    }

    public void onTyreSignalStateChanged(int area, int state) {
        throw new RuntimeException("Stub!");
    }

    public void onTyrePressureStateChanged(int area, int state) {
        throw new RuntimeException("Stub!");
    }

    public void onTyrePressureValueChanged(int area, int value) {
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
