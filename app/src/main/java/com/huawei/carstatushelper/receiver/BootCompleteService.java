package com.huawei.carstatushelper.receiver;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.panorama.AbsBYDAutoPanoramaListener;
import android.hardware.bydauto.panorama.BYDAutoPanoramaDevice;
import android.hardware.bydauto.radar.AbsBYDAutoRadarListener;
import android.hardware.bydauto.radar.BYDAutoRadarDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.byd_helper.BYDAutoStatisticDeviceHelper;
import com.huawei.carstatushelper.util.AutoBootHelper;
import com.huawei.carstatushelper.util.BydApi29Helper;
import com.huawei.carstatushelper.util.RadarDistanceHelper;
import com.socks.library.KLog;
import com.ziwenl.floatingwindowdemo.FloatingWindowService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 测试指令：
 * 显示雷达距离浮窗
 * adb shell am broadcast -a "android.intent.action.SHOW_RADAR_FLOATING"
 * 隐藏雷达距离浮窗
 * adb shell am broadcast -a "android.intent.action.HIDE_RADAR_FLOATING"
 * 更新雷达数据
 * adb shell am broadcast -a "android.intent.action.UPDATE_RADAR_DATA" --eia data 12,12,12,12,12,12,12,12,12
 * 更新雷达数据
 * adb shell am broadcast -a "android.intent.action.UPDATE_RADAR_DATA" --eia data 20,20,20,20,20,20,20,20,20
 * <p>
 * 浮窗参数：
 * x =
 */
public class BootCompleteService extends Service {
    public static final String KEY_INIT_DRIVER_DATA = "init_driver_data";

    private BYDAutoRadarDevice radarDevice;
    private BYDAutoPanoramaDevice panoramaDevice;
    private BYDAutoGearboxDevice gearboxDevice;
    private RadarDistanceHelper radarDistanceHelper;
    private SharedPreferences preferences;

    public BootCompleteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void updateRadarFloatingTriggerType() {
        radarFloatingTriggerType = Integer.parseInt(preferences.getString("radar_floating_trigger_type", "3"));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        KLog.e();
        initNotification();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        updateRadarFloatingTriggerType();

        double latest_fuel_price = Double.parseDouble(preferences.getString("latest_fuel_price", "8.5"));
        double latest_electric_price = Double.parseDouble(preferences.getString("latest_electric_price", "1.7"));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BYDAUTO_STATISTIC_GET) == PackageManager.PERMISSION_GRANTED) {
            BYDAutoStatisticDevice statisticDevice = BYDAutoStatisticDevice.getInstance(this);
            int totalMileageValue = statisticDevice.getTotalMileageValue();//总里程
            int evMileageValue = statisticDevice.getEVMileageValue();//总ev里程
            int hevMileageValue = BYDAutoStatisticDeviceHelper.getInstance(statisticDevice).getHEVMileageValue();//总hev里程
            double totalFuelConValue = statisticDevice.getTotalFuelConValue();//累计燃油消耗
            double totalElecConValue = statisticDevice.getTotalElecConValue();//累计电量消耗
            // TODO: 2022/11/23 单次行程数据计算
            try {
                JSONObject object = new JSONObject();
                object.put("totalMileageValue", totalMileageValue);
                object.put("evMileageValue", evMileageValue);
                object.put("hevMileageValue", hevMileageValue);
                object.put("totalElecConValue", totalElecConValue);
                object.put("totalFuelConValue", totalFuelConValue);
                object.put("latest_fuel_price", latest_fuel_price);
                object.put("latest_electric_price", latest_electric_price);
                String json = object.toString();
                KLog.e("初始化行程数据：" + json);
                preferences.edit().putString(KEY_INIT_DRIVER_DATA, json).apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        radarDistanceHelper = new RadarDistanceHelper(this);

        if (preferences.getBoolean("radar_floating_boot_auto_show_enable", false)) {
            startService(new Intent(this, FloatingWindowService.class));
        }
    }

    private void initNotification() {
        String channelId = getPackageName();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //反射
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= 26) {
            builder = AutoBootHelper.newNotificationBuilder(this, channelId);
        } else {
            builder = new Notification.Builder(this);
        }
        Notification notification = builder.setSmallIcon(R.mipmap.ic_launcher).setContentTitle("服务已启动").setSubText("运行中。。。").build();
        //反射
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT);
            AutoBootHelper.createNotificationChannel(manager, channel);
        }
        startForeground(1, notification);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int count = 0;
