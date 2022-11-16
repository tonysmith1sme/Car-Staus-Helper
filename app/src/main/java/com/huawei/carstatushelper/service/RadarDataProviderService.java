package com.huawei.carstatushelper.service;

import android.app.Service;
import android.content.Intent;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.radar.AbsBYDAutoRadarListener;
import android.hardware.bydauto.radar.BYDAutoRadarDevice;
import android.os.IBinder;

import com.huawei.byd_sdk_29.Api29Helper;

import java.util.HashMap;
import java.util.Map;

public class RadarDataProviderService extends Service {

    private BYDAutoGearboxDevice gearboxDevice;
    private BYDAutoRadarDevice radarDevice;
    private static final Map<Integer, String> autoTypeMap = new HashMap<>();
    private static final Map<Integer, String> radarStateNameMap = new HashMap<>();

    static {
        autoTypeMap.put(BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_P, "P");
        autoTypeMap.put(BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_R, "R");
        autoTypeMap.put(BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_N, "N");
        autoTypeMap.put(BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_D, "D");
        autoTypeMap.put(BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_M, "M");
        autoTypeMap.put(BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_S, "S");

        radarStateNameMap.put(BYDAutoRadarDevice.RADAR_PROBE_STATE_ABNORMAL, "ABNORMAL");
        radarStateNameMap.put(BYDAutoRadarDevice.RADAR_PROBE_STATE_SAFE, "SAFE");
        radarStateNameMap.put(BYDAutoRadarDevice.RADAR_PROBE_STATE_GREEN, "GREEN");
        radarStateNameMap.put(BYDAutoRadarDevice.RADAR_PROBE_STATE_YELLOW, "YELLOW");
        radarStateNameMap.put(BYDAutoRadarDevice.RADAR_PROBE_STATE_RED, "RED");
    }

    public RadarDataProviderService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
//        KLog.e();
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        KLog.e();
        gearboxDevice = BYDAutoGearboxDevice.getInstance(this);
        radarDevice = BYDAutoRadarDevice.getInstance(this);

        gearboxDevice.registerListener(absBYDAutoGearboxListener);
        radarDevice.registerListener(absBYDAutoRadarApi29Listener);
//        KLog.e();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        KLog.e();
        gearboxDevice.unregisterListener(absBYDAutoGearboxListener);
        radarDevice.unregisterListener(absBYDAutoRadarApi29Listener);
    }

    private final AbsBYDAutoRadarListener absBYDAutoRadarApi29Listener = new AbsBYDAutoRadarListener() {
        /**
         * 监听各位置探头状态
         * @param area
         * @param state
         */
        @Override
        public void onRadarProbeStateChanged(int area, int state) {
            super.onRadarProbeStateChanged(area, state);
//            KLog.e("area = " + area + ",state = " + state);
        }

        /**
         * 监听倒车雷达开关状态
         * @param state
         */
        @Override
        public void onReverseRadarSwitchStateChanged(int state) {
            super.onReverseRadarSwitchStateChanged(state);
//            KLog.e("state = " + state);
        }

//        @Override
        public void onRadarObstacleDistanceChanged(int area, int value) {
//            KLog.e("area" + area + ",value = " + value);
            if (radarDevice != null) {
                int[] distance = Api29Helper.getAllRadarDistance(radarDevice);
                Intent intent = new Intent(RadarFloatingService.ACTION_UPDATE_RADAR_DATA);
                intent.putExtra("data", distance);
                sendBroadcast(intent);
            }
        }
    };

    private final AbsBYDAutoGearboxListener absBYDAutoGearboxListener = new AbsBYDAutoGearboxListener() {
        @Override
        public void onGearboxAutoModeTypeChanged(int level) {
            super.onGearboxAutoModeTypeChanged(level);
            String autoTypeName = "null";
            if (autoTypeMap.containsKey(level)) {
                autoTypeName = autoTypeMap.get(level);
            }
//            KLog.e("当前档位：" + level + " " + autoTypeName);
            if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_R) {
                sendBroadcast(new Intent(RadarFloatingService.ACTION_SHOW_RADAR_FLOATING));
            } else {
                sendBroadcast(new Intent(RadarFloatingService.ACTION_HIDE_RADAR_FLOATING));
            }
        }
    };
}