package com.huawei.carstatushelper.util;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;

public class PermissionUtils {
    public static boolean needRequestPermission(Context context, String[] permissions) {
        boolean ret = false;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                ret = true;
                break;
            }
        }
        return ret;
    }
}
