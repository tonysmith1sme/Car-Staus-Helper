//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package android.hardware;

import android.hardware.bydauto.BYDAutoEventValue;

import androidx.annotation.Keep;

@Keep
public interface IBYDAutoListener {
    //    void onDataChanged(IBYDAutoEvent var1);
    void onDataChanged(IBYDAutoEvent iBYDAutoEvent);

    void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue);

    void onError(int i, String str);

}
