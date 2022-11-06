package android.hardware;

public interface IBYDAutoEvent {
    int getDeviceType();

    int getEventType();

    int getValue();

    double getDoubleValue();

    Object getData();

    void setData(Object var1);
}