package com.huawei.carstatushelper.floating;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.hardware.bydauto.speed.AbsBYDAutoSpeedListener;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.hardware.bydauto.tyre.AbsBYDAutoTyreListener;
import android.hardware.bydauto.tyre.BYDAutoTyreDevice;
import android.support.annotation.Keep;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.huawei.carstatushelper.byd_helper.BYDAutoStatisticDeviceHelper;
import com.huawei.carstatushelper.databinding.LayoutFloatingViewpagerItem1Binding;
import com.huawei.carstatushelper.util.BydApi29Helper;
import com.huawei.carstatushelper.view.DialogEngineSpeedView;

import java.text.DecimalFormat;

public class CarStatusPage implements IPage {

    private final Context context;
    private View rootView;
    private DialogEngineSpeedView engineSpeedEsv;
    private TextView engineSpeedTv;
    private TextView tyrePreLeftFrontTv;
    private TextView tyrePreRightFrontTv;
    private TextView tyrePreLeftRearTv;
    private TextView tyrePreRightRearTv;
    private BYDAutoSpeedDevice speedDevice;
    private BYDAutoEngineDevice engineDevice;
    private BYDAutoEnergyDevice energyDevice;
    private BYDAutoTyreDevice tyreDevice;
    private BYDAutoStatisticDevice statisticDevice;
    private TextView instantFuelConTv;

    public CarStatusPage(Context context) {
        this.context = context;
    }

    @Override
    public void init() {
        LayoutFloatingViewpagerItem1Binding binding = LayoutFloatingViewpagerItem1Binding.inflate(LayoutInflater.from(context));
        rootView = binding.getRoot();

        engineSpeedEsv = binding.engineSpeedEsv;
        engineSpeedTv = binding.engineSpeedTv;
        tyrePreLeftFrontTv = binding.tyrePreLeftFrontTv;
        tyrePreRightFrontTv = binding.tyrePreRightFrontTv;
        tyrePreLeftRearTv = binding.tyrePreLeftRearTv;
        tyrePreRightRearTv = binding.tyrePreRightRearTv;
        instantFuelConTv = binding.instantFuelConTv;

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.BYDAUTO_SPEED_GET) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        speedDevice = BYDAutoSpeedDevice.getInstance(context);
        engineDevice = BYDAutoEngineDevice.getInstance(context);
        energyDevice = BYDAutoEnergyDevice.getInstance(context);
        tyreDevice = BYDAutoTyreDevice.getInstance(context);
        statisticDevice = BYDAutoStatisticDevice.getInstance(context);

        updateEngineSpeedData();

        int pressure_lf = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT);
        int pressure_rf = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT);
        int pressure_lr = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR);
        int pressure_rr = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR);
        tyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT, pressure_lf);
        tyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT, pressure_rf);
        tyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR, pressure_lr);
        tyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR, pressure_rr);

        updateInstantFuelCon();
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void onCreate() {
        if (speedDevice == null) {
            return;
        }
        speedDevice.registerListener(speedListener);
        energyDevice.registerListener(energyListener);
        tyreDevice.registerListener(tyreListener);
        statisticDevice.registerListener(statisticListener);
    }

    @Override
    public void onDestroy() {
        if (speedDevice == null) {
            return;
        }
        speedDevice.unregisterListener(speedListener);
        energyDevice.unregisterListener(energyListener);
        tyreDevice.unregisterListener(tyreListener);
        statisticDevice.unregisterListener(statisticListener);
    }

    private final AbsBYDAutoSpeedListener speedListener = new AbsBYDAutoSpeedListener() {
        @Override
        public void onSpeedChanged(double value) {
            super.onSpeedChanged(value);
            updateEngineSpeedData();
        }

        @Override
        public void onAccelerateDeepnessChanged(int value) {
            super.onAccelerateDeepnessChanged(value);
            updateEngineSpeedData();
        }
    };

    private final AbsBYDAutoEnergyListener energyListener = new AbsBYDAutoEnergyListener() {
        @Override
        public void onEnergyModeChanged(int mode) {
            super.onEnergyModeChanged(mode);
            updateEngineSpeedData();
        }

        @Override
        public void onPowerGenerationValueChanged(int value) {
            super.onPowerGenerationValueChanged(value);
            updateEngineSpeedData();
        }

    };

    private final AbsBYDAutoTyreListener tyreListener = new AbsBYDAutoTyreListener() {
        @Override
        public void onTyrePressureValueChanged(int area, int value) {
            super.onTyrePressureValueChanged(area, value);
            if (tyrePreLeftFrontTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT) {
                tyrePreLeftFrontTv.setText("左前：" + value);
            }
            if (tyrePreRightFrontTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT) {
                tyrePreRightFrontTv.setText("右前：" + value);
            }
            if (tyrePreLeftRearTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR) {
                tyrePreLeftRearTv.setText("左后：" + value);
            }
            if (tyrePreRightRearTv != null && area == BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR) {
                tyrePreRightRearTv.setText("右后：" + value);
            }
        }
    };

    private void updateEngineSpeedData() {
        if (engineDevice == null) {
            return;
        }
        int engine_speed = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_SPEED);
        int engine_speed_gb = BydApi29Helper.get(engineDevice, BYDAutoFeatureIds.ENGINE_SPEED_GB);
        int engine_speed_result;
        if (engine_speed_gb > 0 && engine_speed_gb <= 8000) {
            engine_speed_result = engine_speed_gb;
        } else if (engine_speed > 0 && engine_speed <= 8000) {
            engine_speed_result = engine_speed;
        } else {
            engine_speed_result = 0;
        }
        if (engineSpeedTv != null) {
            engineSpeedTv.setText(String.valueOf(engine_speed_result));
        }
        if (engineSpeedEsv != null) {
            engineSpeedEsv.setVelocity(engine_speed_result);
        }
    }

    private final AbsBYDAutoStatisticListener statisticListener = new AbsBYDAutoStatisticListener() {
        /**
         * 瞬时油耗
         *
         * @param value
         */
        @Keep
        public void onInstantFuelConChanged(double value) {
            updateInstantFuelCon();
        }
    };

    DecimalFormat format = new DecimalFormat("##0.0##");

    /**
     * 瞬时油耗
     */
    private void updateInstantFuelCon() {
        if (instantFuelConTv != null) {
            instantFuelConTv.setText(format.format(BYDAutoStatisticDeviceHelper.getInstance(statisticDevice).getInstantFuelConValue()));
        }
    }
}
