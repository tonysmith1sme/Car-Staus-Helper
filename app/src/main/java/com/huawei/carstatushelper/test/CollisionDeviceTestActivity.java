package com.huawei.carstatushelper.test;

import android.hardware.bydauto.collision.AbsBYDAutoCollisionListener;
import android.hardware.bydauto.collision.BYDAutoCollisionDevice;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.huawei.carstatushelper.BR;
import com.huawei.carstatushelper.databinding.ActivityCollisionDeviceTestBinding;
import com.huawei.carstatushelper.util.StringToHex;

public class CollisionDeviceTestActivity extends AppCompatActivity {

    private BYDAutoCollisionDevice collisionDevice;
    private com.huawei.carstatushelper.databinding.ActivityCollisionDeviceTestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCollisionDeviceTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.setVariable(BR.data, "00 00 00 00 00 00 00 00");

        collisionDevice = BYDAutoCollisionDevice.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        collisionDevice.registerListener(collisionListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        collisionDevice.unregisterListener(collisionListener);
    }

    private final AbsBYDAutoCollisionListener collisionListener = new AbsBYDAutoCollisionListener() {
        @Override
        public void onCollisionSignalStateChanged(byte[] collisionInfo) {
            super.onCollisionSignalStateChanged(collisionInfo);
            String hex = StringToHex.bytesToHex(collisionInfo);
            binding.setVariable(BR.data, hex);
        }
    };
}