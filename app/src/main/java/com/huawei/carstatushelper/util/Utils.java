package com.huawei.carstatushelper.util;

import android.app.ActivityManager;
import android.content.Context;

import java.util.Iterator;
import java.util.List;

import timber.log.Timber;

public class Utils {
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        Iterator var3 = appProcesses.iterator();

        ActivityManager.RunningAppProcessInfo appProcess;
        do {
            if (!var3.hasNext()) {
                return false;
            }

            appProcess = (ActivityManager.RunningAppProcessInfo) var3.next();
        } while (!appProcess.processName.equals(context.getPackageName()));

        if (appProcess.importance == 100) {
            Timber.e("前台: %s", appProcess.processName);
            return false;
        } else {
            Timber.e("后台: %s", appProcess.processName);
            return true;
        }
    }
}