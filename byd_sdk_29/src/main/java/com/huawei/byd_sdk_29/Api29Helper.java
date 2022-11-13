package com.huawei.byd_sdk_29;

import android.hardware.bydauto.radar.BYDAutoRadarDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;

import com.socks.library.KLog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Api29Helper {
    public static int getWaterTemperature(BYDAutoStatisticDevice device) {
        if (device != null) {
            try {
                Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
                Method method = clz.getMethod("getWaterTemperature", (Class<?>) null);
                Object invoke = method.invoke(device, (Object) null);
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
                Method method = clz.getMethod("getInstantElecConValue", (Class<?>) null);
                Object invoke = method.invoke(device, null);
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
                Method method = clz.getMethod("getInstantFuelConValue", (Class<?>) null);
                Object invoke = method.invoke(device, null);
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
                Method method = clz.getMethod("getAllRadarDistance", Void.class);
                Object invoke = method.invoke(device, null);
                return (int[]) invoke;
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
                KLog.e();
            }
        }
        return new int[9];
    }
}
