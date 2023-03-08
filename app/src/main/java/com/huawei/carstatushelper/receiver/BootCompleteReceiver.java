package com.huawei.carstatushelper.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.huawei.carstatushelper.util.AutoBootHelper;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, BootCompleteService.class);
        i.putExtra("from_boot", true);
        if (Build.VERSION.SDK_INT >= 26) {
            AutoBootHelper.startForegroundService(context, i);
        } else {
            context.startService(i);
        }
//        if (PrefUtil.isWirelessChargingBootCloseEnable(context)) {
//            BYDWirelessCharger.turnOff(context);
//        }
    }
}