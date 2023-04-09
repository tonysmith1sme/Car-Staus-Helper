package com.huawei.carstatushelper.activity;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import androidx.annotation.Nullable;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.databinding.ActivitySettingsBinding;

public class SettingsActivity extends BackEnableBaseActivity {

    private SharedPreferences preferences;

    @Override
    public CharSequence setPageTitle() {
        return "设置";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySettingsBinding binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(listener);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.settings, new SettingsFragment()).commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        preferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    private final SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if ("app_logo_current_value".equals(key)) {
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