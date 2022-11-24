package com.huawei.carstatushelper.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.util.DpUtil;
import com.ziwenl.floatingwindowdemo.utils.FloatingWindowHelper;

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
public class RadarFloatingService extends Service {
    public static final String ACTION_SHOW_RADAR_FLOATING = "android.intent.action.SHOW_RADAR_FLOATING";
    public static final String ACTION_HIDE_RADAR_FLOATING = "android.intent.action.HIDE_RADAR_FLOATING";
    public static final String ACTION_UPDATE_RADAR_DATA = "android.intent.action.UPDATE_RADAR_DATA";
    private FloatingWindowHelper helper;
    private View rootView;
    private List<TextView> textViewList;
    private TextToSpeech textToSpeech;
    private SharedPreferences preferences;
    private boolean speak_radar_distance_enable;

    public RadarFloatingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_SHOW_RADAR_FLOATING);
        filter.addAction(ACTION_HIDE_RADAR_FLOATING);
        filter.addAction(ACTION_UPDATE_RADAR_DATA);
        registerReceiver(receiver, filter);

        helper = new FloatingWindowHelper(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        speak_radar_distance_enable = preferences.getBoolean("speak_radar_distance_enable", false);
        if (speak_radar_distance_enable) {
            textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {

                }
            });
        }
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }
            if (ACTION_SHOW_RADAR_FLOATING.equals(action)) {
                showRadarFloating();
            } else if (ACTION_HIDE_RADAR_FLOATING.equals(action)) {
                hideRadarFloating();
            } else if (ACTION_UPDATE_RADAR_DATA.equals(action)) {
                int[] data = intent.getIntArrayExtra("data");
                if (textViewList != null) {
                    for (int i = 0; i < Math.min(data.length, textViewList.size()); i++) {
                        textViewList.get(i).setText(data[i] + "cm");
                    }
                }
                if (speak_radar_distance_enable) {
                    int min = Integer.MAX_VALUE;
                    for (int item : data) {
                        min = Math.min(min, item);
                    }
                    textToSpeech.speak(String.valueOf(min), TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        }
    };

    public static void setRadarFloatingConfig(Context context, int radar_floating_width, int radar_floating_height, float radar_floating_text_size) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putInt("radar_floating_width", radar_floating_width).putInt("radar_floating_height", radar_floating_height).putFloat("radar_floating_text_size", radar_floating_text_size).apply();

    }

    private void showRadarFloating() {
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
        try {
            if (bg_color.length() != 0) {
                int bg_color_int = Color.parseColor(bg_color);
                frame.setBackgroundColor(bg_color_int);
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
        if (rootView == null) {
//            KLog.e("未显示浮窗");
            return;
        }
        helper.removeView(rootView);
        //解决浮窗exception: not attached to window manager
        //复现方法show一次，hide两次
        rootView = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        if (speak_radar_distance_enable) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}