//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.radar;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoRadarListener implements IBYDAutoListener {
    public AbsBYDAutoRadarListener() {
        throw new RuntimeException("Stub!");
    }

    public void onRadarProbeStateChanged(int area, int state) {
        throw new RuntimeException("Stub!");
    }

    public void onReverseRadarSwitchStateChanged(int state) {
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
