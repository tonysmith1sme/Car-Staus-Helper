package com.huawei.carstatushelper.util;

import android.content.Context;
import android.content.Intent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import timber.log.Timber;

/**
 * 必须权限
 * <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
 * <uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>
 */
public class AutoBootHelper {
    public static void startForegroundService(Context context, Intent intent) {
        try {
            Class<?> clz = Class.forName("android.content.Context");
            Method startForegroundService = clz.getDeclaredMethod("startForegroundService", Intent.class);
            startForegroundService.setAccessible(true);
            startForegroundService.invoke(context, intent);
            Timber.e("startForegroundService success");
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            Timber.e(e, "startForegroundService failed");
        }
    }
}