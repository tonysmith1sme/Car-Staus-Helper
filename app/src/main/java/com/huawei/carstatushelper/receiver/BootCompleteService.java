package com.huawei.carstatushelper.receiver;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.panorama.AbsBYDAutoPanoramaListener;
import android.hardware.bydauto.panorama.BYDAutoPanoramaDevice;
import android.hardware.bydauto.radar.AbsBYDAutoRadarListener;
import android.hardware.bydauto.radar.BYDAutoRadarDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.byd_helper.BYDAutoStatisticDeviceHelper;
import com.huawei.carstatushelper.util.AutoBootHelper;
import com.huawei.carstatushelper.util.BydApi29Helper;
import com.huawei.carstatushelper.util.DpUtil;
import com.socks.library.KLog;
import com.ziwenl.floatingwindowdemo.utils.FloatingWindowHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    //    public static final String ACTION_SHOW_RADAR_FLOATING = "android.intent.action.SHOW_RADAR_FLOATING";
//    public static final String ACTION_HIDE_RADAR_FLOATING = "android.intent.action.HIDE_RADAR_FLOATING";
//    public static final String ACTION_UPDATE_RADAR_DATA = "android.intent.action.UPDATE_RADAR_DATA";
    private FloatingWindowHelper helper;
    private View rootView;
    private List<TextView> textViewList;
//    private TextToSpeech textToSpeech;
//    private boolean speak_radar_distance_enable;

    private BYDAutoRadarDevice radarDevice;
    private BYDAutoPanoramaDevice panoramaDevice;
    private BYDAutoGearboxDevice gearboxDevice;

    public BootCompleteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        KLog.e();
//        Android29Helper.startForeground(this, "BootCompleteService", "车况助手");

        initNotification();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        radarFloatingTriggerType = Integer.parseInt(preferences.getString("radar_floating_trigger_type", "0"));

        double latest_fuel_price = Double.parseDouble(preferences.getString("latest_fuel_price", "8.5"));
        double latest_electric_price = Double.parseDouble(preferences.getString("latest_electric_price", "1.7"));

//        if (!PermissionUtils.needRequestPermission(this, new String[]{Manifest.permission.BYDAUTO_PANORAMA_COMMON})) {
//        boolean radar_distance_enable = preferences.getBoolean("radar_distance_enable", BuildConfig.DEBUG);
//        if (radar_distance_enable) {
//            startService(new Intent(this, RadarDataProviderService.class));
//            startService(new Intent(this, RadarFloatingService.class));
//        }
//        }
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

//        IntentFilter filter = new IntentFilter();
//        filter.addAction(ACTION_SHOW_RADAR_FLOATING);
//        filter.addAction(ACTION_HIDE_RADAR_FLOATING);
//        filter.addAction(ACTION_UPDATE_RADAR_DATA);
//        registerReceiver(receiver, filter);

        helper = new FloatingWindowHelper(this);
