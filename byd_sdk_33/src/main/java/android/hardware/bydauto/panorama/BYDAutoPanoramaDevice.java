//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware.bydauto.panorama;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

import androidx.annotation.Keep;

@Keep
public class BYDAutoPanoramaDevice extends AbsBYDAutoDevice {
    public static final int BACK_LINE_MULTIMEDIA = 2;
    public static final int BACK_LINE_NOT_SUPPORT = 0;
    public static final int BACK_LINE_PAN_INTERNAL = 1;
    public static final int DISPLAY_MODE_FULL_SCREEN = 1;
    public static final int DISPLAY_MODE_PANORAMA = 0;
    public static final int DISPLAY_MODE_REVERSE = 5;
    public static final int DISPLAY_MODE_RF_REVERSE = 4;
    public static final int DISPLAY_MODE_WIDGET = 3;
    public static final int PANORAMA_COMMAND_BUSY = -2147482647;
    public static final int PANORAMA_COMMAND_FAILED = -2147482648;
    public static final int PANORAMA_COMMAND_INVALID = -2147482645;
    public static final int PANORAMA_COMMAND_SUCCESS = 0;
    public static final int PANORAMA_COMMAND_TIMEOUT = -2147482646;
    public static final int PANORAMA_OFFLINE = 3;
    public static final int PANORAMA_ONLINE = 1;
    public static final int PANORAMA_OUTPUT_COMPOSE = 6;
    public static final int PANORAMA_OUTPUT_FRONT = 2;
    public static final int PANORAMA_OUTPUT_FRONT_LEFT = 8;
    public static final int PANORAMA_OUTPUT_FRONT_RIGHT = 9;
    public static final int PANORAMA_OUTPUT_INVALID = 0;
    public static final int PANORAMA_OUTPUT_LEFT = 4;
    public static final int PANORAMA_OUTPUT_MATCHING = 7;
    public static final int PANORAMA_OUTPUT_OFF = 1;
    public static final int PANORAMA_OUTPUT_REAR = 3;
    public static final int PANORAMA_OUTPUT_REAR_LEFT = 10;
    public static final int PANORAMA_OUTPUT_REAR_RIGHT = 11;
    public static final int PANORAMA_OUTPUT_RIGHT = 5;
    public static final int PANORAMA_OUTPUT_SIGNAL_CVBS = 0;
    public static final int PANORAMA_OUTPUT_SIGNAL_LVDS = 1;
    public static final int PANORAMA_REVERSE = 2;
    public static final int PANORAMA_RF_REVERSE = 0;
    public static final int PANORAMA_RINGHT_CAMERA_SWITCH_OFF = 2;
    public static final int PANORAMA_RINGHT_CAMERA_SWITCH_ON = 1;
    public static final int PANORAMA_ROTATION_HORIZONTAL = 1;
    public static final int PANORAMA_ROTATION_VERTICAL = 2;
    public static final int PANORAMA_WORK_OFF = 0;
    public static final int PANORAMA_WORK_ON = 1;

    BYDAutoPanoramaDevice() {
        super((Context)null);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoPanoramaDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public int getPanoWorkState() {
        throw new RuntimeException("Stub!");
    }

    public int getPanoOutputState() {
        throw new RuntimeException("Stub!");
    }

    public int getPanoOutputSignal() {
        throw new RuntimeException("Stub!");
    }

    public int getBackLineConfig() {
        throw new RuntimeException("Stub!");
    }

    public int getPanoramaOnlineState() {
        throw new RuntimeException("Stub!");
    }

    public int getPanoRotation() {
        throw new RuntimeException("Stub!");
    }

    public int getDisplayMode() {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int device_type, int event_type, double value, Object data) {
        throw new RuntimeException("Stub!");
    }

    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoPanoramaListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoPanoramaListener l) {
        throw new RuntimeException("Stub!");
    }
}
