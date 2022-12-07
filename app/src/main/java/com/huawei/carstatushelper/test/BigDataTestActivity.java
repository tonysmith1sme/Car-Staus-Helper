package com.huawei.carstatushelper.test;

import android.hardware.bydauto.bigdata.AbsBYDAutoBigDataListener;
import android.hardware.bydauto.bigdata.BYDAutoBigDataDevice;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.byd.CanDataCollect.service.CanDataInfo;
import com.huawei.carstatushelper.databinding.ActivityBigDataTestBinding;
import com.socks.library.KLog;

import java.util.Arrays;

public class BigDataTestActivity extends AppCompatActivity {

    private BYDAutoPowerDevice powerDevice;
    private BYDAutoBigDataDevice bigDataDevice;
    private int accStatus;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBigDataTestBinding binding = ActivityBigDataTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mRecyclerView = binding.recyclerView;


        powerDevice = BYDAutoPowerDevice.getInstance(this);
        bigDataDevice = BYDAutoBigDataDevice.getInstance(this);
        getAccStatus();

        powerDevice.registerListener(powerListener);
        bigDataDevice.registerListener(bigDataListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        powerDevice.unregisterListener(powerListener);
        bigDataDevice.unregisterListener(bigDataListener);
    }

    private AbsBYDAutoPowerListener powerListener = new AbsBYDAutoPowerListener() {
        @Override
        public void onPowerCtlStatusChanged(int event_type, int value) {
            super.onPowerCtlStatusChanged(event_type, value);
            if (event_type == BYDAutoPowerDevice.ACC_STATUS) {
                if (value == BYDAutoPowerDevice.POWER_CTL_STATE_ON) {

                } else if (value == BYDAutoPowerDevice.POWER_CTL_STATE_OFF) {

                }
            }
        }
    };

    private AbsBYDAutoBigDataListener bigDataListener = new AbsBYDAutoBigDataListener() {
        @Override
        public void onWholeFrameDataChanged(byte[] data) {
            super.onWholeFrameDataChanged(data);
            if (BigDataTestActivity.this.accStatus == 1) {
                recv_can(data);
            }
        }
    };

    public void getAccStatus() {
//        Log.i(LOG_TAG, "getAccStatus");
        BYDAutoPowerDevice bYDAutoPowerDevice = this.powerDevice;
        if (bYDAutoPowerDevice != null) {
            this.accStatus = bYDAutoPowerDevice.getPowerCtlStatus(BYDAutoPowerDevice.ACC_STATUS);
        }
    }

    private void recvCanData(CanDataInfo cdi) {
        if (cdi == null) {
//            Log.i(LOG_TAG, "receive CanDataInfo data is null");
            return;
        }
//        recvPeriodOrEventCanData(cdi.canChanel, cdi.canid, cdi.candatalen, cdi.canBuffer);
//        recvEventFaultCanData(cdi.canChanel, cdi.canid, cdi.canBuffer);
//        if (!this.period_first_frame_data) {
//            doPeriodAlarm();
//            this.period_first_frame_data = true;
//        }
//        if (!this.b_period_collect_data_flag) {
//            period_data_collect();
//        }
//        if (!this.event_first_frame_data) {
//            doEventAlarm();
//            this.event_first_frame_data = true;
//        }
//        if (!this.b_event_collect_data_flag) {
//            event_data_collect();
//        }
    }

    public void recv_can(byte[] msg) {
        if (msg == null || msg.length <= 0) {
            return;
        }
        CanDataInfo cdi = new CanDataInfo();
        cdi.canid = ((msg[0] & 255) << 24) + ((msg[0 + 1] & 255) << 16) + ((msg[0 + 2] & 255) << 8) + (msg[0 + 3] & 255);
        int index = 0 + 4;
        int index2 = index + 1;
        cdi.subid = msg[index];
        if (cdi.subid == 0) {
            cdi.subid = (byte) -1;
        }
        int index3 = index2 + 1;
        cdi.canChanel = msg[index2];
        if (msg.length - index3 > 64) {
//            Log.d(LOG_TAG, "recv_can data length is error: " + (msg.length - index3));
            return;
        }
        cdi.candatalen = (byte) (msg.length - index3);
        System.arraycopy(msg, index3, cdi.canBuffer, 0, cdi.candatalen);

        KLog.e( "recv_can: canId = " + Long.toHexString(cdi.canid) + " subId = " + cdi.subid + " canChanel = " + cdi.canChanel + " data = " + Arrays.toString(cdi.canBuffer));
        recvCanData(cdi);
    }

    public class ItemViewHolder {
        public ItemViewHolder() {
        }
    }
}