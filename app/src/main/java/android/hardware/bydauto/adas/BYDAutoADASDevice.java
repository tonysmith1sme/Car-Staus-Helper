package android.hardware.bydauto.adas;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

public class BYDAutoADASDevice extends AbsBYDAutoDevice {
    BYDAutoADASDevice(Context con) {
        super(con);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoADASDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoADASListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoADASListener l) {
        throw new RuntimeException("Stub!");
    }
}
