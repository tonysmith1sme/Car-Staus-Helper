package com.huawei.carstatushelper.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huawei.carstatushelper.databinding.ActivitySponsorListBinding;
import com.huawei.carstatushelper.databinding.ListItemSponsorListBinding;
import com.socks.library.KLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 赞助列表
 */
public class SponsorListActivity extends BackEnableBaseActivity {

    private RecyclerView mRecyclerView;
    private ItemAdapter mItemAdapter;
    private List<DataBean> mDataBeanList;

    @Override
    public CharSequence setPageTitle() {
        return "打赏列表";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySponsorListBinding binding = ActivitySponsorListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mRecyclerView = binding.recyclerView;
        mDataBeanList = new ArrayList<>();
        mItemAdapter = new ItemAdapter(mDataBeanList);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        mRecyclerView.setAdapter(mItemAdapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                readJson();
            }
        }).start();
    }

    private void readJson() {
        try {
            InputStream is = getAssets().open("车况助手-二维码收款账单.json");
            String json = getStringByInputStream_1(is);
            is.close();
            JSONArray array = new JSONArray(json);
            List<DataBean> dataBeanList = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String channel = obj.getString("channel");
                String id = obj.getString("id");
                String time = obj.getString("time");
                String money = obj.getString("money");
                String name = obj.getString("name");

                DataBean dataBean = new DataBean();
                dataBean.channel = channel;
                dataBean.id = id;
                dataBean.time = time;
                dataBean.money = money;
                dataBean.name = name;

                dataBeanList.add(dataBean);
                KLog.e();
            }
            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mDataBeanList.addAll(dataBeanList);
//                    mItemAdapter.notifyItemRangeInserted(0, mDataBeanList.size());
                    mRecyclerView.setAdapter(mItemAdapter);
                }
            });
            KLog.e();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static String getStringByInputStream_1(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            byte[] b = new byte[10240];
            int n;
            while ((n = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, n);
            }
        } catch (Exception e) {
            try {
                inputStream.close();
                outputStream.close();
            } catch (Exception e1) {
            }
        }
        return outputStream.toString();
    }

    private static class DataBean {
        String channel;
        String id;
        String time;
        String money;
        String name;
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        ListItemSponsorListBinding binding;
        TextView dataChannel;
        TextView longId;
        TextView dataTime;
        TextView dataMoney;
        TextView sponsorName;

        public ItemViewHolder(ListItemSponsorListBinding binding) {
            super(binding.getRoot());
            dataChannel = binding.dataChannel;
            longId = binding.longId;
            dataTime = binding.dataTime;
            dataMoney = binding.dataMoney;
            sponsorName = binding.sponsorName;
        }
    }

    private static class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        List<DataBean> dataBeans;

        public ItemAdapter(List<DataBean> dataBeans) {
            this.dataBeans = dataBeans;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ListItemSponsorListBinding binding = ListItemSponsorListBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new ItemViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            DataBean dataBean = dataBeans.get(position);
            holder.dataChannel.setText("渠道：" + dataBean.channel);
            holder.longId.setText("订单号：" + dataBean.id);
            holder.dataTime.setText("时间：" + dataBean.time);
            holder.dataMoney.setText("金额：" + dataBean.money);
            holder.sponsorName.setText("付款方：" + dataBean.name);
        }

        @Override
        public int getItemCount() {
            return dataBeans.size();
        }
    }

}