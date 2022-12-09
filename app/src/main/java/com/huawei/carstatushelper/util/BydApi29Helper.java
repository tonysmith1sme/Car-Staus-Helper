package com.huawei.carstatushelper.util;

import android.hardware.bydauto.BYDAutoConstants;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.hardware.bydauto.radar.BYDAutoRadarDevice;

import com.socks.library.KLog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BydApi29Helper {

    public static int getBatteryPowerValue(BYDAutoBodyworkDevice bodyworkDevice) {
        int result = 0;
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice");
            Method getAutoType = clz.getDeclaredMethod("getBatteryPowerValue");
            getAutoType.setAccessible(true);
            Object ret = getAutoType.invoke(bodyworkDevice);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String getAutoType(BYDAutoBodyworkDevice device) {
        if (device == null) {
            return "";
        }
        String autoTypeName = "";
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice");
            Method getAutoType = clz.getDeclaredMethod("getAutoType");
            getAutoType.setAccessible(true);
            Object ret = getAutoType.invoke(device);
            int autoType = (int) ret;
            autoTypeName = StringUtil.getAutoTypeName(autoType);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return autoTypeName;
    }

    public static int[] getAllRadarDistance(BYDAutoRadarDevice device) {
        if (device != null) {
            try {
                Class<?> clz = Class.forName("android.hardware.bydauto.radar.BYDAutoRadarDevice");
                Method method = clz.getDeclaredMethod("getAllRadarDistance");
                method.setAccessible(true);
                Object invoke = method.invoke(device);
                return (int[]) invoke;
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
                KLog.e();
            }
        }
        return new int[9];
    }

    public static int get(BYDAutoEngineDevice engineDevice, int featuresId) {
        int result = 0;
        if (engineDevice != null) {
            try {
                Class<?> clz = Class.forName("android.hardware.bydauto.AbsBYDAutoDevice");
                Method get = clz.getDeclaredMethod("get", int.class, int.class);
                get.setAccessible(true);
                result = (int) get.invoke(engineDevice, BYDAutoConstants.BYDAUTO_DEVICE_ENGINE, featuresId);
//                KLog.e();
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    private static void testReflectInvoke() {
        try {
            Class<?> clz = Class.forName("com.huawei.byd_sdk_29.TestBean");
            Object obj = clz.newInstance();

            Class<?> clzParent = Class.forName("com.huawei.byd_sdk_29.TestParentBean");
            Method get = clzParent.getDeclaredMethod("get", int.class, int.class);
            get.setAccessible(true);
            Object ret = get.invoke(obj, 1, 2);
            KLog.e();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
