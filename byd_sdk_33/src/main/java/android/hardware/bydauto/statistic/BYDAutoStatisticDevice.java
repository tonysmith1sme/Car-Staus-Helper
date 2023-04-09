//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.statistic;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

import androidx.annotation.Keep;

@Keep
public class BYDAutoStatisticDevice extends AbsBYDAutoDevice {
    public static final double AVERAGE_ELEC_CON_PHM_MAX = 99.9;
    public static final double AVERAGE_ELEC_CON_PHM_MIN = -99.9;
    public static final double AVERAGE_FUEL_CON_PHM_MAX = 51.1;
    public static final double AVERAGE_FUEL_CON_PHM_MIN = 0.0;
    public static final double ELEC_CONSUMPTION_MAX = 1676721.4;
    public static final double ELEC_CONSUMPTION_MIN = -1000.0;
    public static final double FUEL_CONSUMPTION_MAX = 104857.4;
    public static final double FUEL_CONSUMPTION_MIN = 0.0;
    public static final int STATISTIC_COMMAND_BUSY = -2147482647;
    public static final int STATISTIC_COMMAND_FAILED = -2147482648;
    public static final int STATISTIC_COMMAND_INVALID_VALUE = -2147482645;
    public static final int STATISTIC_COMMAND_SUCCESS = 0;
    public static final int STATISTIC_COMMAND_TIMEOUT = -2147482646;
    public static final double STATISTIC_DRIVING_TIME_MAX = 9999.9;
    public static final double STATISTIC_DRIVING_TIME_MIN = 0.0;
    public static final int STATISTIC_ELEC_DRIVING_RANGE_MAX = 511;
    public static final int STATISTIC_ELEC_DRIVING_RANGE_MIN = 0;
    public static final double STATISTIC_ELEC_PERCENTAGE_MAX = 100.0;
    public static final double STATISTIC_ELEC_PERCENTAGE_MIN = 0.0;
    public static final int STATISTIC_FUEL_AD_MAX = 4095;
    public static final int STATISTIC_FUEL_AD_MIN = 0;
    public static final int STATISTIC_FUEL_DRIVING_RANGE_MAX = 4095;
    public static final int STATISTIC_FUEL_DRIVING_RANGE_MIN = 0;
    public static final int STATISTIC_FUEL_PERCENTAGE_MAX = 100;
    public static final int STATISTIC_FUEL_PERCENTAGE_MIN = 0;
    public static final int STATISTIC_KEY_BATTERY_LEVEL_LOW = 1;
    public static final int STATISTIC_KEY_BATTERY_LEVEL_NORMAL = 2;
    public static final double STATISTIC_LAST_ELEC_CON_PHM_MAX = 99.9;
    public static final double STATISTIC_LAST_ELEC_CON_PHM_MIN = -99.9;
    public static final double STATISTIC_LAST_FUEL_CON_PHM_MAX = 51.1;
    public static final double STATISTIC_LAST_FUEL_CON_PHM_MIN = 0.0;
    public static final int STATISTIC_MILEAGE_MAX = 999999;
    public static final int STATISTIC_MILEAGE_MIN = 0;
    public static final double STATISTIC_TOTAL_ELEC_CONSUMPTION_MAX = 1676721.4;
    public static final double STATISTIC_TOTAL_ELEC_CONSUMPTION_MIN = -1000.0;
    public static final double STATISTIC_TOTAL_ELEC_CON_PHM_MAX = 99.9;
    public static final double STATISTIC_TOTAL_ELEC_CON_PHM_MIN = -99.9;
    public static final double STATISTIC_TOTAL_FUEL_CONSUMPTION_MAX = 104857.4;
    public static final double STATISTIC_TOTAL_FUEL_CONSUMPTION_MIN = 0.0;
    public static final double STATISTIC_TOTAL_FUEL_CON_PHM_MAX = 51.1;
    public static final double STATISTIC_TOTAL_FUEL_CON_PHM_MIN = 0.0;
    public static final int STATISTIC_TOTAL_MILEAGE_MAX = 999999;
    public static final int STATISTIC_TOTAL_MILEAGE_MIN = 0;
    public static final double TRAVEL_TIME_MAX = 99999.0;
    public static final double TRAVEL_TIME_MIN = 0.0;

    BYDAutoStatisticDevice() {
        super((Context) null);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoStatisticDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public int getTotalMileageValue() {
        throw new RuntimeException("Stub!");
    }

    public double getTotalFuelConValue() {
        throw new RuntimeException("Stub!");
    }

    public double getTotalElecConValue() {
        throw new RuntimeException("Stub!");
    }

    public double getDrivingTimeValue() {
        throw new RuntimeException("Stub!");
    }

    public double getLastFuelConPHMValue() {
        throw new RuntimeException("Stub!");
    }

    public double getTotalFuelConPHMValue() {
        throw new RuntimeException("Stub!");
    }

    public double getLastElecConPHMValue() {
        throw new RuntimeException("Stub!");
    }

    public double getTotalElecConPHMValue() {
        throw new RuntimeException("Stub!");
    }

    public int getElecDrivingRangeValue() {
        throw new RuntimeException("Stub!");
    }

    public int getFuelDrivingRangeValue() {
        throw new RuntimeException("Stub!");
    }

    public int getFuelPercentageValue() {
        throw new RuntimeException("Stub!");
    }

    public double getElecPercentageValue() {
        throw new RuntimeException("Stub!");
    }

    public int getKeyBatteryLevel() {
        throw new RuntimeException("Stub!");
    }

    public int getEVMileageValue() {
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

    public void registerListener(AbsBYDAutoStatisticListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoStatisticListener l) {
        throw new RuntimeException("Stub!");
    }

    public int getMileageNumber(int status) {
        throw new RuntimeException("Stub!");
    }

    public int getHEVMileageValue() {
        throw new RuntimeException("Stub!");
    }

    public double getTravelTime(int target) {
        throw new RuntimeException("Stub!");
    }

    public int getWaterTemperature() {
        throw new RuntimeException("Stub!");
    }

    public double getInstantElecConValue() {
        throw new RuntimeException("Stub!");
    }

    public double getInstantFuelConValue() {
        throw new RuntimeException("Stub!");
    }

}
