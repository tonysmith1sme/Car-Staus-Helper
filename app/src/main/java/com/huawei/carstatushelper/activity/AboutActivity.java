package com.huawei.carstatushelper.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.huawei.carstatushelper.BuildConfig;
import com.huawei.carstatushelper.R;

public class AboutActivity extends BackEnableBaseActivity {

    private TextView mVersionTv;

    @Override
    public CharSequence setPageTitle() {
        return "关于";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mVersionTv = (TextView) findViewById(R.id.version_tv);
        mVersionTv.setText(BuildConfig.VERSION_NAME);
    }
}