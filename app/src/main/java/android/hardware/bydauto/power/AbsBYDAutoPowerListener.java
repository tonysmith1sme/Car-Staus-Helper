package android.hardware.bydauto.power;

import android.support.annotation.Keep;

@Keep
public abstract class AbsBYDAutoPowerListener {
    public void onPowerCtlStatusChanged(int event_type, int value) {
    }

    public void onTpDisplayControllerChanged(int event_type, int value) {
    }

    public void onTftBacklightChanged(int value) {
    }

    public void onMcuStatusChanged(int value) {
    }

    public void onShutdownInfoChanged(int device, int info) {
    }

    public void onBatteryLowVoltageStateChanged(int state) {
    }

}
