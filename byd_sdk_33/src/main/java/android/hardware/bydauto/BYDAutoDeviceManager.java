package android.hardware.bydauto;

import android.content.Context;
import android.hardware.BYDAutoManager;
import android.hardware.IBYDAutoDevice;
import android.os.Handler;

import androidx.annotation.Keep;

import java.util.HashMap;

@Keep
public abstract class BYDAutoDeviceManager implements BYDAutoManager.OnBYDAutoListener {
    private static final boolean DEBUG = false;
    private static final String TAG = "BYDAutoDeviceManager";
    protected BYDAutoManager mAutoManager;
    private static BYDAutoDeviceManager mInstance = null;
    private static final Object LOCK_OBJ = new Object();

    public abstract void addDevice(IBYDAutoDevice iBYDAutoDevice);

    public abstract void removeDevice(IBYDAutoDevice iBYDAutoDevice);

    protected BYDAutoDeviceManager(Context con) {
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoDeviceManager getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(IBYDAutoDevice device) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(IBYDAutoDevice device, int[] featureIDs) {
        throw new RuntimeException("Stub!");
    }

    public int disableDevice(IBYDAutoDevice device) {
        throw new RuntimeException("Stub!");
    }

    public int setInt(int device, int event, int value) {
        throw new RuntimeException("Stub!");
    }

    public int setBuffer(int device, int event, byte[] value) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(int device, int event) {
        throw new RuntimeException("Stub!");
    }

    public int setDouble(int device, int event, double value) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(int device, int event) {
        throw new RuntimeException("Stub!");
    }

    public int setIntArray(int device, int[] event, int[] value) {
        throw new RuntimeException("Stub!");
    }

    public int[] getIntArray(int device, int[] event) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getBuffer(int device, int event) {
        throw new RuntimeException("Stub!");
    }

    public int setDoubleArray(int device, int[] event, float[] value) {
        throw new RuntimeException("Stub!");
    }

    public float[] getDoubleArray(int device, int[] event) {
        throw new RuntimeException("Stub!");
    }

    @Keep
    public static final class BYDAutoDeviceManagerImpl extends BYDAutoDeviceManager {
        private HashMap<Integer, IBYDAutoDevice> mDevices;
        protected Handler mMainHandler;

        protected BYDAutoDeviceManagerImpl(Context con) {
            super(con);
            throw new RuntimeException("Stub!");
        }

        @Override // android.hardware.BYDAutoManager.OnBYDAutoListener
        public void onError(final int errCode, final String errMessage) {
            throw new RuntimeException("Stub!");
        }

        @Override // android.hardware.BYDAutoManager.OnBYDAutoListener
        public void onChanged(final int device_type, final int event_type, final int value, final Object data) {
            throw new RuntimeException("Stub!");
        }

        @Override // android.hardware.BYDAutoManager.OnBYDAutoListener
        public void onChanged(final int device_type, final int event_type, final float value, final Object data) {
            throw new RuntimeException("Stub!");
        }

        @Override // android.hardware.BYDAutoManager.OnBYDAutoListener
        public void onChanged(final int device_type, final int event_type, final byte[] value, final Object data) {
            throw new RuntimeException("Stub!");
        }

        @Override // android.hardware.bydauto.BYDAutoDeviceManager
        public void addDevice(IBYDAutoDevice device) {
            throw new RuntimeException("Stub!");
        }

        @Override // android.hardware.bydauto.BYDAutoDeviceManager
        public void removeDevice(IBYDAutoDevice device) {
            throw new RuntimeException("Stub!");
        }
    }
}