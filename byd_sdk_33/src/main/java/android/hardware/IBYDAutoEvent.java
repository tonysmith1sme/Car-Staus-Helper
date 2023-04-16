//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware;

import androidx.annotation.Keep;

@Keep
public interface IBYDAutoEvent {
    byte[] getBufferData();

    Object getData();

    int getDeviceType();

    double getDoubleValue();

    int getEventType();

    int getValue();

    void setData(Object obj);
}
