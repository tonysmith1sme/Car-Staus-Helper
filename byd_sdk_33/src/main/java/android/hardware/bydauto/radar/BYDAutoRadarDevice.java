//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.radar;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

import androidx.annotation.Keep;

@Keep
public class BYDAutoRadarDevice extends AbsBYDAutoDevice {
    public static final int RADAR_AREA_FRONT_LEFT_MID = 7;
    public static final int RADAR_AREA_FRONT_RIGHT_MID = 8;
    public static final int RADAR_AREA_LEFT = 5;
    public static final int RADAR_AREA_LEFT_FRONT = 1;
    public static final int RADAR_AREA_LEFT_REAR = 3;
    public static final int RADAR_AREA_RIGHT = 6;
    public static final int RADAR_AREA_RIGHT_FRONT = 2;
    public static final int RADAR_AREA_RIGHT_REAR = 4;
    public static final int RADAR_COMMAND_BUSY = -2147482647;
    public static final int RADAR_COMMAND_FAILED = -2147482648;
    public static final int RADAR_COMMAND_INVALID_VALUE = -2147482645;
    public static final int RADAR_COMMAND_SUCCESS = 0;
    public static final int RADAR_COMMAND_TIMEOUT = -2147482646;
    public static final int RADAR_PROBE_STATE_ABNORMAL = 0;
    public static final int RADAR_PROBE_STATE_GREEN = 2;
    public static final int RADAR_PROBE_STATE_RED = 4;
    public static final int RADAR_PROBE_STATE_SAFE = 1;
    public static final int RADAR_PROBE_STATE_YELLOW = 3;
    public static final int RADAR_REVERSE_SWITCH_OFF = 0;
    public static final int RADAR_REVERSE_SWITCH_ON = 1;

    BYDAutoRadarDevice() {
        super((Context) null);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoRadarDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public int getRadarProbeState(int area) {
        throw new RuntimeException("Stub!");
    }

    public int[] getAllRadarProbeStates() {
        throw new RuntimeException("Stub!");
    }

    public int getRadarObstacleDistance(int area) {
        throw new RuntimeException("Stub!");
    }

    public int[] getAllRadarDistance() {
        throw new RuntimeException("Stub!");
    }

    public int[] getAllRadarObstacleDistances() {
        throw new RuntimeException("Stub!");
    }

    public int[][] getAllRadarStatus() {
        throw new RuntimeException("Stub!");
    }

    public int getReverseRadarSwitchState() {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoRadarListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoRadarListener l) {
        throw new RuntimeException("Stub!");
    }
}
