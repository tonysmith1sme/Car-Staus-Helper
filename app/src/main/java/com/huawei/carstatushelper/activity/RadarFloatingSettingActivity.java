package com.huawei.carstatushelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.receiver.BootCompleteService;

import java.util.Random;

public class RadarFloatingSettingActivity extends BackEnableBaseActivity implements View.OnClickListener {
    @Override
    public CharSequence setPageTitle() {
        return "雷达测距";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_floating_setting);

        findViewById(R.id.init_service_btn).setOnClickListener(this);
//        findViewById(R.id.show_radar_floating_btn).setOnClickListener(this);
//        findViewById(R.id.update_radar_data_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.init_service_btn) {
//            startService(new Intent(this, RadarDataProviderService.class));
            startService(new Intent(this, BootCompleteService.class));
//            startService(new Intent(this, RadarFloatingService.class));
            Toast.makeText(this, "service", Toast.LENGTH_SHORT).show();

//        } else if (viewId == R.id.show_radar_floating_btn) {
//            sendBroadcast(new Intent(BootCompleteService.ACTION_SHOW_RADAR_FLOATING));
//            Toast.makeText(this, "未实现", Toast.LENGTH_SHORT).show();

//        } else if (viewId == R.id.update_radar_data_btn) {
//            Intent intent = new Intent(BootCompleteService.ACTION_UPDATE_RADAR_DATA);
//            int[] data = new int[9];
//            Random random = new Random();
//            for (int i = 0; i < data.length; i++) {
//                data[i] = random.nextInt(150);
//            }
//            intent.putExtra("data", data);
//            sendBroadcast(intent);
//            Toast.makeText(this, "未实现", Toast.LENGTH_SHORT).show();
        }
    }
}