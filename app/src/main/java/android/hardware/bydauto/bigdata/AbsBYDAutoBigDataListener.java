package android.hardware.bydauto.bigdata;

import android.hardware.IBYDAutoListener;

public abstract class AbsBYDAutoBigDataListener implements IBYDAutoListener {
    public AbsBYDAutoBigDataListener() {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param value
     */
    public void onNeedRendRegisterTable(int value) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param data
     */
    public void onWholeFrameDataChanged(byte[] data) {
        throw new RuntimeException("Stub!");
    }
}
