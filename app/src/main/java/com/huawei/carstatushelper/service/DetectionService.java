package com.huawei.carstatushelper.service;

import android.accessibilityservice.AccessibilityService;
import android.content.ComponentName;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;

import com.socks.library.KLog;

public class DetectionService extends AccessibilityService {

    final static String TAG = "DetectionService";

    static String foregroundPackageName;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId); // 根据需要返回不同的语义值
    }

    /**
     * 重载辅助功能事件回调函数，对窗口状态变化事件进行处理
     *
     * @param event
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        foregroundPackageName = event.getPackageName().toString();
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            KLog.e("current pkg = " + foregroundPackageName);

            /*
             * 如果 与 DetectionService 相同进程，直接比较 foregroundPackageName 的值即可
             * 如果在不同进程，可以利用 Intent 或 bind service 进行通信
             */
//            foregroundPackageName = event.getPackageName().toString();
//            KLog.e("current pkg = " + foregroundPackageName);

            /*
             * 基于以下还可以做很多事情，比如判断当前界面是否是 Activity，是否系统应用等，
             * 与主题无关就不再展开。
             */
            ComponentName cName = new ComponentName(event.getPackageName().toString(),
                    event.getClassName().toString());
            if (getPackageName().equals(foregroundPackageName)) {

            } else {

            }

        }
    }

    @Override
    public void onInterrupt() {
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }
}