package android.hardware.bydauto.sensor;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

import androidx.annotation.Keep;

@Keep
public final class BYDAutoSensorDevice extends AbsBYDAutoDevice {
    public static final int AUTO_SLOPE_MAX = 60;
    public static final int AUTO_SLOPE_MIN = -60;
    public static final int AX_223 = 1;
    public static final int AX_OFFSET_223 = 2;
    private static final boolean DEBUG = true;
    public static final int G_SENSOR_OR_ANGLE_MAX = 359;
    public static final int G_SENSOR_OR_ANGLE_MIN = 0;
    public static final double HUMIDITY_MAX = 100.0d;
    public static final double HUMIDITY_MIN = 0.0d;
    public static final int LIGHT_INTENSITY_LEVEL1 = 1;
    public static final int LIGHT_INTENSITY_LEVEL2 = 2;
    public static final int LIGHT_INTENSITY_LEVEL3 = 3;
    public static final int LIGHT_INTENSITY_LEVEL4 = 4;
    public static final int LIGHT_INTENSITY_LEVEL5 = 5;
    public static final int SENSOR_AX_INVALID = 0;
    public static final int SENSOR_AX_VALID = 1;
    public static final int SENSOR_COMMAND_BUSY = -2147482647;
    public static final int SENSOR_COMMAND_FAILED = -2147482648;
    public static final int SENSOR_COMMAND_INVALID_VALUE = -2147482645;
    public static final int SENSOR_COMMAND_SUCCESS = 0;
    public static final int SENSOR_COMMAND_TIMEOUT = -2147482646;
    static final String SENSOR_GET_PERM = "android.permission.BYDAUTO_SENSOR_GET";
    static final String SENSOR_SET_PERM = "android.permission.BYDAUTO_SENSOR_SET";
    protected static final String TAG = "BYDAutoSensorDevice";
    public static final double TEMPERATURE_MAX = 125.0d;
    public static final double TEMPERATURE_MIN = -40.0d;
    private final Context mContext;
    private static BYDAutoSensorDevice mInstance = null;
    private static int mDeviceType = 1043;

    private BYDAutoSensorDevice(Context con) {
        super(con);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoSensorDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

//    @Override // android.hardware.bydauto.AbsBYDAutoDevice
//    public int getDevicetype() {
//        return mDeviceType;
//    }

//    @Override // android.hardware.bydauto.AbsBYDAutoDevice
//    public String getSetPermission() {
//        return SENSOR_SET_PERM;
//    }

//    @Override // android.hardware.bydauto.AbsBYDAutoDevice
//    public String getGetPermission() {
//        return SENSOR_GET_PERM;
//    }

    public double getTemperatureSensorValue() {
        throw new RuntimeException("Stub!");
    }

    public double getHumiditySensorValue() {
        throw new RuntimeException("Stub!");
    }

    public int getLightIntensity() {
        throw new RuntimeException("Stub!");
    }

    public int setOrientationAngle(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getSlope() {
        throw new RuntimeException("Stub!");
    }

    public int getSensorAxValue(int flag) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getSensorAxValue() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        throw new RuntimeException("Stub!");
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
        return 1043;
    }

//    @Override // android.hardware.IBYDAutoDevice
//    public int[] getFeatureList() {
//        return null;
//    }

    public void registerListener(AbsBYDAutoSensorListener l) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoSensorListener l, int[] featureIDs) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoSensorListener l) {
        throw new RuntimeException("Stub!");
    }
}