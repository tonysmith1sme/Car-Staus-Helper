package android.hardware.bydauto.radar;

import android.hardware.IBYDAutoListener;

public abstract class AbsBYDAutoRadarListener implements IBYDAutoListener {
    public AbsBYDAutoRadarListener() {
        throw new RuntimeException("Stub!");
    }

    public void onRadarProbeStateChanged(int area, int state) {
        throw new RuntimeException("Stub!");
    }

    public void onReverseRadarSwitchStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

//    public void onDataChanged(IBYDAutoEvent event) {
//        throw new RuntimeException("Stub!");
//    }
}