package com.huawei.carstatushelper.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.SplashActivity;
import com.socks.library.KLog;

public class BootNotificationDetailActivity extends BackEnableBaseActivity {

    @Override
    public CharSequence setPageTitle() {
        return "通知详情";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.e();
        setContentView(R.layout.activity_boot_notification_detail);
        findViewById(R.id.start_app_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        findViewById(R.id.test_radar_floating_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Settings.canDrawOverlays(BootNotificationDetailActivity.this)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    startActivity(intent);
                    return;
                }
//                sendBroadcast(new Intent(BootCompleteService.ACTION_SHOW_RADAR_FLOATING));
            }
        });
    }

    @Override
    public void onBackPressed() {
        KLog.e();
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}