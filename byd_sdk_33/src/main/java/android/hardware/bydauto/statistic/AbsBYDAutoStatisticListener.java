//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.statistic;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoStatisticListener implements IBYDAutoListener {
    public AbsBYDAutoStatisticListener() {
        throw new RuntimeException("Stub!");
    }

    public void onTotalMileageValueChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onTotalFuelConChanged(double value) {
        throw new RuntimeException("Stub!");
    }

    public void onTotalElecConChanged(double value) {
        throw new RuntimeException("Stub!");
    }

    public void onDrivingTimeChanged(double value) {
        throw new RuntimeException("Stub!");
    }

    public void onLastFuelConPHMChanged(double value) {
        throw new RuntimeException("Stub!");
    }

    public void onTotalFuelConPHMChanged(double value) {
        throw new RuntimeException("Stub!");
    }

    public void onLastElecConPHMChanged(double value) {
        throw new RuntimeException("Stub!");
    }

    public void onTotalElecConPHMChanged(double value) {
        throw new RuntimeException("Stub!");
    }

    public void onElecDrivingRangeChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onFuelDrivingRangeChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onFuelPercentageChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onElecPercentageChanged(double value) {
        throw new RuntimeException("Stub!");
    }

    public void onKeyBatteryLevelChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onEVMileageValueChanged(int value) {
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

    public void onHEVMileageValueChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onWaterTemperatureChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onInstantElecConChanged(double value) {
        throw new RuntimeException("Stub!");
    }

    public void onInstantFuelConChanged(double value) {
        throw new RuntimeException("Stub!");
    }
}
