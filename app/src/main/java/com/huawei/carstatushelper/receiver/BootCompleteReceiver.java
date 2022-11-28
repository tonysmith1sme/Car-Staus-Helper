package com.huawei.carstatushelper.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.huawei.carstatushelper.util.AutoBootHelper;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, BootCompleteService.class);
        i.putExtra("from_boot",true);
        AutoBootHelper.startForegroundService(context, i);
    }
}