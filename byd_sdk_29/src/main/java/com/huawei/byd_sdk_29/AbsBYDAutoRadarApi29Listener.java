package com.huawei.byd_sdk_29;

import android.hardware.bydauto.radar.AbsBYDAutoRadarListener;

public abstract class AbsBYDAutoRadarApi29Listener extends AbsBYDAutoRadarListener {
    public abstract void onRadarObstacleDistanceChanged(int area, int value);
}
