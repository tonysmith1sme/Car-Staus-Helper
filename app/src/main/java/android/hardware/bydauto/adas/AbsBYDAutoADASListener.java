package android.hardware.bydauto.adas;

import android.hardware.IBYDAutoListener;

public abstract class AbsBYDAutoADASListener implements IBYDAutoListener {
    public AbsBYDAutoADASListener() {
        throw new RuntimeException("Stub!");
    }

    public void onESPKeyStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }
}
