package com.huawei.carstatushelper;

import android.app.Application;
import android.content.Context;

import me.weishu.reflection.Reflection;

public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Reflection.unseal(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
