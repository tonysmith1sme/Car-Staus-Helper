package com.huawei.carstatushelper.test;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.adas.AbsBYDAutoADASListener;
import android.hardware.bydauto.adas.BYDAutoADASDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.huawei.carstatushelper.BR;
import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.databinding.ActivityAdasTestBinding;

import java.util.Arrays;

import timber.log.Timber;

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
        adasDevice.registerListener(adasListener);
        initData();
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
            case R.id.get_data_btn:
                initData();
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void initData(){
        dataHolder.setHMAState(getFeaturesValue(BYDAutoFeatureIds.ADAS_HMA_STATE));
        dataHolder.setPCWState(getFeaturesValue(BYDAutoFeatureIds.ADAS_PCW_STATE));
        dataHolder.setAEBState(getFeaturesValue(BYDAutoFeatureIds.ADAS_AEB_STATE));
        dataHolder.setESPState(getFeaturesValue(BYDAutoFeatureIds.ADAS_ESP_STATE));
        dataHolder.setLKSMode(getFeaturesValue(BYDAutoFeatureIds.ADAS_LKS_MODE));
        dataHolder.setLKSSensitivity(getFeaturesValue(BYDAutoFeatureIds.ADAS_LKS_SENSITIVITY));
        dataHolder.setLDSWType(getFeaturesValue(BYDAutoFeatureIds.ADAS_LDSW_TYPE));
        dataHolder.setBSDState(getFeaturesValue(BYDAutoFeatureIds.ADAS_BSD_STATE));
        dataHolder.setSLAState(getFeaturesValue(BYDAutoFeatureIds.ADAS_SLA_STATE));
        dataHolder.setAVHState(getFeaturesValue(BYDAutoFeatureIds.ADAS_AVH_STATE));
        dataHolder.setIboosterState(getFeaturesValue(BYDAutoFeatureIds.ADAS_IBOOSTER_STATE));
        dataHolder.setTJAState(getFeaturesValue(BYDAutoFeatureIds.ADAS_TJA_ICA_STATE));
        dataHolder.setLaneOffsetState(getFeaturesValue(BYDAutoFeatureIds.ADAS_LANE_OFFSET_KEY));
        dataHolder.setESPKeyState(getFeaturesValue(BYDAutoFeatureIds.ADAS_CMD_ADAS_ESP_KEY));
        dataHolder.setHDCState(getFeaturesValue(BYDAutoFeatureIds.ADAS_CMD_HDC_STATE));
        dataHolder.setBrakeFootSenseState(getFeaturesValue(BYDAutoFeatureIds.ADAS_CMD_BRAKE_FOOT_SENSE));
        dataHolder.setCSTState(getFeaturesValue(BYDAutoFeatureIds.ADAS_CMD_CST_SWITCH));
        dataHolder.setCSTData(getFeaturesValue(BYDAutoFeatureIds.ADAS_CMD_CST_ONLINE));
        dataHolder.setSuspen(getFeaturesValue(BYDAutoFeatureIds.ADAS_AVM_APA_SUSPEND));
        dataHolder.setInterActiveHint(getFeaturesValue(BYDAutoFeatureIds.ADAS_AVM_APA_INTERACTIVE_HINT));
        dataHolder.setAutoSearchState(getFeaturesValue(BYDAutoFeatureIds.ADAS_AVM_APA_AUTOSEARCH_STATE));
        dataHolder.setAbortReason(getFeaturesValue(BYDAutoFeatureIds.ADAS_AVM_APA_ABORT_REASON));
        dataHolder.setAVMSwitchState(getFeaturesValue(BYDAutoFeatureIds.ADAS_AVM_APA_SWITCH));
        dataHolder.setHDCKeyState(getFeaturesValue(BYDAutoFeatureIds.ADAS_CMD_ADAS_HDC_KEY));
        refreshData();
    }

    private String getFeaturesValue(int event) {
        return adasDevice.get(adasDevice.getDevicetype(), event) + "";
    }

    private void refreshData() {
        binding.setVariable(BR.data, dataHolder);
    }

    private final AbsBYDAutoADASListener adasListener = new AbsBYDAutoADASListener() {
        @Override
        public void onFeatureChanged(String feature, int ifHas) {
            super.onFeatureChanged(feature, ifHas);
            Timber.e("onFeatureChanged, feature = %s ,ifHas = %d", feature, ifHas);
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
            Timber.e("error = %d errMessage = %s", errCode, errMessage);
        }

        @Override
        public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
            super.onDataEventChanged(eventType, eventValue);
            String builder = eventValue.intValue + "," + eventValue.floatValue + "," + eventValue.doubleValue + "," + Arrays.toString(eventValue.intArrayValue) + "," + Arrays.toString(eventValue.floatArrayValue) + "," + Arrays.toString(eventValue.bufferDataValue);
            Timber.e("onDataEventChanged adas信息%d ,data = [%s]", eventType, builder);
        }
    };
}