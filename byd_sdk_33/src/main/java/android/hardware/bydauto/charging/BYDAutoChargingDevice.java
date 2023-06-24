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
    public static final int APPOINTMENT_FAILED = 2;
    public static final int APPOINTMENT_INVALID = 0;
    public static final int APPOINTMENT_VALID = 1;
    public static final int APPO_DAY_INVALID = 0;
    public static final int APPO_DAY_MAX = 31;
    public static final int APPO_DAY_MIN = 1;
    public static final int APPO_HOUR_INVALID = 31;
    public static final int APPO_HOUR_MAX = 23;
    public static final int APPO_HOUR_MIN = 0;
    public static final int APPO_MINUTE_INVALID = 63;
    public static final int APPO_MINUTE_MAX = 59;
    public static final int APPO_MINUTE_MIN = 0;
    public static final int APPO_MONTH_INVALID1 = 0;
    public static final int APPO_MONTH_INVALID2 = 15;
    public static final int APPO_MONTH_MAX = 12;
    public static final int APPO_MONTH_MIN = 1;
    public static final int APPO_YEAR_INVALID = 2127;
    public static final int APPO_YEAR_MAX = 2099;
    public static final int APPO_YEAR_MIN = 2000;
    public static final int BATTERY_TYPE_INVALID = 65535;
    public static final int BATTERY_TYPE_IRON = 1;
    public static final int BATTERY_TYPE_LEAD_ACID = 0;
    public static final int CAP_CONFIRM = 1;
    public static final int CAP_INVALID = 0;
    public static final int CAP_NOPROMPT = 1;
    public static final int CAP_PROMPT = 2;
    public static final int CAP_TIMEOUT = 2;
    public static final int CAR_DISCHARGE_CLOSE = 2;
    public static final int CAR_DISCHARGE_INVALID = 0;
    public static final int CAR_DISCHARGE_OPEN = 1;
    public static final int CHARGE_MODE_IMMEDIATELY = 1;
    public static final int CHARGE_MODE_INVALID = 0;
    public static final int CHARGE_MODE_RESERVE = 2;
    public static final int CHARGE_MODE_SMART = 3;
    public static final int CHARGE_STOP_CAPACITY_100 = 1;
    public static final int CHARGE_STOP_CAPACITY_50 = 6;
    public static final int CHARGE_STOP_CAPACITY_60 = 5;
    public static final int CHARGE_STOP_CAPACITY_70 = 4;
    public static final int CHARGE_STOP_CAPACITY_80 = 3;
    public static final int CHARGE_STOP_CAPACITY_90 = 2;
    public static final int CHARGE_STOP_CAPACITY_INVALID = 0;
    public static final int CHARGE_STOP_INVALID = 0;
    public static final int CHARGE_STOP_OFF = 1;
    public static final int CHARGE_STOP_ON = 2;
    public static final int CHARGE_STOP_SUPPORT_AVAILABLE = 2;
    public static final int CHARGE_STOP_SUPPORT_DEFAULT = 3;
    public static final int CHARGE_STOP_SUPPORT_DISAVAILABLE = 1;
    public static final int CHARGE_STOP_SUPPORT_RESERVED = 0;
    public static final int CHARGE_TEMP_CTL_STATE_INVALID = 0;
    public static final int CHARGE_TEMP_CTL_STATE_OFF = 2;
    public static final int CHARGE_TEMP_CTL_STATE_ON = 1;
    public static final int CHARGE_WIRELESS_CHARGING_INVALID = 0;
    public static final int CHARGE_WIRELESS_CHARGING_OFF = 2;
    public static final int CHARGE_WIRELESS_CHARGING_ON = 1;
    public static final int CHARGE_WIRELESS_CHARGING_ONLINE_OFF = 0;
    public static final int CHARGE_WIRELESS_CHARGING_ONLINE_ON = 1;
    public static final int CHARGING_BATTERY_STATE_BREAKDOWN_AC = 8;
    public static final int CHARGING_BATTERY_STATE_BREAKDOWN_C10 = 5;
    public static final int CHARGING_BATTERY_STATE_BREAKDOWN_CHARGER = 7;
    public static final int CHARGING_BATTERY_STATE_BREAKDOWN_CHARGING_GUN = 6;
    public static final int CHARGING_BATTERY_STATE_CHARGING = 1;
    public static final int CHARGING_BATTERY_STATE_CHARGING_PAUSE = 13;
    public static final int CHARGING_BATTERY_STATE_CHARG_FINISH = 2;
    public static final int CHARGING_BATTERY_STATE_CHARG_TERMINATE = 4;
    public static final int CHARGING_BATTERY_STATE_DISCHARG = 3;
    public static final int CHARGING_BATTERY_STATE_DISCHARG_CBU = 10;
    public static final int CHARGING_BATTERY_STATE_DISCHARG_FINISH = 12;
    public static final int CHARGING_BATTERY_STATE_READY = 0;
    public static final int CHARGING_BATTERY_STATE_SCHEDULE = 9;
    public static final int CHARGING_BATTERY_STATE_TIMEOUT = 11;
    public static final double CHARGING_CAPACITY_MAX = 131.07d;
    public static final double CHARGING_CAPACITY_MIN = 0.0d;
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
    static final String CHARGING_GET_PERM = "android.permission.BYDAUTO_CHARGING_GET";
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
    public static final double CHARGING_POWER_MAX = 500.0d;
    public static final double CHARGING_POWER_MIN = -500.0d;
    public static final int CHARGING_SCHEDULE_STATE_CANCEL = 2;
    public static final int CHARGING_SCHEDULE_STATE_INVALID = 1;
    public static final int CHARGING_SCHEDULE_STATE_LOCAL = 4;
    public static final int CHARGING_SCHEDULE_STATE_NONE = 3;
    public static final int CHARGING_SCHEDULE_STATE_REMOTE = 5;
    static final String CHARGING_SET_PERM = "android.permission.BYDAUTO_CHARGING_SET";
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
    private static final boolean DEBUG = false;
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DEVICE_THE_FEATURE_LINK_ERROR = 65535;
    public static final int DEVICE_THE_FEATURE_NEVER_GET = 2;
    public static final int DISCHARGE_CONNECT_DISCHARGE_CAR = 1;
    public static final int DISCHARGE_CONNECT_DISCHARGE_CHARGE_CAR = 2;
    public static final int DISCHARGE_CONNECT_NONE = 0;
    public static final int DISCHARGE_LIMIT_VAL_MAX = 100;
    public static final int DISCHARGE_LIMIT_VAL_MIN = 0;
    public static final int DISCHARGE_LOWEST_VAL_MAX = 100;
    public static final int DISCHARGE_LOWEST_VAL_MIN = 0;
    public static final double DISCHARGE_QUANTITY_MAX = 1000.0d;
    public static final double DISCHARGE_QUANTITY_MIN = 0.0d;
    public static final int DISCHARGE_STATE_FINISH = 2;
    public static final int DISCHARGE_STATE_HOUSEHOLD_APPLIANCE = 2;
    public static final int DISCHARGE_STATE_NON = 1;
    public static final int DISCHARGE_STATE_ONGOING = 1;
    public static final int DISCHARGE_STATE_POWER_SYSTEM = 5;
    public static final int DISCHARGE_STATE_READY = 0;
    public static final int DISCHARGE_STATE_SINGLE_PHASE_VEHICLE = 7;
    public static final int DISCHARGE_STATE_SOCKET = 6;
    public static final int DISCHARGE_STATE_THREE_PHASE_EQUIPMENT = 3;
    public static final int DISCHARGE_STATE_THREE_PHASE_VEHICLE = 4;
    public static final int DISCHARGE_TYPE_INNER_SOCKET = 2;
    public static final int DISCHARGE_TYPE_VTOL = 1;
    public static final int DISCHARGE_WARNING_STATE_INVALID = 1;
    public static final int DISCHARGE_WARNING_STATE_VALID = 2;
    public static final int ELECTRICITY_PRICE_LEVEL_LENGTH = 24;
    public static final String FEATURE_CHARGE_BY_APPOINTMENT = "ChargeByAppointment";
    public static final String FEATURE_CHARGE_DISCHARGE_TO_CAR = "ChargeDischargeToCar";
    public static final String FEATURE_CHARGE_STOP = "ChargeStop";
    public static final String FEATURE_CHARGE_TEMP_CTL = "ChargeTemperatureControl";
    public static final String FEATURE_CHARGE_WIRELESS_CHARGING = "ChargeWirlessCharging";
    public static final String FEATURE_CHARGING_SMART_TRAVEL = "SmartTravel";
    public static final String FEATURE_SOC_SAVE_SWITCH_CONFIG = "soc_sava_switch_config";
    public static final int HAS_WEATHER_AND_TIME_REQUIREMENT = 0;
    public static final int HOUR_MAX = 23;
    public static final int HOUR_MIN = 0;
    public static final int LOW_WARN_NONE = 0;
    public static final int LOW_WARN_TIPS = 1;
    public static final int MINUTE_MAX = 59;
    public static final int MINUTE_MIN = 0;
    public static final int NOT_HAS_WEATHER_AND_TIME_REQUIREMENT = 1;
    public static final int NOT_SMART_CHARGING_STATE = 3;
    public static final int PREDICTED_TEMPERATURE_MAX = 160;
    public static final int PREDICTED_TEMPERATURE_MIN = -80;
    public static final int RESET_INVALID = 0;
    public static final int RESET_ITEM_CHARGE = 0;
    public static final int RESET_VALID = 1;
    public static final int REST_HOUR_MAX = 254;
    public static final int REST_HOUR_MIN = 0;
    public static final int SAFE_BATTERY_INVALID = 127;
    public static final int SAFE_BATTERY_MAX = 100;
    public static final int SAFE_BATTERY_MIN = 20;
    public static final int SMART_CHARGING_STATE_ENTER = 0;
    public static final int SMART_CHARGING_STATE_EXIT = 2;
    public static final int SMART_CHARGING_STATE_ONGOING = 1;
    public static final int SOC_SAVE_SWITCH_INVALID = 0;
    public static final int SOC_SAVE_SWITCH_OFF = 1;
    public static final int SOC_SAVE_SWITCH_ON = 2;
    protected static final String TAG = "BYDAutoChargingDevice";
    public static final int WIRELESS_CHARGING_STATE_FAULT = 2;
    public static final int WIRELESS_CHARGING_STATE_FULL = 1;
    public static final int WIRELESS_CHARGING_STATE_ONGOING = 0;
    public static final int WIRELESS_CHARGING_STATE_STANDBY = 3;
    private static int mDeviceType = 1009;
    private static BYDAutoChargingDevice mInstance = null;
    private final Context mContext;

    private BYDAutoChargingDevice(Context con) {
        super(con);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoChargingDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

//    @Override // android.hardware.bydauto.AbsBYDAutoDevice
//    public int getDevicetype() {
//        throw new RuntimeException("Stub!");
//    }
//
//    @Override // android.hardware.bydauto.AbsBYDAutoDevice
//    public String getSetPermission() {
//        throw new RuntimeException("Stub!");
//    }
//
//    @Override // android.hardware.bydauto.AbsBYDAutoDevice
//    public String getGetPermission() {
//        throw new RuntimeException("Stub!");
//    }

    public int getChargingState() {
        throw new RuntimeException("Stub!");
    }

    public int setChargingMode(int mode) {
        throw new RuntimeException("Stub!");
    }

    public int getChargingMode() {
        throw new RuntimeException("Stub!");
    }

    public ChargingTimerInfo getChargingTimerInfo(int type) {
        throw new RuntimeException("Stub!");
    }

    public int setChargingTimerInfo(ChargingTimerInfo timerInfo) {
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

    public int getSmartChargingState() {
        throw new RuntimeException("Stub!");
    }

    public int getDischargeState(int type) {
        throw new RuntimeException("Stub!");
    }

    public int getDischargeWarningState() {
        throw new RuntimeException("Stub!");
    }

    public int hasFeature(String feature) {
        throw new RuntimeException("Stub!");
    }

    public int getWirelessChargingState() {
        throw new RuntimeException("Stub!");
    }

    public int setWirelessChargingSwitchState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getWirelessChargingSwitchState() {
        throw new RuntimeException("Stub!");
    }

    public int getWirelessChargingOnline5sState() {
        throw new RuntimeException("Stub!");
    }

    public int reset(int item, int value) {
        throw new RuntimeException("Stub!");
    }

    public int setChargeTempCtlState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getChargeTempCtlState() {
        throw new RuntimeException("Stub!");
    }

    public int getBatteryType() {
        throw new RuntimeException("Stub!");
    }

    public int getChargeStopSupportConfig() {
        throw new RuntimeException("Stub!");
    }

    public int setChargeStopSwitchState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getChargeStopSwitchState() {
        throw new RuntimeException("Stub!");
    }

    public int setChargeStopCapacityState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getChargeStopCapacityState() {
        throw new RuntimeException("Stub!");
    }

    public int setPredictTemperature(int minTemp, int maxTemp) {
        throw new RuntimeException("Stub!");
    }

    public int getWeatherAndTimeRequest() {
        throw new RuntimeException("Stub!");
    }

    public int getCarDischargeState() {
        throw new RuntimeException("Stub!");
    }

    public int setCarDischargeState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getCarDischargeLowWarn() {
        throw new RuntimeException("Stub!");
    }

    public int getVtovDischargeConnectState() {
        throw new RuntimeException("Stub!");
    }

    public int getVtovDischargeLimitVal() {
        throw new RuntimeException("Stub!");
    }

    public int sendVtovDischargeLimitVal(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getVtovDischargeLowestVal() {
        throw new RuntimeException("Stub!");
    }

    public double getVtovDischargeQuantity() {
        throw new RuntimeException("Stub!");
    }

    public int getCapState() {
        throw new RuntimeException("Stub!");
    }

    public int sendCapState(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getSocSaveSwitch() {
        throw new RuntimeException("Stub!");
    }

    public int setSocSaveSwitch(int value) {
        throw new RuntimeException("Stub!");
    }

    public int setCarPlan(int appointmentFlag, int chargeMode, int safeBattery, int year, int month, int day, int hour, int minute) {
        throw new RuntimeException("Stub!");
    }

    public int setElectricityPriceLevel(byte[] value) {
        throw new RuntimeException("Stub!");
    }

    public void setAllStatus() {throw new RuntimeException("Stub!");
    }

    public void getAllStatus() {throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, float value, Object data) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        throw new RuntimeException("Stub!");
    }

//    @Override // android.hardware.IBYDAutoDevice
//    public int[] getFeatureList() {
//        throw new RuntimeException("Stub!");
//    }

    public void registerListener(AbsBYDAutoChargingListener l) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoChargingListener l, int[] featureIDs) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoChargingListener l) {
        throw new RuntimeException("Stub!");
    }


}
