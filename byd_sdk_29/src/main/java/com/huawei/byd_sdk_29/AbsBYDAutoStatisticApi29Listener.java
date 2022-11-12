package com.huawei.byd_sdk_29;

import android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener;

public abstract class AbsBYDAutoStatisticApi29Listener extends AbsBYDAutoStatisticListener {
    public abstract void onWaterTemperatureChanged(int value);

    public abstract void onInstantElecConChanged(double value);

    public abstract void onInstantFuelConChanged(double value);
}
