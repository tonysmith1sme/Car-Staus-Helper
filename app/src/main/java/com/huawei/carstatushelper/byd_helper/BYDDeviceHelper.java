package com.huawei.carstatushelper.byd_helper;

import android.hardware.bydauto.AbsBYDAutoDevice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import timber.log.Timber;

public class BYDDeviceHelper {
    public static Object get(AbsBYDAutoDevice bydAutoDevice, int deviceType, int featuresId) {
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.AbsBYDAutoDevice");
            Method get = clz.getDeclaredMethod("get", int.class, int.class);
            get.setAccessible(true);
            Object result = get.invoke(bydAutoDevice, deviceType, featuresId);

            Timber.e("获取features对应数据：%d --> %s", featuresId, result);

            return result;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
            Timber.e(e);
        }
        return 0;
    }
}