package com.huawei.carstatushelper.activity;

import android.os.Bundle;

import com.huawei.carstatushelper.R;

public class AgreementActivity extends BackEnableBaseActivity {

    @Override
    public CharSequence setPageTitle() {
        return "使用协议";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
    }
}