package com.huawei.carstatushelper.util;

import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {
    public static String getEnergyModeName(int energyMode) {
        String modeName;
        switch (energyMode) {
            case BYDAutoEnergyDevice.ENERGY_MODE_STOP:
                modeName = "STOP";
                break;
            case BYDAutoEnergyDevice.ENERGY_MODE_EV://有效
                modeName = "EV";
                break;
            case BYDAutoEnergyDevice.ENERGY_MODE_FORCE_EV://有效
                modeName = "FORCE EV";
                break;
            case BYDAutoEnergyDevice.ENERGY_MODE_HEV://有效
                modeName = "HEV";
                break;
            case BYDAutoEnergyDevice.ENERGY_MODE_FUEL:
                modeName = "FUEL";
                break;
            case BYDAutoEnergyDevice.ENERGY_MODE_KEEP:
                modeName = "KEEP";
                break;
            default:
                modeName = "mode:" + energyMode;
                break;
        }
        return modeName;
    }

    public static String getOperationModeName(int operationMode) {
        String modeName;
        switch (operationMode) {
            case BYDAutoEnergyDevice.ENERGY_OPERATION_ECONOMY:
                modeName = "ECO";
                break;
            case BYDAutoEnergyDevice.ENERGY_OPERATION_SPORT:
                modeName = "SPORT";
                break;
            default:
                modeName = "mode:" + operationMode;
                break;
        }
        return modeName;
    }

    public static String getAutoModelName(BYDAutoBodyworkDevice device) {
        if (device == null) {
            return "";
        }
        int code = device.getAutoModelName();
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "秦 HEV");
        map.put(2, "秦 EV");
        map.put(3, "秦 燃油款");
        map.put(4, "唐 HEV");
        map.put(5, "唐 燃油款");
        map.put(6, "唐 EV");
        map.put(7, "宋 2018 燃油款");
        map.put(8, "宋 2018 HEV");
        map.put(9, "宋 2018 EV");
        map.put(10, "宋 MAX HEV");
        map.put(11, "NULL");
        if (map.containsKey(code)) {
            return map.get(code);
        }
        if (code == -2147482645) {
            return "INVALID_VALUE";
        }
        return "code:" + code;
    }

    public static String getGearboxTypeName(int gearboxType){
        Map<Integer, String> gearboxTypeNameMap = new HashMap<>();
        gearboxTypeNameMap.put(BYDAutoGearboxDevice.GEARBOX_TYPE_MT, "MT");
        gearboxTypeNameMap.put(BYDAutoGearboxDevice.GEARBOX_TYPE_AMT, "AMT");
        gearboxTypeNameMap.put(BYDAutoGearboxDevice.GEARBOX_TYPE_AT, "AT");
        gearboxTypeNameMap.put(BYDAutoGearboxDevice.GEARBOX_TYPE_CVT, "CVT");
        gearboxTypeNameMap.put(BYDAutoGearboxDevice.GEARBOX_TYPE_DCT, "DCT");
        gearboxTypeNameMap.put(BYDAutoGearboxDevice.GEARBOX_TYPE_INVALID1, "ECVT");
        gearboxTypeNameMap.put(BYDAutoGearboxDevice.GEARBOX_TYPE_INVALID2, "INVALID2");
        gearboxTypeNameMap.put(BYDAutoGearboxDevice.GEARBOX_TYPE_NONE, "NONE");
        gearboxTypeNameMap.put(BYDAutoGearboxDevice.GEARBOX_TYPE_INVALID, "INVALID");
        String gearboxTypeName = "INVALID";
        if (gearboxTypeNameMap.containsKey(gearboxType)) {
            gearboxTypeName = gearboxTypeNameMap.get(gearboxType);
        }
        return gearboxTypeName;
    }

    public static String getGearboxLevelName(int level) {
        if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_P) {
            return "P";
        }
        if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_R) {
            return "R";
        }
        if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_N) {
            return "N";
        }
        if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_D) {
            return "D";
        }
        if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_M) {
            return "M";
        }
        if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_S) {
            return "S";
        }
        return "level:" + level;
    }
}
