//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.ac;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

import androidx.annotation.Keep;

@Keep
public class BYDAutoAcDevice extends AbsBYDAutoDevice {
    public static final int AC_COMMAND_BUSY = -2147482647;
    public static final int AC_COMMAND_FAILED = -2147482648;
    public static final int AC_COMMAND_INVALID_VALUE = -2147482645;
    public static final int AC_COMMAND_SUCCESS = 0;
    public static final int AC_COMMAND_TIMEOUT = -2147482646;
    public static final int AC_COMPRESSOR_MANUAL_SIGN_OFF = 0;
    public static final int AC_COMPRESSOR_MANUAL_SIGN_ON = 1;
    public static final int AC_COMPRESSOR_OFF = 0;
    public static final int AC_COMPRESSOR_ON = 1;
    public static final int AC_CTRLMODE_AUTO = 0;
    public static final int AC_CTRLMODE_MANUAL = 1;
    public static final int AC_CTRL_SOURCE_UI_KEY = 0;
    public static final int AC_CTRL_SOURCE_VOICE = 1;
    public static final int AC_CYCLEMODE_INLOOP = 1;
    public static final int AC_CYCLEMODE_OUTLOOP = 0;
    public static final int AC_DEFROST_AREA_FRONT = 1;
    public static final int AC_DEFROST_AREA_REAR = 2;
    public static final int AC_DEFROST_STATE_OFF = 0;
    public static final int AC_DEFROST_STATE_ON = 1;
    public static final int AC_FAULT_NUM_SHOWN_STATE_INVALID = 0;
    public static final int AC_FAULT_NUM_SHOWN_STATE_OFF = 1;
    public static final int AC_FAULT_NUM_SHOWN_STATE_ON = 2;
    public static final int AC_FAULT_NUM_SHOWN_STATE_RESERVED = 3;
    public static final int AC_POWER_OFF = 0;
    public static final int AC_POWER_ON = 1;
    public static final int AC_TEMPCTRL_SEPARATE_OFF = 0;
    public static final int AC_TEMPCTRL_SEPARATE_ON = 1;
    public static final int AC_TEMPERATURE_DEPUTY = 2;
    public static final int AC_TEMPERATURE_MAIN = 1;
    public static final int AC_TEMPERATURE_MAIN_DEPUTY = 0;
    public static final int AC_TEMPERATURE_OUT = 4;
    public static final int AC_TEMPERATURE_REAR = 3;
    public static final int AC_TEMPERATURE_UNIT_OC = 1;
    public static final int AC_TEMPERATURE_UNIT_OF = 0;
    public static final int AC_TEMP_INVALID = 0;
    public static final int AC_TEMP_IN_CELSIUS_MAX = 33;
    public static final int AC_TEMP_IN_CELSIUS_MIN = 17;
    public static final int AC_TEMP_IN_FAHRENHEIT_MAX = 91;
    public static final int AC_TEMP_IN_FAHRENHEIT_MIN = 64;
    public static final int AC_TEMP_OUT_CELSIUS_MAX = 50;
    public static final int AC_TEMP_OUT_CELSIUS_MIN = -40;
    public static final int AC_TEMP_OUT_FAHRENHEIT_MAX = 122;
    public static final int AC_TEMP_OUT_FAHRENHEIT_MIN = -40;
    public static final int AC_VENTILATION_STATE_OFF = 0;
    public static final int AC_VENTILATION_STATE_ON = 1;
    public static final int AC_WINDLEVEL_0 = 0;
    public static final int AC_WINDLEVEL_1 = 1;
    public static final int AC_WINDLEVEL_2 = 2;
    public static final int AC_WINDLEVEL_3 = 3;
    public static final int AC_WINDLEVEL_4 = 4;
    public static final int AC_WINDLEVEL_5 = 5;
    public static final int AC_WINDLEVEL_6 = 6;
    public static final int AC_WINDLEVEL_7 = 7;
    public static final int AC_WINDLEVEL_MANUAL_SIGN_OFF = 0;
    public static final int AC_WINDLEVEL_MANUAL_SIGN_ON = 1;
    public static final int AC_WINDMODE_DEFROST = 5;
    public static final int AC_WINDMODE_FACE = 1;
    public static final int AC_WINDMODE_FACEDEFROST = 7;
    public static final int AC_WINDMODE_FACEFOOT = 2;
    public static final int AC_WINDMODE_FACEFOOTDEFROST = 6;
    public static final int AC_WINDMODE_FOOT = 3;
    public static final int AC_WINDMODE_FOOTDEFROST = 4;
    public static final int AC_WINDMODE_MANUAL_SIGN_OFF = 0;
    public static final int AC_WINDMODE_MANUAL_SIGN_ON = 1;
    public static final int AC_WINDMODE_SHOWN_STATE_OFF = 0;
    public static final int AC_WINDMODE_SHOWN_STATE_ON = 1;

    BYDAutoAcDevice() {
        super((Context) null);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoAcDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public int start(int setSource) {
        throw new RuntimeException("Stub!");
    }

    public int stop(int setSource) {
        throw new RuntimeException("Stub!");
    }

    public int getAcStartState() {
        throw new RuntimeException("Stub!");
    }

    public int startRearAc(int setSource) {
        throw new RuntimeException("Stub!");
    }

    public int stopRearAc(int setSource) {
        throw new RuntimeException("Stub!");
    }

    public int getRearAcStartState() {
        throw new RuntimeException("Stub!");
    }

    public int setAcControlMode(int setSource, int mode) {
        throw new RuntimeException("Stub!");
    }

    public int getAcControlMode() {
        throw new RuntimeException("Stub!");
    }

    public int setAcCycleMode(int setSource, int mode) {
        throw new RuntimeException("Stub!");
    }

    public int getAcCycleMode() {
        throw new RuntimeException("Stub!");
    }

    public int setAcVentilationState(int setSource, int state) {
        throw new RuntimeException("Stub!");
    }

    public int getAcVentilationState() {
        throw new RuntimeException("Stub!");
    }

    public int setAcTemperatureControlMode(int setSource, int mode) {
        throw new RuntimeException("Stub!");
    }

    public int getAcTemperatureControlMode() {
        throw new RuntimeException("Stub!");
    }

    public int setAcDefrostState(int setSource, int area, int state) {
        throw new RuntimeException("Stub!");
    }

    public int getAcDefrostState(int area) {
        throw new RuntimeException("Stub!");
    }

    public int getAcCompressorManualSign() {
        throw new RuntimeException("Stub!");
    }

    public int setAcCompressorMode(int setSource, int mode) {
        throw new RuntimeException("Stub!");
    }

    public int getAcCompressorMode() {
        throw new RuntimeException("Stub!");
    }

    public int getAcWindModeManualSign() {
        throw new RuntimeException("Stub!");
    }

    public int setAcWindMode(int setSource, int mode) {
        throw new RuntimeException("Stub!");
    }

    public int getAcWindMode() {
        throw new RuntimeException("Stub!");
    }

    public int getAcWindLevelManualSign() {
        throw new RuntimeException("Stub!");
    }

    public int setAcWindLevel(int setSource, int level) {
        throw new RuntimeException("Stub!");
    }

    public int getAcWindLevel() {
        throw new RuntimeException("Stub!");
    }

    public int getTemperatureUnit() {
        throw new RuntimeException("Stub!");
    }

    public int setAcTemperature(int type, int value, int tempSource, int unit) {
        throw new RuntimeException("Stub!");
    }

    public int getTemprature(int area) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoAcListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoAcListener l) {
        throw new RuntimeException("Stub!");
    }
}
