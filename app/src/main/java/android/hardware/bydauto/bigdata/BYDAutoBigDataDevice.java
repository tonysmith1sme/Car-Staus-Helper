package android.hardware.bydauto.bigdata;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

public class BYDAutoBigDataDevice extends AbsBYDAutoDevice {
    BYDAutoBigDataDevice(Context con) {
        super(con);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoBigDataDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int getType() {
        return 1061;
    }

    public void registerListener(AbsBYDAutoBigDataListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoBigDataListener l) {
        throw new RuntimeException("Stub!");
    }
}
