package com.huawei.carstatushelper.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.huawei.carstatushelper.BuildConfig;
import com.huawei.carstatushelper.databinding.ActivityAboutBinding;
import com.huawei.carstatushelper.test.AdasTestActivity;
import com.huawei.carstatushelper.test.BigDataTestActivity;
import com.huawei.carstatushelper.test.CollisionDeviceTestActivity;
import com.huawei.carstatushelper.test.InstrumentTestActivity;
import com.huawei.carstatushelper.test.ReflectTestActivity;

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
            public boolean onLongClick(View view) {
                String[] names = {"大数据接口测试", "通用接口反射测试", "车辆接口详情", "ADAS测试", "仪表测试", "碰撞测试"};
                new AlertDialog.Builder(AboutActivity.this).setItems(names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            startActivity(new Intent(view.getContext(), BigDataTestActivity.class));
                        } else if (which == 1) {
                            startActivity(new Intent(view.getContext(), ReflectTestActivity.class));
                        } else if (which == 2) {
                            startActivity(new Intent(view.getContext(), CarStatusDetailActivity.class));
                        } else if (which == 3) {
                            startActivity(new Intent(view.getContext(), AdasTestActivity.class));
                        } else if (which == 4) {
                            startActivity(new Intent(view.getContext(), InstrumentTestActivity.class));
                        } else if (which == 5) {
                            startActivity(new Intent(view.getContext(), CollisionDeviceTestActivity.class));
                        }
                    }
                }).show();
                return true;
            }
        });
    }
}