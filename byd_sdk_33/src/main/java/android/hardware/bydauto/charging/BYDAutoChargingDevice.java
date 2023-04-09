//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.charging;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

import androidx.annotation.Keep;

@Keep
public class BYDAutoChargingDevice extends AbsBYDAutoDevice {
    public static final int CHARGING_BATTERY_STATE_BREAKDOWN_AC = 8;
    public static final int CHARGING_BATTERY_STATE_BREAKDOWN_C10 = 5;
    public static final int CHARGING_BATTERY_STATE_BREAKDOWN_CHARGER = 7;
    public static final int CHARGING_BATTERY_STATE_BREAKDOWN_CHARGING_GUN = 6;
    public static final int CHARGING_BATTERY_STATE_CHARGING = 1;
    public static final int CHARGING_BATTERY_STATE_CHARG_FINISH = 2;
    public static final int CHARGING_BATTERY_STATE_CHARG_TERMINATE = 4;
    public static final int CHARGING_BATTERY_STATE_DISCHARG = 3;
    public static final int CHARGING_BATTERY_STATE_DISCHARG_CBU = 10;
    public static final int CHARGING_BATTERY_STATE_DISCHARG_FINISH = 12;
    public static final int CHARGING_BATTERY_STATE_READY = 0;
    public static final int CHARGING_BATTERY_STATE_SCHEDULE = 9;
    public static final int CHARGING_BATTERY_STATE_TIMEOUT = 11;
    public static final double CHARGING_CAPACITY_MAX = 65.534;
    public static final double CHARGING_CAPACITY_MIN = 0.0;
    public static final int CHARGING_CAP_AC = 1;
    public static final int CHARGING_CAP_DC = 2;
    public static final int CHARGING_CAP_STATE_OFF = 0;
    public static final int CHARGING_CAP_STATE_ON = 1;
    public static final int CHARGING_CHARGER_STATE_CONNECTED = 1;
    public static final int CHARGING_CHARGER_STATE_NOT_CONNECTED = 0;
    public static final int CHARGING_COMMAND_BUSY = -2147482647;
    public static final int CHARGING_COMMAND_FAILED = -2147482648;
    public static final int CHARGING_COMMAND_INVALID_VALUE = -2147482645;
    public static final int CHARGING_COMMAND_SUCCESS = 0;
    public static final int CHARGING_COMMAND_TIMEOUT = -2147482646;
    public static final int CHARGING_ENTER = 1;
    public static final int CHARGING_EXIT = 2;
    public static final int CHARGING_FAILURE = 1;
    public static final int CHARGING_FAULT_STATE_MAJOR = 3;
    public static final int CHARGING_FAULT_STATE_MINOR = 2;
    public static final int CHARGING_FAULT_STATE_NORMAL = 1;
    public static final int CHARGING_GUN_STATE_CONNECTED_AC = 2;
    public static final int CHARGING_GUN_STATE_CONNECTED_AC_DC = 4;
    public static final int CHARGING_GUN_STATE_CONNECTED_DC = 3;
    public static final int CHARGING_GUN_STATE_CONNECTED_NONE = 1;
    public static final int CHARGING_GUN_STATE_CONNECTED_VTOL = 5;
    public static final int CHARGING_GUN_STATE_OFF = 2;
    public static final int CHARGING_GUN_STATE_ON = 1;
    public static final int CHARGING_PORT_STATE_LOCK_FINISH = 1;
    public static final int CHARGING_PORT_STATE_LOCK_INVALID = 2;
    public static final int CHARGING_PORT_STATE_UNLOCK_FINISH = 3;
    public static final int CHARGING_PORT_STATE_UNLOCK_INVALID = 4;
    public static final double CHARGING_POWER_MAX = 500.0;
    public static final double CHARGING_POWER_MIN = -500.0;
    public static final int CHARGING_SCHEDULE_STATE_CANCEL = 2;
    public static final int CHARGING_SCHEDULE_STATE_INVALID = 1;
    public static final int CHARGING_SCHEDULE_STATE_LOCAL = 4;
    public static final int CHARGING_SCHEDULE_STATE_NONE = 3;
    public static final int CHARGING_SCHEDULE_STATE_REMOTE = 5;
    public static final int CHARGING_STATE_DISABLE = 1;
    public static final int CHARGING_STATE_ENABLE = 0;
    public static final int CHARGING_SUCCESS = 2;
    public static final int CHARGING_TYPE_AC = 2;
    public static final int CHARGING_TYPE_DEFAULT = 1;
    public static final int CHARGING_TYPE_GB_DC = 4;
    public static final int CHARGING_TYPE_GB_NON_DC = 5;
    public static final int CHARGING_TYPE_VTOG = 3;
    public static final int CHARGING_WORK_STATE_FINISH = 3;
    public static final int CHARGING_WORK_STATE_READY = 1;
    public static final int CHARGING_WORK_STATE_START = 2;
    public static final int CHARGING_WORK_STATE_TERMINATE = 4;
    public static final int DISCHARGE_STATE_HOUSEHOLD_APPLIANCE = 2;
    public static final int DISCHARGE_STATE_NON = 1;
    public static final int DISCHARGE_STATE_POWER_SYSTEM = 5;
    public static final int DISCHARGE_STATE_SINGLE_PHASE_VEHICLE = 7;
    public static final int DISCHARGE_STATE_SOCKET = 6;
    public static final int DISCHARGE_STATE_THREE_PHASE_EQUIPMENT = 3;
    public static final int DISCHARGE_STATE_THREE_PHASE_VEHICLE = 4;
    public static final int HOUR_MAX = 23;
    public static final int HOUR_MIN = 0;
    public static final int MINUTE_MAX = 59;
    public static final int MINUTE_MIN = 0;
    public static final int REST_HOUR_MAX = 254;
    public static final int REST_HOUR_MIN = 0;

    BYDAutoChargingDevice() {
        super((Context)null);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoChargingDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public int getChargerFaultState() {
        throw new RuntimeException("Stub!");
    }

    public int getChargerWorkState() {
        throw new RuntimeException("Stub!");
    }

    public double getChargingCapacity() {
        throw new RuntimeException("Stub!");
    }

    public int getChargingType() {
        throw new RuntimeException("Stub!");
    }

    public int[] getChargingRestTime() {
        throw new RuntimeException("Stub!");
    }

    public int getChargingCapState(int type) {
        throw new RuntimeException("Stub!");
    }

    public int getChargingPortLockRebackState() {
        throw new RuntimeException("Stub!");
    }

    public int getDischargeRequestState() {
        throw new RuntimeException("Stub!");
    }

    public int getChargerState() {
        throw new RuntimeException("Stub!");
    }

    public int getChargingGunState() {
        throw new RuntimeException("Stub!");
    }

    public double getChargingPower() {
        throw new RuntimeException("Stub!");
    }

    public int getBatteryManagementDeviceState() {
        throw new RuntimeException("Stub!");
    }

    public int getChargingScheduleEnableState() {
        throw new RuntimeException("Stub!");
    }

    public int getChargingScheduleState() {
        throw new RuntimeException("Stub!");
    }

    public int getChargingGunNotInsertedState() {
        throw new RuntimeException("Stub!");
    }

    public int[] getChargingScheduleTime() {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, double value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoChargingListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoChargingListener l) {
        throw new RuntimeException("Stub!");
    }
}
