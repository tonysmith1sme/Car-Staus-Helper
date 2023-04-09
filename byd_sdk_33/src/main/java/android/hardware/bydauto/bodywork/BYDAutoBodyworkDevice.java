//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.bodywork;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

import androidx.annotation.Keep;

@Keep
public class BYDAutoBodyworkDevice extends AbsBYDAutoDevice {
    public static final int AUTO_MODEL_NEW_QIN_EV = 2;
    public static final int AUTO_MODEL_NEW_QIN_FUEL = 3;
    public static final int AUTO_MODEL_NEW_QIN_HEV = 1;
    public static final int AUTO_MODEL_NEW_TANG_EV = 6;
    public static final int AUTO_MODEL_NEW_TANG_FUEL = 5;
    public static final int AUTO_MODEL_NEW_TANG_HEV = 4;
    public static final int AUTO_MODEL_NULL = 11;
    public static final int AUTO_MODEL_SONG_18_EV = 9;
    public static final int AUTO_MODEL_SONG_18_FUEL = 7;
    public static final int AUTO_MODEL_SONG_18_HEV = 8;
    public static final int AUTO_MODEL_SONG_MAX_HEV = 10;
    public static final int AUTO_TYPE_3A = 20;
    public static final int AUTO_TYPE_3B = 36;
    public static final int AUTO_TYPE_5A = 5;
    public static final int AUTO_TYPE_5AEV = 29;
    public static final int AUTO_TYPE_5B = 22;
    public static final int AUTO_TYPE_5BH = 27;
    public static final int AUTO_TYPE_5BHE = 53;
    public static final int AUTO_TYPE_5BHI = 64;
    public static final int AUTO_TYPE_6A = 7;
    public static final int AUTO_TYPE_6B = 8;
    public static final int AUTO_TYPE_F0 = 0;
    public static final int AUTO_TYPE_F3 = 1;
    public static final int AUTO_TYPE_F3R = 2;
    public static final int AUTO_TYPE_F6 = 6;
    public static final int AUTO_TYPE_G3 = 3;
    public static final int AUTO_TYPE_HA = 13;
    public static final int AUTO_TYPE_HAC = 46;
    public static final int AUTO_TYPE_HAD = 50;
    public static final int AUTO_TYPE_HADA = 63;
    public static final int AUTO_TYPE_HADE = 51;
    public static final int AUTO_TYPE_HADF = 66;
    public static final int AUTO_TYPE_HADG = 85;
    public static final int AUTO_TYPE_HAEA = 49;
    public static final int AUTO_TYPE_HAEC = 62;
    public static final int AUTO_TYPE_HAEV = 28;
    public static final int AUTO_TYPE_HA_15A = 37;
    public static final int AUTO_TYPE_HB = 14;
    public static final int AUTO_TYPE_HC = 15;
    public static final int AUTO_TYPE_HD = 31;
    public static final int AUTO_TYPE_L3 = 4;
    public static final int AUTO_TYPE_L3G = 33;
    public static final int AUTO_TYPE_M6 = 10;
    public static final int AUTO_TYPE_MEE = 44;
    public static final int AUTO_TYPE_MEF = 42;
    public static final int AUTO_TYPE_MEFD = 81;
    public static final int AUTO_TYPE_MEH = 43;
    public static final int AUTO_TYPE_MEHD = 67;
    public static final int AUTO_TYPE_RSA = 45;
    public static final int AUTO_TYPE_S6 = 9;
    public static final int AUTO_TYPE_S6DM = 17;
    public static final int AUTO_TYPE_S8 = 16;
    public static final int AUTO_TYPE_SA = 11;
    public static final int AUTO_TYPE_SA2E = 84;
    public static final int AUTO_TYPE_SA2FC = 82;
    public static final int AUTO_TYPE_SA2H = 83;
    public static final int AUTO_TYPE_SADM = 12;
    public static final int AUTO_TYPE_SAEA = 40;
    public static final int AUTO_TYPE_SAEC = 70;
    public static final int AUTO_TYPE_SAEG = 94;
    public static final int AUTO_TYPE_SAEV = 39;
    public static final int AUTO_TYPE_SAFG = 73;
    public static final int AUTO_TYPE_SAH = 32;
    public static final int AUTO_TYPE_SAHA = 88;
    public static final int AUTO_TYPE_SAHB = 89;
    public static final int AUTO_TYPE_SAHC = 90;
    public static final int AUTO_TYPE_SAHE = 65;
    public static final int AUTO_TYPE_SAHG = 61;
    public static final int AUTO_TYPE_SAHX = 74;
    public static final int AUTO_TYPE_SC = 30;
    public static final int AUTO_TYPE_SCEA = 41;
    public static final int AUTO_TYPE_SCH = 35;
    public static final int AUTO_TYPE_SE = 23;
    public static final int AUTO_TYPE_SEF = 24;
    public static final int AUTO_TYPE_SEH = 48;
    public static final int AUTO_TYPE_ST = 47;
    public static final int AUTO_TYPE_STC = 56;
    public static final int AUTO_TYPE_STE = 58;
    public static final int AUTO_TYPE_STEB = 59;
    public static final int AUTO_TYPE_STEM = 60;
    public static final int AUTO_TYPE_STF = 54;
    public static final int AUTO_TYPE_STFD = 55;
    public static final int AUTO_TYPE_STM = 57;
    public static final int AUTO_TYPE_VA = 18;
    public static final int AUTO_TYPE_VB = 19;
    public static final int AUTO_TYPE_VBEV = 26;
    public static final int AUTO_TYPE_VBH = 25;
    public static final int AUTO_TYPE_VC = 21;
    public static final int AUTO_TYPE_e6B = 38;
    public static final int AUTO_TYPE_e6H = 34;
    public static final int AUTO_TYPE_e6K = 52;
    public static final int BODYWORK_ALARM_STATE_OFF = 0;
    public static final int BODYWORK_ALARM_STATE_ON = 1;
    public static final int BODYWORK_AUTO_SYSTEM_STATE_NORMAL = 0;
    public static final int BODYWORK_AUTO_SYSTEM_STATE_SET_SECURE = 1;
    public static final int BODYWORK_AUTO_SYSTEM_STATE_START_SECURE = 2;
    public static final int BODYWORK_AUTO_SYSTEM_STATE_UNDEFINED = 255;
    public static final int BODYWORK_BATTERY_VOLTAGE_LEVEL_INVALID = 255;
    public static final int BODYWORK_BATTERY_VOLTAGE_LEVEL_LOW = 0;
    public static final int BODYWORK_BATTERY_VOLTAGE_LEVEL_NORMAL = 1;
    public static final int BODYWORK_CMD_DOOR_HOOD = 5;
    public static final int BODYWORK_CMD_DOOR_LEFT_FRONT = 1;
    public static final int BODYWORK_CMD_DOOR_LEFT_REAR = 3;
    public static final int BODYWORK_CMD_DOOR_LUGGAGE_DOOR = 6;
    public static final int BODYWORK_CMD_DOOR_RIGHT_FRONT = 2;
    public static final int BODYWORK_CMD_DOOR_RIGHT_REAR = 4;
    public static final int BODYWORK_CMD_MOON_ROOF = 5;
    public static final int BODYWORK_CMD_STEERING_WHEEL_ANGEL = 1;
    public static final int BODYWORK_CMD_STEERING_WHEEL_SPEED = 2;
    public static final int BODYWORK_CMD_SUNSHADE_PANEL = 6;
    public static final int BODYWORK_CMD_WINDOW_LEFT_FRONT = 1;
    public static final int BODYWORK_CMD_WINDOW_LEFT_REAR = 3;
    public static final int BODYWORK_CMD_WINDOW_RIGHT_FRONT = 2;
    public static final int BODYWORK_CMD_WINDOW_RIGHT_REAR = 4;
    public static final int BODYWORK_COMMAND_BUSY = -2147482647;
    public static final int BODYWORK_COMMAND_FAILED = -2147482648;
    public static final int BODYWORK_COMMAND_INVALID_VALUE = -2147482645;
    public static final int BODYWORK_COMMAND_SUCCESS = 0;
    public static final int BODYWORK_COMMAND_TIMEOUT = -2147482646;
    public static final int BODYWORK_LOW_POWER_BOTH = 3;
    public static final int BODYWORK_LOW_POWER_ELEC = 2;
    public static final int BODYWORK_LOW_POWER_FUEL = 1;
    public static final int BODYWORK_LOW_POWER_NORMAL = 0;
    public static final int BODYWORK_POWER_LEVEL_ACC = 1;
    public static final int BODYWORK_POWER_LEVEL_INVALID = 255;
    public static final int BODYWORK_POWER_LEVEL_OFF = 0;
    public static final int BODYWORK_POWER_LEVEL_ON = 2;
    public static final int BODYWORK_STATE_CLOSED = 0;
    public static final int BODYWORK_STATE_OPEN = 1;
    public static final int BODYWORK_STATE_UNDEFINED = 255;
    public static final double BODYWORK_STEERING_WHEEL_ANGEL_MAX = 780.0;
    public static final double BODYWORK_STEERING_WHEEL_ANGEL_MIN = -780.0;
    public static final double BODYWORK_STEERING_WHEEL_SPEED_MAX = 1016.0;
    public static final double BODYWORK_STEERING_WHEEL_SPEED_MIN = 0.0;
    public static final int CONFIG_ANTI_PINCH_MOON_ROOF = 3;
    public static final int CONFIG_MOON_ROOF_SUNSHADE_PANEL = 1;
    public static final int CONFIG_NONE = 0;
    public static final int CONFIG_SUNSHADE_PANEL = 2;
    public static final int WINDOW_OPEN_PERCENT_MAX = 100;
    public static final int WINDOW_OPEN_PERCENT_MIN = 0;

