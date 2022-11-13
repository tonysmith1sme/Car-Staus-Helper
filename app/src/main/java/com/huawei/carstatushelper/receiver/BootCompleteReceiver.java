package com.huawei.carstatushelper.receiver;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import com.huawei.carstatushelper.BuildConfig;
import com.huawei.carstatushelper.service.BootCompleteTestService;
import com.huawei.carstatushelper.service.RadarDataProviderService;
import com.huawei.carstatushelper.service.RadarFloatingService;
import com.huawei.carstatushelper.util.PermissionUtils;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!PermissionUtils.needRequestPermission(context, new String[]{Manifest.permission.BYDAUTO_RADAR_GET, Manifest.permission.BYDAUTO_GEARBOX_GET})) {
            context.startService(new Intent(context, RadarDataProviderService.class));
        }
        if (Settings.canDrawOverlays(context)) {
            context.startService(new Intent(context, RadarFloatingService.class));
        }
        if (BuildConfig.DEBUG) {
            context.startService(new Intent(context, BootCompleteTestService.class));
        }
    }
}