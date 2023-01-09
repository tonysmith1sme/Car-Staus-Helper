package com.huawei.carstatushelper.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtil {
    public static boolean isWirelessChargingBootCloseEnable(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean ret = preferences.getBoolean("wireless_charging_boot_close_enable", false);
        return ret;
    }
}
