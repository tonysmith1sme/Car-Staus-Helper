//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.engine;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

import androidx.annotation.Keep;

@Keep
public class BYDAutoEngineDevice extends AbsBYDAutoDevice {
    public static final int ENGINE_CAPACITY_MAX = 250;
    public static final int ENGINE_CAPACITY_MIN = 0;
    public static final int ENGINE_COMMAND_BUSY = -2147482647;
    public static final int ENGINE_COMMAND_FAILED = -2147482648;
    public static final int ENGINE_COMMAND_INVALID_VALUE = -2147482645;
    public static final int ENGINE_COMMAND_SUCCESS = 0;
    public static final int ENGINE_COMMAND_TIMEOUT = -2147482646;
    public static final int ENGINE_COOLANT_LEVEL_LOW = 2;
    public static final int ENGINE_COOLANT_LEVEL_NORMAL = 1;
    public static final double ENGINE_DISPLACEMENT_MAX = 25.5;
    public static final double ENGINE_DISPLACEMENT_MIN = 0.0;
    public static final int ENGINE_OIL_MAX = 254;
    public static final int ENGINE_OIL_MIN = 0;
    public static final int ENGINE_POWER_MAX = 300;
    public static final int ENGINE_POWER_MIN = -100;
    public static final int ENGINE_SPEED_MAX = 8000;
    public static final int ENGINE_SPEED_MIN = 0;
    public static final String ENGINE_TYPE1 = "371QA";
    public static final String ENGINE_TYPE10 = "488QA";
    public static final String ENGINE_TYPE11 = "4G15";
    public static final String ENGINE_TYPE12 = "4G18";
    public static final String ENGINE_TYPE13 = "4G69";
    public static final String ENGINE_TYPE14 = "473QE";
    public static final String ENGINE_TYPE15 = "471ZQA";
    public static final String ENGINE_TYPE2 = "473QB";
    public static final String ENGINE_TYPE3 = "473QC";
    public static final String ENGINE_TYPE4 = "473QD";
    public static final String ENGINE_TYPE5 = "476ZQA";
    public static final String ENGINE_TYPE6 = "483QA";
    public static final String ENGINE_TYPE7 = "483QB";
    public static final String ENGINE_TYPE8 = "483QB CNG";
    public static final String ENGINE_TYPE9 = "487ZQA";

    BYDAutoEngineDevice() {
        super((Context)null);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoEngineDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public double getEngineDisplacement() {
        throw new RuntimeException("Stub!");
    }

    public String getEngineCode() {
        throw new RuntimeException("Stub!");
    }

    public int getEnginePower() {
        throw new RuntimeException("Stub!");
    }

    public int getEngineSpeed() {
        throw new RuntimeException("Stub!");
    }

    public int getEngineCoolantLevel() {
        throw new RuntimeException("Stub!");
    }

    public int getOilLevel() {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoEngineListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoEngineListener l) {
        throw new RuntimeException("Stub!");
    }
}
