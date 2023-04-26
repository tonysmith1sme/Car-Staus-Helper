package com.huawei.carstatushelper.test;

import android.content.pm.PackageManager;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.adas.AbsBYDAutoADASListener;
import android.hardware.bydauto.adas.BYDAutoADASDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.huawei.carstatushelper.BR;
import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.databinding.ActivityAdasTestBinding;
import com.socks.library.KLog;

public class AdasTestActivity extends AppCompatActivity implements View.OnClickListener {
    private BYDAutoADASDevice adasDevice;
    private com.huawei.carstatushelper.databinding.ActivityAdasTestBinding binding;
    private AdasDataHolder dataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdasTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.setVariable(BR.listener, this);
        dataHolder = new AdasDataHolder();
        binding.setVariable(BR.data, dataHolder);

        adasDevice = BYDAutoADASDevice.getInstance(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adasDevice.unregisterListener(adasListener);
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        switch (vId) {
            case R.id.request_permission_btn:
                try {
                    String[] permission = {"android.permission.BYDAUTO_ADAS_GET", "android.permission.BYDAUTO_ADAS_SET", "android.permission.BYDAUTO_ADAS_COMMON"};
                    boolean granted = true;
                    for (String per : permission) {
                        if (ContextCompat.checkSelfPermission(this, per) != PackageManager.PERMISSION_GRANTED) {
                            granted = false;
                            break;
                        }
                    }
                    if (!granted) {
                        ActivityCompat.requestPermissions(this, permission, 0);
                        return;
                    }
                } catch (Exception e) {
                    String[] permission = {"android.permission.BYDAUTO_ADAS_GET", "android.permission.BYDAUTO_ADAS_SET",
//                            "android.permission.BYDAUTO_ADAS_COMMON"
                    };
                    boolean granted = true;
                    for (String per : permission) {
                        if (ContextCompat.checkSelfPermission(this, per) != PackageManager.PERMISSION_GRANTED) {
                            granted = false;
                            break;
                        }
                    }
                    if (!granted) {
                        ActivityCompat.requestPermissions(this, permission, 0);
                        return;
                    }
                }
                Toast.makeText(this, "权限授予成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.register_btn:
                adasDevice.registerListener(adasListener);
                break;
            case R.id.unregister_btn:
                adasDevice.unregisterListener(adasListener);
                break;
            default:
                break;
        }
    }

    private void refreshData() {
        binding.setVariable(BR.data, dataHolder);
    }

    private final AbsBYDAutoADASListener adasListener = new AbsBYDAutoADASListener() {
        @Override
        public void onFeatureChanged(String feature, int ifHas) {
            super.onFeatureChanged(feature, ifHas);
            KLog.e("onFeatureChanged, feature = " + feature + " ,ifHas = " + ifHas);
        }

        @Override
        public void onHMAStateChanged(int state) {
            super.onHMAStateChanged(state);
            dataHolder.setHMAState(state + "");
            refreshData();
        }

        @Override
        public void onPCWStateChanged(int state) {
            super.onPCWStateChanged(state);
            dataHolder.setPCWState(state + "");
            refreshData();
        }

        @Override
        public void onAEBStateChanged(int state) {
            super.onAEBStateChanged(state);
            dataHolder.setAEBState(state + "");
            refreshData();
        }

        @Override
        public void onESPStateChanged(int state) {
            super.onESPStateChanged(state);
            dataHolder.setESPState(state + "");
            refreshData();
        }

        @Override
        public void onLKSModeChanged(int mode) {
            super.onLKSModeChanged(mode);
            dataHolder.setLKSMode(mode + "");
            refreshData();
        }

        @Override
        public void onLKSSensitivityChanged(int sensitivity) {
            super.onLKSSensitivityChanged(sensitivity);
            dataHolder.setLKSSensitivity(sensitivity + "");
            refreshData();
        }

        @Override
        public void onLDSWTypeChanged(int type) {
            super.onLDSWTypeChanged(type);
            dataHolder.setLDSWType(type + "");
            refreshData();
        }

        @Override
        public void onBSDStateChanged(int state) {
            super.onBSDStateChanged(state);
            dataHolder.setBSDState(state + "");
            refreshData();
        }

        @Override
        public void onSLAStateChanged(int state) {
            super.onSLAStateChanged(state);
            dataHolder.setSLAState(state + "");
            refreshData();
        }

        @Override
        public void onAVHStateChanged(int state) {
            super.onAVHStateChanged(state);
            dataHolder.setAVHState(state + "");
            refreshData();
        }

        @Override
        public void onIboosterStateChanged(int state) {
            super.onIboosterStateChanged(state);
            dataHolder.setIboosterState(state + "");
            refreshData();
        }

        @Override
        public void onTJAStateChanged(int state) {
            super.onTJAStateChanged(state);
            dataHolder.setTJAState(state + "");
            refreshData();
        }

        @Override
        public void onLaneOffsetStateChanged(int state) {
            super.onLaneOffsetStateChanged(state);
            dataHolder.setLaneOffsetState(state + "");
            refreshData();
        }

        @Override
        public void onESPKeyStateChanged(int state) {
            super.onESPKeyStateChanged(state);
            dataHolder.setESPKeyState(state + "");
            refreshData();
        }

        @Override
        public void onHDCStateChanged(int state) {
            super.onHDCStateChanged(state);
            dataHolder.setHDCState(state + "");
            refreshData();
        }

        @Override
        public void onBrakeFootSenseStateChanged(int state) {
            super.onBrakeFootSenseStateChanged(state);
            dataHolder.setBrakeFootSenseState(state + "");
            refreshData();
        }

        @Override
        public void onCSTStateChanged(int state) {
            super.onCSTStateChanged(state);
            dataHolder.setCSTState(state + "");
            refreshData();
        }

        @Override
        public void onCSTDataChanged(int state) {
            super.onCSTDataChanged(state);
            dataHolder.setCSTData(state + "");
            refreshData();
        }

        @Override
        public void onSuspenChanged(int state) {
            super.onSuspenChanged(state);
            dataHolder.setSuspen(state + "");
            refreshData();
        }

        @Override
        public void onInterActiveHintChanged(int state) {
            super.onInterActiveHintChanged(state);
            dataHolder.setInterActiveHint(state + "");
            refreshData();
        }

        @Override
        public void onAutoSearchStateChanged(int state) {
            super.onAutoSearchStateChanged(state);
            dataHolder.setAutoSearchState(state + "");
            refreshData();
        }

        @Override
        public void onAbortReasonChanged(int state) {
            super.onAbortReasonChanged(state);
            dataHolder.setAbortReason(state + "");
            refreshData();
        }

        @Override
        public void onActiveChanged(int state) {
            super.onActiveChanged(state);
            dataHolder.setActive(state + "");
            refreshData();
        }

        @Override
        public void onAVMSwitchStateChanged(int state) {
            super.onAVMSwitchStateChanged(state);
            dataHolder.setAVMSwitchState(state + "");
            refreshData();
        }

        @Override
        public void onAutoParkButtonStateChanged(int state) {
            super.onAutoParkButtonStateChanged(state);
            dataHolder.setAutoParkButtonState(state + "");
            refreshData();
        }

        @Override
        public void onHDCKeyStateChanged(int state) {
            super.onHDCKeyStateChanged(state);
            dataHolder.setHDCKeyState(state + "");
            refreshData();
        }

        @Override
        public void onError(int errCode, String errMessage) {
            super.onError(errCode, errMessage);
            KLog.e("error = " + errCode + " errMessage = " + errMessage);
        }

        @Override
        public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
            super.onDataEventChanged(eventType, eventValue);
        }
    };
}