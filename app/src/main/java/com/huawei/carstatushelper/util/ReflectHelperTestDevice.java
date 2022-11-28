package com.huawei.carstatushelper.util;

import android.content.Context;

public class ReflectHelperTestDevice {

    public static ReflectHelperTestDevice getInstance(Context con) {
        return new ReflectHelperTestDevice();
    }

    public int getAutoSystemState() {
        return 1133;
    }

    public int hasFeature(String feature) {
        if ("aabbcc".equals(feature)) {
            return 3322;
        }
        return 2233;
    }

    public int testFun4(int level) {
        if (level == 3) {
            return 4466;
        }
        return 5588;
    }

    public int setAcWindLevel(int setSource, int level) {
        if (setSource == 1 && level == 2) {
            return 3344;
        }
        if (setSource == 3 && level == 5) {
            return 5566;
        }
        return 8877;
    }

    public int testFun5(int level, String arg2) {
        if (level == 3) {
            return 4466;
        }
        if (level == 4 && "abc".equals(arg2)) {
            return 4477;
        }
        return 5588;
    }


    public String testFun6() {
        return "abc";
    }

    public int testFun3(int[] args) {
        return 1166;
    }

    public int testFun2(String[] args) {
        return 1155;
    }

    public int testFun1(int[] arr1, String[] arr2) {
        return 1122;
    }

}
