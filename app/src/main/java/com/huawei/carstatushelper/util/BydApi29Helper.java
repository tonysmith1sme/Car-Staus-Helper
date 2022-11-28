package com.huawei.carstatushelper.util;

import android.hardware.bydauto.BYDAutoConstants;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.hardware.bydauto.radar.BYDAutoRadarDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;

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


    /**
     * @param statisticDevice
     * @param status          0-->里程1;
     *                        1-->里程2;
     *                        2-->总里程
     * @return
     */
    public static int getMileageNumber(BYDAutoStatisticDevice statisticDevice, int status) {
//        if (status == 0) {
//            cmd = BYDAutoFeatureIds.STATISTIC_DD_MILEAGE1;
//        } else if (status == 1) {
//            cmd = BYDAutoFeatureIds.STATISTIC_DD_MILEAGE2;
//        } else if (status == 2) {
//            cmd = BYDAutoFeatureIds.STATISTIC_TOTAL_MILEAGE;
//        }
        int result = 0;
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.statistic.BYDAutoStatisticDevice");
            Method getMileageNumber = clz.getDeclaredMethod("getMileageNumber", int.class);
            getMileageNumber.setAccessible(true);
            Object invoke = getMileageNumber.invoke(statisticDevice, status);
            result = ((int) invoke);
        } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getHEVMileageValue(BYDAutoStatisticDevice statisticDevice) {
        int result = 65535;
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
     * @param target 1，总行程时间 BYDAutoFeatureIds.STATISTIC_DRIVING_TIME
     *               2，里程1行驶时间 BYDAutoFeatureIds.STATISTIC_MILEAGE1_DRIVE_TIME
     *               4，里程2行驶时间 BYDAutoFeatureIds.STATISTIC_MILEAGE2_DRIVE_TIME
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
