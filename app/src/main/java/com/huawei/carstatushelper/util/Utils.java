package com.huawei.carstatushelper.util;

import android.app.ActivityManager;
import android.content.Context;

import com.socks.library.KLog;

import java.util.Iterator;
import java.util.List;

public class Utils {
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        Iterator var3 = appProcesses.iterator();

        ActivityManager.RunningAppProcessInfo appProcess;
        do {
            if (!var3.hasNext()) {
                return false;
            }

            appProcess = (ActivityManager.RunningAppProcessInfo) var3.next();
        } while (!appProcess.processName.equals(context.getPackageName()));

        if (appProcess.importance == 100) {
//            TTLog.i("前台", new Object[]{appProcess.processName});
            KLog.e("前台", new Object[]{appProcess.processName});
            return false;
        } else {
//            TTLog.i("后台", new Object[]{appProcess.processName});
            KLog.e("后台", new Object[]{appProcess.processName});
            return true;
        }
    }
}
