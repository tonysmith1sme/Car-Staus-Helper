package android.acquisitionservice;

import android.content.Context;

public class BYDAcquisitionClient {
    public BYDAcquisitionClient(Context conetxt, String value) {
        throw new RuntimeException("Stub!");
    }

    public static android.acquisitionservice.BYDAcquisitionClient getInstance(android.content.Context ctx, String str) {
        throw new RuntimeException("Stub!");
    }

    public static void onAcqSucceed(java.util.ArrayList list) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(android.acquisitionservice.BYDAcquisitionClient.OnBYDAcqListener listener) {
        throw new RuntimeException("Stub!");
    }

    public int sendBuffer(int a, byte[] data) {
        throw new RuntimeException("Stub!");
    }

    public int sendFile(android.util.ArrayMap map1, android.util.ArrayMap map2) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(android.acquisitionservice.BYDAcquisitionClient.OnBYDAcqListener listener) {
        throw new RuntimeException("Stub!");
    }

    public class OnBYDAcqListener {

    }
}
