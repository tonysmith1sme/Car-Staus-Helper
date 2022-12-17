package com.huawei.carstatushelper.activity;

import android.os.Bundle;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.databinding.ActivityImageDetailBinding;

public class ImageDetailActivity extends BackEnableBaseActivity {
    public static final String KEY_IMAGE_RES_ID = "imageResId";

    @Override
    public CharSequence setPageTitle() {
        return "预览";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityImageDetailBinding binding = ActivityImageDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int imageResId = getIntent().getIntExtra(KEY_IMAGE_RES_ID, R.mipmap.ic_launcher);
        binding.image.setImageResource(imageResId);
    }
}