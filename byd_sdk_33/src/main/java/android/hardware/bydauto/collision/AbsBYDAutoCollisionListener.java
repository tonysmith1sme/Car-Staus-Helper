package android.hardware.bydauto.collision;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

/* loaded from: classes.dex */
@Keep
public abstract class AbsBYDAutoCollisionListener implements IBYDAutoListener {
    private static final boolean DEBUG = true;
    protected static final String TAG = "AbsBYDAutoCollisionListener";

    public void onCollisionSignalStateChanged(byte[] collisionInfo) {
    }

    private CollisionMessageData parse(Object obj) {
        return null;
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        if (event instanceof BYDAutoCollisionEvent) {
            byte[] bufData = ((BYDAutoCollisionEvent) event).getBufferData();
            onCollisionSignalStateChanged(bufData);
        }
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
    }

    /* loaded from: classes.dex */
    @Keep
    private class CollisionMessageData {
        private CollisionMessageData() {
        }
    }
}