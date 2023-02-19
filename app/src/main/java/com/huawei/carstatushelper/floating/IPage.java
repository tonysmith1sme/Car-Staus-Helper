package com.huawei.carstatushelper.floating;

import android.view.View;

public interface IPage {
    void init();

    View getRootView();

    void onCreate();

    void onDestroy();
}
