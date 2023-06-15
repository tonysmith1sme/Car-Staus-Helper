//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto;

import android.content.Context;
import android.hardware.IBYDAutoDevice;
import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.IBYDAutoListenerIdsMap;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoDevice implements IBYDAutoDevice {
//    private static final boolean DEBUG = false;
//    private static String TAG = "AbsBYDAutoDevice";
//    private final ArrayList<IBYDAutoListener> mAutoListener = new ArrayList<>();
//    private Context mContext;
//    private BYDAutoDeviceManager mDeviceManager;
//    private IBYDAutoListenerMap mIBYDAutoListenerMap;

//    public abstract int getDevicetype();

//    public abstract String getGetPermission();

//    public abstract String getSetPermission();

    protected AbsBYDAutoDevice(Context con) {
        throw new RuntimeException("Stub!");
    }

    @Keep
    public class IBYDAutoListenerMap extends IBYDAutoListenerIdsMap<IBYDAutoListener> {
        IBYDAutoListenerMap() {
            throw new RuntimeException("Stub!");
        }
    }

    @Override // android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, float value, Object data) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public boolean onPostEvent(IBYDAutoEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public boolean onError(int errCode, String errMessage) {
        throw new RuntimeException("Stub!");
    }

//    private void postEventValue(IBYDAutoEvent event, IBYDAutoListener l) {
//        throw new RuntimeException("Stub!");
//    }

    @Override // android.hardware.IBYDAutoDevice
    public void registerListener(IBYDAutoListener l) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(IBYDAutoListener l, int[] featureIDs) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public void unregisterListener(IBYDAutoListener l) {
        throw new RuntimeException("Stub!");
    }

    public int set(int device, int event, int value) {
        throw new RuntimeException("Stub!");
    }

    public int set(int device, int event, byte[] value) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int device, int event, double value) {
        throw new RuntimeException("Stub!");
    }

    public int set(int device, int[] event, int[] params) {
        throw new RuntimeException("Stub!");
    }

    public int set(int device, int[] event, float[] params) {
        throw new RuntimeException("Stub!");
    }

    public int get(int device, int event) {
        throw new RuntimeException("Stub!");
    }

    public int[] getIntArray(int device, int[] event) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getBuffer(int device, int event) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(int device, int event) {
        throw new RuntimeException("Stub!");
    }

    protected float[] getDoubleArray(int device, int[] event) {
        throw new RuntimeException("Stub!");
    }

    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        throw new RuntimeException("Stub!");
    }

    public int setMediaState(int device, int event, int value) {
        throw new RuntimeException("Stub!");
    }

    public int setMediaInfo(int device, int event, byte[] value) {
        throw new RuntimeException("Stub!");
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        throw new RuntimeException("Stub!");
    }

    public String arrayToStr(int[] intArray) {
        throw new RuntimeException("Stub!");
    }

    public String arrayToStr(float[] floatArray) {
        throw new RuntimeException("Stub!");
    }

    private boolean checkDeviceFeatures(int deviceType, int[] featureIds) {
        throw new RuntimeException("Stub!");
    }
}
