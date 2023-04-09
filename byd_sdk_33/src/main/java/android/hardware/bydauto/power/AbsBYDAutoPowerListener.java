package android.hardware.bydauto.power;

import android.hardware.IBYDAutoEvent;
import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoPowerListener {
    public void onPowerCtlStatusChanged(int event_type, int value) {
        throw new RuntimeException("Stub!");
    }

    public void onTpDisplayControllerChanged(int event_type, int value) {
        throw new RuntimeException("Stub!");
    }

    public void onTftBacklightChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onMcuStatusChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onShutdownInfoChanged(int device, int info) {
        throw new RuntimeException("Stub!");
    }

    public void onBatteryLowVoltageStateChanged(int state) {
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
}
