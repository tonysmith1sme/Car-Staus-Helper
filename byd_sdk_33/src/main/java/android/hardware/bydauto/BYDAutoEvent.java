//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto;

import android.hardware.IBYDAutoEvent;

import androidx.annotation.Keep;

@Keep
public class BYDAutoEvent implements IBYDAutoEvent {
    private byte[] mBufData;
    private Object mData;
    private int mDeviceSubType;
    private int mDeviceType;
    private float mDoubleValue;
    private int mIntValue;

    public BYDAutoEvent(int deviceType, int eventType, int value) {
        this.mDeviceType = deviceType;
        this.mDeviceSubType = eventType;
        this.mIntValue = value;
        this.mData = null;
    }

    public BYDAutoEvent(int deviceType, int eventType, int value, Object data) {
        this.mDeviceType = deviceType;
        this.mDeviceSubType = eventType;
        this.mIntValue = value;
        this.mData = data;
    }

    public BYDAutoEvent(int deviceType, int eventType, float value) {
        this.mDeviceType = deviceType;
        this.mDeviceSubType = eventType;
        this.mDoubleValue = value;
        this.mData = null;
    }

    public BYDAutoEvent(int deviceType, int eventType, float value, Object data) {
        this.mDeviceType = deviceType;
        this.mDeviceSubType = eventType;
        this.mDoubleValue = value;
        this.mData = data;
    }

    public BYDAutoEvent(int deviceType, int eventType, byte[] value, Object data) {
        this.mDeviceType = deviceType;
        this.mDeviceSubType = eventType;
        this.mBufData = value;
        this.mData = data;
    }

    @Override // android.hardware.IBYDAutoEvent
    public int getDeviceType() {
        return this.mDeviceType;
    }

    @Override // android.hardware.IBYDAutoEvent
    public int getEventType() {
        return this.mDeviceSubType;
    }

    @Override // android.hardware.IBYDAutoEvent
    public int getValue() {
        return this.mIntValue;
    }

    @Override // android.hardware.IBYDAutoEvent
    public double getDoubleValue() {
        return Double.parseDouble(Float.toString(this.mDoubleValue));
    }

    @Override // android.hardware.IBYDAutoEvent
    public Object getData() {
        return this.mData;
    }

    @Override // android.hardware.IBYDAutoEvent
    public void setData(Object data) {
        this.mData = data;
    }

    @Override // android.hardware.IBYDAutoEvent
    public byte[] getBufferData() {
        return this.mBufData;
    }

}
