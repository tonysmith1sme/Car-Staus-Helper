package com.huawei.carstatushelper.view;

import android.content.Context;
import android.util.AttributeSet;

import com.huawei.carstatushelper.BuildConfig;
import com.xw.sample.dashboardviewdemo.DashboardView4;

public class CarSpeedView extends DashboardView4 {
    public CarSpeedView(Context context) {
        this(context, null);
    }

    public CarSpeedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarSpeedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        if (BuildConfig.DEBUG) {
//            setVelocity(130);
//        }
    }

    @Override
    protected int setMax() {
        return 200;
    }

    @Override
    protected int setSection() {
        return 10;
    }

    @Override
    protected int setPortion() {
        return 1;
    }

    @Override
    protected String setHeaderText() {
        return "km/h";
    }
}
