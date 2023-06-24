package android.hardware.bydauto.doormirror;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoRearViewMirrorListener implements IBYDAutoListener {
    private static final boolean DEBUG = false;
    protected static final String TAG = "AbsBYDRearViewMirrorListener";

    public void onAutoExternalRearMirrorStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onAutoExternalRearMirrorAntiglareStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    private RearViewMirrorMessageData parse(Object obj) {
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

    @Keep
    private class RearViewMirrorMessageData {
        private RearViewMirrorMessageData() {
            throw new RuntimeException("Stub!");
        }
    }
}