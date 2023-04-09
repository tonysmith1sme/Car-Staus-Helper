//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.speed;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

import androidx.annotation.Keep;

@Keep
public class BYDAutoSpeedDevice extends AbsBYDAutoDevice {
    public static final int DEEP_PERSENT_MAX = 100;
    public static final int DEEP_PERSENT_MIN = 0;
    public static final int SPEED_COMMAND_BUSY = -2147482647;
    public static final int SPEED_COMMAND_FAILED = -2147482648;
    public static final int SPEED_COMMAND_INVALID = -2147482645;
    public static final int SPEED_COMMAND_SUCCESS = 0;
    public static final int SPEED_COMMAND_TIMEOUT = -2147482646;
    public static final double SPEED_MAX = 282.0;
    public static final double SPEED_MIN = 0.0;

    BYDAutoSpeedDevice() {
        super((Context)null);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoSpeedDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public double getCurrentSpeed() {
        throw new RuntimeException("Stub!");
    }

    public int getAccelerateDeepness() {
        throw new RuntimeException("Stub!");
    }

    public int getBrakeDeepness() {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, double value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoSpeedListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoSpeedListener l) {
        throw new RuntimeException("Stub!");
    }
}
