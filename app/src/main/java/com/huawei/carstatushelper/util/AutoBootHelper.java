package com.huawei.carstatushelper.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import com.socks.library.KLog;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
            KLog.e("startForegroundService success");
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            KLog.e("startForegroundService failed, error: " + e);
        }
    }

    public static void createNotificationChannel(NotificationManager manager, NotificationChannel channel) {
        try {
            Class<?> clz = Class.forName("android.app.NotificationManager");
            Method method = clz.getDeclaredMethod("createNotificationChannel", NotificationChannel.class);
            method.setAccessible(true);
            method.invoke(manager, channel);
//            System.out.println();
            KLog.e("createNotificationChannel success");
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            KLog.e("createNotificationChannel failed, error: " + e);
        }
    }

    public static Notification.Builder newNotificationBuilder(Context context, String channelId) {
        try {
            Class<?> clz = Class.forName("android.app.Notification$Builder");
            Constructor<?> constructor = clz.getDeclaredConstructor(Context.class, String.class);
            constructor.setAccessible(true);
            Object obj = constructor.newInstance(context, channelId);
            KLog.e("newNotificationBuilder success");
            return (Notification.Builder) obj;
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            KLog.e("newNotificationBuilder failed, error: " + e);
        }
        return new Notification.Builder(context);
    }

    public static void simple(){

    }
}
