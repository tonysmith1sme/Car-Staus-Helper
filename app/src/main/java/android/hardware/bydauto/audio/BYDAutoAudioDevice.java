package android.hardware.bydauto.audio;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

public class BYDAutoAudioDevice extends AbsBYDAutoDevice {
    BYDAutoAudioDevice(Context con) {
        super(con);
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoAudioDevice getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    @Override
    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public void setDiracLiveMode(int a){
        throw new RuntimeException("Stub!");
    }

    public void setLoudnessState(int a){
        throw new RuntimeException("Stub!");
    }

    public void setArkamysMode(int a){
        throw new RuntimeException("Stub!");
    }

    public void setArkamysState(int a){
        throw new RuntimeException("Stub!");
    }
}
