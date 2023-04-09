//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware;

import androidx.annotation.Keep;

@Keep
public interface IBYDAutoEvent {
    int getDeviceType();

    int getEventType();

    int getValue();

    double getDoubleValue();

    Object getData();

    void setData(Object var1);
}