    BYDAutoBodyworkDevice() {
        super((Context)null);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoBodyworkDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public int getWindowState(int area) {
        throw new RuntimeException("Stub!");
    }

    public int getDoorState(int area) {
        throw new RuntimeException("Stub!");
    }

    public int getAutoSystemState() {
        throw new RuntimeException("Stub!");
    }

    public double getSteeringWheelValue(int type) {
        throw new RuntimeException("Stub!");
    }

    public int getPowerLevel() {
        throw new RuntimeException("Stub!");
    }

    public int getBatteryVoltageLevel() {
        throw new RuntimeException("Stub!");
    }

    public String getAutoVIN() {
        throw new RuntimeException("Stub!");
    }

    public int getMoonRoofConfig() {
        throw new RuntimeException("Stub!");
    }

    public int getFuelElecLowPower() {
        throw new RuntimeException("Stub!");
    }

    public int getAlarmState() {
        throw new RuntimeException("Stub!");
    }

    public int getWindowOpenPercent(int area) {
        throw new RuntimeException("Stub!");
    }

    public int getAutoModelName() {
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

    public void registerListener(AbsBYDAutoBodyworkListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoBodyworkListener l) {
        throw new RuntimeException("Stub!");
    }

    public int getBatteryPowerValue() {
        throw new RuntimeException("Stub!");
    }

    public int getAutoType() {
        throw new RuntimeException("Stub!");
    }

    public int setAllWindowState(int leftFront, int rightFront, int leftRear, int rightRear) {
        throw new RuntimeException("Stub!");
    }

}
