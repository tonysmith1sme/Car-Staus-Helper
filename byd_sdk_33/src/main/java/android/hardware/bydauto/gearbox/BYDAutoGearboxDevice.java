//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.gearbox;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

import androidx.annotation.Keep;

@Keep
public class BYDAutoGearboxDevice extends AbsBYDAutoDevice {
    public static final int GEARBOX_AUTO_MODE_D = 4;
    public static final int GEARBOX_AUTO_MODE_M = 5;
    public static final int GEARBOX_AUTO_MODE_N = 3;
    public static final int GEARBOX_AUTO_MODE_P = 1;
    public static final int GEARBOX_AUTO_MODE_R = 2;
    public static final int GEARBOX_AUTO_MODE_S = 6;
    public static final int GEARBOX_BRAKE_FLUID_LEVEL_LOW = 1;
    public static final int GEARBOX_BRAKE_FLUID_LEVEL_NORMAL = 2;
    public static final int GEARBOX_BREAK_PADAL_NOT_PRESS = 2;
    public static final int GEARBOX_BREAK_PADAL_PRESS = 1;
    public static final String GEARBOX_CODE_0 = "DABS15-41";
    public static final String GEARBOX_CODE_1 = "F4A4";
    public static final String GEARBOX_CODE_10 = "5RT14";
    public static final String GEARBOX_CODE_11 = "6T25";
    public static final String GEARBOX_CODE_12 = "6DT25";
    public static final String GEARBOX_CODE_13 = "6DT33";
    public static final String GEARBOX_CODE_14 = "6T18";
    public static final String GEARBOX_CODE_15 = "6DT35";
    public static final String GEARBOX_CODE_2 = "F4A4B";
    public static final String GEARBOX_CODE_3 = "VT2-04O";
    public static final String GEARBOX_CODE_4 = "SSG";
    public static final String GEARBOX_CODE_5 = "5T09";
    public static final String GEARBOX_CODE_6 = "5T14";
    public static final String GEARBOX_CODE_7 = "5T19";
    public static final String GEARBOX_CODE_8 = "5T19-1";
    public static final String GEARBOX_CODE_9 = "5RT10";
    public static final int GEARBOX_COMMAND_BUSY = -2147482647;
    public static final int GEARBOX_COMMAND_FAILED = -2147482648;
    public static final int GEARBOX_COMMAND_INVALID_VALUE = -2147482645;
    public static final int GEARBOX_COMMAND_SUCCESS = 0;
    public static final int GEARBOX_COMMAND_TIMEOUT = -2147482646;
    public static final int GEARBOX_PARK_BREAK_SWITCH_INVALID = 0;
    public static final int GEARBOX_PARK_BREAK_SWITCH_VALID = 1;
    public static final int GEARBOX_REAL_LEVEL_D = 0;
    public static final int GEARBOX_REAL_LEVEL_N = 2;
    public static final int GEARBOX_REAL_LEVEL_R = 1;
    public static final int GEARBOX_TYPE_AMT = 1;
    public static final int GEARBOX_TYPE_AT = 2;
    public static final int GEARBOX_TYPE_CVT = 3;
    public static final int GEARBOX_TYPE_DCT = 4;
    public static final int GEARBOX_TYPE_INVALID = 255;
    public static final int GEARBOX_TYPE_INVALID1 = 5;
    public static final int GEARBOX_TYPE_INVALID2 = 6;
    public static final int GEARBOX_TYPE_MT = 0;
    public static final int GEARBOX_TYPE_NONE = 7;

    BYDAutoGearboxDevice() {
        super((Context)null);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoGearboxDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public int getGearboxType() {
        throw new RuntimeException("Stub!");
    }

    public int getGearboxAutoModeType() {
        throw new RuntimeException("Stub!");
    }

    public int getGearboxManualModeLevel() {
        throw new RuntimeException("Stub!");
    }

    public String getGearboxCode() {
        throw new RuntimeException("Stub!");
    }

    public int getBrakeFluidLevel() {
        throw new RuntimeException("Stub!");
    }

    public int getParkBrakeSwitch() {
        throw new RuntimeException("Stub!");
    }

    public int getBrakePedalState() {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoGearboxListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoGearboxListener l) {
        throw new RuntimeException("Stub!");
    }
}
