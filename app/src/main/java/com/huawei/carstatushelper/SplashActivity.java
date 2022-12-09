package com.huawei.carstatushelper;

import android.Manifest;
import android.content.ComponentName;
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
import com.huawei.carstatushelper.receiver.BootCompleteService;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String[] BYD_PERMISSIONS = {
            Manifest.permission.BYDAUTO_BODYWORK_COMMON,
            Manifest.permission.BYDAUTO_AC_COMMON,
            Manifest.permission.BYDAUTO_PANORAMA_COMMON,
            Manifest.permission.BYDAUTO_PANORAMA_GET,
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
//            case R.id.set_fast_mode_btn:
//                //ActivityTaskManager: START intent = Intent { act=android.intent.action.MANAGE_APP_PERMISSIONS cmp=com.android.permissioncontroller/com.android.packageinstaller.permission.ui.PermissionsActivity (has extras) }, processName = com.byd.systemsettings
//                //ActivityTaskManager: START intent = Intent { act=android.intent.action.APPLICATION_SETTINGS cat=[android.intent.category.DEFAULT] flg=0x10200000 cmp=com.byd.systemsettings/.applications.ApplicationActivity (has extras) }, processName = system
//                ComponentName componentName = new ComponentName("com.android.permissioncontroller", "com.android.packageinstaller.permission.ui.PermissionsActivity");
//                try {
//                    Intent intent = new Intent("android.intent.action.MANAGE_APP_PERMISSIONS");
//                    intent.setComponent(componentName);
//                    startActivity(intent);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Toast.makeText(this, "打开界面失败，请手动打开", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            //跳转自启动设置
//            case R.id.set_boot_with_start_btn:
//                ComponentName componetName = new ComponentName("com.byd.appstartmanagement", "com.byd.appstartmanagement.frame.AppStartManagement");
//                try {
//                    Intent intent = new Intent();
//                    intent.setComponent(componetName);
//                    startActivity(intent);
//                } catch (Exception e) {
//                    Toast.makeText(this, "跳转异常，请检查跳转配置、包名及Activity访问权限", Toast.LENGTH_SHORT).show();
//                }
//                break;
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
                startService(new Intent(this, BootCompleteService.class));

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
            if (getBaseContext().checkSelfPermission(perm) != PackageManager.PERMISSION_GRANTED) {
                isAllGranted = false;
                break;
            }
        }
        return isAllGranted;
    }
}