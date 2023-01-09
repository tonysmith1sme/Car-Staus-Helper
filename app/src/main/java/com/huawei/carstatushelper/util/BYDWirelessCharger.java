package com.huawei.carstatushelper.util;

import android.content.Context;

import java.lang.reflect.Method;

public class BYDWirelessCharger {
    public static void turnOn(Context context) {
        toggle(1, context);
    }

    public static void turnOff(Context context) {
        toggle(2, context);
    }

    private static void toggle(int i, Context context) {
        try {
            Method declaredMethod = Class.class.getDeclaredMethod("forName", String.class);
            Method declaredMethod2 = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
            Class cls = (Class) declaredMethod.invoke(null, "dalvik.system.VMRuntime");
            ((Method) declaredMethod2.invoke(cls, "setHiddenApiExemptions", new Class[]{String[].class})).invoke(((Method) declaredMethod2.invoke(cls, "getRuntime", null)).invoke(null, new Object[0]), new String[]{"L"});
            Class<?> cls2 = Class.forName("android.hardware.bydauto.charging.BYDAutoChargingDevice");
            Object invoke = cls2.getMethod("getInstance", Context.class).invoke(null, context);
            int intValue = ((Integer) cls2.getMethod("getDevicetype", new Class[0]).invoke(invoke, new Object[0])).intValue();
            int intValue2 = ((Integer) Class.forName("android.hardware.bydauto.BYDAutoFeatureIds").getDeclaredField("CHARGING_CHARGE_WIRELESS_CHARGING_SWITCH_SET").get(null)).intValue();
            Method declaredMethod3 = Class.forName("android.hardware.bydauto.AbsBYDAutoDevice").getDeclaredMethod("set", Integer.TYPE, Integer.TYPE, Integer.TYPE);
            declaredMethod3.setAccessible(true);
            declaredMethod3.invoke(invoke, Integer.valueOf(intValue), Integer.valueOf(intValue2), Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}