package com.huawei.carstatushelper;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import com.huawei.carstatushelper.util.CrashHandler;

import me.weishu.reflection.Reflection;

public class App extends Application implements Application.ActivityLifecycleCallbacks {
    private static App mTestActivityLifecycleApplcation;
    private int mActivityCount = 0;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Reflection.unseal(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mTestActivityLifecycleApplcation = this;

        CrashHandler.getInstance().init(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int default_night_mode = Integer.parseInt(preferences.getString(getString(R.string.key_default_night_mode), String.valueOf(AppCompatDelegate.MODE_NIGHT_UNSPECIFIED)));
        AppCompatDelegate.setDefaultNightMode(default_night_mode);

        registerActivityLifecycleCallbacks(this);
    }

    public static App getInstance() {
        if (null == mTestActivityLifecycleApplcation)
            mTestActivityLifecycleApplcation = new App();
        return mTestActivityLifecycleApplcation;
    }

    /**
     * 当activity的计数为0时表示应用在后台，否则就在前台
     *
     * @return
     */
    public int getActivityCount() {
        return mActivityCount;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        mActivityCount++;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        mActivityCount--;
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
