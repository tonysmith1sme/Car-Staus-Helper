package com.huawei.carstatushelper.byd_helper;

import android.hardware.bydauto.BYDAutoConstants;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;

import com.socks.library.KLog;

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
            KLog.e("获取充电相关数据：" + featuresId + " --> " + result);
            return result;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Object getPower() {
        return device.getChargingPower();
//        return get(BYDAutoFeatureIds.CHARGING_POWER);
    }

    public Object getMinute() {
        return get(BYDAutoFeatureIds.CHARGING_FULL_REST_MINUTE);
    }

    public Object getHour() {
        return get(BYDAutoFeatureIds.CHARGING_FULL_REST_HOUR);
    }

    /**
     * 获取充电电压
     *
     * @return
     */
    public Object getVoltage() {
        return get(BYDAutoFeatureIds.CHARGING_CHARGE_BATTERY_VOLT);
    }

    /**
     * 获取充电电流
     *
     * @return
     */
    public Object getCurrent() {
        return get(BYDAutoFeatureIds.CHARGING_CHARGE_CURRENT);
    }

    /**
     * 电池状态
     * public static final int CHARGING_BATTERY_STATE_CHARGING = 1;
     * 未充电状态下是 15
     *
     * @return
     */
    public Object getBatteryState() {
        return get(BYDAutoFeatureIds.CHARGING_BATTERRY_DEVICE_STATE);
    }

    /**
     * 充电枪连接状态
     * public static final int CHARGING_GUN_STATE_CONNECTED_DC = 3;
     * public static final int CHARGING_GUN_STATE_CONNECTED_NONE = 1;
     *
     * @return
     */
    public Object getGunConnect() {
        return get(BYDAutoFeatureIds.CHARGING_GUN_CONNECT_STATE);
    }

    /**
     * 充电机连接状态
     * public static final int CHARGING_CHARGER_STATE_CONNECTED = 1;
     * public static final int CHARGING_CHARGER_STATE_NOT_CONNECTED = 0;
     *
     * @return
     */
    public Object getChargerConnect() {
        return get(BYDAutoFeatureIds.CHARGING_CHARGER_CONNECT_STATE);
    }
}
