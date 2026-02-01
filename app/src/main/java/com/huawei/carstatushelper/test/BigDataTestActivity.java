package com.huawei.carstatushelper.test;

import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.bigdata.AbsBYDAutoBigDataListener;
import android.hardware.bydauto.bigdata.BYDAutoBigDataDevice;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.byd.CanDataCollect.service.CanDataInfo;
import com.huawei.carstatushelper.databinding.ActivityBigDataTestBinding;
import com.huawei.carstatushelper.databinding.ListItemBigDataBinding;
import com.huawei.carstatushelper.util.StringToHex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

public class BigDataTestActivity extends AppCompatActivity {

    private BYDAutoPowerDevice powerDevice;
    private BYDAutoBigDataDevice bigDataDevice;
    private int accStatus;
    private RecyclerView mRecyclerView;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBigDataTestBinding binding = ActivityBigDataTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mRecyclerView = binding.recyclerView;
        itemAdapter = new ItemAdapter();
        mRecyclerView.setAdapter(itemAdapter);

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
            if (event_type == BYDAutoFeatureIds.POWER_ACC_STATUS) {
                if (value == BYDAutoPowerDevice.POWER_CTL_STATE_ON) {

                } else if (value == BYDAutoPowerDevice.POWER_CTL_STATE_OFF) {

                }
            }
        }
    };

    private final AbsBYDAutoBigDataListener bigDataListener = new AbsBYDAutoBigDataListener() {
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
            this.accStatus = bYDAutoPowerDevice.getPowerCtlStatus(BYDAutoFeatureIds.POWER_ACC_STATUS);
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

        runOnUiThread(new Runnable() {
            long lastUpdateTime;

            @Override
            public void run() {
                if (System.currentTimeMillis() - lastUpdateTime < 500) {
                    return;
                }
                lastUpdateTime = System.currentTimeMillis();
                itemAdapter.addData(cdi);
            }
        });
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

        Timber.e("recv_can: canId = %s subId = %s canChanel = %s data = %s", Long.toHexString(cdi.canid), cdi.subid, cdi.canChanel, Arrays.toString(cdi.canBuffer));
        recvCanData(cdi);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ListItemBigDataBinding binding;

        public ItemViewHolder(ListItemBigDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private List<CanDataInfo> dataList;

        public ItemAdapter() {
            this.dataList = new ArrayList<>();
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemBigDataBinding binding = ListItemBigDataBinding.inflate(getLayoutInflater());
            return new ItemViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            CanDataInfo canDataInfo = dataList.get(position);
            int canChanel = canDataInfo.canChanel & 0xff;
            long canid = canDataInfo.canid;
            int subid = canDataInfo.subid & 0xff;
            int len = canDataInfo.candatalen & 0xff;
            byte[] canBuffer = canDataInfo.canBuffer;
            String data_hex = StringToHex.bytesToHexString(canBuffer);

            holder.binding.canChannel.setText("" + canChanel);
            holder.binding.canId.setText("" + canid);
            holder.binding.subId.setText("" + subid);
            holder.binding.canDataLen.setText("" + len);
            holder.binding.data.setText(data_hex);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public void addData(CanDataInfo cdi) {
            dataList.add(cdi);
            notifyDataSetChanged();
        }
    }
}