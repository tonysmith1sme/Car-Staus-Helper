package com.huawei.carstatushelper.view;

import android.content.Context;
import android.util.AttributeSet;

import com.xw.sample.dashboardviewdemo.DashboardView4;

public class DialogEngineSpeedView extends DashboardView4 {
    public DialogEngineSpeedView(Context context) {
        this(context, null);
    }

    public DialogEngineSpeedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DialogEngineSpeedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int setMax() {
        return 6000;
    }

    @Override
    protected int setSection() {
        return 6;
    }

    @Override
    protected int setPortion() {
        return 10;
    }

    @Override
    protected String setHeaderText() {
        return " ...";
    }

    @Override
    protected int setUnit() {
        return 1000;
    }
}
