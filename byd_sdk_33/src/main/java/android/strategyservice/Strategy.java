package android.strategyservice;

import android.os.Parcel;

import androidx.annotation.Keep;

import java.util.HashMap;
import java.util.List;

@Keep
public class Strategy {
    private static final String TAG = "Strategy";
    public HashMap<String, String> configs = new HashMap<>();
    public HashMap<String, List<String>> permissions = new HashMap<>();
    public String strategyData;

    public Strategy() {
    }

    public Strategy(Parcel data) {
        throw new RuntimeException("Stub!");
    }
}