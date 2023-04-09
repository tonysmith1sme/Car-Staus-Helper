//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto;

import android.content.Context;
import android.hardware.IBYDAutoDevice;
import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoDevice implements IBYDAutoDevice {
    protected AbsBYDAutoDevice(Context con) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, double value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public boolean onPostEvent(IBYDAutoEvent event) {
        throw new RuntimeException("Stub!");
    }

    public int set(int device, int event, int value) {
        throw new RuntimeException("Stub!");
    }

    public int set(int device, int event, byte[] value) {
        throw new RuntimeException("Stub!");
    }

    public int set(int device, int event, double value) {
        throw new RuntimeException("Stub!");
    }

    public int set(int device, int[] event, int[] params) {
        throw new RuntimeException("Stub!");
    }

    public int set(int device, int[] event, double[] params) {
        throw new RuntimeException("Stub!");
    }

    public int get(int device, int event) {
        throw new RuntimeException("Stub!");
    }

    public int[] getIntArray(int device, int[] event) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getBuffer(int device, int event) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(int device, int event) {
        throw new RuntimeException("Stub!");
    }

    public double[] getDoubleArray(int device, int[] event) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(IBYDAutoListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(IBYDAutoListener l) {
        throw new RuntimeException("Stub!");
    }
}
