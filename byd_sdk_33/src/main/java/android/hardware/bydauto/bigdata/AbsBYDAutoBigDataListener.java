package android.hardware.bydauto.bigdata;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoBigDataListener implements IBYDAutoListener {
    private static final boolean DEBUG = true;
    protected static final String TAG = "AbsBYDAutoBigDataListener";

    public void onNeedRendRegisterTable(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onWholeFrameDataChanged(byte[] data) {
        throw new RuntimeException("Stub!");
    }

    private BigDataMessageData parse(Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void onError(int errCode, String errMessage) {
        throw new RuntimeException("Stub!");
    }

    public final void onDataChanged(IBYDAutoEvent event) {
        throw new RuntimeException("Stub!");
    }

    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        throw new RuntimeException("Stub!");
    }

    @Keep
    private class BigDataMessageData {
        private BigDataMessageData() {
            throw new RuntimeException("Stub!");
        }
    }
}