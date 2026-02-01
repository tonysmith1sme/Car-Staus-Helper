package com.huawei.carstatushelper.byd_helper;

import android.hardware.bydauto.BYDAutoConstants;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import timber.log.Timber;

public class BYDAutoInstrumentDeviceHelper {
    private static BYDAutoInstrumentDeviceHelper INSTANCE;
    private BYDAutoInstrumentDevice device;

    private BYDAutoInstrumentDeviceHelper(BYDAutoInstrumentDevice device) {
        this.device = device;
    }

    public static BYDAutoInstrumentDeviceHelper getInstance(BYDAutoInstrumentDevice device) {
        if (INSTANCE == null) {
            INSTANCE = new BYDAutoInstrumentDeviceHelper(device);
        }
        return INSTANCE;
    }

    public int set(int event, int value) {
        try {
            Class<?> clz = Class.forName("android.hardware.bydauto.AbsBYDAutoDevice");
            Method set = clz.getDeclaredMethod("set", int.class, int.class, int.class);
            set.setAccessible(true);
            int result = (int) set.invoke(device, BYDAutoConstants.BYDAUTO_DEVICE_INSTRUMENT, event, value);
            return result;
        } catch (ClassNotFoundException | InvocationTargetException | IllegalArgumentException | IllegalAccessException | SecurityException | NoSuchMethodException e) {
            e.printStackTrace();
            Timber.e(e);
        }
        return 0;
    }
}