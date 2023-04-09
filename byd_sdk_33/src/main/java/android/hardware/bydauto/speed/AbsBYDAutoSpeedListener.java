//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.speed;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoSpeedListener implements IBYDAutoListener {
    public AbsBYDAutoSpeedListener() {
        throw new RuntimeException("Stub!");
    }

    public void onSpeedChanged(double value) {
        throw new RuntimeException("Stub!");
    }

    public void onAccelerateDeepnessChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onBrakeDeepnessChanged(int value) {
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
