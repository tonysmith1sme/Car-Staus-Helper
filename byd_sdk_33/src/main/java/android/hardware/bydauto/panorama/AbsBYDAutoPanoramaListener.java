//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.panorama;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoPanoramaListener implements IBYDAutoListener {
    public AbsBYDAutoPanoramaListener() {
        throw new RuntimeException("Stub!");
    }

    public void onPanoWorkStateChanged(int mode) {
        throw new RuntimeException("Stub!");
    }

    public void onPanOutputStateChanged(int mode) {
        throw new RuntimeException("Stub!");
    }

    public void onBackLineConfigChanged(int mode) {
        throw new RuntimeException("Stub!");
    }

    public void onPanoramaOnlineStateChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onPanoRotationChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onDisplayModeChanged(int mode) {
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
