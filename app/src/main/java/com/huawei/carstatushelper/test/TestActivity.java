package com.huawei.carstatushelper.test;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.databinding.ActivityTestBinding;
import com.huawei.carstatushelper.floating.RadarDistanceHelper;
import com.huawei.carstatushelper.view.EnginePowerView;
import com.huawei.carstatushelper.view.EngineSpeedView;
import com.huawei.carstatushelper.view.MotorSpeedView;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private EngineSpeedView engineSpeedEsv;
    private EditText engineSpeedEt;
    private MotorSpeedView motorSpeedMsv;
    private EnginePowerView enginePowerEpv;
    private RadarDistanceHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestBinding binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        engineSpeedEsv = binding.engineSpeedEsv;
        enginePowerEpv = binding.enginePowerEpv;
        motorSpeedMsv = binding.motorSpeedMsv;

        engineSpeedEt = binding.engineSpeedEt;
        binding.testBtn.setOnClickListener(this);
        binding.commitBtn.setOnClickListener(this);

        helper = new RadarDistanceHelper(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putString("radar_floating_bg_color","#ffff0000").apply();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.commit_btn) {
//            String engineSpeedStr = engineSpeedEt.getText().toString();
//            if (engineSpeedStr.isEmpty()) {
//                Toast.makeText(this, "input error", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            int engineSpeed = Integer.parseInt(engineSpeedStr);
//            engineSpeedEsv.setVelocity(engineSpeed);
//            enginePowerEpv.setVelocity(engineSpeed);
//            motorSpeedMsv.setVelocity(engineSpeed);

            helper.showRadarFloating();
        } else if (viewId == R.id.test_btn) {
            Toast.makeText(this, "测试", Toast.LENGTH_SHORT).show();
        }
    }
}