package com.huawei.carstatushelper.receiver;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;

import com.huawei.byd_sdk_29.Api29Helper;
import com.huawei.carstatushelper.service.RadarDataProviderService;
import com.huawei.carstatushelper.service.RadarFloatingService;
import com.huawei.carstatushelper.util.Android29Helper;
import com.socks.library.KLog;

import org.json.JSONException;
import org.json.JSONObject;

public class BootCompleteService extends Service {
    public static final String KEY_INIT_DRIVER_DATA = "init_driver_data";

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
        Android29Helper.startForeground(this, "BootCompleteService", "车况助手");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        double latest_fuel_price = Double.parseDouble(preferences.getString("latest_fuel_price", "8.5"));
        double latest_electric_price = Double.parseDouble(preferences.getString("latest_electric_price", "1.7"));

//        if (!PermissionUtils.needRequestPermission(this, new String[]{Manifest.permission.BYDAUTO_PANORAMA_COMMON})) {
        boolean radar_distance_enable = preferences.getBoolean("radar_distance_enable", false);
        if (radar_distance_enable) {
            startService(new Intent(this, RadarDataProviderService.class));
            startService(new Intent(this, RadarFloatingService.class));
        }
//        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BYDAUTO_STATISTIC_GET) == PackageManager.PERMISSION_GRANTED) {
            BYDAutoStatisticDevice statisticDevice = BYDAutoStatisticDevice.getInstance(this);
            int totalMileageValue = statisticDevice.getTotalMileageValue();//总里程
            int evMileageValue = statisticDevice.getEVMileageValue();//总ev里程
            int hevMileageValue = Api29Helper.getHEVMileageValue(statisticDevice);//总hev里程
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
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int ret = super.onStartCommand(intent, flags, startId);
        KLog.e();
        return ret;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KLog.e();
    }
}