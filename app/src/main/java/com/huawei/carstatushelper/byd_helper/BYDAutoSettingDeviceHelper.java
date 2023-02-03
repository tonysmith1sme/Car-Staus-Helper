package com.huawei.carstatushelper.byd_helper;

import android.hardware.bydauto.BYDAutoConstants;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;

import com.socks.library.KLog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BYDAutoSettingDeviceHelper {
    private static BYDAutoSettingDeviceHelper INSTANCE;
    private BYDAutoSettingDevice device;

    private BYDAutoSettingDeviceHelper(BYDAutoSettingDevice device) {
        this.device = device;
    }

    public static BYDAutoSettingDeviceHelper getInstance(BYDAutoSettingDevice device) {
        if (INSTANCE == null) {
            INSTANCE = new BYDAutoSettingDeviceHelper(device);
        }
        return INSTANCE;
    }

    private int get(int featuresId) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Class<?> clz = Class.forName("android.hardware.bydauto.AbsBYDAutoDevice");
        Method get = clz.getDeclaredMethod("get", int.class, int.class);
        get.setAccessible(true);
        int result = (int) get.invoke(device, BYDAutoConstants.BYDAUTO_DEVICE_SETTING, featuresId);
        return result;
    }

    public int setLeftViewMirrorFlipAngle(int value) {
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.setting.BYDAutoSettingDevice");
            Method method = clz.getDeclaredMethod("setLeftViewMirrorFlipAngle", int.class);
            method.setAccessible(true);
            Object invoke = method.invoke(device, value);
            return ((int) invoke);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int setRightViewMirrorFlipAngle(int value) {
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.setting.BYDAutoSettingDevice");
            Method method = clz.getDeclaredMethod("setRightViewMirrorFlipAngle", int.class);
            method.setAccessible(true);
            Object invoke = method.invoke(device, value);
            return ((int) invoke);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getLeftViewMirrorFlipAngle() {
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.setting.BYDAutoSettingDevice");
            Method method = clz.getDeclaredMethod("getLeftViewMirrorFlipAngle");
            method.setAccessible(true);
            Object invoke = method.invoke(device);
            return (int) invoke;
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            KLog.e();
        }
        return 0;
    }

    public int getRightViewMirrorFlipAngle() {
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.setting.BYDAutoSettingDevice");
            Method method = clz.getDeclaredMethod("getRightViewMirrorFlipAngle");
            method.setAccessible(true);
            Object invoke = method.invoke(device);
            return (int) invoke;
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            KLog.e();
        }
        return 0;
    }
}
