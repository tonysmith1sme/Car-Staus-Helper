package android.hardware.bydauto.adas;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.support.annotation.Keep;

@Keep
public class BYDAutoADASDevice extends AbsBYDAutoDevice {
    public static final int ABS_TCS_ESP_ACC_AEB_ACTIVE = 9;
    public static final int ADAS_COMMAND_BUSY = -2147482647;
    public static final int ADAS_COMMAND_FAILED = -2147482648;
    public static final int ADAS_COMMAND_INVALID_VALUE = -2147482645;
    public static final int ADAS_COMMAND_SUCCESS = 0;
    public static final int ADAS_COMMAND_TIMEOUT = -2147482646;
    static final String ADAS_GET_PERM = "android.permission.BYDAUTO_ADAS_GET";
    public static final int ADAS_INVALID = 0;
    static final String ADAS_SET_PERM = "android.permission.BYDAUTO_ADAS_SET";
    protected static final int ADAS_SLA_STATE_DEFECT = 4;
    protected static final int ADAS_SLA_STATE_FUSION_MODE = 1;
    protected static final int ADAS_SLA_STATE_NV_ONLY_MODE = 3;
    protected static final int ADAS_SLA_STATE_OFF = 0;
    protected static final int ADAS_SLA_STATE_VISION_MODE = 2;
    public static final int ADAS_VALID = 1;
    public static final int ANY_DOOR_OPEN = 3;
    public static final int APS_FAILURE = 5;
    public static final int APS_TIME_OUT = 16;
    public static final int AUTO_ESP_STATE_OFF = 1;
    public static final int AUTO_ESP_STATE_ON = 0;
    protected static final int AUTO_HOLD_STATE1 = 0;
    protected static final int AUTO_HOLD_STATE2 = 1;
    protected static final int AUTO_HOLD_STATE3 = 2;
    protected static final int AUTO_HOLD_STATE4 = 3;
    public static final int AUTO_IBOOSTER_STATE_COMFORTABLE = 2;
    public static final int AUTO_IBOOSTER_STATE_SPORT = 1;
    public static final int AUTO_IBOOSTER_STATE_STANDARD = 0;
    public static final int AVM_APA_FAULT = 3;
    public static final int AVM_FUNCTION_OFF = 1;
    public static final int AVM_FUNCTION_ON = 2;
    public static final int BLUETOOTH_DISCONNECT = 7;
    public static final int BLUETOOTH_DISCONNECT_TIMEOUT = 19;
    public static final int BRAKE_FOOT_SENSE_COMFORTABLE = 2;
    public static final int BRAKE_FOOT_SENSE_COMFORTABLE_GET = 0;
    public static final int BRAKE_FOOT_SENSE_COMFORTABLE_SET = 2;
    public static final int BRAKE_FOOT_SENSE_INVALID = 3;
    public static final int BRAKE_FOOT_SENSE_INVALID_SET = 0;
    public static final int BRAKE_FOOT_SENSE_SPORT = 1;
    public static final int BRAKE_FOOT_SENSE_STANDARD = 0;
    public static final int BRAKE_FOOT_SENSE_STANDARD_GET = 1;
    public static final int BRAKE_FOOT_SENSE_STANDARD_SET = 1;
    public static final int BRAKING_PEDAL = 4;
    public static final int BSD_STATIC_CALIBRATION_INVALID = 0;
    public static final int BSD_STATIC_CALIBRATION_START = 1;
    public static final int CHARGING_GUN_ACTIVE = 12;
    public static final int CHOOSE_PARK_MODE = 7;
    public static final int CLEANING_CAMERA = 9;
    public static final int CLEAR_AHEAD = 5;
    public static final int CONFIRM_PARKING_OUT_DIRECTION = 3;
    public static final int CONNECT_BLUETOOTH_TO_CONTROL = 8;
    public static final int CST_STATE_ACTIVE_GET = 2;
    public static final int CST_STATE_DISABLED_GET = 0;
    public static final int CST_STATE_FAILURE_GET = 3;
    public static final int CST_STATE_STANDBY_GET = 1;
    protected static final int CST_SWITCH_INVALID_SET = 0;
    protected static final int CST_SWITCH_OFF_SET = 2;
    protected static final int CST_SWITCH_ON_SET = 1;
    public static final int CURRENT_STEP_NUMBER_OVER_THRESHOLD = 14;
    private static final boolean DEBUG = true;
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DEVICE_THE_FEATURE_LINK_ERROR = 65535;
    public static final int DEVICE_THE_FEATURE_NEVER_GET = 2;
    public static final int DRIVER_OVERRIDE = 11;
    public static final int EPB_ACTIVE = 10;
    public static final int EPB_FAILURE = 20;
    public static final int EPS_FAILURE = 1;
    public static final int ESC_FAILURE = 2;
    public static final int ESP_KEY_NO_ACTION = 0;
    public static final int ESP_KEY_PRESSED = 1;
    public static final int ESP_OFFLINE = 3;
    public static final int ESP_WITHOUT_HARD_SWITCH = 2;
    public static final int ESP_WITH_HARD_SWITCH = 1;
    public static final String FEATURE_ADAS_AVH = "AVH";
    public static final String FEATURE_ADAS_BRAKE_FOOT_SENSE = "BrakeFootSense";
    public static final String FEATURE_ADAS_CST = "CST";
    public static final String FEATURE_ADAS_ESP = "ESP";
    public static final String FEATURE_ADAS_HDC = "HDC";
    public static final String FEATURE_ADAS_LDW = "LDW";
    public static final String FEATURE_ADAS_LKS = "LKS";
    public static final String FEATURE_ADAS_TJA = "TJA";
    public static final String FEATURE_AEB = "AutomaticEmergencyBrake";
    public static final String FEATURE_BSD = "BlindSpotDetection";
    public static final String FEATURE_HMA = "IntelligentFarAndNearLight";
    public static final String FEATURE_LDSW = "LaneDepartureWarning";
    public static final String FEATURE_LKS_MODE = "LaneKeepingAssistMode";
    public static final String FEATURE_LKS_SENSITIVITY = "LaneKeepingAssistSensitivity";
    public static final String FEATURE_PCW = "PredictionCollisionWarning";
    public static final String FEATURE_SLA = "TrafficSignRecognition";
    public static final int FINISH_PARKING = 2;
    public static final int FUNCATION_DEFECT = 2;
    public static final int FUNCATION_OFF = 0;
    public static final int FUNCATION_ON = 1;
    public static final int HDC_FUNCTION_INVALID = 0;
    public static final int HDC_FUNCTION_OFF = 2;
    public static final int HDC_FUNCTION_ON = 1;
    protected static final int HDC_STATE_OFFLINE = 3;
    protected static final int HDC_STATE_OFF_NO_LAMP = 0;
    protected static final int HDC_STATE_ON_LAMP_FLASH = 2;
    protected static final int HDC_STATE_ON_LAMP_ON = 1;
    public static final int HMA_STATE_ACTIVE = 3;
    public static final int HMA_STATE_CAMERA_BLOCKED = 5;
    public static final int HMA_STATE_FAULT = 4;
    public static final int HMA_STATE_OFF = 0;
    public static final int HMA_STATE_PASSIVE = 1;
    public static final int HMA_STATE_STANDBY = 2;
    public static final int INTERRUPT_NUMBER_OVER_THRESHOLD = 15;
    public static final int INVALID_BRAKE_PEDAL_SWITCH_STATE = 7;
    public static final int INVALID_VEHICLE_SPEED = 6;
    public static final int IS_PARKING = 1;
    public static final int IS_PARKING_OUT = 4;
    public static final int LDSW_TYPE_ALL = 2;
    public static final int LDSW_TYPE_SOUND = 1;
    public static final int LDSW_TYPE_VIBRATE = 0;
    public static final int LDW_STATIC_CALIBRATION_INVALID = 0;
    public static final int LDW_STATIC_CALIBRATION_START = 1;
    public static final int LKS_MODE_ALL = 3;
    public static final int LKS_MODE_DIVERGE = 1;
    public static final int LKS_MODE_KEEPING = 2;
    public static final int LKS_MODE_OFF = 0;
    public static final int LKS_MODE_OPEN = 4;
    public static final int LKS_SENSITIVITY_INTELLIGENCE = 0;
    public static final int LKS_SENSITIVITY_STANDARD = 1;
    public static final int MIRROR_FOLD = 5;
    public static final int NOT_READY = 0;
    public static final int NO_INTERRUPTION = 0;
    public static final int OBSTACLE_ON_PATH = 2;
    public static final int PAUSE_AUTO_PARKING_ON = 1;
    public static final int PRESS_INVALID = 0;
    public static final int PRESS_VALID = 1;
    public static final int READY = 1;
    public static final int RESET_INVALID = 0;
    public static final int RESET_ITEM_DRIVE = 2;
    public static final int RESET_ITEM_LKS_LDWS = 1;
    public static final int RESET_ITEM_SAFETY = 0;
    public static final int RESET_VALID = 1;
    public static final int RETAIN = 6;
    public static final int ROUTE_PLANNING_FAILURE = 18;
    public static final int SAS_FAILURE = 4;
    public static final int SAS_NOT_CALIBRATED = 8;
    public static final int SCU_FAILURE = 3;
    public static final int SEAT_BELT_RELEASED = 6;
    public static final int SLOT_SEARCHING = 1;
    public static final int SPACE_IN_PARKOUT_MODE_LIMITED = 13;
    protected static final String TAG = "BYDAutoADASDevice";
    public static final int TJA_INVALID = 0;
    public static final int TJA_OFF = 1;
    public static final int TJA_ON = 2;
    public static final int TJA_STATE_ACTIVE1 = 2;
    public static final int TJA_STATE_ACTIVE2 = 3;
    public static final int TJA_STATE_FAULT = 4;
    public static final int TJA_STATE_OFF = 0;
    public static final int TJA_STATE_PASSIVE = 1;
    public static final int VEHICLE_SPEED_OVER_THRESHOL = 17;
    public static final int WAITING_FOR_DRIVER_OPERATE_GEAR = 4;
    public static final int WAITING_FOR_VEHICLE_DRIVER_CONFIRM_PARK = 5;
    public static final int WAITING_FOR_VEHICLE_SLOW_DOWN = 3;
    public static final int WAITING_FOR_VEHICLE_STOP = 2;
    protected final int HDC_SWITCH_INVALID;
    protected final int HDC_SWITCH_OFF;
    protected final int HDC_SWITCH_ON;
    private final Context mContext;
    private static BYDAutoADASDevice mInstance = null;
    private static int mDeviceType = 1038;

