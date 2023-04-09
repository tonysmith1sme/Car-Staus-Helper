//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.tyre;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

import androidx.annotation.Keep;

@Keep
public class BYDAutoTyreDevice extends AbsBYDAutoDevice {
    public static final int TYRE_AIR_LEAK_STATE_NORMAL = 0;
    public static final int TYRE_AIR_LEAK_STATE_QUICK = 1;
    public static final int TYRE_AIR_LEAK_STATE_SLOW = 2;
    public static final int TYRE_BATTERY_STATE_LOW = 1;
    public static final int TYRE_BATTERY_STATE_NORMAL = 0;
    public static final int TYRE_COMMAND_AREA_LEFT_FRONT = 1;
    public static final int TYRE_COMMAND_AREA_LEFT_REAR = 3;
    public static final int TYRE_COMMAND_AREA_RIGHT_FRONT = 2;
    public static final int TYRE_COMMAND_AREA_RIGHT_REAR = 4;
    public static final int TYRE_COMMAND_BUSY = -2147482647;
    public static final int TYRE_COMMAND_FAILED = -2147482648;
    public static final int TYRE_COMMAND_INVALID_VALUE = -2147482645;
    public static final int TYRE_COMMAND_SUCCESS = 0;
    public static final int TYRE_COMMAND_TIMEOUT = -2147482646;
    public static final int TYRE_PRESSURE_STATE_NORMAL = 0;
    public static final int TYRE_PRESSURE_STATE_OVERPRESSURE = 1;
    public static final int TYRE_PRESSURE_STATE_UNDERPRESSURE = 2;
    public static final int TYRE_PRESSURE_VALUE_MAX = 4094;
    public static final int TYRE_PRESSURE_VALUE_MIN = 0;
    public static final int TYRE_SIGNAL_STATE_ERROR = 1;
    public static final int TYRE_SIGNAL_STATE_NORMAL = 0;
    public static final int TYRE_SYSTEM_STATE_BREAKDOWN = 3;
    public static final int TYRE_SYSTEM_STATE_MASKED = 4;
    public static final int TYRE_SYSTEM_STATE_NORMAL = 0;
    public static final int TYRE_SYSTEM_STATE_SELF_CHECKING = 1;
    public static final int TYRE_SYSTEM_STATE_SIGNAL_ANOMAL = 2;
    public static final int TYRE_TEMPERATURE_STATE_HIGH = 2;
    public static final int TYRE_TEMPERATURE_STATE_NORMAL = 0;
    public static final int TYRE_TEMPERATURE_STATE_SLEEP = 3;
    public static final int TYRE_TEMPERATURE_STATE_SUPER_HIGH = 1;

    BYDAutoTyreDevice() {
        super((Context)null);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoTyreDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public int getTyreSystemState() {
        throw new RuntimeException("Stub!");
    }

    public int getTyreTemperatureState() {
        throw new RuntimeException("Stub!");
    }

    public int getTyreBatteryState() {
        throw new RuntimeException("Stub!");
    }

    public int getTyreAirLeakState(int area) {
        throw new RuntimeException("Stub!");
    }

    public int getTyreSignalState(int area) {
        throw new RuntimeException("Stub!");
    }

    public int getTyrePressureState(int area) {
        throw new RuntimeException("Stub!");
    }

    public int getTyrePressureValue(int area) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoTyreListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoTyreListener l) {
        throw new RuntimeException("Stub!");
    }
}
