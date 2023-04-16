//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware;

import androidx.annotation.Keep;

@Keep
public interface IBYDAutoDevice {
//    int[] getFeatureList();

    int getType();

    boolean onError(int i, String str);

    boolean onPostEvent(IBYDAutoEvent iBYDAutoEvent);

    boolean postEvent(int i, int i2, float f, Object obj);

    boolean postEvent(int i, int i2, int i3, Object obj);

    boolean postEvent(int i, int i2, byte[] bArr, Object obj);

    void registerListener(IBYDAutoListener iBYDAutoListener);

    void unregisterListener(IBYDAutoListener iBYDAutoListener);
}
