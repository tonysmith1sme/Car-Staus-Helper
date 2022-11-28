package android.hardware.bydauto.panorama;

import android.content.Context;

public class BYDAutoPanoramaDevice {
    public static final int PANORAMA_WORK_OFF = 0;
    public static final int PANORAMA_WORK_ON = 1;

    public static BYDAutoPanoramaDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoPanoramaListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoPanoramaListener l) {
        throw new RuntimeException("Stub!");
    }
}
