package com.huawei.carstatushelper.activity;

import android.app.AlertDialog;
import android.app.BydDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.databinding.ActivitySettingsBinding;
import com.huawei.carstatushelper.receiver.BootCompleteService;
import com.socks.library.KLog;

import java.util.List;

public class SettingsActivity extends BackEnableBaseActivity {

    private SharedPreferences preferences;
    private TextToSpeech tts;

    @Override
    public CharSequence setPageTitle() {
        return "设置";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySettingsBinding binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.settings, new SettingsFragment()).commit();
        }
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(listener);

        tts = new TextToSpeech(SettingsActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                KLog.e("onInit = " + i);
            }
        });
    }

    private void updateUI() {
        getFragmentManager().beginTransaction().replace(R.id.settings, new SettingsFragment()).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        preferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    private final SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if ("smart_remind_enable".equals(key)) {
                boolean smart_remind_enable = sharedPreferences.getBoolean(key, false);
                if (smart_remind_enable) {
                    List<TextToSpeech.EngineInfo> engineInfoList = tts.getEngines();
                    String[] infos = new String[engineInfoList.size()];
                    for (int i = 0; i < engineInfoList.size(); i++) {
                        TextToSpeech.EngineInfo engineInfo = engineInfoList.get(i);
                        String label = engineInfo.label;
                        String name = engineInfo.name;
                        int icon = engineInfo.icon;
                        infos[i] = label + ":" + name;
                    }
                    //app版
                    new AlertDialog.Builder(SettingsActivity.this).setTitle("请选择语音引擎").setItems(infos, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    TextToSpeech.EngineInfo engineInfo = engineInfoList.get(i);
                                    String ttsPkg = engineInfo.name;
                                    preferences.edit().putString("default_tts_engine_pkg", ttsPkg).apply();
                                    updateUI();
                                    startService(new Intent(SettingsActivity.this, BootCompleteService.class));
                                }
                            })
                            .setCancelable(false).show();
                    //support.v7版

                    //byd版
//                    new BydDialog.Builder(SettingsActivity.this).setItems(infos, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    }).setCancelable(false).show();
                } else {
                    startService(new Intent(SettingsActivity.this, BootCompleteService.class));
                }
            } else if ("app_logo_current_value".equals(key)) {
                String app_logo_current_value = sharedPreferences.getString("app_logo_current_value", "0");
                //车况助手
                if ("0".equals(app_logo_current_value)) {
                    PackageManager pm = getPackageManager();
                    pm.setComponentEnabledSetting(new ComponentName(SettingsActivity.this, "com.huawei.carstatushelper.SplashActivity.jindihui"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                    pm.setComponentEnabledSetting(new ComponentName(SettingsActivity.this, "com.huawei.carstatushelper.SplashActivity"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                    //津迪会
                } else if ("1".equals(app_logo_current_value)) {
                    PackageManager pm = getPackageManager();
                    pm.setComponentEnabledSetting(new ComponentName(SettingsActivity.this, "com.huawei.carstatushelper.SplashActivity"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                    pm.setComponentEnabledSetting(new ComponentName(SettingsActivity.this, "com.huawei.carstatushelper.SplashActivity.jindihui"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                }
            }
        }
    };


    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.root_preferences);
        }
    }
}