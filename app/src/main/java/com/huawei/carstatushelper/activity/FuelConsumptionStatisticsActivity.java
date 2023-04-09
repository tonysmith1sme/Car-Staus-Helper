package com.huawei.carstatushelper.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.huawei.carstatushelper.databinding.ActivityFuelConsumptionStatisticsBinding;

public class FuelConsumptionStatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFuelConsumptionStatisticsBinding binding = ActivityFuelConsumptionStatisticsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}