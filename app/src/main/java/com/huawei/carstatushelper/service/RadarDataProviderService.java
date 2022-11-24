package com.huawei.carstatushelper.service;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.bydauto.panorama.AbsBYDAutoPanoramaListener;
import android.hardware.bydauto.panorama.BYDAutoPanoramaDevice;
import android.hardware.bydauto.radar.AbsBYDAutoRadarListener;
import android.hardware.bydauto.radar.BYDAutoRadarDevice;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;

import com.huawei.byd_sdk_29.Api29Helper;

public class RadarDataProviderService extends Service {

    private BYDAutoRadarDevice radarDevice;
//    private static final Map<Integer, String> autoTypeMap = new HashMap<>();
//    private static final Map<Integer, String> radarStateNameMap = new HashMap<>();

//    static {
//        autoTypeMap.put(BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_P, "P");
//        autoTypeMap.put(BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_R, "R");
//        autoTypeMap.put(BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_N, "N");
//        autoTypeMap.put(BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_D, "D");
//        autoTypeMap.put(BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_M, "M");
//        autoTypeMap.put(BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_S, "S");
//
//        radarStateNameMap.put(BYDAutoRadarDevice.RADAR_PROBE_STATE_ABNORMAL, "ABNORMAL");
//        radarStateNameMap.put(BYDAutoRadarDevice.RADAR_PROBE_STATE_SAFE, "SAFE");
//        radarStateNameMap.put(BYDAutoRadarDevice.RADAR_PROBE_STATE_GREEN, "GREEN");
//        radarStateNameMap.put(BYDAutoRadarDevice.RADAR_PROBE_STATE_YELLOW, "YELLOW");
//        radarStateNameMap.put(BYDAutoRadarDevice.RADAR_PROBE_STATE_RED, "RED");
//    }

    private BYDAutoPanoramaDevice panoramaDevice;

    public RadarDataProviderService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int ret = super.onStartCommand(intent, flags, startId);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BYDAUTO_PANORAMA_COMMON) == PackageManager.PERMISSION_GRANTED) {
            if (panoramaDevice == null) {
                panoramaDevice = BYDAutoPanoramaDevice.getInstance(this);
                panoramaDevice.registerListener(panoramaListener);
            }
            if (radarDevice == null) {
                radarDevice = BYDAutoRadarDevice.getInstance(this);
                radarDevice.registerListener(radarListener);
            }
        }
        return ret;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (panoramaDevice != null) {
            panoramaDevice.unregisterListener(panoramaListener);
        }
        if (radarDevice != null) {
            radarDevice.unregisterListener(radarListener);
        }
    }

    private final AbsBYDAutoPanoramaListener panoramaListener = new AbsBYDAutoPanoramaListener() {
        /**
         * 监听全景打开状态（）
         * @param mode
         */
        @Override
        public void onPanoWorkStateChanged(int mode) {
            super.onPanoWorkStateChanged(mode);
            if (mode == BYDAutoPanoramaDevice.PANORAMA_WORK_ON) {
                sendBroadcast(new Intent(RadarFloatingService.ACTION_SHOW_RADAR_FLOATING));
            } else {
                sendBroadcast(new Intent(RadarFloatingService.ACTION_HIDE_RADAR_FLOATING));
            }
        }
    };

    private final AbsBYDAutoRadarListener radarListener = new AbsBYDAutoRadarListener() {
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
}