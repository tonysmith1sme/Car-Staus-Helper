package android.hardware.bydauto.bigdata;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.support.annotation.Keep;

@Keep
public final class BYDAutoBigDataDevice extends AbsBYDAutoDevice {
    public static final int BIGDATA_COMMAND_BUSY = -2147482647;
    public static final int BIGDATA_COMMAND_FAILED = -2147482648;
    public static final int BIGDATA_COMMAND_INVALID_VALUE = -2147482645;
    public static final int BIGDATA_COMMAND_SUCCESS = 0;
    public static final int BIGDATA_COMMAND_TIMEOUT = -2147482646;
    static final String BIGDATA_GET_PERM = "android.permission.BYDAUTO_BIGDATA_GET";
    private static final boolean DEBUG = true;
    protected static final String TAG = "BYDAutoBigDataDevice";
    private static int mDeviceType = 1061;
    private static BYDAutoBigDataDevice mInstance = null;
    private final Context mContext;

    private BYDAutoBigDataDevice(Context con) {
        super(con);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoBigDataDevice getInstance(Context con) {
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

    public void getAllStatus() {throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
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

    public void registerListener(AbsBYDAutoBigDataListener l) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoBigDataListener l, int[] featureIDs) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoBigDataListener l) {
        throw new RuntimeException("Stub!");
    }
}