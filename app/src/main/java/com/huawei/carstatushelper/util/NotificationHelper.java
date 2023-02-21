package com.huawei.carstatushelper.util;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.os.Build;

import com.huawei.carstatushelper.R;

public class NotificationHelper {
    public static void showNotification(Service context) {
        String channelId = context.getPackageName();
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        //反射
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= 26) {
            builder = AutoBootHelper.newNotificationBuilder(context, channelId);
        } else {
            builder = new Notification.Builder(context);
        }
        Notification notification = builder.setSmallIcon(R.mipmap.ic_launcher).setContentTitle("服务已启动").setSubText("运行中。。。").build();
        //反射
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT);
            AutoBootHelper.createNotificationChannel(manager, channel);
        }
        context.startForeground(1, notification);
    }
}
