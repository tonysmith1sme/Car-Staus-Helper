//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.doorlock;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

import androidx.annotation.Keep;

@Keep
public class BYDAutoDoorLockDevice extends AbsBYDAutoDevice {
    public static final int DOOR_LOCK_AREA_BACK = 5;
    public static final int DOOR_LOCK_AREA_CHILDLOCK_LEFT = 6;
    public static final int DOOR_LOCK_AREA_CHILDLOCK_RIGHT = 7;
    public static final int DOOR_LOCK_AREA_LEFT_FRONT = 1;
    public static final int DOOR_LOCK_AREA_LEFT_REAR = 2;
    public static final int DOOR_LOCK_AREA_RIGHT_FRONT = 3;
    public static final int DOOR_LOCK_AREA_RIGHT_REAR = 4;
    public static final int DOOR_LOCK_COMMAND_BUSY = -2147482647;
    public static final int DOOR_LOCK_COMMAND_FAILED = -2147482648;
    public static final int DOOR_LOCK_COMMAND_INVALID_VALUE = -2147482645;
    public static final int DOOR_LOCK_COMMAND_SUCCESS = 0;
    public static final int DOOR_LOCK_COMMAND_TIMEOUT = -2147482646;
    public static final int DOOR_LOCK_STATE_INVALID = 0;
    public static final int DOOR_LOCK_STATE_LOCK = 2;
    public static final int DOOR_LOCK_STATE_UNLOCK = 1;

    BYDAutoDoorLockDevice() {
        super((Context)null);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoDoorLockDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public int getDoorLockStatus(int area) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoDoorLockListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoDoorLockListener l) {
        throw new RuntimeException("Stub!");
    }
}
