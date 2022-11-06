package com.huawei.carstatushelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.huawei.carstatushelper.BuildConfig;
import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.databinding.ActivityAboutBinding;

public class AboutActivity extends BackEnableBaseActivity {

    @Override
    public CharSequence setPageTitle() {
        return "关于";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAboutBinding binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.versionTv.setText(BuildConfig.VERSION_NAME);
        binding.iconIv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(v.getContext(), ReflectBydActivity.class));
                return true;
            }
        });
    }
}