//        preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        speak_radar_distance_enable = preferences.getBoolean("speak_radar_distance_enable", false);
//        if (speak_radar_distance_enable) {
//            textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//                @Override
//                public void onInit(int i) {
//                    KLog.e("tts onInit result = " + i);
//                }
//            });
//        }
    }

    private void initNotification() {
        String channelId = getPackageName();
        //新建class
        NotificationChannel channel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //反射
//        manager.createNotificationChannel(channel);
        AutoBootHelper.createNotificationChannel(manager, channel);

        //反射
//        Notification.Builder builder = new Notification.Builder(this, channelId);
        Notification.Builder builder = AutoBootHelper.newNotificationBuilder(this, channelId);
        if (builder == null) {
            return;
        }
//        Intent intent = new Intent(this, BootNotificationDetailActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notification = builder
                .setSmallIcon(R.mipmap.ic_launcher)
//                .setTicker("车况助手ticker")
//                .setContentTitle("提示信息")
                .setContentTitle("服务已启动")
//                .setContentText("开机自启动服务包括雷达数据监测与雷达距离浮窗服务，请务关闭")
                .setSubText("运行中。。。")
//                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                do {
                    KLog.e("boot complete test ,count = " + count);
                    count++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (count <= 120);
                KLog.e("boot complete test finish");
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int ret = super.onStartCommand(intent, flags, startId);
        KLog.e();
//        boolean from_boot = intent.getBooleanExtra("from_boot", false);
//        if (from_boot) {
//        initNotification();
//        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BYDAUTO_PANORAMA_GET) == PackageManager.PERMISSION_GRANTED) {
            if (getBaseContext().checkSelfPermission(Manifest.permission.BYDAUTO_PANORAMA_COMMON) == PackageManager.PERMISSION_GRANTED) {
                if (panoramaDevice == null) {
                    panoramaDevice = BYDAutoPanoramaDevice.getInstance(this);
                    panoramaDevice.registerListener(panoramaListener);
                    KLog.e("panoramaDevice 初始化成功");
                } else {
                    KLog.e("panoramaDevice 已启动");
                }
            } else {
                KLog.e("BYDAUTO_PANORAMA_COMMON 未授权");
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
//        unregisterReceiver(receiver);
//        if (speak_radar_distance_enable) {
//            textToSpeech.stop();
//            textToSpeech.shutdown();
//        }
    }

    private final AbsBYDAutoGearboxListener gearboxListener = new AbsBYDAutoGearboxListener() {
        /**
         * 获取自动变速箱档位变化
         * @param level
         */
        @Override
        public void onGearboxAutoModeTypeChanged(int level) {
            super.onGearboxAutoModeTypeChanged(level);
            if (radarFloatingTriggerType == TYPE_GEARBOX_R) {
                if (level == BYDAutoGearboxDevice.GEARBOX_AUTO_MODE_R) {
                    showRadarFloating();
                } else {
                    hideRadarFloating();
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

    private final AbsBYDAutoPanoramaListener panoramaListener = new AbsBYDAutoPanoramaListener() {
        /**
         * 监听全景打开状态（）
         * @param mode
         */
        @Override
        public void onPanoWorkStateChanged(int mode) {
            super.onPanoWorkStateChanged(mode);
            KLog.e("onPanoWorkStateChanged：" + mode);
            if (radarFloatingTriggerType == TYPE_PANO_WORK_STATE) {
                if (mode == BYDAutoPanoramaDevice.PANORAMA_WORK_ON) {
//                sendBroadcast(new Intent(BootCompleteService.ACTION_SHOW_RADAR_FLOATING));
                    showRadarFloating();
                } else {
//                sendBroadcast(new Intent(BootCompleteService.ACTION_HIDE_RADAR_FLOATING));
                    hideRadarFloating();
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
            if (radarFloatingTriggerType == TYPE_PANO_OUTPUT_STATE) {
                //关闭显示
                if (mode == BYDAutoPanoramaDevice.PANORAMA_OUTPUT_OFF) {
                    hideRadarFloating();
                    //打开显示
                } else {
                    showRadarFloating();
                }
            }

        }
    };

    private final AbsBYDAutoRadarListener radarListener = new AbsBYDAutoRadarListener() {
        public void onRadarObstacleDistanceChanged(int area, int value) {
            KLog.e();
            if (radarDevice != null) {
                int[] distance = BydApi29Helper.getAllRadarDistance(radarDevice);
//                Intent intent = new Intent(BootCompleteService.ACTION_UPDATE_RADAR_DATA);
//                intent.putExtra("data", distance);
//                sendBroadcast(intent);
                updateRadarFloating(distance);
            }
        }
    };

//    private final BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            KLog.e();
//            String action = intent.getAction();
//            if (action == null) {
//                return;
//            }
//            if (ACTION_SHOW_RADAR_FLOATING.equals(action)) {
//                showRadarFloating();
//            } else if (ACTION_HIDE_RADAR_FLOATING.equals(action)) {
//                hideRadarFloating();
//            } else if (ACTION_UPDATE_RADAR_DATA.equals(action)) {
//                int[] data = intent.getIntArrayExtra("data");
//                updateRadarFloating(data);
//            }
//        }
//    };

    private void updateRadarFloating(int[] data) {
        if (textViewList != null) {
            for (int i = 0; i < Math.min(data.length, textViewList.size()); i++) {
                textViewList.get(i).setText(data[i] + "cm");
            }
        }
//        if (speak_radar_distance_enable) {
//            int min = Integer.MAX_VALUE;
//            for (int item : data) {
//                min = Math.min(min, item);
//            }
//            textToSpeech.speak(String.valueOf(min), TextToSpeech.QUEUE_FLUSH, null, null);
//        }
    }

    private void showRadarFloating() {
        KLog.e();
        if (!Settings.canDrawOverlays(this)) {
//            KLog.e("无浮窗权限");
            return;
        }
        if (rootView != null && helper.contains(rootView)) {
//            KLog.e("浮窗已显示");
            return;
        }
        rootView = LayoutInflater.from(this).inflate(R.layout.layout_radar_floating, null, false);
        View frame = rootView.findViewById(R.id.layout1);
        rootView.findViewById(R.id.close_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideRadarFloating();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int x = Integer.parseInt(preferences.getString("radar_floating_x", "0"));
        int y = Integer.parseInt(preferences.getString("radar_floating_y", "0"));
        int width = Integer.parseInt(preferences.getString("radar_floating_width", "0"));//单位dp
        int height = Integer.parseInt(preferences.getString("radar_floating_height", "0"));//单位dp
        String bg_color = preferences.getString("radar_floating_bg_color", "");

        float text_size = Float.parseFloat(preferences.getString("radar_floating_text_size", "0"));
        String text_color = preferences.getString("radar_floating_text_color", "");
        String text_bg_color = preferences.getString("radar_floating_text_bg_color", "");
        //设置雷达浮窗背景框颜色
        try {
            if (bg_color.length() != 0) {
                int bg_color_int = Color.parseColor(bg_color);
//                frame.setBackgroundColor(bg_color_int);
                GradientDrawable drawable = new GradientDrawable();
                drawable.setStroke(DpUtil.dip2px(this, 2), bg_color_int);
                drawable.setCornerRadius(DpUtil.dip2px(this, 8));
                frame.setBackground(drawable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //横屏 x = 280,y = 110,width = 311dp,height = 500dp
        //竖屏 x = 70,y = 780,width = 270dp,height = 450dp
        int orientation = getResources().getConfiguration().orientation;

        //自定义浮窗宽高
        if (width > 0 && height > 0) {
            int widthPx = DpUtil.dip2px(this, width);
            int heightPx = DpUtil.dip2px(this, height);
            frame.setLayoutParams(new LinearLayout.LayoutParams(widthPx, heightPx));
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int widthPx = DpUtil.dip2px(this, 311);
            int heightPx = DpUtil.dip2px(this, 500);
            frame.setLayoutParams(new LinearLayout.LayoutParams(widthPx, heightPx));
        } else {
            int widthPx = DpUtil.dip2px(this, 270);
            int heightPx = DpUtil.dip2px(this, 450);
            frame.setLayoutParams(new LinearLayout.LayoutParams(widthPx, heightPx));
        }

        //自定义浮窗偏移
        if (x > 0 && y > 0) {
            helper.addView(rootView, x, y, true);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            helper.addView(rootView, 280, 110, true);
        } else {
            helper.addView(rootView, 70, 780, true);
        }

        TextView tv0 = (TextView) rootView.findViewById(R.id.distance0_tv);
        TextView tv1 = (TextView) rootView.findViewById(R.id.distance1_tv);
        TextView tv2 = (TextView) rootView.findViewById(R.id.distance2_tv);
        TextView tv3 = (TextView) rootView.findViewById(R.id.distance3_tv);
        TextView tv4 = (TextView) rootView.findViewById(R.id.distance4_tv);
        TextView tv5 = (TextView) rootView.findViewById(R.id.distance5_tv);
        TextView tv6 = (TextView) rootView.findViewById(R.id.distance6_tv);
        TextView tv7 = (TextView) rootView.findViewById(R.id.distance7_tv);
        TextView tv8 = (TextView) rootView.findViewById(R.id.distance8_tv);
        textViewList = new ArrayList<>();
        textViewList.add(tv0);
        textViewList.add(tv1);
        textViewList.add(tv2);
        textViewList.add(tv3);
        textViewList.add(tv4);
        textViewList.add(tv5);
        textViewList.add(tv6);
        textViewList.add(tv7);
        textViewList.add(tv8);

        int text_color_int = 0;
        if (text_color.length() != 0) {
            try {
                text_color_int = Color.parseColor(text_color);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int text_bg_color_int = 0;
        try {
            text_bg_color_int = Color.parseColor(text_bg_color);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (TextView textView : textViewList) {
            if (text_size != 0) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, text_size);
            }
            if (text_color_int != 0) {
                textView.setTextColor(text_color_int);
            }
            if (text_bg_color_int != 0) {
                textView.setBackgroundColor(text_bg_color_int);
            }
        }
    }

    private void hideRadarFloating() {
        KLog.e();
        if (rootView == null) {
//            KLog.e("未显示浮窗");
            return;
        }
        helper.removeView(rootView);
        //解决浮窗exception: not attached to window manager
        //复现方法show一次，hide两次
        rootView = null;
    }
}