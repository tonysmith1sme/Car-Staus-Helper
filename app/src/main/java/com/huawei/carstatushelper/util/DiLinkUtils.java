package com.huawei.carstatushelper.util;

/* loaded from: classes.dex */
public class DiLinkUtils {
    public static final String ACCESSIBILITY_ACTION = "android.intent.action.ACCESSIBILITY_SETTINGS";
    public static final String ACTION_CATEGORY = "android.intent.category.DEFAULT";
    public static final String ACTION_LANGUAGE = "android.intent.action.LANGUAGE_SETTINGS";
    public static final String ACTION_VERSION = "android.intent.action.DETAILS_SETTINGS";
    public static final String ALL_SETTINGS_RESET_ACTION = "android.intent.action.ALL_SETTINGS_RESET_SETTINGS";
    public static final String APPLICATION_ACTION = "android.intent.action.APPLICATION_SETTINGS";
    public static final String AUTHORITY_ACTION = "android.intent.action.PERMISSION_SETTINGS";
    public static final String DATA_ACTION = "android.intent.action.DATA_SETTINGS";
    public static final String FACTORY_RESET = "android.intent.action.FACTORY_RESET_SETTINGS";
    public static final String HOTSPOT_ACTION = "android.intent.action.HOTSPOT_SETTINGS";
    public static final String INPUT_METHOD_ACTION = "android.intent.action.INPUT_METHOD_SETTINGS";
    public static final String NOTIFY_ACTION = "android.intent.action.NOTIFICATION_SETTINGS";
    public static final String SECURITY_ACTION = "android.intent.action.SECURITY_SETTINGS";
    public static final String STORAGE_ACTION = "android.intent.action.STORAGE_SETTINGS";
    private static final String TAG = "DlinkUtils";
    public static final String TIME_ACTION = "android.intent.action.TIME_SETTINGS";
    public static final String TIME_ZONE_ACTION = "android.intent.action.TIME_ZONE_SETTINGS";
    public static final String UNIT_ACTION = "android.intent.action.UNIT_SETTINGS";
    public static final String WIFI_ACTION = "android.intent.action.WIFI_SETTINGS";

    public static boolean isRowVersion() {
        return false;
    }
}
