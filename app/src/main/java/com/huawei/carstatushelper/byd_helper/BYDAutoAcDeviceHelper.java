package com.huawei.carstatushelper.byd_helper;

import android.hardware.bydauto.ac.BYDAutoAcDevice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BYDAutoAcDeviceHelper {
    private static BYDAutoAcDeviceHelper INSTANCE;
    private BYDAutoAcDevice device;

    private BYDAutoAcDeviceHelper(BYDAutoAcDevice device) {
        this.device = device;
    }

    public static BYDAutoAcDeviceHelper getInstance(BYDAutoAcDevice device) {
        if (INSTANCE == null) {
            INSTANCE = new BYDAutoAcDeviceHelper(device);
        }
        return INSTANCE;
    }

    public int setAcCompressorMode(int setSource, int mode) {
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.ac.BYDAutoAcDevice");
            Method method = clz.getDeclaredMethod("setAcCompressorMode", int.class, int.class);
            method.setAccessible(true);
            int ret = (int) method.invoke(device, setSource, mode);
            return ret;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
