package com.huawei.carstatushelper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.huawei.carstatushelper.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String[] BYD_PERMISSIONS = {
            Manifest.permission.BYDAUTO_BODYWORK_COMMON,
            Manifest.permission.BYDAUTO_AC_COMMON,
            Manifest.permission.BYDAUTO_PANORAMA_COMMON,
            Manifest.permission.BYDAUTO_SETTING_COMMON,
            Manifest.permission.BYDAUTO_INSTRUMENT_COMMON,
            Manifest.permission.BYDAUTO_DOOR_LOCK_COMMON,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashBinding binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.checkFloatingPermissionBtn.setOnClickListener(this);
        binding.checkBydPermissionBtn.setOnClickListener(this);
        binding.jumpToMainBtn.setOnClickListener(this);

        if (Settings.canDrawOverlays(this) && isBydAutoPermissionGranted()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        switch (vId) {
            case R.id.check_floating_permission_btn:
                if (Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "浮窗权限已授予", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    startActivity(intent);
                }
                break;
            case R.id.check_byd_permission_btn:
//                if (BuildConfig.DEBUG) {
//                    Toast.makeText(this, "比亚迪车辆权限已授予", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                boolean granted = isBydAutoPermissionGranted();
                if (granted) {
                    Toast.makeText(this, "比亚迪车辆权限已授予", Toast.LENGTH_SHORT).show();
                } else {
                    ActivityCompat.requestPermissions(this, BYD_PERMISSIONS, 125);
                }
                break;
            case R.id.jump_to_main_btn:
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "请检查浮窗权限", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if (!BuildConfig.DEBUG) {
                if (!isBydAutoPermissionGranted()) {
                    Toast.makeText(this, "请检查车辆权限", Toast.LENGTH_SHORT).show();
                    return;
                }
//                }
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            default:
                break;
        }
    }

    private boolean isBydAutoPermissionGranted() {
        boolean isAllGranted = true;
        for (String perm : BYD_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                isAllGranted = false;
                break;
            }
        }
        return isAllGranted;
    }
}