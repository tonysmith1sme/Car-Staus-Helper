package android.hardware.bydauto.collision;

import android.hardware.bydauto.BYDAutoEvent;

import androidx.annotation.Keep;

/* loaded from: classes.dex */
@Keep
public class BYDAutoCollisionEvent extends BYDAutoEvent {
    private byte[] mBufData;

    public BYDAutoCollisionEvent(int deviceType, int eventType, int value) {
        super(deviceType, eventType, value);
        this.mBufData = null;
    }

    public BYDAutoCollisionEvent(int deviceType, int eventType, int value, Object data) {
        super(deviceType, eventType, value, data);
        this.mBufData = null;
    }

    public BYDAutoCollisionEvent(int deviceType, int eventType, double value) {
        super(deviceType, eventType, (float) value);
        this.mBufData = null;
    }

    public BYDAutoCollisionEvent(int deviceType, int eventType, double value, Object data) {
        super(deviceType, eventType, (float) value, data);
        this.mBufData = null;
    }

    public BYDAutoCollisionEvent(int deviceType, int eventType, byte[] value, Object data) {
        super(deviceType, eventType, -1, data);
        this.mBufData = null;
        this.mBufData = value;
    }

    @Override // android.hardware.bydauto.BYDAutoEvent, android.hardware.IBYDAutoEvent
    public byte[] getBufferData() {
        byte[] mBuf = this.mBufData;
        return mBuf;
    }
}