    private BYDAutoADASDevice(Context con) {
        super(con);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoADASDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    //    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        throw new RuntimeException("Stub!");
    }

    //    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        throw new RuntimeException("Stub!");
    }

    //    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        throw new RuntimeException("Stub!");
    }

    public int hasFeature(String feature) {
        throw new RuntimeException("Stub!");
    }

    public int hasFeature2(String feature) {
        throw new RuntimeException("Stub!");
    }

    public int setHMAState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getHMAState() {
        throw new RuntimeException("Stub!");
    }

    public int getLaneOffsetState() {
        throw new RuntimeException("Stub!");
    }

    public int setPCWState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getPCWState() {
        throw new RuntimeException("Stub!");
    }

    public int setAEBState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getAEBState() {
        throw new RuntimeException("Stub!");
    }

    public int setESPState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getESPState() {
        throw new RuntimeException("Stub!");
    }

    public int setLKSMode(int mode) {
        throw new RuntimeException("Stub!");
    }

    public int getLKSMode() {
        throw new RuntimeException("Stub!");
    }

    public int setLKSSensitivity(int sensitivity) {
        throw new RuntimeException("Stub!");
    }

    public int getLKSSensitivity() {
        throw new RuntimeException("Stub!");
    }

    public int setLDSWType(int type) {
        throw new RuntimeException("Stub!");
    }

    public int getLDSWType() {
        throw new RuntimeException("Stub!");
    }

    public int setBSDState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getBSDState() {
        throw new RuntimeException("Stub!");
    }

    public int setSLAState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getSLAState() {
        throw new RuntimeException("Stub!");
    }

    public int setAVHState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getAVHState() {
        throw new RuntimeException("Stub!");
    }

    public int setIboosterState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getIboosterState() {
        throw new RuntimeException("Stub!");
    }

    public int setBSDStaticCalibration(int value) {
        throw new RuntimeException("Stub!");
    }

    public int setLDWStaticCalibration(int value) {
        throw new RuntimeException("Stub!");
    }

    public int setTJAState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getTJAState() {
        throw new RuntimeException("Stub!");
    }

    public int reset(int item, int value) {
        throw new RuntimeException("Stub!");
    }

    public int getESPOnlineState() {
        throw new RuntimeException("Stub!");
    }

    public int setHDCState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getHDCState() {
        throw new RuntimeException("Stub!");
    }

    public int get123State() {
        throw new RuntimeException("Stub!");
    }

    public int setBrakeFootSenseState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getBrakeFootSenseState() {
        throw new RuntimeException("Stub!");
    }

    public int setCSTState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getCSTState() {
        throw new RuntimeException("Stub!");
    }

    public int getCSTData() {
        throw new RuntimeException("Stub!");
    }

    public int getSuspen() {
        throw new RuntimeException("Stub!");
    }

    public int getInterActiveHint() {
        throw new RuntimeException("Stub!");
    }

    public int getAutoSearchState() {
        throw new RuntimeException("Stub!");
    }

    public int getAbortReason() {
        throw new RuntimeException("Stub!");
    }

    public int getActive() {
        throw new RuntimeException("Stub!");
    }

    public int getAutoParkButtonState() {
        throw new RuntimeException("Stub!");
    }

    public int setAVMSwitchState(int state) {
        throw new RuntimeException("Stub!");
    }

    public int getAVMSwitchState() {
        throw new RuntimeException("Stub!");
    }

    public int setAdasReset(int safetyResetState, int lksLdwsResetState, int driveResetState) {
        throw new RuntimeException("Stub!");
    }

    public int getHDCKeyState() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        throw new RuntimeException("Stub!");
    }

    //    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoADASListener l) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoADASListener l, int[] featureIDs) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoADASListener l) {
        throw new RuntimeException("Stub!");
    }
}