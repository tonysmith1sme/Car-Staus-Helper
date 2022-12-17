package com.huawei.carstatushelper.byd_helper;

import android.hardware.bydauto.BYDAutoConstants;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;

import com.socks.library.KLog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BYDAutoStatisticDeviceHelper {
    private static BYDAutoStatisticDeviceHelper INSTANCE;
    BYDAutoStatisticDevice device;
    private static final int INVALID_DATA = 65535;
    public static final int INVALID_DATA_1 = -10011;

    private BYDAutoStatisticDeviceHelper(BYDAutoStatisticDevice device) {
        this.device = device;
    }

    public static BYDAutoStatisticDeviceHelper getInstance(BYDAutoStatisticDevice device) {
        if (INSTANCE == null) {
            INSTANCE = new BYDAutoStatisticDeviceHelper(device);
        }
        return INSTANCE;
    }

    private int get(int featuresId) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Class<?> clz = Class.forName("android.hardware.bydauto.AbsBYDAutoDevice");
        Method get = clz.getDeclaredMethod("get", int.class, int.class);
        get.setAccessible(true);
        int result = (int) get.invoke(device, BYDAutoConstants.BYDAUTO_DEVICE_STATISTIC, featuresId);
        return result;
    }

    public int getAVERAGE_BATTERY_TEMP() {
        int result = 0;
        try {
            result = get(BYDAutoFeatureIds.STATISTIC_AVERAGE_BATTERY_TEMP);
            if (result == INVALID_DATA || result == INVALID_DATA_1) {
                return 0;
            }
        } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getESTIMATE_SOC_V1() {
        int result = 0;
        try {
            result = get(BYDAutoFeatureIds.STATISTIC_ESTIMATE_SOC_V1);
            if (result == INVALID_DATA || result == INVALID_DATA_1) {
                return 0;
            }
        } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getLOWEST_BATTERY_TEMP() {
        int result = 0;
        try {
            result = get(BYDAutoFeatureIds.STATISTIC_LOWEST_BATTERY_TEMP);
            if (result == INVALID_DATA || result == INVALID_DATA_1) {
                return 0;
            }
        } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getHIGHEST_BATTERY_TEMP() {
        int result = 0;
        try {
            result = get(BYDAutoFeatureIds.STATISTIC_HIGHEST_BATTERY_TEMP);
            if (result == INVALID_DATA || result == INVALID_DATA_1) {
                return 0;
            }
        } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param status 0-->里程1;
     *               1-->里程2;
     *               2-->总里程
     * @return
     */
    public double getMileageNumber(int status) {
//        if (status == 0) {
//            cmd = BYDAutoFeatureIds.STATISTIC_DD_MILEAGE1;
//        } else if (status == 1) {
//            cmd = BYDAutoFeatureIds.STATISTIC_DD_MILEAGE2;
//        } else if (status == 2) {
//            cmd = BYDAutoFeatureIds.STATISTIC_TOTAL_MILEAGE;
//        }
        double result = 0;
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
            Method getMileageNumber = clz.getDeclaredMethod("getMileageNumber", int.class);
            getMileageNumber.setAccessible(true);
            Object invoke = getMileageNumber.invoke(device, status);
            if (status == 0 || status == 1) {
                int mileage = (int) invoke;
                result = mileage * 1.0f / 10;
            } else {
                result = ((int) invoke);
            }
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getHEVMileageValue() {
        int result = 65535;
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
            Method getHEVMileageValue = clz.getDeclaredMethod("getHEVMileageValue");
            getHEVMileageValue.setAccessible(true);
            result = (int) getHEVMileageValue.invoke(device);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param target 1，总行程时间 BYDAutoFeatureIds.STATISTIC_DRIVING_TIME
     *               2，里程1行驶时间 BYDAutoFeatureIds.STATISTIC_MILEAGE1_DRIVE_TIME
     *               3，里程2行驶时间 BYDAutoFeatureIds.STATISTIC_MILEAGE2_DRIVE_TIME
     * @return
     */
    public double getTravelTime(int target) {
        double result = 0;
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
            Method getTravelTime = clz.getDeclaredMethod("getTravelTime", int.class);
            getTravelTime.setAccessible(true);
            result = (double) getTravelTime.invoke(device, target);
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getWaterTemperature() {
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
            Method method = clz.getDeclaredMethod("getWaterTemperature");
            method.setAccessible(true);
            Object invoke = method.invoke(device);
            return (int) invoke;
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            KLog.e();
        }
        return 0;
    }

    public double getInstantElecConValue() {
        if (device != null) {
            try {
                Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
                Method method = clz.getDeclaredMethod("getInstantElecConValue");
                method.setAccessible(true);
                Object invoke = method.invoke(device);
                return (double) invoke;
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
                KLog.e();
            }
        }
        return 0;
    }

    public double getInstantFuelConValue() {
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
            Method method = clz.getDeclaredMethod("getInstantFuelConValue");
            method.setAccessible(true);
            Object invoke = method.invoke(device);
            return (double) invoke;
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            KLog.e();
        }
        return 0;
    }
}