package android.hardware.bydauto.power;

import android.content.Context;

public final class BYDAutoPowerDevice {
    public static final int ACC_STATUS = -1728053193;
    public static final int POWER_CTL_STATE_OFF = 0;
    public static final int POWER_CTL_STATE_ON = 1;

    public static synchronized BYDAutoPowerDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoPowerListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoPowerListener l) {
        throw new RuntimeException("Stub!");
    }

    public int getPowerCtlStatus(int event_type) {
        throw new RuntimeException("Stub!");
    }

    public int setPowerCtlStatus(int event_type, int value) {
        throw new RuntimeException("Stub!");
    }

}
