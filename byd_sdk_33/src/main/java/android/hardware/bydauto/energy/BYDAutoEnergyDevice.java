//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.energy;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

import androidx.annotation.Keep;

@Keep
public class BYDAutoEnergyDevice extends AbsBYDAutoDevice {
    public static final int ENERGY_COMMAND_BUSY = -2147482647;
    public static final int ENERGY_COMMAND_FAILED = -2147482648;
    public static final int ENERGY_COMMAND_INVALID = -2147482645;
    public static final int ENERGY_COMMAND_SUCCESS = 0;
    public static final int ENERGY_COMMAND_TIMEOUT = -2147482646;
    public static final int ENERGY_MODE_EV = 1;
    public static final int ENERGY_MODE_FORCE_EV = 2;
    public static final int ENERGY_MODE_FUEL = 4;
    public static final int ENERGY_MODE_HEV = 3;
    public static final int ENERGY_MODE_KEEP = 5;
    public static final int ENERGY_MODE_STOP = 0;
    public static final int ENERGY_OPERATION_ECONOMY = 1;
    public static final int ENERGY_OPERATION_SPORT = 2;
    public static final int ENERGY_POWER_GENERATING = 1;
    public static final int ENERGY_POWER_GENERATION_END = 2;
    public static final int ENERGY_POWER_GENERATION_ERROR = 3;
    public static final int ENERGY_POWER_GENERATION_INVALID = 0;
    public static final int ENERGY_POWER_GENERATION_VALUE_MAX = 31;
    public static final int ENERGY_POWER_GENERATION_VALUE_MIN = 1;
    public static final int ENERGY_ROAD_SURFACE_COMMON = 1;
    public static final int ENERGY_ROAD_SURFACE_KEEP = 0;
    public static final int ENERGY_ROAD_SURFACE_MUDDY = 3;
    public static final int ENERGY_ROAD_SURFACE_SAND = 4;
    public static final int ENERGY_ROAD_SURFACE_SNOW = 2;
    public static final int ENERGY_STATE_ELECTRIC_POWER_FOUR_WHEEL_DRIVE = 1;
    public static final int ENERGY_STATE_ELECTRIC_POWER_FOUR_WHEEL_DRIVE_FEEDBACK = 4;
    public static final int ENERGY_STATE_ELECTRIC_POWER_FRONT_WHEEL_DRIVE = 2;
    public static final int ENERGY_STATE_ELECTRIC_POWER_FRONT_WHEEL_DRIVE_FEEDBACK = 5;
    public static final int ENERGY_STATE_ELECTRIC_POWER_REAR_WHEEL_DRIVE = 3;
    public static final int ENERGY_STATE_ELECTRIC_POWER_REAR_WHEEL_DRIVE_FEEDBACK = 6;
    public static final int ENERGY_STATE_FUEL_POWER_DRIVE = 17;
    public static final int ENERGY_STATE_GENERATE_ELECTRICITY = 18;
    public static final int ENERGY_STATE_HEV_FRONT_WHEEL_DRIVE_PARALLELING = 8;
    public static final int ENERGY_STATE_HEV_THREE_POWER = 7;
    public static final int ENERGY_STATE_HEV_TWO_POWER_FOUR_WHEEL_DRIVE = 9;
    public static final int ENERGY_STATE_HIGH_SPEED_GENERATE_ELECTRICITY = 22;
    public static final int ENERGY_STATE_HYBRID_POWER_FEEDBACK_MODE_1 = 14;
    public static final int ENERGY_STATE_HYBRID_POWER_FEEDBACK_MODE_2 = 15;
    public static final int ENERGY_STATE_HYBRID_POWER_FEEDBACK_MODE_3 = 16;
    public static final int ENERGY_STATE_HYBRID_POWER_LOW_POWER_OUTPUT = 10;
    public static final int ENERGY_STATE_HYBRID_POWER_RUNNING_GENERATE_ELECTRICITY_1 = 11;
    public static final int ENERGY_STATE_HYBRID_POWER_RUNNING_GENERATE_ELECTRICITY_2 = 12;
    public static final int ENERGY_STATE_HYBRID_POWER_RUNNING_GENERATE_ELECTRICITY_3 = 13;
    public static final int ENERGY_STATE_LOW_SPEED_GENERATE_ELECTRICITY_1 = 20;
    public static final int ENERGY_STATE_LOW_SPEED_GENERATE_ELECTRICITY_2 = 21;
    public static final int ENERGY_STATE_MAX = 255;
    public static final int ENERGY_STATE_NONE = 0;
    public static final int ENERGY_STATE_SERIES_REAR_WHEEL_DRIVE = 19;

    BYDAutoEnergyDevice() {
        super((Context)null);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoEnergyDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public int getEnergyMode() {
        throw new RuntimeException("Stub!");
    }

    public int getOperationMode() {
        throw new RuntimeException("Stub!");
    }

    public int getPowerGenerationState() {
        throw new RuntimeException("Stub!");
    }

    public int getPowerGenerationValue() {
        throw new RuntimeException("Stub!");
    }

    public int getRoadSurfaceMode() {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoEnergyListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoEnergyListener l) {
        throw new RuntimeException("Stub!");
    }
}
