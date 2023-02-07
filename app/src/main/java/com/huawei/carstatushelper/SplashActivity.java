package com.huawei.carstatushelper;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.huawei.carstatushelper.activity.ImageDetailActivity;
import com.huawei.carstatushelper.databinding.ActivitySplashBinding;
import com.huawei.carstatushelper.receiver.BootCompleteService;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String[] BYD_PERMISSIONS = {
            Manifest.permission.BYDAUTO_BODYWORK_COMMON,
            Manifest.permission.BYDAUTO_AC_COMMON,
            Manifest.permission.BYDAUTO_PANORAMA_COMMON,
            Manifest.permission.BYDAUTO_PANORAMA_GET,
            Manifest.permission.BYDAUTO_SETTING_COMMON,
            Manifest.permission.BYDAUTO_INSTRUMENT_COMMON,
            Manifest.permission.BYDAUTO_DOOR_LOCK_COMMON
    };
    private com.huawei.carstatushelper.databinding.ActivitySplashBinding binding;
    private SharedPreferences preferences;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.checkFloatingPermissionBtn.setOnClickListener(this);
        binding.checkBydPermissionBtn.setOnClickListener(this);
        binding.setFastModeBtn.setOnClickListener(this);
        binding.setBootWithStartBtn.setOnClickListener(this);
        binding.selectTtsEngineBtn.setOnClickListener(this);

//        binding.testNotificationBtn.setOnClickListener(this);
//        binding.testEngineSpeedFloatingBtn.setOnClickListener(this);
//        binding.testRadarDistanceFloatingBtn.setOnClickListener(this);

        binding.jumpToMainBtn.setOnClickListener(this);

//        if (Settings.canDrawOverlays(this) && isBydAutoPermissionGranted()) {
//            startActivity(new Intent(this, MainActivity.class));
//            finish();
//        }
        binding.image1.setOnClickListener(onClickListener);
        binding.image2.setOnClickListener(onClickListener);
        binding.image3.setOnClickListener(onClickListener);
        binding.image4.setOnClickListener(onClickListener);
        binding.image5.setOnClickListener(onClickListener);
        binding.image6.setOnClickListener(onClickListener);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        boolean agreement_save = preferences.getBoolean(KEY_AGREEMENT_SAVE, false);
//        binding.agreementCb.setChecked(agreement_save);

//        binding.agreementTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(SplashActivity.this, AgreementActivity.class));
//            }
//        });

        tts = new TextToSpeech(SplashActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                KLog.e("onInit = " + i);
            }
        });
        if (!preferences.getBoolean("has_read_disclaimers", false)) {
            new AlertDialog.Builder(this)
                    .setTitle("免责声明")
                    .setMessage("为保证行车驾驶安全，行车过程中不建议操作软件。免费软件，无偿使用，对于使用过程中造成的任何问题以及影响，车况助手开发及相关管理、测试人员对此概不负责，同意请点击确定，否则点击取消退出软件。")
                    .setCancelable(false)
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            preferences.edit().putBoolean("has_read_disclaimers", true).apply();
                        }
                    })
                    .show();
            return;
        }
        if (preferences.getBoolean("key_skip_guide_page", false)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

//    private static final String KEY_AGREEMENT_SAVE = "agreement_save";

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int viewId = view.getId();
            Map<Integer, Integer> map = new HashMap<>();
            map.put(R.id.image1, R.drawable.guide_backgound_running_1);
            map.put(R.id.image2, R.drawable.guide_backgound_running_2);
            map.put(R.id.image3, R.drawable.guide_backgound_running_3);

            map.put(R.id.image4, R.drawable.guide_auto_boot_1);
            map.put(R.id.image5, R.drawable.guide_auto_boot_2);
            map.put(R.id.image6, R.drawable.guide_auto_boot_3);

            if (map.containsKey(viewId)) {
                Integer resId = map.get(viewId);
                Intent intent = new Intent(SplashActivity.this, ImageDetailActivity.class);
                intent.putExtra(ImageDetailActivity.KEY_IMAGE_RES_ID, resId);
                startActivity(intent);
            }
        }
    };

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
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                break;
            case R.id.check_byd_permission_btn:
                boolean granted = isBydAutoPermissionGranted();
                if (granted) {
                    Toast.makeText(this, "比亚迪车辆权限已授予", Toast.LENGTH_SHORT).show();
                } else {
                    ActivityCompat.requestPermissions(this, BYD_PERMISSIONS, 125);
                }
                break;
            case R.id.set_fast_mode_btn://跳转极速模式
                try {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.byd.rapidmode", "com.byd.rapidmode.RapidModeActivity"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "跳转极速模式失败，请手动打开", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.set_boot_with_start_btn://跳转禁止自启动
                try {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.byd.appstartmanagement", "com.byd.appstartmanagement.frame.AppStartManagement"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(this, "跳转禁止自启动失败，请手动打开", Toast.LENGTH_SHORT).show();
                }
                break;
//            case R.id.test_notification_btn:
//                startService(new Intent(this, BootCompleteService.class));
//                break;
//            case R.id.test_engine_speed_floating_btn:
//                startService(new Intent(this, FloatingWindowService.class));
//                break;
//            case R.id.test_radar_distance_floating_btn:
//                RadarDistanceHelper radarDistanceHelper = new RadarDistanceHelper(this);
//                radarDistanceHelper.showRadarFloating();
//                break;
            case R.id.select_tts_engine_btn:
                List<TextToSpeech.EngineInfo> engineInfoList = tts.getEngines();
                String[] infos = new String[engineInfoList.size()];
                for (int i = 0; i < engineInfoList.size(); i++) {
                    TextToSpeech.EngineInfo engineInfo = engineInfoList.get(i);
                    String label = engineInfo.label;
                    String name = engineInfo.name;
                    int icon = engineInfo.icon;
                    infos[i] = label + ":" + name;
                }
                if (engineInfoList.size() == 0) {
                    Toast.makeText(this, "请先安装tts语音引擎", Toast.LENGTH_SHORT).show();
                    return;
                }
                //app版
                new AlertDialog.Builder(SplashActivity.this)
                        .setTitle("请选择语音引擎")
                        .setItems(infos, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                TextToSpeech.EngineInfo engineInfo = engineInfoList.get(i);
                                String ttsPkg = engineInfo.name;
                                preferences.edit()
                                        .putString("default_tts_engine_pkg", ttsPkg)
                                        .putBoolean("smart_remind_enable", true)
                                        .apply();
                                Toast.makeText(SplashActivity.this, "语音引擎设置成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
            case R.id.jump_to_main_btn:
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "请检查浮窗权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!BuildConfig.DEBUG) {
                    if (!isBydAutoPermissionGranted()) {
                        Toast.makeText(this, "请检查车辆权限", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
//                if (!binding.agreementCb.isChecked()) {
//                    Toast.makeText(this, "请先阅读并同意协议", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                preferences.edit().putBoolean(KEY_AGREEMENT_SAVE, true).apply();
                preferences.edit().putBoolean("key_skip_guide_page", true).apply();

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