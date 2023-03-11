package android.hardware.bydauto.adas;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.support.annotation.Keep;

@Keep
public abstract class AbsBYDAutoADASListener implements IBYDAutoListener {
    private static final boolean DEBUG = true;
    protected static final String TAG = "AbsBYDAutoADASListener";

    public void onFeatureChanged(String feature, int ifHas) {
        throw new RuntimeException("Stub!");
    }

    /**
     * HMA: IntelligentFarAndNearLight
     * 智能远近光灯状态监听
     *
     * @param state
     */
    public void onHMAStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * PCW: PredictionCollisionWarning
     * 碰撞预警
     *
     * @param state
     */
    public void onPCWStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * AEB: AutomaticEmergencyBrake
     * 自动紧急制动
     *
     * @param state
     */
    public void onAEBStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * ESP: Electronic Stability Program
     * 车身电子稳定系统
     * @param state
     */
    public void onESPStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * LKS: LaneKeepingAssist
     * 车道保持车道保持辅助
     * @param mode
     */
    public void onLKSModeChanged(int mode) {
        throw new RuntimeException("Stub!");
    }

    /**
     * LKS: LaneKeepingAssist
     * 车道保持辅助
     * @param sensitivity
     */
    public void onLKSSensitivityChanged(int sensitivity) {
        throw new RuntimeException("Stub!");
    }

    /**
     * LDSW: LaneDepartureWarning
     * 车道偏离预警
     *
     * @param type
     */
    public void onLDSWTypeChanged(int type) {
        throw new RuntimeException("Stub!");
    }

    /**
     * BSD: BlindSpotDetection
     * 盲区监测状态监听
     *
     * @param state
     */
    public void onBSDStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * SLA: TrafficSignRecognition
     *
     * @param state
     */
    public void onSLAStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param state
     */
    public void onAVHStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param state
     */
    public void onIboosterStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * TJS: 交通拥堵辅助
     * 交通拥堵辅助
     * @param state
     */
    public void onTJAStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * 车道偏移量
     * @param state
     */
    public void onLaneOffsetStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param state
     */
    public void onESPKeyStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param state
     */
    public void onHDCStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param state
     */
    public void onBrakeFootSenseStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param state
     */
    public void onCSTStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param state
     */
    public void onCSTDataChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param state
     */
    public void onSuspenChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param state
     */
    public void onInterActiveHintChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param state
     */
    public void onAutoSearchStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param state
     */
    public void onAbortReasonChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param state
     */
    public void onActiveChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * AVM: Around View Montoring
     * 全息影像
     * @param state
     */
    public void onAVMSwitchStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param state
     */
    public void onAutoParkButtonStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    /**
     * @param state
     */
    public void onHDCKeyStateChanged(int state) {
        throw new RuntimeException("Stub!");
    }

    private ADASMessageData parse(Object obj) {
        throw new RuntimeException("Stub!");
    }

    //    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        throw new RuntimeException("Stub!");
    }

    //    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        throw new RuntimeException("Stub!");
    }

    /* loaded from: classes.dex */
    private class ADASMessageData {
        private ADASMessageData() {
            throw new RuntimeException("Stub!");
        }
    }
}