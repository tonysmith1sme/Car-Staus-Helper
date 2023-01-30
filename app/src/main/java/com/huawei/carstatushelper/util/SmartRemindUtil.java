package com.huawei.carstatushelper.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.preference.PreferenceManager;

import com.huawei.carstatushelper.R;

public class SmartRemindUtil {

    public static boolean isEnable(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean smart_remind_enable = preferences.getBoolean("smart_remind_enable", false);
        return smart_remind_enable;
    }

    public static String getGearboxLevelName(Context context, int level) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String name_p = preferences.getString("gearbox_level_p_content", context.getString(R.string.default_gearbox_level_p_content));
        String name_r = preferences.getString("gearbox_level_r_content", context.getString(R.string.default_gearbox_level_r_content));
        String name_n = preferences.getString("gearbox_level_n_content", context.getString(R.string.default_gearbox_level_n_content));
        String name_d = preferences.getString("gearbox_level_d_content", context.getString(R.string.default_gearbox_level_d_content));
        if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_P) {
            return name_p;
        }
        if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_R) {
            return name_r;
        }
        if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_N) {
            return name_n;
        }
        if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_D) {
            return name_d;
        }
        return "";
    }

    public static String getWelcomeContent(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String welcome = preferences.getString("boot_welcome_tts_content", context.getString(R.string.default_boot_welcome_content));
        return welcome;
    }

    public static boolean isRadarDistanceTTS(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean radar_distance_tts_enable = preferences.getBoolean("radar_distance_tts_enable", false);
        return radar_distance_tts_enable;
    }
}
