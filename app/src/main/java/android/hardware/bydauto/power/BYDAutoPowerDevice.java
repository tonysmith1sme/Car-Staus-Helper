package android.hardware.bydauto.power;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

public class BYDAutoPowerDevice extends AbsBYDAutoDevice {
    BYDAutoPowerDevice(Context con) {
        super(con);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoPowerDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int getType() {
        return 1005;
    }

    public void registerListener(AbsBYDAutoPowerListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoPowerListener l) {
        throw new RuntimeException("Stub!");
    }

    public int getPowerCtlStatus(long l){
        throw new RuntimeException("Stub!");
    }
}
