package com.huawei.carstatushelper.test;

import android.hardware.bydauto.adas.AbsBYDAutoADASListener;
import android.hardware.bydauto.adas.BYDAutoADASDevice;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.byd_helper.BYDAutoSettingDeviceHelper;
import com.huawei.carstatushelper.databinding.ActivityReflectTestBinding;
import com.huawei.carstatushelper.util.ReflectHelper;
import com.socks.library.KLog;

public class ReflectTestActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private EditText clzNameEt;
    private EditText methodNameEt;
    private EditText methodArgTypeEt;
    private EditText methodArgValueEt;
    private TextView testResultTv;
    private int newInstanceType = 0;
    private TextView rearViewMirrorFlipStateTv;
    private BYDAutoSettingDevice settingDevice;
    private BYDAutoSettingDeviceHelper settingDeviceHelper;
    private BYDAutoADASDevice adasDevice;
    private Button testBtn;
    private TextView onActiveChangeTv;
    private TextView onLksModeChangedTv;
    private TextView onLksSensitivityChangedTv;
    private TextView onLaneOffsetStateChangedTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityReflectTestBinding binding = ActivityReflectTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        clzNameEt = binding.clzNameEt;
        binding.instanceTypeRg.setOnCheckedChangeListener(this);
        methodNameEt = binding.methodNameEt;
        methodArgTypeEt = binding.methodArgTypeEt;
        methodArgValueEt = binding.methodArgValueEt;
        testBtn = binding.testBtn;
        testResultTv = binding.testResultTv;
        rearViewMirrorFlipStateTv = binding.rearViewMirrorFlipStateTv;
        onActiveChangeTv = binding.onActiveChangeTv;
        onLksModeChangedTv = binding.onLksModeChangedTv;
        onLksSensitivityChangedTv = binding.onLksSensitivityChangedTv;
        onLaneOffsetStateChangedTv = binding.onLaneOffsetStateChangedTv;

        testBtn.setOnClickListener(this);
        binding.rearViewMirrorFlipStateChangeBtn.setOnClickListener(this);
        binding.setRearMirrorAngleBtn.setOnClickListener(this);

//        ReflectHelper.testHelper(this);

        settingDevice = BYDAutoSettingDevice.getInstance(this);
        adasDevice = BYDAutoADASDevice.getInstance(this);

        settingDevice.registerListener(settingListener);
        adasDevice.registerListener(adasListener);

        settingDeviceHelper = BYDAutoSettingDeviceHelper.getInstance(settingDevice);

        settingListener.onRearViewMirrorFlipSwitchChanged(settingDevice.getRearViewMirrorFlip());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        settingDevice.unregisterListener(settingListener);
        adasDevice.unregisterListener(adasListener);
    }

    private final AbsBYDAutoADASListener adasListener = new AbsBYDAutoADASListener() {
        @Override
        public void onActiveChanged(int state) {
            super.onActiveChanged(state);
            KLog.e();
            onActiveChangeTv.setText(String.valueOf(state));
        }

        @Override
        public void onLKSModeChanged(int mode) {
            super.onLKSModeChanged(mode);
            KLog.e();
            onLksModeChangedTv.setText(String.valueOf(mode));
        }

        @Override
        public void onLKSSensitivityChanged(int sensitivity) {
            super.onLKSSensitivityChanged(sensitivity);
            KLog.e();
            onLksSensitivityChangedTv.setText(String.valueOf(sensitivity));

        }

        @Override
        public void onLaneOffsetStateChanged(int state) {
            super.onLaneOffsetStateChanged(state);
            KLog.e();
            onLaneOffsetStateChangedTv.setText(String.valueOf(state));

        }
    };

    private final AbsBYDAutoSettingListener settingListener = new AbsBYDAutoSettingListener() {
        @Override
        public void onRearViewMirrorFlipSwitchChanged(int state) {
            super.onRearViewMirrorFlipSwitchChanged(state);
            if (state == BYDAutoSettingDevice.SET_OFF) {
                rearViewMirrorFlipStateTv.setText("关");
            } else if (state == BYDAutoSettingDevice.SET_ON) {
                rearViewMirrorFlipStateTv.setText("开");
            } else if (state == BYDAutoSettingDevice.SET_INVALID) {
                rearViewMirrorFlipStateTv.setText("无效");
            } else {
                rearViewMirrorFlipStateTv.setText("未知");
            }
        }
    };

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.test_btn:
                String clzName = clzNameEt.getText().toString();
                String methodName = methodNameEt.getText().toString();
                String methodArgType = methodArgTypeEt.getText().toString();
                String methodArgValue = methodArgValueEt.getText().toString();

                String invoke = ReflectHelper.invoke(this, newInstanceType, clzName, methodName, methodArgType, methodArgValue);
                testResultTv.setText(invoke);
                Toast.makeText(this, "result: " + invoke, Toast.LENGTH_SHORT).show();
                break;
            case R.id.rear_view_mirror_flip_state_change_btn:
                //0,1,2,3
                //0==2
                int rearViewMirrorFlip = settingDevice.getRearViewMirrorFlip();
                if (rearViewMirrorFlip == BYDAutoSettingDevice.SET_OFF) {
                    int ret = settingDevice.setRearViewMirrorFlip(BYDAutoSettingDevice.SET_ON);
                    Toast.makeText(this, getRetName(ret), Toast.LENGTH_SHORT).show();
                } else if (rearViewMirrorFlip == BYDAutoSettingDevice.SET_ON) {
                    int ret = settingDevice.setRearViewMirrorFlip(BYDAutoSettingDevice.SET_OFF);
                    Toast.makeText(this, getRetName(ret), Toast.LENGTH_SHORT).show();
                } else if (rearViewMirrorFlip == BYDAutoSettingDevice.SET_INVALID) {
                    Toast.makeText(this, "无此设置项", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.get_rear_mirror_angle_btn:
                int leftViewMirrorFlipAngle = settingDeviceHelper.getLeftViewMirrorFlipAngle();
                int rightViewMirrorFlipAngle = settingDeviceHelper.getRightViewMirrorFlipAngle();
                Toast.makeText(this, "左：" + leftViewMirrorFlipAngle + ",右：" + rightViewMirrorFlipAngle, Toast.LENGTH_SHORT).show();
                break;
            case R.id.set_rear_mirror_angle_btn:
                if (mirrorAngle > 8) {
                    mirrorAngle = 0;
                }
                //角度取值0-8
                int ret1 = settingDeviceHelper.setLeftViewMirrorFlipAngle(mirrorAngle);
                int ret2 = settingDeviceHelper.setRightViewMirrorFlipAngle(mirrorAngle);
                mirrorAngle++;
                Toast.makeText(this, "ret1:" + ret1 + ",ret2:" + ret2, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    int mirrorAngle;

    private String getRetName(int ret) {
        if (ret == BYDAutoSettingDevice.SETTING_COMMAND_SUCCESS) {
            return "成功";
        }
        if (ret == BYDAutoSettingDevice.SETTING_COMMAND_FAILED) {
            return "失败";
        }
        if (ret == BYDAutoSettingDevice.SETTING_COMMAND_INVALID) {
            return "无效输入";
        }
        if (ret == BYDAutoSettingDevice.SETTING_COMMAND_TIMEOUT) {
            return "超时";
        }
        if (ret == BYDAutoSettingDevice.SETTING_COMMAND_BUSY) {
            return "系统忙";
        }
        return "unknown";
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.type_static) {
            newInstanceType = 0;
        } else if (i == R.id.type_constructor) {
            newInstanceType = 1;
        }
    }
}