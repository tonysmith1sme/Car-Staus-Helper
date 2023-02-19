package com.huawei.carstatushelper.floating;

import android.content.Context;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.engine.AbsBYDAutoEngineListener;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.hardware.bydauto.speed.AbsBYDAutoSpeedListener;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.hardware.bydauto.tyre.AbsBYDAutoTyreListener;
import android.hardware.bydauto.tyre.BYDAutoTyreDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.huawei.carstatushelper.databinding.LayoutFloatingViewpagerItem1Binding;
import com.huawei.carstatushelper.util.BydApi29Helper;
import com.huawei.carstatushelper.view.DialogEngineSpeedView;

public class CarStatusPage implements IPage{

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

    public CarStatusPage(Context context) {
        this.context = context;
    }

    @Override
    public void init() {
//        rootView = LayoutInflater.from(context).inflate(R.layout.layout_floating_viewpager_item1, null, false);
        LayoutFloatingViewpagerItem1Binding binding = LayoutFloatingViewpagerItem1Binding.inflate(LayoutInflater.from(context));
        rootView = binding.getRoot();

        engineSpeedEsv = binding.engineSpeedEsv;
        engineSpeedTv = binding.engineSpeedTv;
        tyrePreLeftFrontTv = binding.tyrePreLeftFrontTv;
        tyrePreRightFrontTv = binding.tyrePreRightFrontTv;
        tyrePreLeftRearTv = binding.tyrePreLeftRearTv;
        tyrePreRightRearTv = binding.tyrePreRightRearTv;

        speedDevice = BYDAutoSpeedDevice.getInstance(context);
        engineDevice = BYDAutoEngineDevice.getInstance(context);
        energyDevice = BYDAutoEnergyDevice.getInstance(context);
        tyreDevice = BYDAutoTyreDevice.getInstance(context);

        updateEngineSpeedData();

        int pressure_lf = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT);
        int pressure_rf = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT);
        int pressure_lr = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR);
        int pressure_rr = tyreDevice.getTyrePressureValue(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR);
        tyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_FRONT, pressure_lf);
        tyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_FRONT, pressure_rf);
        tyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_LEFT_REAR, pressure_lr);
        tyreListener.onTyrePressureValueChanged(BYDAutoTyreDevice.TYRE_COMMAND_AREA_RIGHT_REAR, pressure_rr);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void onCreate() {
        speedDevice.registerListener(speedListener);
        engineDevice.registerListener(engineListener);
        energyDevice.registerListener(energyListener);
        tyreDevice.registerListener(tyreListener);
    }

    @Override
    public void onDestroy() {
        speedDevice.unregisterListener(speedListener);
        engineDevice.unregisterListener(engineListener);
        energyDevice.unregisterListener(energyListener);
        tyreDevice.unregisterListener(tyreListener);
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
        }

        @Override
        public void onBrakeDeepnessChanged(int value) {
            super.onBrakeDeepnessChanged(value);
        }
    };

    private final AbsBYDAutoEngineListener engineListener = new AbsBYDAutoEngineListener() {
        @Override
        public void onEngineSpeedChanged(int value) {
            super.onEngineSpeedChanged(value);
        }

        @Override
        public void onEngineCoolantLevelChanged(int state) {
            super.onEngineCoolantLevelChanged(state);
        }

        @Override
        public void onOilLevelChanged(int value) {
            super.onOilLevelChanged(value);
        }
    };

    private final AbsBYDAutoEnergyListener energyListener = new AbsBYDAutoEnergyListener() {
        @Override
        public void onEnergyModeChanged(int mode) {
            super.onEnergyModeChanged(mode);
        }

        @Override
        public void onOperationModeChanged(int mode) {
            super.onOperationModeChanged(mode);
        }

        @Override
        public void onPowerGenerationStateChanged(int mode) {
            super.onPowerGenerationStateChanged(mode);
        }

        @Override
        public void onPowerGenerationValueChanged(int value) {
            super.onPowerGenerationValueChanged(value);
            updateEngineSpeedData();
        }

        @Override
        public void onRoadSurfaceChanged(int type) {
            super.onRoadSurfaceChanged(type);
        }
    };

    private final AbsBYDAutoTyreListener tyreListener = new AbsBYDAutoTyreListener() {
        @Override
        public void onTyreSystemStateChanged(int state) {
            super.onTyreSystemStateChanged(state);
        }

        @Override
        public void onTyreTemperatureStateChanged(int state) {
            super.onTyreTemperatureStateChanged(state);
        }

        @Override
        public void onTyreBatteryStateChanged(int state) {
            super.onTyreBatteryStateChanged(state);
        }

        @Override
        public void onTyreAirLeakStateChanged(int area, int state) {
            super.onTyreAirLeakStateChanged(area, state);
        }

        @Override
        public void onTyreSignalStateChanged(int area, int state) {
            super.onTyreSignalStateChanged(area, state);
        }

        @Override
        public void onTyrePressureStateChanged(int area, int state) {
            super.onTyrePressureStateChanged(area, state);
        }

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
        int engine_speed_result = 0;
        if (engine_speed_gb > 0 && engine_speed_gb <= 8000) {
            engine_speed_result = engine_speed_gb;
        } else if (engine_speed > 0 && engine_speed <= 8000) {
            engine_speed_result = engine_speed;
        }
        if (engineSpeedTv != null) {
            engineSpeedTv.setText(String.valueOf(engine_speed_result));
        }
        if (engineSpeedEsv != null) {
            engineSpeedEsv.setVelocity(engine_speed_result);
        }
    }
}
