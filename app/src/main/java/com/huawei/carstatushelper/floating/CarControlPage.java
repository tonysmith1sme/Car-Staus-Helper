package com.huawei.carstatushelper.floating;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.byd_helper.BYDAutoAcDeviceHelper;
import com.huawei.carstatushelper.databinding.LayoutFloatingViewpagerItem2Binding;

public class CarControlPage implements IPage, View.OnClickListener {
    private final Context context;
    private View rootView;
    private BYDAutoAcDevice acDevice;
    private TextView currentTemperatureTv;
    private TextView currentWindLevelTv;
    private Button acControlModeBtn;
    private Button acOnOffBtn;
    private Button acCompressorBtn;
    private Button defrostModeBtn;
    private Button acCycleModeBtn;
    private Button ventilateModeBtn;

    public CarControlPage(Context context) {
        this.context = context;
    }

    @Override
    public void init() {
//        rootView = LayoutInflater.from(context).inflate(R.layout.layout_floating_viewpager_item2, null, false);
        LayoutFloatingViewpagerItem2Binding binding = LayoutFloatingViewpagerItem2Binding.inflate(LayoutInflater.from(context));
        rootView = binding.getRoot();

        currentTemperatureTv = binding.currentTemperatureTv;
        currentWindLevelTv = binding.currentWindLevelTv;

        acControlModeBtn = binding.acControlModeBtn;
        acOnOffBtn = binding.acOnOffBtn;
        acCompressorBtn = binding.acCompressorBtn;

        defrostModeBtn = binding.defrostModeBtn;
        acCycleModeBtn = binding.acCycleModeBtn;
        ventilateModeBtn = binding.ventilateModeBtn;

        acControlModeBtn.setOnClickListener(this);
        acOnOffBtn.setOnClickListener(this);
        acCompressorBtn.setOnClickListener(this);

        defrostModeBtn.setOnClickListener(this);
        acCycleModeBtn.setOnClickListener(this);
        ventilateModeBtn.setOnClickListener(this);

        binding.temperaturePlusBtn.setOnClickListener(this);
        binding.temperatureSubBtn.setOnClickListener(this);

        binding.windLevelPlusBtn.setOnClickListener(this);
        binding.windLevelSubBtn.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.BYDAUTO_AC_GET) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        acDevice = BYDAutoAcDevice.getInstance(context);

        //空调的手动、与自动控制方式
        acListener.onAcCtrlModeChanged(acDevice.getAcControlMode());
        //获取空调开启状态
        int acStartState = acDevice.getAcStartState();
        if (acStartState == BYDAutoAcDevice.AC_POWER_ON) {
            acListener.onAcStarted();
        } else if (acStartState == BYDAutoAcDevice.AC_POWER_OFF) {
            acListener.onAcStoped();
        }
        //A/C
        int acCompressorMode = acDevice.getAcCompressorMode();
        acListener.onAcCompressorModeChanged(acCompressorMode);

        //除霜
        int acDefrostState = acDevice.getAcDefrostState(BYDAutoAcDevice.AC_DEFROST_AREA_FRONT);
        acListener.onAcDefrostStateChanged(BYDAutoAcDevice.AC_DEFROST_AREA_FRONT, acDefrostState);
        //获取空调内外循环
        acListener.onAcCycleModeChanged(acDevice.getAcCycleMode());

        //通风
        int acVentilationState = acDevice.getAcVentilationState();
        acListener.onAcVentilationStateChanged(acVentilationState);

        //主驾温度
        final int temprature = acDevice.getTemprature(BYDAutoAcDevice.AC_TEMPERATURE_MAIN);
        acListener.onTemperatureChanged(BYDAutoAcDevice.AC_TEMPERATURE_MAIN, temprature);

        //风量
        int windLevel = acDevice.getAcWindLevel();
        acListener.onAcWindLevelChanged(windLevel);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void onCreate() {
        if (acDevice == null) {
            return;
        }
        acDevice.registerListener(acListener);
    }

