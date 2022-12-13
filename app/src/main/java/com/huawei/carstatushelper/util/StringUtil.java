package com.huawei.carstatushelper.util;

import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {
    public static final int ENERGY_MODE_EV = 1;
    public static final int ENERGY_MODE_FORCE_EV = 2;
    public static final int ENERGY_MODE_FUEL = 4;
    public static final int ENERGY_MODE_HEV = 3;
    public static final int ENERGY_MODE_KEEP = 5;
    public static final int ENERGY_MODE_STOP = 0;

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

    public static final int ENERGY_OPERATION_ECONOMY = 1;
    public static final int ENERGY_OPERATION_KEEP = 3;

    protected static final int ENERGY_OPERATION_MODE_ECO = 2;
    protected static final int ENERGY_OPERATION_MODE_NORMAL = 1;
    protected static final int ENERGY_OPERATION_MODE_SPORT = 3;

    public static final int ENERGY_OPERATION_MUDDY = 5;
    public static final int ENERGY_OPERATION_NORMAL = 3;
    public static final int ENERGY_OPERATION_SAND = 6;
    public static final int ENERGY_OPERATION_SNOW = 4;
    public static final int ENERGY_OPERATION_SPORT = 2;


    public static String getOperationModeName(int operationMode) {
        String modeName;
        switch (operationMode) {
            case BYDAutoEnergyDevice.ENERGY_OPERATION_ECONOMY:
                modeName = "ECO";
                break;
            case BYDAutoEnergyDevice.ENERGY_OPERATION_SPORT:
                modeName = "SPORT";
                break;
            case ENERGY_OPERATION_MUDDY:
                modeName = "MUDDY";
                break;
            case ENERGY_OPERATION_NORMAL:
                modeName = "NORMAL";
                break;
            case ENERGY_OPERATION_SAND:
                modeName = "SAND";
                break;
            case ENERGY_OPERATION_SNOW:
                modeName = "SNOW";
                break;
            default:
                modeName = "mode:" + operationMode;
                break;
        }
        return modeName;
    }

    public static String getAutoTypeName(int autoType) {
        Map<Integer, String> map = new HashMap<>();
        map.put(20, "3A");
        map.put(36, "3B");
        map.put(5, "5A");
        map.put(29, "5AEV");
        map.put(22, "5B");
        map.put(27, "5BH");
        map.put(53, "5BHE");
        map.put(64, "5BHI");
        map.put(7, "6A");
        map.put(8, "6B");
        map.put(96, "EL");
        map.put(117, "EL20");
        map.put(127, "EM2E");
        map.put(95, "EMEA");
        map.put(0, "F0");
        map.put(1, "F3");
        map.put(2, "F3R");
        map.put(6, "F6");
        map.put(3, "G3");
        map.put(13, "HA");
        map.put(101, "HA2EM");
        map.put(122, "HA2FL");
//        map.put(122,"HA2FM");
        map.put(102, "HA2HE");
        map.put(46, "HAC");
        map.put(50, "HAD");
        map.put(63, "HADA");
        map.put(51, "HADE");
        map.put(66, "HADF");
        map.put(85, "HADG");
        map.put(49, "HAEA");
        map.put(62, "HAEC");
        map.put(28, "HAEV");
        map.put(37, "HA_15A");
        map.put(14, "HB");
        map.put(15, "HC");
        map.put(92, "HCE");
        map.put(93, "HCF");
        map.put(121, "HCHY");
        map.put(31, "HD");
        map.put(98, "HDE");
        map.put(124, "HDE21");
        map.put(99, "HDF");
        map.put(131, "KD");
        map.put(4, "L3");
        map.put(33, "L3G");
        map.put(10, "M6");
        map.put(44, "MEE");
        map.put(109, "MEEY");
        map.put(42, "MEF");
        map.put(81, "MEFD");
        map.put(43, "MEH");
        map.put(67, "MEHD");
        map.put(113, "MEHM");
        map.put(45, "RSA");
        map.put(9, "S6");
        map.put(17, "S6DM");
        map.put(16, "S8");
        map.put(11, "SA");
        map.put(84, "SA2E");
        map.put(112, "SA2EM");
        map.put(82, "SA2FC");
        map.put(110, "SA2FL");
        map.put(83, "SA2H");
        map.put(111, "SA2HG");
        map.put(72, "SA3E");
        map.put(68, "SA3F");
        map.put(139, "SA3FL");
        map.put(71, "SA3H");
        map.put(12, "SADM");
        map.put(40, "SAEA");
        map.put(70, "SAEC");
        map.put(97, "SAED");
        map.put(94, "SAEG");
        map.put(39, "SAEV");
//        map.put(73,"SAFG");
        map.put(73, "SAFJ");
        map.put(32, "SAH");
        map.put(88, "SAHA");
        map.put(89, "SAHB");
        map.put(90, "SAHC");
        map.put(65, "SAHE");
        map.put(61, "SAHG");
        map.put(74, "SAHX");
        map.put(30, "SC");
        map.put(128, "SC2E");
        map.put(41, "SCEA");
        map.put(69, "SCED");
        map.put(35, "SCH");
        map.put(23, "SE");
        map.put(24, "SEF");
        map.put(48, "SEH");
        map.put(130, "SK2F");
        map.put(129, "SK2H");
        map.put(47, "ST");
        map.put(56, "STC");
        map.put(58, "STE");
        map.put(59, "STEB");
        map.put(114, "STEL");
        map.put(60, "STEM");
        map.put(54, "STF");
//        map.put(53,"STF20");
        map.put(116, "STF21");
        map.put(55, "STFD");
//        map.put(52,"STH20");
        map.put(115, "STH21");
//        map.put(56,"STHM");
        map.put(57, "STM");
        map.put(18, "VA");
        map.put(19, "VB");
        map.put(26, "VBEV");
        map.put(25, "VBH");
        map.put(21, "VC");
        map.put(38, "E6B");
        map.put(34, "E6H");
        map.put(52, "E6K");
        if (map.containsKey(autoType)) {
            return map.get(autoType);
        }
        return String.valueOf(autoType);
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

    public static String getGearboxTypeName(int gearboxType) {
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

    public static String getAcTemperatureAreaName(int area) {
        if (area == BYDAutoAcDevice.AC_TEMPERATURE_MAIN) {
            return "主驾驶温度";
        } else if (area == BYDAutoAcDevice.AC_TEMPERATURE_DEPUTY) {
            return "副驾驶温度";
        } else if (area == BYDAutoAcDevice.AC_TEMPERATURE_REAR) {
            return "后空调";
        } else if (area == BYDAutoAcDevice.AC_TEMPERATURE_OUT) {
            return "车外温度";
        }
        return String.valueOf(area);
    }

    /**
     * 获取电源档位
     *
     * @param level
     * @return
     */
    public static String getPowerLevelName(int level) {
        Map<Integer, String> map = new HashMap<>();
        map.put(BYDAutoBodyworkDevice.BODYWORK_POWER_LEVEL_OFF, "OFF");
        map.put(BYDAutoBodyworkDevice.BODYWORK_POWER_LEVEL_ACC, "ACC");
        map.put(BYDAutoBodyworkDevice.BODYWORK_POWER_LEVEL_ON, "ON");
        map.put(3, "OK");
        map.put(4, "FAKE_OK");
        map.put(BYDAutoBodyworkDevice.BODYWORK_POWER_LEVEL_INVALID, "INVALID");
        if (map.containsKey(level)) {
            return map.get(level);
        }
        return String.valueOf(level);
    }
}
