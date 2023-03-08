package com.huawei.carstatushelper.util;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.os.Build;

import com.huawei.carstatushelper.R;
import com.socks.library.KLog;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NotificationHelper {
    public static void showNotification(Service context) {
        String channelId = context.getPackageName();
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        //反射
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= 26) {
            builder = newNotificationBuilder(context, channelId);
        } else {
            builder = new Notification.Builder(context);
        }
        Notification notification = builder.setSmallIcon(R.mipmap.ic_launcher).setContentTitle("服务已启动").setSubText("运行中。。。").build();
        //反射
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel(manager, channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT);
        }
        context.startForeground(1, notification);
    }

    private static void createNotificationChannel(NotificationManager manager, String id, CharSequence name, int importance) {
        try {
            Class<?> aClass = Class.forName("android.app.NotificationChannel");
            Constructor<?> constructor = aClass.getDeclaredConstructor(String.class, CharSequence.class, int.class);
            Object instance = constructor.newInstance(id, name, importance);

            Class<?> clz = Class.forName("android.app.NotificationManager");
//            Method method = clz.getDeclaredMethod("createNotificationChannel", NotificationChannel.class);
            Method method = clz.getDeclaredMethod("createNotificationChannel", aClass);
            method.setAccessible(true);
            method.invoke(manager, instance);
//            System.out.println();
            KLog.e("createNotificationChannel success");
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            KLog.e("createNotificationChannel failed, error: " + e);
        }
    }

    private static Notification.Builder newNotificationBuilder(Context context, String channelId) {
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
}
