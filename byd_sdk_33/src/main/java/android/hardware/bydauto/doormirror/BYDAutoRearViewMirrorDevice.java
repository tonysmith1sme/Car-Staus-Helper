package android.hardware.bydauto.doormirror;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import androidx.annotation.Keep;

@Keep
public final class BYDAutoRearViewMirrorDevice extends AbsBYDAutoDevice {
    private static final boolean DEBUG = false;
    public static final int REAR_VIEW_MIRROR_ANTIGLARE_STATE_INVALID = 0;
    public static final int REAR_VIEW_MIRROR_ANTIGLARE_STATE_MILD = 1;
    public static final int REAR_VIEW_MIRROR_ANTIGLARE_STATE_MODERATE = 2;
    public static final int REAR_VIEW_MIRROR_ANTIGLARE_STATE_SERIOUS = 3;
    public static final int REAR_VIEW_MIRROR_COMMAND_BUSY = -2147482647;
    public static final int REAR_VIEW_MIRROR_COMMAND_FAILED = -2147482648;
    public static final int REAR_VIEW_MIRROR_COMMAND_INVALID = -2147482645;
    public static final int REAR_VIEW_MIRROR_COMMAND_SUCCESS = 0;
    public static final int REAR_VIEW_MIRROR_COMMAND_TIMEOUT = -2147482646;
    public static final int REAR_VIEW_MIRROR_FOLD = 1;
    static final String REAR_VIEW_MIRROR_GET_PERM = "android.permission.BYDAUTO_REAR_VIEW_MIRROR_GET";
    public static final int REAR_VIEW_MIRROR_NO_ACTION = 0;
    static final String REAR_VIEW_MIRROR_SET_PERM = "android.permission.BYDAUTO_REAR_VIEW_MIRROR_SET";
    public static final int REAR_VIEW_MIRROR_UNFOLD = 2;
    protected static final String TAG = "BYDAutoRearViewMirrorDevice";
    private static int mDeviceType = 1047;
    private static BYDAutoRearViewMirrorDevice mInstance = null;
    private final Context mContext;

    private BYDAutoRearViewMirrorDevice(Context con) {
        super(con);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoRearViewMirrorDevice getInstance(Context con) {
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

    public int getAutoExternalRearMirrorState() {
        throw new RuntimeException("Stub!");
    }

    public int getAutoExternalRearMirrorAntiglareState() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoRearViewMirrorListener l) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoRearViewMirrorListener l, int[] featureIDs) {
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

    public void unregisterListener(AbsBYDAutoRearViewMirrorListener l) {
        throw new RuntimeException("Stub!");
    }
}