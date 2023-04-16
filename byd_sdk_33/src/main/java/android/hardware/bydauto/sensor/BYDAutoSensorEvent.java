package android.hardware.bydauto.sensor;

import android.hardware.bydauto.BYDAutoEvent;

import androidx.annotation.Keep;

@Keep
public class BYDAutoSensorEvent extends BYDAutoEvent {
    private byte[] mBufData;

    public BYDAutoSensorEvent(int deviceType, int eventType, byte[] value, Object data) {
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