    @Override
    public void onDestroy() {
        if (acDevice == null) {
            return;
        }
        acDevice.unregisterListener(acListener);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (acDevice == null) {
            return;
        }
        if (viewId == R.id.ac_control_mode_btn) {
            int acControlMode = acDevice.getAcControlMode();
            if (acControlMode == BYDAutoAcDevice.AC_CTRLMODE_AUTO) {
                acDevice.setAcControlMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_CTRLMODE_MANUAL);
            } else if (acControlMode == BYDAutoAcDevice.AC_CTRLMODE_MANUAL) {
                acDevice.setAcControlMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_CTRLMODE_AUTO);
            }
        } else if (viewId == R.id.ac_on_off_btn) {
            int acStartState = acDevice.getAcStartState();
            if (acStartState == BYDAutoAcDevice.AC_POWER_ON) {
                acDevice.stop(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY);
            } else if (acStartState == BYDAutoAcDevice.AC_POWER_OFF) {
                acDevice.start(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY);
            }
        } else if (viewId == R.id.ac_compressor_btn) {
            int acCompressorMode = acDevice.getAcCompressorMode();
            if (acCompressorMode == BYDAutoAcDevice.AC_COMPRESSOR_OFF) {
                BYDAutoAcDeviceHelper.getInstance(acDevice).setAcCompressorMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_COMPRESSOR_ON);
            } else if (acCompressorMode == BYDAutoAcDevice.AC_COMPRESSOR_ON) {
                BYDAutoAcDeviceHelper.getInstance(acDevice).setAcCompressorMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_COMPRESSOR_OFF);
            }
        } else if (viewId == R.id.ac_cycle_mode_btn) {
            int acCycleMode = acDevice.getAcCycleMode();
            if (acCycleMode == BYDAutoAcDevice.AC_CYCLEMODE_OUTLOOP) {
                acDevice.setAcCycleMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_CYCLEMODE_INLOOP);
            } else if (acCycleMode == BYDAutoAcDevice.AC_CYCLEMODE_INLOOP) {
                acDevice.setAcCycleMode(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_CYCLEMODE_OUTLOOP);
            }
        } else if (viewId == R.id.ventilate_mode_btn) {
            int state = acDevice.getAcVentilationState();
            if (state == BYDAutoAcDevice.AC_VENTILATION_STATE_OFF) {
                acDevice.setAcVentilationState(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_VENTILATION_STATE_ON);
            } else if (state == BYDAutoAcDevice.AC_VENTILATION_STATE_ON) {
                acDevice.setAcVentilationState(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, BYDAutoAcDevice.AC_VENTILATION_STATE_OFF);
            }
        }
    }

    private final AbsBYDAutoAcListener acListener = new AbsBYDAutoAcListener() {
        @Override
        public void onAcStarted() {
            super.onAcStarted();
            acOnOffBtn.setText("开启");
            acOnOffBtn.setTextColor(ContextCompat.getColor(context, R.color.color_button_state_on));

        }

        @Override
        public void onAcStoped() {
            super.onAcStoped();
            acOnOffBtn.setText("关闭");
            acOnOffBtn.setTextColor(ContextCompat.getColor(context, R.color.color_button_state_off));
        }

        @Override
        public void onAcRearStarted() {
            super.onAcRearStarted();
        }

        @Override
        public void onAcRearStoped() {
            super.onAcRearStoped();
        }

        @Override
        public void onAcCtrlModeChanged(int mode) {
            super.onAcCtrlModeChanged(mode);
            if (mode == BYDAutoAcDevice.AC_CTRLMODE_AUTO) {
                acControlModeBtn.setText("自动");
                acControlModeBtn.setTextColor(ContextCompat.getColor(context, R.color.color_button_state_on));
            } else if (mode == BYDAutoAcDevice.AC_CTRLMODE_MANUAL) {
                acControlModeBtn.setText("手动");
                acControlModeBtn.setTextColor(ContextCompat.getColor(context, R.color.color_button_state_off));
            }
        }

        @Override
        public void onAcCycleModeChanged(int mode) {
            super.onAcCycleModeChanged(mode);
            if (mode == BYDAutoAcDevice.AC_CYCLEMODE_OUTLOOP) {
                acCycleModeBtn.setTextColor(ContextCompat.getColor(context, R.color.color_button_state_on));
            } else if (mode == BYDAutoAcDevice.AC_CYCLEMODE_INLOOP) {
                acCycleModeBtn.setTextColor(ContextCompat.getColor(context, R.color.color_button_state_off));

            }
        }

        @Override
        public void onAcVentilationStateChanged(int state) {
            super.onAcVentilationStateChanged(state);
            if (state == BYDAutoAcDevice.AC_VENTILATION_STATE_OFF) {
                ventilateModeBtn.setTextColor(ContextCompat.getColor(context, R.color.color_button_state_off));
            } else if (state == BYDAutoAcDevice.AC_VENTILATION_STATE_ON) {
                ventilateModeBtn.setTextColor(ContextCompat.getColor(context, R.color.color_button_state_on));
            }
        }

        @Override
        public void onAcDefrostStateChanged(int area, int state) {
            super.onAcDefrostStateChanged(area, state);
            if (area == BYDAutoAcDevice.AC_DEFROST_AREA_FRONT) {
                if (defrostModeBtn != null) {
                    if (state == BYDAutoAcDevice.AC_DEFROST_STATE_OFF) {
                        defrostModeBtn.setTextColor(ContextCompat.getColor(context, R.color.color_button_state_off));
                    } else if (state == BYDAutoAcDevice.AC_DEFROST_STATE_ON) {
                        defrostModeBtn.setTextColor(ContextCompat.getColor(context, R.color.color_button_state_on));
                    }
                }
            }
        }

        @Override
        public void onAcCompressorManualSignChanged(int sign) {
            super.onAcCompressorManualSignChanged(sign);
        }

        @Override
        public void onAcCompressorModeChanged(int mode) {
            super.onAcCompressorModeChanged(mode);
            if (mode == BYDAutoAcDevice.AC_COMPRESSOR_OFF) {
                acCompressorBtn.setTextColor(ContextCompat.getColor(context, R.color.color_button_state_off));
            } else if (mode == BYDAutoAcDevice.AC_COMPRESSOR_ON) {
                acCompressorBtn.setTextColor(ContextCompat.getColor(context, R.color.color_button_state_on));
            }
        }

        @Override
        public void onAcWindModeManualSignChanged(int sign) {
            super.onAcWindModeManualSignChanged(sign);
        }

        @Override
        public void onAcWindModeChanged(int mode) {
            super.onAcWindModeChanged(mode);
        }

        @Override
        public void onAcWindLevelManualSignChanged(int sign) {
            super.onAcWindLevelManualSignChanged(sign);
        }

        @Override
        public void onAcWindLevelChanged(int level) {
            super.onAcWindLevelChanged(level);
            currentWindLevelTv.setText(String.valueOf(level));
        }

        @Override
        public void onTemperatureUnitChanged(int unit) {
            super.onTemperatureUnitChanged(unit);
        }

        @Override
        public void onTemperatureChanged(int area, int value) {
            super.onTemperatureChanged(area, value);
            if (area == BYDAutoAcDevice.AC_TEMPERATURE_MAIN) {
                if (currentTemperatureTv != null) {
                    currentTemperatureTv.setText(String.valueOf(value));
                }
            }
        }

        @Override
        public void onAcWindModeShownStateChanged(int state) {
            super.onAcWindModeShownStateChanged(state);
        }
    };
}
