//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware;

import androidx.annotation.Keep;

@Keep
public interface IBYDAutoDevice {
    int getType();

    boolean onPostEvent(IBYDAutoEvent var1);

    boolean postEvent(int var1, int var2, int var3, Object var4);

    boolean postEvent(int var1, int var2, double var3, Object var5);

    boolean postEvent(int var1, int var2, byte[] var3, Object var4);

    void registerListener(IBYDAutoListener var1);

    void unregisterListener(IBYDAutoListener var1);
}
