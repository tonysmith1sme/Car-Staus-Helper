package com.ziwenl.floatingwindowdemo;

import android.app.Service;
import android.content.Intent;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.engine.AbsBYDAutoEngineListener;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.carstatushelper.BuildConfig;
import com.huawei.carstatushelper.MainActivity;
import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.view.DialogEngineSpeedView;
import com.socks.library.KLog;
import com.ziwenl.floatingwindowdemo.utils.FloatingWindowHelper;

public class FloatingWindowService extends Service implements View.OnClickListener {
    private static final String TAG = FloatingWindowService.class.getSimpleName();

    private FloatingWindowHelper mFloatingWindowHelper;
    private View mExampleViewC;
    private TextView mCurrentTempTv;
    private TextView mCurrentWindLevelTv;
    private SeekBar mTempSeekBar;
    private SeekBar mWindSeekBar;
    private BYDAutoAcDevice mBydAutoAcDevice;
    private BYDAutoEngineDevice mBydAutoEngineDevice;
    private DialogEngineSpeedView mEngineSpeedView;
    private TextView mEngineSpeedTv;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mFloatingWindowHelper = new FloatingWindowHelper(this);
        mExampleViewC = LayoutInflater.from(this).inflate(R.layout.widget_test_view_c, null, false);
        try {
            mFloatingWindowHelper.addView(mExampleViewC, 0, 0, true);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "error:" + e.toString(), Toast.LENGTH_SHORT).show();
        }

        initView();

        if (!BuildConfig.DEBUG) {
            mBydAutoAcDevice = BYDAutoAcDevice.getInstance(this);
            mBydAutoEngineDevice = BYDAutoEngineDevice.getInstance(this);
        }
        if (mBydAutoAcDevice == null | mBydAutoEngineDevice == null) {
            return;
        }

        initData();

        mBydAutoEngineDevice.registerListener(absBYDAutoEngineListener);
    }

    private void initView() {
        if (mExampleViewC == null) {
            KLog.e("root view为空");
            return;
        }
        mEngineSpeedView = (DialogEngineSpeedView) mExampleViewC.findViewById(R.id.engine_speed_esv);
        mEngineSpeedTv = (TextView) mExampleViewC.findViewById(R.id.engine_speed_tv);

        mCurrentTempTv = (TextView) mExampleViewC.findViewById(R.id.current_temperature_tv);
        mCurrentWindLevelTv = (TextView) mExampleViewC.findViewById(R.id.current_wind_level_tv);

        //温度范围：17-33
        mTempSeekBar = (SeekBar) mExampleViewC.findViewById(R.id.temprature_seekbar);
        mTempSeekBar.setMax(BYDAutoAcDevice.AC_TEMP_IN_CELSIUS_MAX - BYDAutoAcDevice.AC_TEMP_IN_CELSIUS_MIN);
        //风量范围：0-7
        mWindSeekBar = (SeekBar) mExampleViewC.findViewById(R.id.wind_level_seekbar);
        mWindSeekBar.setMax(BYDAutoAcDevice.AC_WINDLEVEL_7);

        mTempSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        mWindSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        mExampleViewC.findViewById(R.id.close_btn).setOnClickListener(this);
        mExampleViewC.findViewById(R.id.back_btn).setOnClickListener(this);
    }

    void initData() {
        if (mBydAutoAcDevice == null) {
            return;
        }
        final int currentTemp = mBydAutoAcDevice.getTemprature(BYDAutoAcDevice.AC_TEMPERATURE_MAIN);
        mCurrentTempTv.setText(String.valueOf(currentTemp));
        mTempSeekBar.setProgress(currentTemp - BYDAutoAcDevice.AC_TEMP_IN_CELSIUS_MIN);

        final int currentWindLevel = mBydAutoAcDevice.getAcWindLevel();
        mCurrentWindLevelTv.setText(String.valueOf(currentWindLevel));
        mWindSeekBar.setProgress(currentWindLevel);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBydAutoEngineDevice == null) {
            return;
        }
        mBydAutoEngineDevice.unregisterListener(absBYDAutoEngineListener);
    }

    private final AbsBYDAutoEngineListener absBYDAutoEngineListener = new AbsBYDAutoEngineListener() {
        @Override
        public void onEngineSpeedChanged(int value) {
            super.onEngineSpeedChanged(value);
            mEngineSpeedView.setVelocity(value);
            mEngineSpeedTv.setText(String.valueOf(value));
        }
    };

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        if (vId == R.id.back_btn) {
            Intent intent = new Intent(FloatingWindowService.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            stopSelf();
        } else if (vId == R.id.close_btn) {
            mFloatingWindowHelper.clear();
            stopSelf();
            Log.e(TAG, "onClick: 停止浮窗服务");
        }
    }

    private final SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        int currentTempValue;

        int currentWindLevelValue;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            int seekBarId = seekBar.getId();
            if (seekBarId == R.id.temprature_seekbar) {
                currentTempValue = progress + BYDAutoAcDevice.AC_TEMP_IN_CELSIUS_MIN;
                mCurrentTempTv.setText(String.valueOf(currentTempValue));
            } else if (seekBarId == R.id.wind_level_seekbar) {
                currentWindLevelValue = progress;
                mCurrentWindLevelTv.setText(String.valueOf(progress));
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            int seekBarId = seekBar.getId();
            if (seekBarId == R.id.temprature_seekbar) {
                mBydAutoAcDevice.setAcTemperature(
                        BYDAutoAcDevice.AC_TEMPERATURE_MAIN_DEPUTY,
                        currentTempValue,
                        BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY,
                        BYDAutoAcDevice.AC_TEMPERATURE_UNIT_OC
                );
                mCurrentTempTv.setText(String.valueOf(currentTempValue));
            } else if (seekBarId == R.id.wind_level_seekbar) {
                mCurrentWindLevelTv.setText(String.valueOf(currentWindLevelValue));
                if (currentWindLevelValue == 0) {
                    mBydAutoAcDevice.stop(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY);
                } else {
                    mBydAutoAcDevice.setAcWindLevel(BYDAutoAcDevice.AC_CTRL_SOURCE_UI_KEY, currentWindLevelValue);
                }
            }
        }
    };
}