//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.instrument;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

import androidx.annotation.Keep;

@Keep
public class BYDAutoInstrumentDevice extends AbsBYDAutoDevice {
    public static final int BAR = 1;
    public static final int DEGREE_CENTIGRADE = 1;
    public static final int DEGREE_FAHRENHEIT = 2;
    public static final int DEVICE_HAS_THE_MALFUNCTION = 1;
    public static final int DEVICE_NOT_HAS_THE_MALFUNCTION = 0;
    public static final double EXTERNAL_CHARGING_POWER_MAX = 10000.0;
    public static final double EXTERNAL_CHARGING_POWER_MIN = 0.0;
    public static final int FUEL_CONSUMPTION_AND_DISTANCE_UNIT = 3;
    public static final int HP = 2;
    public static final int INSTRUMENT_ALARM_BUZZLE_ATATE_OFF = 0;
    public static final int INSTRUMENT_ALARM_BUZZLE_ATATE_ON = 1;
    public static final int INSTRUMENT_COMMAND_BUSY = -2147482647;
    public static final int INSTRUMENT_COMMAND_FAILED = -2147482648;
    public static final int INSTRUMENT_COMMAND_INVALID_VALUE = -2147482645;
    public static final int INSTRUMENT_COMMAND_SUCCESS = 0;
    public static final int INSTRUMENT_COMMAND_TIMEOUT = -2147482646;
    public static final int KM_P_L_AND_KM = 2;
    public static final int KPA = 3;
    public static final int KW = 1;
    public static final int KWH_P_100KM_AND_KM = 5;
    public static final int KWH_P_100MI_AND_MILE = 6;
    public static final int L_P_100KM_AND_KM = 1;
    public static final int MAINTENANCE_MILEAGE = 2;
    public static final int MAINTENANCE_MILEAGE_KILOMETER_MAX = 20001;
    public static final int MAINTENANCE_MILEAGE_KILOMETER_MIN = 0;
    public static final int MAINTENANCE_TIME = 1;
    public static final int MAINTENANCE_TIME_DAY_MAX = 991;
    public static final int MAINTENANCE_TIME_DAY_MIN = 0;
    public static final int MALFUNCTION_ABS_SYSTEM = 6;
    public static final int MALFUNCTION_BATTERY = 16;
    public static final int MALFUNCTION_CHARGING_SYSTEM = 4;
    public static final int MALFUNCTION_ELECTRIC_PARKING_BRAKE = 10;
    public static final int MALFUNCTION_ENGINE = 5;
    public static final int MALFUNCTION_EPS = 12;
    public static final int MALFUNCTION_ESP = 7;
    public static final int MALFUNCTION_EV = 20;
    public static final int MALFUNCTION_FRONT_BELT = 23;
    public static final int MALFUNCTION_HEV = 21;
    public static final int MALFUNCTION_HIGH_BATTERY_TEMPERATURE = 17;
    public static final int MALFUNCTION_HIGH_MOTOR_TEMPERATURE = 15;
    public static final int MALFUNCTION_HIGH_WATER_TEMPERATURE = 9;
    public static final int MALFUNCTION_INSTRUMENT_DISPLAY = 1;
    public static final int MALFUNCTION_MACHINE_OIL_LOW_PRESSURE = 2;
    public static final int MALFUNCTION_OK = 19;
    public static final int MALFUNCTION_PARKING_BRAKE = 3;
    public static final int MALFUNCTION_POWER_SYSTEM = 18;
    public static final int MALFUNCTION_QUICK_AIR_LEAK = 8;
    public static final int MALFUNCTION_SMART_KEY = 22;
    public static final int MALFUNCTION_SRS = 11;
    public static final int MALFUNCTION_SVS = 14;
    public static final int MALFUNCTION_TYRE_PRESSURE = 13;
    public static final int MPG_GB_AND_MILE = 3;
    public static final int MPG_US_AND_MILE = 4;
    public static final int NO_MALFUNCTION = 0;
    public static final int POWER_UNIT = 4;
    public static final int PRESSURE_UNIT = 2;
    public static final int PSI = 2;
    public static final int TEMPERATURE_UNIT = 1;

    BYDAutoInstrumentDevice() {
        super((Context)null);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoInstrumentDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public int getMalfunctionInfo(int typeName) {
        throw new RuntimeException("Stub!");
    }

    public int getUnit(int unitName) {
        throw new RuntimeException("Stub!");
    }

    public int setUnit(int unitName, int unitValue) {
        throw new RuntimeException("Stub!");
    }

    public int getMaintenanceInfo(int typeName) {
        throw new RuntimeException("Stub!");
    }

    public int setMaintenanceInfo(int typeName, int infoValue) {
        throw new RuntimeException("Stub!");
    }

    public int getAlarmBuzzleState() {
        throw new RuntimeException("Stub!");
    }

    public double getExternalChargingPower() {
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

    public void registerListener(AbsBYDAutoInstrumentListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoInstrumentListener l) {
        throw new RuntimeException("Stub!");
    }
}
