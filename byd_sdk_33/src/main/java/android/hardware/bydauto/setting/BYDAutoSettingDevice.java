//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.setting;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

import androidx.annotation.Keep;

@Keep
public class BYDAutoSettingDevice extends AbsBYDAutoDevice {
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DM20_SOC_TARGET_MIN = 15;
    public static final int DM25_SOC_TARGET_MIN = 25;
    public static final String FEATURE_BACK_DOOR = "BackDoor";
    public static final String FEATURE_OVERSPEED_LOCKING = "OverspeedLocking";
    public static final String FEATURE_REARVIEW_MIRROR_FOLLOW_UP = "RearviewMirrorFollowUp";
    public static final int OVERSPEED_LOCKING_OFF = 0;
    public static final int OVERSPEED_LOCKING_ON = 1;
    public static final int SETTING_COMMAND_BUSY = -2147482647;
    public static final int SETTING_COMMAND_FAILED = -2147482648;
    public static final int SETTING_COMMAND_INVALID = -2147482645;
    public static final int SETTING_COMMAND_SUCCESS = 0;
    public static final int SETTING_COMMAND_TIMEOUT = -2147482646;
    public static final int SET_AC_AUTO_AIR_COMFORT = 2;
    public static final int SET_AC_AUTO_AIR_ECONOMY = 1;
    public static final int SET_CAR_LIGHT_DELAY_VALUE_MAX = 60;
    public static final int SET_CAR_LIGHT_DELAY_VALUE_MIN = 0;
    public static final int SET_CAR_LOCK_OFF_4DOORS = 0;
    public static final int SET_CAR_LOCK_OFF_DR_SIDE = 1;
    public static final int SET_DR_ENERGY_FB_LARGE = 2;
    public static final int SET_DR_ENERGY_FB_STANDARD = 1;
    public static final int SET_DR_SOC_TARGET_MAX = 70;
    public static final int SET_DR_SOC_TARGET_MIN = 15;
    public static final int SET_DR_ST_ASSIS_COMFORT = 1;
    public static final int SET_DR_ST_ASSIS_SPORT = 2;
    public static final int SET_INS_THEME_MAX = 10;
    public static final int SET_INS_THEME_MIN = 1;
    public static final int SET_INVALID = 255;
    public static final int SET_LANGUAGE_ARABIC = 5;
    public static final int SET_LANGUAGE_COMPLEX_CHINESE = 2;
    public static final int SET_LANGUAGE_ENGLISH = 3;
    public static final int SET_LANGUAGE_RUSSIAN = 4;
    public static final int SET_LANGUAGE_SIMPLE_CHINESE = 1;
    public static final int SET_OFF = 0;
    public static final int SET_ON = 1;

    BYDAutoSettingDevice() {
        super((Context) null);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoSettingDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public int setACBTWind(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getACBTWind() {
        throw new RuntimeException("Stub!");
    }

    public int setACTunnelCycle(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getACTunnelCycle() {
        throw new RuntimeException("Stub!");
    }

    public int setACPauseCycle(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getACPauseCycle() {
        throw new RuntimeException("Stub!");
    }

    public int setACAutoAir(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getACAutoAir() {
        throw new RuntimeException("Stub!");
    }

    public int setEnergyFeedback(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getEnergyFeedback() {
        throw new RuntimeException("Stub!");
    }

    public int setSOCTarget(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getSOCTarget() {
        throw new RuntimeException("Stub!");
    }

    public int setChargingPort(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getChargingPort() {
        throw new RuntimeException("Stub!");
    }

    public int setSteerAssis(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getSteerAssis() {
        throw new RuntimeException("Stub!");
    }

    public int setRearViewMirrorFlip(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getRearViewMirrorFlip() {
        throw new RuntimeException("Stub!");
    }

    public int setDriverSeatAutoReturn(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getDriverSeatAutoReturn() {
        throw new RuntimeException("Stub!");
    }

    public int setSteerPositionAutoReturn(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getSteerPositionAutoReturn() {
        throw new RuntimeException("Stub!");
    }

    public int setPM25Power(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getPM25Power() {
        throw new RuntimeException("Stub!");
    }

    public int setPM25SwitchCheck(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getPM25SwitchCheck() {
        throw new RuntimeException("Stub!");
    }

    public int setPM25TimeCheck(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getPM25TimeCheck() {
        throw new RuntimeException("Stub!");
    }

    public int setRemoteControlDownwindowState(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getRemoteControlDownwindowState() {
        throw new RuntimeException("Stub!");
    }

    public int setLockCarRiseWindow(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getLockCarRiseWindow() {
        throw new RuntimeException("Stub!");
    }

    public int setBackHomeLightDelayValue(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getBackHomeLightDelayValue() {
        throw new RuntimeException("Stub!");
    }

    public int setLeftHomeLightDelayValue(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getLeftHomeLightDelayValue() {
        throw new RuntimeException("Stub!");
    }

    public int setLockOff(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getLockOff() {
        throw new RuntimeException("Stub!");
    }

    public int setRemoteControlUpwindowState(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getRemoteControlUpwindowState() {
        throw new RuntimeException("Stub!");
    }

    public int setMicroSwitchLockWindowState(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getMicroSwitchLockWindowState() {
        throw new RuntimeException("Stub!");
    }

    public int getRearAcOnlineState() {
        throw new RuntimeException("Stub!");
    }

    public int setBackDoorElectricMode(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getBackDoorElectricMode() {
        throw new RuntimeException("Stub!");
    }

    public int hasFeature(String feature) {
        throw new RuntimeException("Stub!");
    }

    public int getOverspeedLock() {
        throw new RuntimeException("Stub!");
    }

    public int setOverspeedLock(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getLanguage() {
        throw new RuntimeException("Stub!");
    }

    public int setLanguage(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getSafeWarnState() {
        throw new RuntimeException("Stub!");
    }

    public int getMaintainRemindState() {
        throw new RuntimeException("Stub!");
    }

    public int setMicroSwitchUnlockWindowState(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getMicroSwitchUnlockWindowState() {
        throw new RuntimeException("Stub!");
    }

    public int setAutoExternalRearMirrorFollowUpSwitch(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getAutoExternalRearMirrorFollowUpSwitch() {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoSettingListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoSettingListener l) {
        throw new RuntimeException("Stub!");
    }

    public int setLeftViewMirrorFlipAngle(int value) {
        throw new RuntimeException("Stub!");
    }

    public int setRightViewMirrorFlipAngle(int value) {
        throw new RuntimeException("Stub!");
    }

    public int getLeftViewMirrorFlipAngle() {
        throw new RuntimeException("Stub!");
    }

    public int getRightViewMirrorFlipAngle() {
        throw new RuntimeException("Stub!");
    }

}
