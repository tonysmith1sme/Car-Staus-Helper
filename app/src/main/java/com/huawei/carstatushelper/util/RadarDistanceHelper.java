package com.huawei.carstatushelper.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huawei.carstatushelper.R;
import com.socks.library.KLog;
import com.ziwenl.floatingwindowdemo.utils.FloatingWindowHelper;

import java.util.ArrayList;
import java.util.List;

public class RadarDistanceHelper {
    private final Context context;
    private final FloatingWindowHelper helper;
    private final SharedPreferences preferences;
    private View rootView;
    private List<TextView> textViewList;

    public RadarDistanceHelper(Context context) {
        this.context = context;
        helper = new FloatingWindowHelper(context);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void updateRadarFloating(int[] data) {
        if (!isRadarDistanceEnable()) {
            return;
        }
        if (textViewList != null) {
            for (int i = 0; i < Math.min(data.length, textViewList.size()); i++) {
                textViewList.get(i).setText(String.valueOf(data[i]));
            }
        }
    }

    private boolean isRadarDistanceEnable() {
        return preferences.getBoolean("radar_distance_enable", true);
    }

    public void showRadarFloating() {
        if (!isRadarDistanceEnable()) {
            return;
        }
        KLog.e();
        if (!Settings.canDrawOverlays(context)) {
//            KLog.e("无浮窗权限");
            return;
        }
        if (rootView != null && helper.contains(rootView)) {
//            KLog.e("浮窗已显示");
            return;
        }
        rootView = LayoutInflater.from(context).inflate(R.layout.layout_radar_floating, null, false);
        View frame = rootView.findViewById(R.id.layout1);
        rootView.findViewById(R.id.close_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideRadarFloating();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
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
                GradientDrawable drawable = new GradientDrawable();
                drawable.setStroke(DpUtil.dip2px(context, 2), bg_color_int);
                drawable.setCornerRadius(DpUtil.dip2px(context, 8));
                frame.setBackground(drawable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //横屏 x = 280,y = 110,width = 311dp,height = 500dp
        //竖屏 x = 70,y = 780,width = 270dp,height = 450dp
        int orientation = context.getResources().getConfiguration().orientation;

        //自定义浮窗宽高
        if (width > 0 && height > 0) {
            int widthPx = DpUtil.dip2px(context, width);
            int heightPx = DpUtil.dip2px(context, height);
            frame.setLayoutParams(new LinearLayout.LayoutParams(widthPx, heightPx));
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int widthPx = DpUtil.dip2px(context, 311);
            int heightPx = DpUtil.dip2px(context, 500);
            frame.setLayoutParams(new LinearLayout.LayoutParams(widthPx, heightPx));
        } else {
            int widthPx = DpUtil.dip2px(context, 270);
            int heightPx = DpUtil.dip2px(context, 450);
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

    public void hideRadarFloating() {
        if (!isRadarDistanceEnable()) {
            return;
        }
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
