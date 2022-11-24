package com.huawei.carstatushelper.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

import com.huawei.carstatushelper.R;
import com.socks.library.KLog;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class Android29Helper {
    private static void createNotificationChannel(Context context, String id, CharSequence name, int importance) {
        try {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager == null) {
                return;
            }

            Class<?> clz = Class.forName("android.app.NotificationChannel");
            Constructor<?> constructor = clz.getDeclaredConstructor(String.class, CharSequence.class, int.class);
            constructor.setAccessible(true);
            Object channel = constructor.newInstance(id, name, importance);

            Class<?> clzz = Class.forName("android.app.NotificationManager");
            Method createNotificationChannel = clzz.getDeclaredMethod("createNotificationChannel", NotificationChannel.class);
            createNotificationChannel.invoke(manager, channel);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            KLog.e(e.toString());
        }
    }

    /**
     * //禁止后台自启动，关闭
     * //极速模式，白名单
     *
     * @param context
     * @param cls
     */
    public static void startForegroundService(Context context, Class<?> cls) {
        try {
            Class<?> clz = Class.forName("android.content.Context");
            Method startForegroundService = clz.getDeclaredMethod("startForegroundService", Intent.class);
            startForegroundService.setAccessible(true);
            startForegroundService.invoke(context, new Intent(context, cls));
            KLog.e("startForegroundService invoke success");
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            KLog.e(e.toString());
        }
    }

    private static Notification.Builder createNotificationBuilder(Context context, String channelId) {
        try {
            Class<?> clz = Class.forName("android.app.Notification$Builder");
            Constructor<?> constructor = clz.getDeclaredConstructor(Context.class, String.class);
            constructor.setAccessible(true);
            Object obj = constructor.newInstance(context, channelId);
            return (Notification.Builder) obj;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            KLog.e(e.toString());
        }
        return new Notification.Builder(context);
    }

    public static void startForeground(Service context, String title, String subText) {
        String channelId = context.getPackageName() + new Random().nextInt(1000);

        Android29Helper.createNotificationChannel(context, channelId, context.getPackageName(), NotificationManager.IMPORTANCE_DEFAULT);

        Notification.Builder builder = Android29Helper.createNotificationBuilder(context, channelId);

        Notification notification = builder
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSubText(subText)
                .build();
        context.startForeground(1, notification);
    }
}
