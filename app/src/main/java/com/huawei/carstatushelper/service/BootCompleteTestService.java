package com.huawei.carstatushelper.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.socks.library.KLog;

public class BootCompleteTestService extends Service {
    public static final String ACTION_STOP_TEST = "com.huawei.carstatushelper.activity.BOOTCOMPLETETESTSERVICE_STOP";
    private boolean interrupt;

    public BootCompleteTestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        KLog.e();
        new Thread(new Runnable() {
            int count;

            @Override
            public void run() {
                while (true) {
                    KLog.e("boot complete test --> " + count);
                    count++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count > 30) {
                        break;
                    }
                    if (interrupt) {
                        break;
                    }
                }
                KLog.e("boot complete test finish");
            }
        }).start();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_STOP_TEST);
        registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KLog.e();
        unregisterReceiver(receiver);
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            interrupt = true;
            KLog.e();
        }
    };
}