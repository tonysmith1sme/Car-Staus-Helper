package com.huawei.carstatushelper.byd_helper;

import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BYDAutoBodyworkDeviceHelper {
    private static BYDAutoBodyworkDeviceHelper INSTANCE;
    private BYDAutoBodyworkDevice device;

    private BYDAutoBodyworkDeviceHelper(BYDAutoBodyworkDevice device) {
        this.device = device;
    }

    public static BYDAutoBodyworkDeviceHelper getInstance(BYDAutoBodyworkDevice device) {
        if (INSTANCE == null) {
            INSTANCE = new BYDAutoBodyworkDeviceHelper(device);
        }
        return INSTANCE;
    }

    /**
     * @param leftFront  0 <= leftFront <= 5
     * @param rightFront 0 <= rightFront <= 5
     * @param leftRear   0 <= leftRear <= 5
     * @param rightRear  0 <= rightRear <= 5
     * @return
     */
    public int setAllWindowState(int leftFront, int rightFront, int leftRear, int rightRear) {
//        Log.m57d(TAG, "setAllWindowState leftFront is: " + leftFront + " rightFront state is " + rightFront + " leftRear state is " + leftRear + " rightRear state is " + rightRear);
//        this.mContext.enforceCallingOrSelfPermission(BODYWORK_SET_PERM, null);
//        if (leftFront < 0 || leftFront > 5 || rightFront < 0 || rightFront > 5 || leftRear < 0 || leftRear > 5 || rightRear < 0 || rightRear > 5) {
//            return -2147482645;
//        }
//        int[] cmd = {BYDAutoFeatureIds.BODYWORK_LF_WINDOW_CTRL_SET, BYDAutoFeatureIds.BODYWORK_RF_WINDOW_CTRL_SET, BYDAutoFeatureIds.BODYWORK_LR_WINDOW_CTRL_SET, BYDAutoFeatureIds.BODYWORK_RR_WINDOW_CTRL_SET};
//        int[] values = {leftFront, rightFront, leftRear, rightRear};
//        return super.set(mDeviceType, cmd, values);
        int ret = 0;
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice");
            Method method = clz.getDeclaredMethod("setAllWindowState", int.class, int.class, int.class, int.class);
            method.setAccessible(true);
            ret = (int) method.invoke(device, leftFront, rightFront, leftRear, rightRear);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
