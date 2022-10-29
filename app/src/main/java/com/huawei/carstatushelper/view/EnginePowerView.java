package com.huawei.carstatushelper.view;

import android.content.Context;
import android.util.AttributeSet;

import com.huawei.carstatushelper.BuildConfig;
import com.xw.sample.dashboardviewdemo.DashboardView4;

public class EnginePowerView extends DashboardView4 {
    public EnginePowerView(Context context) {
        this(context, null);
    }

    public EnginePowerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EnginePowerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        if (BuildConfig.DEBUG) {
//            setVelocity(-30);
//        }
    }

    @Override
    protected int setMin() {
        return -60;
    }

    @Override
    protected int setMax() {
        return 220;
    }

    @Override
    protected int setSection() {
        return 14;
    }

    @Override
    protected int setPortion() {
        return 1;
    }

    @Override
    protected String setHeaderText() {
        return " kw";
    }
}
