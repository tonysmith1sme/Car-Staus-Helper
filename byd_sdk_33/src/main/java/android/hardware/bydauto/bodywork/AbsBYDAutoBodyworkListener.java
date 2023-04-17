//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.bodywork;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoBodyworkListener implements IBYDAutoListener {
    private static final boolean DEBUG = true;
    protected static final String TAG = "AbsBYDAutoBodyworkListener";

    public void onWindowStateChanged(int area, int state) {
    }

    public void onDoorStateChanged(int area, int state) {
    }

    public void onAutoSystemStateChanged(int state) {
    }

    public void onSteeringWheelValueChanged(int type, double value) {
    }

    public void onSteeringWheelAngleChanged(double value, int sensorState, int calibrationState) {
    }

    public void onSteeringWheelSpeedChanged(double value, int sensorState, int calibrationState) {
    }

    public void onPowerLevelChanged(int level) {
    }

    public void onBatteryVoltageLevelChanged(int level) {
    }

    public void onPowerDayModeChanged(int state) {
    }

    public void onAutoVINChanged(String vin) {
    }

    public void onMoonRoofConfigChanged(int config) {
    }

    public void onFuelElecLowPowerChanged(int state) {
    }

    public void onAlarmStateChanged(int state) {
    }

    public void onWindowOpenPercentChanged(int area, int percent) {
    }

    public void onCarWindowAntiPinchConfigChanged(int config) {
    }

    public void onRainCloseWindowChanged(int state) {
    }

    public void onMessage5sOnlineStateChanged(int id, int state) {
    }

    public void onHasMessageChanged(int id, int state) {
    }

    public void onSunroofStateChanged(int state) {
    }

    public void onWindowPermitStateChanged(int state) {
    }

    public void onWindoblindInitStateChanged(int state) {
    }

    public void onSunroofInitStateChanged(int state) {
    }

    public void onSunroofCloseNoticeChanged(int state) {
    }

    public void onSunroofWindowblindPositionChanged(int value) {
    }

    public void onSunroofPositionChanged(int value) {
    }

    public void onSmartVoiceLimitChanged(int value) {
    }

    public void onBatteryPowerChanged(int value) {
    }

    private BodyworkMessageData parse(Object obj) {
        throw new RuntimeException("Stub!");
    }

    @Keep
    private class BodyworkMessageData {
        private BodyworkMessageData() {
        }
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
