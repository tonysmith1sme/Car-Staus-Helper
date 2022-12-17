package com.huawei.carstatushelper.view;

import android.content.Context;
import android.util.AttributeSet;

import com.xw.sample.dashboardviewdemo.DashboardView4;

/**
 * 电机转速表
 */
public class RearMotorSpeedView extends DashboardView4 {
    public RearMotorSpeedView(Context context) {
        this(context, null);
    }

    public RearMotorSpeedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RearMotorSpeedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int setMax() {
        return 16000;
    }

    @Override
    protected int setSection() {
        return 8;
    }

    @Override
    protected int setPortion() {
        return 5;
    }

    @Override
    protected String setHeaderText() {
        return " x1k rpm(后电机)";
    }

//    private String headerText = " x1k rpm(后电机)";

//    public void setHeader(String headerText){
//        this.headerText = headerText;
//    }

    @Override
    protected int setUnit() {
        return 1000;
    }

}