//                do {
//                    KLog.e("boot complete test ,count = " + count);
//                    count++;
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                } while (count <= 120);
//                KLog.e("boot complete test finish");
//            }
//        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int ret = super.onStartCommand(intent, flags, startId);
        KLog.e();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BYDAUTO_PANORAMA_COMMON) == PackageManager.PERMISSION_GRANTED) {
            if (panoramaDevice == null) {
                panoramaDevice = BYDAutoPanoramaDevice.getInstance(this);
                KLog.e("panoramaDevice 初始化成功");
            } else {
                KLog.e("panoramaDevice 已启动");
            }
        } else {
            KLog.e("BYDAUTO_PANORAMA_COMMON 未授权");
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BYDAUTO_PANORAMA_GET) == PackageManager.PERMISSION_GRANTED) {
            if (panoramaDevice != null) {
                panoramaDevice.registerListener(panoramaListener);
            } else {
                KLog.e("panoramaDevice == null");
            }
        } else {
            KLog.e("BYDAUTO_PANORAMA_GET 未授权");
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BYDAUTO_GEARBOX_GET) == PackageManager.PERMISSION_GRANTED) {
            if (gearboxDevice == null) {
                gearboxDevice = BYDAutoGearboxDevice.getInstance(this);
                gearboxDevice.registerListener(gearboxListener);
            }
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BYDAUTO_RADAR_GET) == PackageManager.PERMISSION_GRANTED) {
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
        KLog.e();
        if (panoramaDevice != null) {
            panoramaDevice.unregisterListener(panoramaListener);
        }
        if (gearboxDevice != null) {
            gearboxDevice.unregisterListener(gearboxListener);
        }
        if (radarDevice != null) {
            radarDevice.unregisterListener(radarListener);
        }
    }

    private final AbsBYDAutoGearboxListener gearboxListener = new AbsBYDAutoGearboxListener() {
        /**
         * 获取自动变速箱档位变化
         * @param level
         */
        @Override
        public void onGearboxAutoModeTypeChanged(int level) {
            super.onGearboxAutoModeTypeChanged(level);
            KLog.e("gearbox auto mode type = " + level);
            updateRadarFloatingTriggerType();
            if (radarFloatingTriggerType == TYPE_GEARBOX_R) {
                if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_R) {
                    radarDistanceHelper.showRadarFloating();
                } else {
                    radarDistanceHelper.hideRadarFloating();
                }
            }
        }
    };

    /**
     * 0,onPanoWorkStateChanged
     * 1,onPanOutputStateChanged
     * 2,
     */
    int radarFloatingTriggerType;
    private static final int TYPE_PANO_WORK_STATE = 1;
    private static final int TYPE_PANO_OUTPUT_STATE = 2;
    private static final int TYPE_GEARBOX_R = 0;
    private static final int TYPE_DISTANCE_150_CM = 3;

    private final AbsBYDAutoPanoramaListener panoramaListener = new AbsBYDAutoPanoramaListener() {
        /**
         * 监听全景打开状态（）
         * @param mode
         */
        @Override
        public void onPanoWorkStateChanged(int mode) {
            super.onPanoWorkStateChanged(mode);
            KLog.e("onPanoWorkStateChanged：" + mode);
            updateRadarFloatingTriggerType();
            if (radarFloatingTriggerType == TYPE_PANO_WORK_STATE) {
                if (mode == BYDAutoPanoramaDevice.PANORAMA_WORK_ON) {
                    radarDistanceHelper.showRadarFloating();
                } else {
                    radarDistanceHelper.hideRadarFloating();
                }
            }
        }

        /**
         * 监听影像输出状态
         * @param mode
         */
        @Override
        public void onPanOutputStateChanged(int mode) {
            super.onPanOutputStateChanged(mode);
            KLog.e("onPanOutputStateChanged = " + mode);
            updateRadarFloatingTriggerType();
            if (radarFloatingTriggerType == TYPE_PANO_OUTPUT_STATE) {
                //关闭显示
                if (mode == BYDAutoPanoramaDevice.PANORAMA_OUTPUT_OFF) {
                    radarDistanceHelper.hideRadarFloating();
                    //打开显示
                } else {
                    radarDistanceHelper.showRadarFloating();
                }
            }

        }
    };

    private final int[] mRadarDistanceArr = new int[]{
            150, 150, 150,
            150, 150, 150,
            150, 150, 150,
    };

    private boolean shouldShowRadarDistance() {
        boolean shouldShow = false;
        for (int distance : mRadarDistanceArr) {
            if (distance != 150) {
                shouldShow = true;
                break;
            }
        }
        return shouldShow;
    }

    private boolean radarDistanceShowing;

    private final AbsBYDAutoRadarListener radarListener = new AbsBYDAutoRadarListener() {
        public void onRadarObstacleDistanceChanged(int area, int value) {
            KLog.e("radar distance ,area = " + area + " value = " + value);
            if (radarDevice != null) {
                int[] distance = BydApi29Helper.getAllRadarDistance(radarDevice);
                System.arraycopy(distance, 0, mRadarDistanceArr, 0, distance.length);

                //            updateRadarFloatingTriggerType();
                if (radarFloatingTriggerType == TYPE_DISTANCE_150_CM) {
                    if (shouldShowRadarDistance()) {
                        if (!radarDistanceShowing) {
                            radarDistanceHelper.showRadarFloating();
                            radarDistanceShowing = true;
                        }
                        radarDistanceHelper.updateRadarFloating(distance);
                    } else {
                        radarDistanceHelper.hideRadarFloating();
                        radarDistanceShowing = false;
                    }
                }
            }
        }
    };
}