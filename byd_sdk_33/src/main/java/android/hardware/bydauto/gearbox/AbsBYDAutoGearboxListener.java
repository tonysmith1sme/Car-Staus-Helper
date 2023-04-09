//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.gearbox;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoGearboxListener implements IBYDAutoListener {
    public AbsBYDAutoGearboxListener() {
        throw new RuntimeException("Stub!");
    }

    public void onGearboxAutoModeTypeChanged(int level) {
        throw new RuntimeException("Stub!");
    }

    public void onGearboxManualModeLevelChanged(int level) {
        throw new RuntimeException("Stub!");
    }

    public void onBrakeFluidLevelChanged(int level) {
        throw new RuntimeException("Stub!");
    }

    public void onParkBrakeSwitchChanged(int level) {
        throw new RuntimeException("Stub!");
    }

    public void onBrakePedalStateChanged(int level) {
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
