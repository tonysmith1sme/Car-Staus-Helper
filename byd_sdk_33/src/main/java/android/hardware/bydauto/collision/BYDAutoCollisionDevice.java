package android.hardware.bydauto.collision;

import android.content.Context;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.util.Log;

import androidx.annotation.Keep;

import java.util.Arrays;

/* loaded from: classes.dex */
@Keep
public final class BYDAutoCollisionDevice extends AbsBYDAutoDevice {
    public static final int COLLISION_COMMAND_BUSY = -2147482647;
    public static final int COLLISION_COMMAND_FAILED = -2147482648;
    public static final int COLLISION_COMMAND_INVALID_VALUE = -2147482645;
    public static final int COLLISION_COMMAND_SUCCESS = 0;
    public static final int COLLISION_COMMAND_TIMEOUT = -2147482646;
    static final String COLLISION_GET_PERM = "android.permission.BYDAUTO_COLLISION_GET";
    static final String COLLISION_SET_PERM = "android.permission.BYDAUTO_COLLISION_SET";
    public static final int COLLISION_SIGNAL = 2;
    private static final boolean DEBUG = true;
    public static final int NORMAL_SIGNAL = 1;
    protected static final String TAG = "BYDAutoCollisionDevice";
    private static int mDeviceType = 1015;
    private static BYDAutoCollisionDevice mInstance = null;
    private final Context mContext;

    private BYDAutoCollisionDevice(Context con) {
        super(con);
        this.mContext = con;
    }

    public static synchronized BYDAutoCollisionDevice getInstance(Context con) {
        BYDAutoCollisionDevice bYDAutoCollisionDevice;
        synchronized (BYDAutoCollisionDevice.class) {
            if (mInstance == null) {
                mInstance = new BYDAutoCollisionDevice(con);
            }
            bYDAutoCollisionDevice = mInstance;
        }
        return bYDAutoCollisionDevice;
    }

//    @Override // android.hardware.bydauto.AbsBYDAutoDevice
//    public int getDevicetype() {
//        return mDeviceType;
//    }

//    @Override // android.hardware.bydauto.AbsBYDAutoDevice
//    public String getSetPermission() {
//        return COLLISION_SET_PERM;
//    }

//    @Override // android.hardware.bydauto.AbsBYDAutoDevice
//    public String getGetPermission() {
//        return COLLISION_GET_PERM;
//    }

    public byte[] getCollisionInfo() {
        this.mContext.enforceCallingOrSelfPermission(COLLISION_GET_PERM, null);
        byte[] resultInfo = super.getBuffer(mDeviceType, 564);
        return resultInfo;
    }

    public void getAllStatus() {
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        Log.d(TAG, "postEvent device_type: " + device_type + ", event_type =" + Integer.toHexString(event_type) + ", value = " + Arrays.toString(value));
        return onPostEvent(new BYDAutoCollisionEvent(device_type, event_type, value, data));
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 1015;
    }

//    @Override // android.hardware.IBYDAutoDevice
//    public int[] getFeatureList() {
//        return null;
//    }

    public void registerListener(AbsBYDAutoCollisionListener l) {
        Log.i(TAG, "registerListener");
        this.mContext.enforceCallingOrSelfPermission(COLLISION_GET_PERM, null);
        if (l != null) {
            super.registerListener((IBYDAutoListener) l);
        }
    }

    public void registerListener(AbsBYDAutoCollisionListener l, int[] featureIDs) {
        Log.i(TAG, "registerListener2");
        this.mContext.enforceCallingOrSelfPermission(COLLISION_GET_PERM, null);
        if (l != null) {
            super.registerListener((IBYDAutoListener) l, featureIDs);
        }
    }

    public void unregisterListener(AbsBYDAutoCollisionListener l) {
        Log.i(TAG, "unregisterListener");
        this.mContext.enforceCallingOrSelfPermission(COLLISION_GET_PERM, null);
        if (l != null) {
            super.unregisterListener((IBYDAutoListener) l);
        }
    }
}