package com.huawei.byd_sdk_29;

import android.hardware.bydauto.BYDAutoConstants;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.hardware.bydauto.radar.BYDAutoRadarDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;

import com.socks.library.KLog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Api29Helper {
    public static int getAutoType(BYDAutoBodyworkDevice device) {
        int autoType = 0;
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice");
            Method getAutoType = clz.getMethod("getAutoType");
            Object ret = getAutoType.invoke(device);
            autoType = (int) ret;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return autoType;
    }

    public static int getWaterTemperature(BYDAutoStatisticDevice device) {
        if (device != null) {
            try {
                Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
                Method method = clz.getMethod("getWaterTemperature");
                Object invoke = method.invoke(device);
                return (int) invoke;
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
                KLog.e();
            }
        }
        return 26;
    }

    public static double getInstantElecConValue(BYDAutoStatisticDevice device) {
        if (device != null) {
            try {
                Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
                Method method = clz.getMethod("getInstantElecConValue");
                Object invoke = method.invoke(device);
                return (double) invoke;
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
                KLog.e();
            }
        }
        return 0;
    }

    public static double getInstantFuelConValue(BYDAutoStatisticDevice device) {
        if (device != null) {
            try {
                Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
                Method method = clz.getMethod("getInstantFuelConValue");
                Object invoke = method.invoke(device);
                return (double) invoke;
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
                KLog.e();
            }
        }
        return 0;
    }

    public static int[] getAllRadarDistance(BYDAutoRadarDevice device) {
        if (device != null) {
            try {
                Class<?> clz = Class.forName("android.hardware.bydauto.radar.BYDAutoRadarDevice");
                Method method = clz.getMethod("getAllRadarDistance");
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

            Object ret = get.invoke(obj, 1, 2);
            KLog.e();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
