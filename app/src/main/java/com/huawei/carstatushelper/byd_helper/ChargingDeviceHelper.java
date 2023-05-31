package com.huawei.carstatushelper.byd_helper;

import android.hardware.bydauto.BYDAutoConstants;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ChargingDeviceHelper {
    private static ChargingDeviceHelper INSTANCE;
    private BYDAutoChargingDevice device;

    private ChargingDeviceHelper(BYDAutoChargingDevice device) {
        this.device = device;
    }

    public static ChargingDeviceHelper getInstance(BYDAutoChargingDevice device) {
        if (INSTANCE == null) {
            INSTANCE = new ChargingDeviceHelper(device);
        }
        return INSTANCE;
    }

    private Object get(int featuresId) {
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.AbsBYDAutoDevice");
            Method get = clz.getDeclaredMethod("get", int.class, int.class);
            get.setAccessible(true);
            Object result = get.invoke(device, BYDAutoConstants.BYDAUTO_DEVICE_CHARGING, featuresId);
            return result;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getPower() {
        return (int) get(BYDAutoFeatureIds.CHARGING_POWER);
    }

    public int getMinute() {
        return (int) get(BYDAutoFeatureIds.CHARGING_FULL_REST_MINUTE);
    }

    public int getHour() {
        return (int) get(BYDAutoFeatureIds.CHARGING_FULL_REST_HOUR);
    }

    /**
     * 获取充电电压
     *
     * @return
     */
    public int getVoltage() {
        return (int) get(BYDAutoFeatureIds.CHARGING_CHARGE_BATTERY_VOLT);
    }

    /**
     * 获取充电电流
     *
     * @return
     */
    public double getCurrent() {
        return (double) get(BYDAutoFeatureIds.CHARGING_CHARGE_CURRENT);
    }

    /**
     * 电池状态
     *
     * @return
     */
    public int getBatteryState() {
        return (int) get(BYDAutoFeatureIds.CHARGING_BATTERRY_DEVICE_STATE);
    }

    /**
     * 充电枪连接状态
     *
     * @return
     */
    public int getGunConnect() {
        return (int) get(BYDAutoFeatureIds.CHARGING_GUN_CONNECT_STATE);
    }

    /**
     * 充电机连接状态
     *
     * @return
     */
    public int getChargerConnect() {
        return (int) get(BYDAutoFeatureIds.CHARGING_CHARGER_CONNECT_STATE);
    }
}
