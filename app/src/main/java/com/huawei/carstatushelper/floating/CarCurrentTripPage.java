package com.huawei.carstatushelper.floating;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.byd_helper.BYDAutoStatisticDeviceHelper;
import com.huawei.carstatushelper.databinding.LayoutFloatingViewpagerItem3Binding;
import com.huawei.carstatushelper.receiver.BootCompleteService;
import com.huawei.carstatushelper.util.BydManifest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class CarCurrentTripPage implements IPage, View.OnClickListener {
    private static final DecimalFormat format = new DecimalFormat("##0.0##");

    private Context context;
    private View rootView;
    private TextView currentTravelMileageTv;
    private TextView currentTravelEnergyCostTv;
    private TextView currentTravelYuanCostTv;
    private TextView currentTravelElecCostTv;
    private TextView currentTravelFuelCostTv;
    private TextView currentComprehensiveElecCostTv;
    private TextView currentComprehensiveFuelCostTv;
    private BYDAutoStatisticDevice statisticDevice;

    private SharedPreferences mPreferences;
    private int init_totalMileageValue;
    private int init_evMileageValue;
    private int init_hevMileageValue;
    private double init_totalElecConValue;
    private double init_totalFuelConValue;
    private double init_latest_electric_price;
    private double init_latest_fuel_price;

    public CarCurrentTripPage(Context context) {
        this.context = context;
    }

    @Override
    public void init() {
        LayoutFloatingViewpagerItem3Binding binding = LayoutFloatingViewpagerItem3Binding.inflate(LayoutInflater.from(context));
        rootView = binding.getRoot();

        currentTravelMileageTv = binding.currentTravelMileageTv;
        currentTravelEnergyCostTv = binding.currentTravelEnergyCostTv;
        currentTravelYuanCostTv = binding.currentTravelYuanCostTv;
        currentTravelElecCostTv = binding.currentTravelElecCostTv;
        currentTravelFuelCostTv = binding.currentTravelFuelCostTv;
        currentComprehensiveElecCostTv = binding.currentComprehensiveElecCostTv;
        currentComprehensiveFuelCostTv = binding.currentComprehensiveFuelCostTv;

        binding.resetCurrentMileageBtn.setOnClickListener(this);
        binding.pauseCurrentMileageBtn.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(context, BydManifest.permission.BYDAUTO_STATISTIC_GET) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        statisticDevice = BYDAutoStatisticDevice.getInstance(context);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = mPreferences.getString(BootCompleteService.KEY_INIT_DRIVER_DATA, null);
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONObject initDriverData = new JSONObject(json);
                init_totalMileageValue = initDriverData.getInt("totalMileageValue");
                init_evMileageValue = initDriverData.getInt("evMileageValue");
                init_hevMileageValue = initDriverData.getInt("hevMileageValue");
                init_totalElecConValue = initDriverData.getDouble("totalElecConValue");
                init_totalFuelConValue = initDriverData.getDouble("totalFuelConValue");
                init_latest_fuel_price = Double.parseDouble(mPreferences.getString("latest_fuel_price", context.getString(R.string.default_latest_fuel_price)));
                init_latest_electric_price = Double.parseDouble(mPreferences.getString("latest_electric_price", context.getString(R.string.default_latest_electric_price)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //总里程
        statisticListener.onTotalMileageValueChanged(statisticDevice.getTotalMileageValue());
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void onCreate() {
        if (statisticDevice == null) {
            return;
        }
        statisticDevice.registerListener(statisticListener);
    }

    @Override
    public void onDestroy() {
        if (statisticDevice == null) {
            return;
        }
        statisticDevice.unregisterListener(statisticListener);
    }

    private final AbsBYDAutoStatisticListener statisticListener = new AbsBYDAutoStatisticListener() {
        @Override
        public void onTotalMileageValueChanged(int value) {
            super.onTotalMileageValueChanged(value);
            BYDAutoStatisticDeviceHelper statisticDeviceHelper = BYDAutoStatisticDeviceHelper.getInstance(statisticDevice);
            //总HEV里程
            int hevMileageValue = statisticDeviceHelper.getHEVMileageValue();
            //更新单次行程能耗数据
            int evMileageValue = statisticDevice.getEVMileageValue();
            double totalElecConValue = statisticDevice.getTotalElecConValue();
            double totalFuelConValue = statisticDevice.getTotalFuelConValue();

            //本次行程总里程
            int total_mileage = value - init_totalMileageValue;
            //本次行程ev里程
            int ev_mileage = evMileageValue - init_evMileageValue;
            //本次行程hev里程
            int hev_mileage = hevMileageValue - init_hevMileageValue;
            if (currentTravelMileageTv != null) {
                currentTravelMileageTv.setText(String.valueOf(total_mileage));
            }
            //本次行程电耗
            double elec_cost = totalElecConValue - init_totalElecConValue;
            //同步车机负一屏计算方式，油发电不计算费用
            if (elec_cost <= 0) {
                elec_cost = 0;
            }
            //本次行程油耗
            double fuel_cost = totalFuelConValue - init_totalFuelConValue;
            //本次行程能耗(电耗+油耗)
            String cost = format.format(elec_cost) + "KWH+" + format.format(fuel_cost) + "L";
            if (currentTravelEnergyCostTv != null) {
                currentTravelEnergyCostTv.setText(cost);
            }
            //本次行程花费
            double yuan = elec_cost * init_latest_electric_price + fuel_cost * init_latest_fuel_price;
            if (currentTravelYuanCostTv != null) {
                currentTravelYuanCostTv.setText(format.format(yuan));
            }
            //本次行程平均电耗(kwh/100km)
            if (currentTravelElecCostTv != null && ev_mileage != 0) {
                currentTravelElecCostTv.setText(format.format(elec_cost * 100 / ev_mileage));
            }
            //本次行程平均油耗
            if (currentTravelFuelCostTv != null && hev_mileage != 0) {
                currentTravelFuelCostTv.setText(format.format(fuel_cost * 100 / hev_mileage));
            }
            //本次行程花费的钱相当于多少电
            double valueKwh = yuan / init_latest_electric_price;//多少kwh
            //本次行程花费的钱相当于多少油
            double valueL = yuan / init_latest_fuel_price;//多少L
            double c = 0;
            double d = 0;
            if (total_mileage != 0) {
                //本次行程综合电耗
                d = valueKwh * 100.0f / total_mileage;//kwh/100km
                //本次行程综合油耗
                c = valueL * 100.0f / total_mileage;//L/100km
            }
            if (currentComprehensiveElecCostTv != null) {
                currentComprehensiveElecCostTv.setText(format.format(d));
            }
            if (currentComprehensiveFuelCostTv != null) {
                currentComprehensiveFuelCostTv.setText(format.format(c));
            }
        }
    };

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        if (vId == R.id.reset_current_mileage_btn) {

        } else if (vId == R.id.pause_current_mileage_btn) {

        }
    }
}
