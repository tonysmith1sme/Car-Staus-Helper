package com.huawei.carstatushelper.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.service.RadarDataProviderService;
import com.huawei.carstatushelper.service.RadarFloatingService;
import com.huawei.carstatushelper.util.PermissionUtils;

import java.util.Random;

public class RadarFloatingSettingActivity extends BackEnableBaseActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_RADAR = 1000;

    @Override
    public CharSequence setPageTitle() {
        return "雷达测距";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_floating_setting);

        findViewById(R.id.grant_permission_btn).setOnClickListener(this);
        findViewById(R.id.show_radar_floating_btn).setOnClickListener(this);
        findViewById(R.id.update_radar_data_btn).setOnClickListener(this);

//        String[] permissions = new String[]{Manifest.permission.BYDAUTO_RADAR_GET, Manifest.permission.BYDAUTO_GEARBOX_GET};
//        if (PermissionUtils.needRequestPermission(this, permissions)) {
//            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_RADAR);
//            return;
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(connection);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_RADAR) {
//            boolean grantSuccess = true;
//            for (int grantResult : grantResults) {
//                if (grantResult != PackageManager.PERMISSION_GRANTED) {
//                    grantSuccess = false;
//                    break;
//                }
//            }
            if (!PermissionUtils.needRequestPermission(this, permissions)) {
                startService(new Intent(this, RadarDataProviderService.class));
            } else {
            }
        }
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.grant_permission_btn) {
            if (Settings.canDrawOverlays(RadarFloatingSettingActivity.this)) {
                Toast.makeText(RadarFloatingSettingActivity.this, "已授权浮窗权限", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
        } else if (viewId == R.id.show_radar_floating_btn) {
            if (!Settings.canDrawOverlays(RadarFloatingSettingActivity.this)) {
                Toast.makeText(RadarFloatingSettingActivity.this, "请先授予浮窗权限", Toast.LENGTH_SHORT).show();
                return;
            }
//                RadarFloatingService.setRadarFloatingConfig(RadarFloatingSettingActivity.this, 600, 700, 12);
            sendBroadcast(new Intent(RadarFloatingService.ACTION_SHOW_RADAR_FLOATING));
        } else if (viewId == R.id.update_radar_data_btn) {
            Intent intent = new Intent(RadarFloatingService.ACTION_UPDATE_RADAR_DATA);
            int[] data = new int[9];
            Random random = new Random();
            for (int i = 0; i < data.length; i++) {
                data[i] = random.nextInt(150);
            }
            intent.putExtra("data", data);
            sendBroadcast(intent);
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}