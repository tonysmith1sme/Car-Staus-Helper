package android.hardware.bydauto.sensor;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.BYDAutoFeatureIds;

import androidx.annotation.Keep;

@Keep
public abstract class AbsBYDAutoSensorListener implements IBYDAutoListener {
    private static final boolean DEBUG = true;
    protected static final String TAG = "AbsBYDAutoSensorListener";

    public void onTemperatureSensorValueChanged(double value) {
    }

    public void onHumiditySensorValueChanged(double value) {
    }

    public void onLightIntensityChanged(int value) {
    }

    public void onSlopeValueChanged(int value) {
    }

    public void onAccSensorDataChanged(byte[] data) {
    }

    private SensorMessageData parse(Object obj) {
        return null;
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        Object obj = event.getData();
        parse(obj);
        int type = event.getEventType();
        if (type == 1498) {
            event.getDoubleValue();
            if (0 >= 0.0d && 0 <= 100.0d) {
                onHumiditySensorValueChanged(0);
            }
        } else if (type == 1500) {
            event.getDoubleValue();
            if (0 >= -40.0d && 0 <= 125.0d) {
                onTemperatureSensorValueChanged(0);
            }
        } else if (type == 573571116) {
            int value = event.getValue();
            if (value >= -60 && value <= 60) {
                onSlopeValueChanged(value);
            }
        } else if (type == BYDAutoFeatureIds.SENSOR_LIGHT) {
            onLightIntensityChanged(event.getValue());
        } else if (type == BYDAutoFeatureIds.SENSOR_AX_STATUS_223) {
            int ax1 = BYDAutoSensorDevice.getInstance(null).getSensorAxValue(1);
            int ax2 = BYDAutoSensorDevice.getInstance(null).getSensorAxValue(2);
            int axStatus = event.getValue();
            byte[] bufData = {(byte) ((ax1 >> 8) & 255), (byte) (ax1 & 255), (byte) ((ax2 >> 8) & 255), (byte) (ax2 & 255), 0, 0, 0, 0, (byte) (axStatus & 255)};
            onAccSensorDataChanged(bufData);
        }
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
    }

    @Keep
    private class SensorMessageData {
        private SensorMessageData() {
        }
    }
}