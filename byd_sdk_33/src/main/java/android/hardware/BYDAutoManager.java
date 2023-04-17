package android.hardware;

import android.content.Context;
import android.strategyservice.IStrategyListener;
import android.strategyservice.StrategyManager;

import androidx.annotation.Keep;

import java.util.ArrayList;
import java.util.List;

@Keep
public final class BYDAutoManager {
    public static final int BYDAUTO_COMMAND_RESULT_BUSY = -2147482647;
    public static final int BYDAUTO_COMMAND_RESULT_FAILED = -2147482648;
    public static final int BYDAUTO_COMMAND_RESULT_INVALID_VALUE = -2147482645;
    public static final int BYDAUTO_COMMAND_RESULT_SUCCESS = 0;
    public static final int BYDAUTO_COMMAND_RESULT_TIMEOUT = -2147482646;
    private static final boolean DEBUG = false;
    private static final String TAG = "BYDAutoManager";
    public static final int UNKNOWN_ERROR = Integer.MIN_VALUE;
    private Context mContext;
    private boolean mInitialized = false;
    private List<String> mItems;
    private StrategyListener mStrategyListener;
    private StrategyManager mStrategyService;
    public static BYDAutoManager mInstance = null;
    private static boolean mForbidCall = false;
    private static ArrayList<OnBYDAutoListener> mOnBYDAutoListeners = new ArrayList<>();

    @Keep
    public interface OnBYDAutoListener {
        void onChanged(int i, int i2, float f, Object obj);

        void onChanged(int i, int i2, int i3, Object obj);

        void onChanged(int i, int i2, byte[] bArr, Object obj);

        void onError(int i, String str);
    }

    private native int nativeDisableDevice(int i);

    private native void nativeDisableObserver();

    private native int nativeEnableDevice(int i);

    private native int nativeEnableDevice(int i, int[] iArr);

    private native void nativeEnableObserver();

    private native byte[] nativeGetBuffer(int i, int i2);

    private native float nativeGetDouble(int i, int i2);

    private native float[] nativeGetDoubleArray(int i, int[] iArr);

    private native int nativeGetInt(int i, int i2);

    private native int[] nativeGetIntArray(int i, int[] iArr);

    private native void nativeInit();

    private native int nativeSetBuffer(int i, int i2, byte[] bArr);

    private native int nativeSetDouble(int i, int i2, float f);

    private native int nativeSetDoubleArray(int i, int[] iArr, float[] fArr);

    private native int nativeSetInt(int i, int i2, int i3);

    private native int nativeSetIntArray(int i, int[] iArr, int[] iArr2);

    public BYDAutoManager(Context context) {
        throw new RuntimeException("Stub!");
    }

    private boolean init() {
        throw new RuntimeException("Stub!");
    }

    private void initPermissionStrategy(Context context) {
        throw new RuntimeException("Stub!");
    }

    @Keep
    public class StrategyListener extends IStrategyListener.Stub {
        StrategyListener() {
        }

        @Override // android.strategyservice.IStrategyListener
        public void onStrategyChange(String name, List<String> changeItems) {
            throw new RuntimeException("Stub!");
        }
    }

    public void updateApiStrategy(String name, List<String> changeItems) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(int deviceType, int eventType) {
        throw new RuntimeException("Stub!");
    }

    public int setInt(int deviceType, int eventType, int value) {
        throw new RuntimeException("Stub!");
    }

    public int setBuffer(int deviceType, int eventType, byte[] value) {
        throw new RuntimeException("Stub!");
    }

    public float getDouble(int deviceType, int eventType) {
        throw new RuntimeException("Stub!");
    }

    public int setDouble(int deviceType, int eventType, double value) {
        throw new RuntimeException("Stub!");
    }

    public int[] getIntArray(int deviceType, int[] eventType) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getBuffer(int deviceType, int eventType) {
        throw new RuntimeException("Stub!");
    }

    public int setIntArray(int deviceType, int[] eventType, int[] value) {
        throw new RuntimeException("Stub!");
    }

    public float[] getDoubleArray(int deviceType, int[] eventType) {
        throw new RuntimeException("Stub!");
    }

    public int setDoubleArray(int deviceType, int[] eventType, float[] value) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(int deviceType) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(int deviceID, int[] featureIDs) {
        throw new RuntimeException("Stub!");
    }

    public int disableDevice(int deviceType) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(OnBYDAutoListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(OnBYDAutoListener l) {
        throw new RuntimeException("Stub!");
    }

    public static void dispatchError(int errCode, String errMessage) {
        throw new RuntimeException("Stub!");
    }

    public static void dispatchResponse(int device_type, int event_type, int successful) {
        throw new RuntimeException("Stub!");
    }

    public static void dispatchNativeEvent(int device_type, int event_type, int value) {
        throw new RuntimeException("Stub!");
    }

    public static void dispatchNativeEvent(int device_type, int event_type, float value) {
        throw new RuntimeException("Stub!");
    }

    public static void dispatchNativeEvent(int device_type, int event_type, byte[] value) {
        throw new RuntimeException("Stub!");
    }
}