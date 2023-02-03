package android.hardware.bydauto.doormirror;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* loaded from: classes.dex */
public abstract class AbsBYDAutoRearViewMirrorListener implements IBYDAutoListener {
    private static final boolean DEBUG = true;
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

    //    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        throw new RuntimeException("Stub!");
    }

    //    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        throw new RuntimeException("Stub!");
    }

    /* loaded from: classes.dex */
    private class RearViewMirrorMessageData {
        private RearViewMirrorMessageData() {
            throw new RuntimeException("Stub!");
        }
    }
}