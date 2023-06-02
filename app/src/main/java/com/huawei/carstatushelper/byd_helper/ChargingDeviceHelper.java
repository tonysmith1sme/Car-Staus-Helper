package com.huawei.carstatushelper.byd_helper;

import android.hardware.bydauto.BYDAutoConstants;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;

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
        return BYDDeviceHelper.get(device, BYDAutoConstants.BYDAUTO_DEVICE_CHARGING, featuresId);
    }

    public Object getPower() {
        return device.getChargingPower();
//        return get(BYDAutoFeatureIds.CHARGING_POWER);
    }

    public Object getMinute() {
        int data = (int) get(BYDAutoFeatureIds.CHARGING_FULL_REST_MINUTE);
        return data > 60 ? 0 : data;
    }

    public Object getHour() {
        int data = (int) get(BYDAutoFeatureIds.CHARGING_FULL_REST_HOUR);
        return data > 24 ? 0 : data;
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
     * public static final int CHARGING_BATTERY_STATE_CHARG_FINISH = 2;
     * 未充电状态下是 15
     *
     * @return
     */
    public Object getBatteryState() {
        int ret = (int) get(BYDAutoFeatureIds.CHARGING_BATTERRY_DEVICE_STATE);
        if (ret == BYDAutoChargingDevice.CHARGING_BATTERY_STATE_CHARGING) {
            return "充电中";
        }
        if (ret == BYDAutoChargingDevice.CHARGING_BATTERY_STATE_CHARG_FINISH) {
            return "充电结束";
        }
        if (ret == 15) {
            return "放电中";
        }
        return "type:" + ret;
    }

    /**
     * 充电枪连接状态
     * public static final int CHARGING_GUN_STATE_CONNECTED_DC = 3;
     * public static final int CHARGING_GUN_STATE_CONNECTED_NONE = 1;
     *
     * @return
     */
    public String getGunConnect() {
        int ret = (int) get(BYDAutoFeatureIds.CHARGING_GUN_CONNECT_STATE);
        if (ret == BYDAutoChargingDevice.CHARGING_GUN_STATE_CONNECTED_NONE) {
            return "未连接";
        }
        if (ret == BYDAutoChargingDevice.CHARGING_GUN_STATE_CONNECTED_DC) {
            return "直流已连接";
        }
        return "type:" + ret;
    }

    /**
     * 充电机连接状态
     * public static final int CHARGING_CHARGER_STATE_CONNECTED = 1;
     * public static final int CHARGING_CHARGER_STATE_NOT_CONNECTED = 0;
     *
     * @return
     */
    public String getChargerConnect() {
        int ret = (int) get(BYDAutoFeatureIds.CHARGING_CHARGER_CONNECT_STATE);
        if (ret == BYDAutoChargingDevice.CHARGING_CHARGER_STATE_CONNECTED) {
            return "已连接";
        }
        return "未连接";
    }
}
