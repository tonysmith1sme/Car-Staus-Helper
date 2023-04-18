package com.huawei.carstatushelper.receiver;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.panorama.AbsBYDAutoPanoramaListener;
import android.hardware.bydauto.panorama.BYDAutoPanoramaDevice;
import android.hardware.bydauto.radar.AbsBYDAutoRadarListener;
import android.hardware.bydauto.radar.BYDAutoRadarDevice;
import android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;

import androidx.annotation.Keep;
import androidx.core.content.ContextCompat;

import com.huawei.carstatushelper.MainActivity;
import com.huawei.carstatushelper.byd_helper.BYDAutoStatisticDeviceHelper;
import com.huawei.carstatushelper.floating.RadarDistanceHelper;
import com.huawei.carstatushelper.service.FloatingService;
import com.huawei.carstatushelper.util.AutoBootHelper;
import com.huawei.carstatushelper.util.BydApi29Helper;
import com.huawei.carstatushelper.util.BydManifest;
import com.huawei.carstatushelper.util.NotificationHelper;
import com.huawei.carstatushelper.util.SmartRemindUtil;
import com.huawei.carstatushelper.util.Utils;
import com.socks.library.KLog;

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
    private TextToSpeech tts;

    private BYDAutoBodyworkDevice bodyworkDevice;
    private BYDAutoChargingDevice chargingDevice;
    private BYDAutoEnergyDevice energyDevice;
    private BYDAutoStatisticDevice statisticDevice;

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
        NotificationHelper.showNotification(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        updateRadarFloatingTriggerType();

        saveCurrentTripData(this);

        radarDistanceHelper = new RadarDistanceHelper(this);

        if (preferences.getBoolean("radar_floating_boot_auto_show_enable", false)) {
            startService(new Intent(this, FloatingService.class));
        }
    }

    public static void saveCurrentTripData(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (ContextCompat.checkSelfPermission(context, BydManifest.permission.BYDAUTO_STATISTIC_GET) == PackageManager.PERMISSION_GRANTED) {
            BYDAutoStatisticDevice statisticDevice = BYDAutoStatisticDevice.getInstance(context);
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
                String json = object.toString();
                KLog.e("初始化行程数据：" + json);
                if (preferences.getBoolean(MainActivity.KEY_PAUSE_CURRENT_MILEAGE_DATA, false)) {
                    preferences.edit().putBoolean(MainActivity.KEY_PAUSE_CURRENT_MILEAGE_DATA, false).apply();
                    return;
                }
                preferences.edit().putString(KEY_INIT_DRIVER_DATA, json).apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int ret = super.onStartCommand(intent, flags, startId);
        KLog.e();
        if (ContextCompat.checkSelfPermission(this, BydManifest.permission.BYDAUTO_PANORAMA_COMMON) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, BydManifest.permission.BYDAUTO_PANORAMA_GET) == PackageManager.PERMISSION_GRANTED) {
            if (panoramaDevice == null) {
                panoramaDevice = BYDAutoPanoramaDevice.getInstance(this);
                KLog.e("panoramaDevice 初始化成功");
            } else {
                KLog.e("panoramaDevice 已启动");
            }
        } else {
            KLog.e("BYDAUTO_PANORAMA_COMMON 未授权");
        }
        if (ContextCompat.checkSelfPermission(this, BydManifest.permission.BYDAUTO_PANORAMA_GET) == PackageManager.PERMISSION_GRANTED) {
            if (panoramaDevice != null) {
                panoramaDevice.registerListener(panoramaListener);
            } else {
                KLog.e("panoramaDevice == null");
            }
        } else {
            KLog.e("BYDAUTO_PANORAMA_GET 未授权");
        }
        if (ContextCompat.checkSelfPermission(this, BydManifest.permission.BYDAUTO_GEARBOX_GET) == PackageManager.PERMISSION_GRANTED) {
            if (gearboxDevice == null) {
                gearboxDevice = BYDAutoGearboxDevice.getInstance(this);
                gearboxDevice.registerListener(gearboxListener);
            }
        }
        if (ContextCompat.checkSelfPermission(this, BydManifest.permission.BYDAUTO_RADAR_GET) == PackageManager.PERMISSION_GRANTED) {
            if (radarDevice == null) {
                radarDevice = BYDAutoRadarDevice.getInstance(this);
                radarDevice.registerListener(radarListener);
            }
        }
        if (ContextCompat.checkSelfPermission(this, BydManifest.permission.BYDAUTO_BODYWORK_COMMON) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, BydManifest.permission.BYDAUTO_BODYWORK_GET) == PackageManager.PERMISSION_GRANTED) {
            if (bodyworkDevice == null) {
                bodyworkDevice = BYDAutoBodyworkDevice.getInstance(this);
            }
        }
        if (ContextCompat.checkSelfPermission(this, BydManifest.permission.BYDAUTO_CHARGING_GET) == PackageManager.PERMISSION_GRANTED) {
            if (chargingDevice == null) {
                chargingDevice = BYDAutoChargingDevice.getInstance(this);
            }
        }
        if (ContextCompat.checkSelfPermission(this, BydManifest.permission.BYDAUTO_ENERGY_GET) == PackageManager.PERMISSION_GRANTED) {
            if (energyDevice == null) {
                energyDevice = BYDAutoEnergyDevice.getInstance(this);
                energyDevice.registerListener(energyListener);
            }
        }
        if (ContextCompat.checkSelfPermission(this, BydManifest.permission.BYDAUTO_STATISTIC_GET) == PackageManager.PERMISSION_GRANTED) {
            if (statisticDevice == null) {
                statisticDevice = BYDAutoStatisticDevice.getInstance(this);
                statisticDevice.registerListener(energyListener);
            }
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String ttsEnginePkg = preferences.getString("default_tts_engine_pkg", "");
        if (SmartRemindUtil.isEnable(this) && ttsEnginePkg.length() != 0) {
            tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        String welcomeContent = SmartRemindUtil.getWelcomeContent(BootCompleteService.this);
                        speakTTS(welcomeContent);
                    } else {
                        tts = null;
                    }
                }
            }, ttsEnginePkg);
        } else {
            if (tts != null) {
                tts.stop();
                tts.shutdown();
                tts = null;
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
        if (energyDevice != null) {
            energyDevice.unregisterListener(energyListener);
        }
        if (statisticDevice != null) {
            statisticDevice.unregisterListener(statisticListener);
        }
        if (tts != null) {
            tts.stop();
            tts.shutdown();
            tts = null;
        }
    }

    private void speakTTS(String ttsText) {
        if (tts != null && ttsText.length() != 0) {
            if (tts.isSpeaking()) {
                tts.stop();
            }
            tts.speak(ttsText, TextToSpeech.QUEUE_FLUSH, null, null);
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
            boolean haveWarningMsg = false;
            if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_P) {
                boolean enable = preferences.getBoolean("steering_wheel_not_reset_remind_enable", true);
                if (enable) {
                    //获取方向盘角度
                    double steeringWheelValue = bodyworkDevice.getSteeringWheelValue(BYDAutoBodyworkDevice.BODYWORK_CMD_STEERING_WHEEL_ANGEL);
                    if (steeringWheelValue > 45 || steeringWheelValue < -45) {
                        haveWarningMsg = true;
                        speakTTS("方向盘未回正");
                    }
                }
                //记录油量、电量，用于加油、充电统计
                //当油量电量有变大时记录公里数
                String autoVIN = bodyworkDevice.getAutoVIN();
                if (autoVIN.endsWith("131100")) {
                    int fuelPercentageValue = statisticDevice.getFuelPercentageValue();//当前油量百分比
                    double elecPercentageValue = statisticDevice.getElecPercentageValue();
                    int elecPercentageIntValue;//当前电量百分比
                    if (elecPercentageValue <= 1) {
                        elecPercentageIntValue = (int) (elecPercentageValue * 100);
                    } else {
                        elecPercentageIntValue = (int) elecPercentageValue;
                    }

                    int last_fuel_percentage = preferences.getInt("last_fuel_percentage", -1);//上次油量百分比
                    int last_elec_percentage = preferences.getInt("last_elec_percentage", -1);//上次电量百分比
                    if (last_fuel_percentage == -1 || last_elec_percentage == -1) {
                        preferences.edit()
                                .putInt("last_fuel_percentage", fuelPercentageValue)
                                .putInt("last_elec_percentage", elecPercentageIntValue)
                                .apply();
                        return;
                    }
                    //如果当前油量较上次油量增加了10%以上，则提醒是否加油了
                    if (fuelPercentageValue - last_fuel_percentage > 10 || elecPercentageIntValue - last_elec_percentage > 10) {
                        try {
                            new AlertDialog.Builder(BootCompleteService.this)
                                    .setTitle("油量、电量增加提醒")
                                    .setMessage("本次加油百分比[" + (fuelPercentageValue - last_fuel_percentage) + "],充电百分比[" + (elecPercentageIntValue - last_elec_percentage) + "]")
                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    }).show();
                        } catch (Exception e) {
                            KLog.e(e);
                        }
                    } else {
                        preferences.edit()
                                .putInt("last_fuel_percentage", fuelPercentageValue)
                                .putInt("last_elec_percentage", elecPercentageIntValue)
                                .apply();
                    }
                }
            }
            if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_D) {
//                boolean enable = preferences.getBoolean("driving_charging_cap_not_close_remind_enable", true);
//                if (enable) {
//                    //获取充电口盖打开状态
//                    int state1 = chargingDevice.getChargingCapState(BYDAutoChargingDevice.CHARGING_CAP_AC);
//                    int state2 = chargingDevice.getChargingCapState(BYDAutoChargingDevice.CHARGING_CAP_DC);
//                    if (state1 == BYDAutoChargingDevice.CHARGING_CAP_STATE_ON || state2 == BYDAutoChargingDevice.CHARGING_CAP_STATE_ON) {
//                        haveWarningMsg = true;
//                        speakTTS("充电盖未关闭");
//                    }
//                }
            }
            if (SmartRemindUtil.isEnable(BootCompleteService.this) && !haveWarningMsg) {
                String gearboxLevelName = SmartRemindUtil.getGearboxLevelName(BootCompleteService.this, level);
                speakTTS(gearboxLevelName);
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

    private final int[] mRadarDistanceArr = new int[]{150, 150, 150, 150, 150, 150, 150, 150, 150};

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
        long lastSpeakTime;

        /**
         * 加 @Keep 注解防止混淆时被优化掉
         *
         * @param area
         * @param value
         */
        @Keep
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
                        if (SmartRemindUtil.isEnable(BootCompleteService.this) && SmartRemindUtil.isRadarDistanceTTS(BootCompleteService.this)) {
                            int min = 150;
                            for (int i : distance) {
                                min = Math.min(i, min);
                            }
                            if (min != 150) {
                                if (System.currentTimeMillis() - lastSpeakTime > 500) {
                                    lastSpeakTime = System.currentTimeMillis();
                                    speakTTS(min + "");
                                }
                            }
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

    private final AbsBYDAutoEnergyListener energyListener = new AbsBYDAutoEnergyListener() {
        @Override
        public void onEnergyModeChanged(int mode) {
            super.onEnergyModeChanged(mode);
            boolean ret = preferences.getBoolean("auto_show_engine_speed_floating", false);
            if (ret) {
                Intent intent = new Intent(BootCompleteService.this, FloatingService.class);
                if (mode == BYDAutoEnergyDevice.ENERGY_MODE_EV || mode == BYDAutoEnergyDevice.ENERGY_MODE_FORCE_EV) {
                    stopService(intent);
                } else {
                    if (Build.VERSION.SDK_INT >= 26 && Utils.isBackground(BootCompleteService.this)) {
                        AutoBootHelper.startForegroundService(BootCompleteService.this, intent);
                    } else {
                        startService(intent);
                    }
                }
            }
        }
    };

    private final AbsBYDAutoStatisticListener statisticListener = new AbsBYDAutoStatisticListener() {
        @Override
        public void onFuelPercentageChanged(int value) {
            super.onFuelPercentageChanged(value);
        }

        @Override
        public void onElecPercentageChanged(double value) {
            super.onElecPercentageChanged(value);
        }
    };
}