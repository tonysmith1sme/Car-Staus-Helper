package android.acquisitionservice;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.ArrayMap;

import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class BYDAcquisitionClient {
    private static final String ACQUISITION_SEND_BUFFER_PERM = "android.permission.BYDACQUISITION_SEND_BUFFER";
    private static final String ACQUISITION_SEND_FILE_PERM = "android.permission.BYDACQUISITION_SEND_FILE";
    private static final int ACTION_CONTROL = 2;
    private static final int ACTION_SEND_BUFFER = 0;
    private static final int ACTION_SEND_FILE = 1;
    private static final String TAG = "BYDAcquisitionClient";
    private static BYDAcquisitionClient mInstance;
    private static ArrayList<OnBYDAcqListener> mOnBYDAcqListeners = new ArrayList<>();
    private Context mContext;
    private EventHandler mEventHandler;
    private HandlerThread mHandlerThread;
    private long mNativeClient;

    /* loaded from: classes.dex */
    public interface OnBYDAcqListener {
        void onAcqSucceed(ArrayList<String> arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeAcquisitionControl(int i);

    private native void nativeDisableListener();

    private native void nativeEnableListener();

    private native void nativeInit(String str);

    private native void nativeRelease();

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeSendBuffer(int i, byte[] bArr);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeSendFile(ArrayMap<String, String> arrayMap, ArrayMap<String, String> arrayMap2);

    public BYDAcquisitionClient(Context context, String clientName) {
        throw new RuntimeException("Stub!");
    }

    public static BYDAcquisitionClient getInstance(Context context, String clientName) {
        throw new RuntimeException("Stub!");
    }

    /* loaded from: classes.dex */
    private class EventHandler extends Handler {
        public EventHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            throw new RuntimeException("Stub!");
        }
    }

    /* loaded from: classes.dex */
    private class SerializableArrayMap implements Serializable {
        private ArrayMap<String, String> fileParts;
        private ArrayMap<String, String> stringParts;

        private SerializableArrayMap() {
        }

        public ArrayMap<String, String> getFileParts() {
            throw new RuntimeException("Stub!");
        }

        public ArrayMap<String, String> getStringParts() {
            throw new RuntimeException("Stub!");
        }

        public void setArrayMap(ArrayMap<String, String> fileParts, ArrayMap<String, String> stringParts) {
            throw new RuntimeException("Stub!");
        }
    }

    public int sendBuffer(int priority, byte[] data) {
        throw new RuntimeException("Stub!");
    }

    public int sendFile(ArrayMap<String, String> fileParts, ArrayMap<String, String> stringParts) {
        throw new RuntimeException("Stub!");
    }

    public int acquisitionControl(int cmd) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(OnBYDAcqListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(OnBYDAcqListener l) {
        throw new RuntimeException("Stub!");
    }

    public static void onAcqSucceed(ArrayList<String> fileList) {
        throw new RuntimeException("Stub!");
    }

    protected void finalize() throws Throwable {
        throw new RuntimeException("Stub!");
    }
}