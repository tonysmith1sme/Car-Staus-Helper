//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.setting;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoSettingListener implements IBYDAutoListener {
    public AbsBYDAutoSettingListener() {
        throw new RuntimeException("Stub!");
    }

    public void onACBTWindSwitchChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onACTunnelCycleSwitchChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onACPauseCycleSwitchChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onACAutoAirModeChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onEnergyFeedbackStrengthChanged(int level) {
        throw new RuntimeException("Stub!");
    }

    public void onSOCTargetRangeChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onChargingPortSwitchChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onSteerAssisModeChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onRearViewMirrorFlipSwitchChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onDriverSeatAutoReturnSwitchChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onSteerPositionAutoReturnSwitchChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onPM25PowerSwitchChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onPM25SwitchCheckChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onPM25TimeCheckChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onControlWindowSwitchChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onLockCarRiseWindowChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onBackHomeLightDelayValueChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onLeftHomeLightDelayValueChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onLockOffDoorChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onRemoteControlUpwindowStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onMicroSwitchLockWindowStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onRearAcOnlineStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onBackDoorElectricModeChanged(int mode) {
        throw new RuntimeException("Stub!");
    }

    public void onFeatureChanged(String feature, int ifHas) {
        throw new RuntimeException("Stub!");
    }

    public void onOverspeedLockStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onLanguageChanged(int value) {
        throw new RuntimeException("Stub!");
    }

    public void onSafeWarnStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onMaintainRemindStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onMicroSwitchUnlockWindowStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    public void onAutoExternalRearMirrorFollowUpSwitchChanged(int state) {
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
