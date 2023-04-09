//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.energy;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoEnergyListener implements IBYDAutoListener {
    public AbsBYDAutoEnergyListener() {
        throw new RuntimeException("Stub!");
    }

    public void onEnergyModeChanged(int mode) {
        throw new RuntimeException("Stub!");
    }

    public void onOperationModeChanged(int mode) {
        throw new RuntimeException("Stub!");
    }

    public void onPowerGenerationStateChanged(int mode) {
        throw new RuntimeException("Stub!");
    }

    public void onPowerGenerationValueChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onRoadSurfaceChanged(int type) {
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
