package com.huawei.carstatushelper.service;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

public class AccessibilityUtil {
    /**
     * 方法6：使用 Android AccessibilityService 探测窗口变化，跟据系统回传的参数获取 前台对象 的包名与类名
     *
     * @param packageName 需要检查是否位于栈顶的App的包名
     * @return true 在前台，false 在后台
     */
    public static boolean isForegroundPkgViaDetectionService(String packageName) {
        return packageName.equals(DetectionService.foregroundPackageName);
    }

    final static String TAG = "AccessibilityUtil";

    // 此方法用来判断当前应用的辅助功能服务是否开启
    public static boolean isAccessibilitySettingsOn(Context context) {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            Log.i(TAG, e.getMessage());
        }

        if (accessibilityEnabled == 1) {
            String services = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (services != null) {
                return services.toLowerCase().contains(context.getPackageName().toLowerCase());
            }
        }

        return false;
    }

//    private void anyMethod() {
//        // 判断辅助功能是否开启
//        if (!isAccessibilitySettingsOn(getContext())) {
//            // 引导至辅助功能设置页面
//            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
//        } else {
//            // 执行辅助功能服务相关操作
//        }
//    }
}
