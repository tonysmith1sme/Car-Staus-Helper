package com.huawei.carstatushelper.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.huawei.carstatushelper.util.Android29Helper;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Android29Helper.startForegroundService(context, BootCompleteService.class);
    }
}