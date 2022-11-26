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

    public static int getHEVMileageValue(BYDAutoStatisticDevice statisticDevice) {
        int result = 0;
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
            Method getHEVMileageValue = clz.getDeclaredMethod("getHEVMileageValue");
            getHEVMileageValue.setAccessible(true);
            result = (int) getHEVMileageValue.invoke(statisticDevice);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 1,BYDAutoFeatureIds.STATISTIC_DRIVING_TIME
     * 2,BYDAutoFeatureIds.STATISTIC_MILEAGE1_DRIVE_TIME
     * 3,
     * 4,BYDAutoFeatureIds.STATISTIC_MILEAGE2_DRIVE_TIME
     *
     * @param target
     * @return
     */
    public static double getTravelTime(BYDAutoStatisticDevice statisticDevice, int target) {
        double result = 0;
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
            Method getTravelTime = clz.getDeclaredMethod("getTravelTime", int.class);
            getTravelTime.setAccessible(true);
            result = (double) getTravelTime.invoke(statisticDevice, target);
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String getAutoType(BYDAutoBodyworkDevice device) {
        int autoType = 0;
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice");
            Method getAutoType = clz.getDeclaredMethod("getAutoType");
            getAutoType.setAccessible(true);
            Object ret = getAutoType.invoke(device);
            autoType = (int) ret;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return String.valueOf(autoType);
    }

    public static String getWaterTemperature(BYDAutoStatisticDevice device) {
        if (device != null) {
            try {
                Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
                Method method = clz.getDeclaredMethod("getWaterTemperature");
                method.setAccessible(true);
                Object invoke = method.invoke(device);
                int ret = (int) invoke;
                return String.valueOf(ret > 0 ? ret : 0);
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
                KLog.e();
            }
        }
        return "26";
    }

    public static String getInstantElecConValue(BYDAutoStatisticDevice device) {
        if (device != null) {
            try {
                Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
                Method method = clz.getDeclaredMethod("getInstantElecConValue");
                method.setAccessible(true);
                Object invoke = method.invoke(device);
                double ret = (double) invoke;
                return String.valueOf(ret > 0 ? ret : 0);
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
                KLog.e();
            }
        }
        return "0";
    }

    public static String getInstantFuelConValue(BYDAutoStatisticDevice device) {
        if (device != null) {
            try {
                Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
                Method method = clz.getDeclaredMethod("getInstantFuelConValue");
                method.setAccessible(true);
                Object invoke = method.invoke(device);
                return String.valueOf((double) invoke);
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
                KLog.e();
            }
        }
        return "0";
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
