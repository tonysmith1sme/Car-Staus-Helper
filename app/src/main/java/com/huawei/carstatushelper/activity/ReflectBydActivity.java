package com.huawei.carstatushelper.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.bydauto.BYDAutoConstants;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.bigdata.AbsBYDAutoBigDataListener;
import android.hardware.bydauto.bigdata.BYDAutoBigDataDevice;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.speed.AbsBYDAutoSpeedListener;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.huawei.carstatushelper.databinding.ActivityReflectBydBinding;
import com.huawei.carstatushelper.databinding.LayoutReflectItemBinding;
import com.huawei.carstatushelper.util.StringToHex;
import com.socks.library.KLog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反射比亚迪接口测试界面
 * <p>
 * 调用比亚迪最新车机中的sdk方法：
 * 方法1：
 * <p>
 * 方法2：
 */
public class ReflectBydActivity extends BackEnableBaseActivity {
    public static final String BYDAUTO_BIGDATA_GET = "android.permission.BYDAUTO_BIGDATA_GET";
    public static final String BYDAUTO_POWER_GET = "android.permission.BYDAUTO_POWER_GET";

    private BYDAutoBigDataDevice autoBigDataDevice;
    //    private TextView respBigdataTv;
    private List<DataBean> mDataBeanList;
    private ItemAdapter itemAdapter;
    private RecyclerView recyclerView;
    //    private BYDAutoPowerDevice autoPowerDevice;

    @Override
    public CharSequence setPageTitle() {
        return "测试";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityReflectBydBinding binding = ActivityReflectBydBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        respBigdataTv = binding.respBigdataTv;
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDataBeanList = new ArrayList<>();
        itemAdapter = new ItemAdapter(mDataBeanList);
        recyclerView.setAdapter(itemAdapter);

        binding.powerOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BYDAutoBodyworkDevice bodyworkDevice = BYDAutoBodyworkDevice.getInstance(ReflectBydActivity.this);
                    Class<?> clz = Class.forName("android.hardware.bydauto.AbsBYDAutoDevice");
                    Method set = clz.getDeclaredMethod("set", int.class, int.class, int.class);
                    set.setAccessible(true);
                    set.invoke(bodyworkDevice, BYDAutoConstants.BYDAUTO_DEVICE_BODYWORK, BYDAutoFeatureIds.BODYWORK_POWER_LEVEL, BYDAutoBodyworkDevice.BODYWORK_POWER_LEVEL_ACC);
                    Toast.makeText(ReflectBydActivity.this, "Acc执行成功", Toast.LENGTH_SHORT).show();
                } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    Toast.makeText(ReflectBydActivity.this, "error:" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.rearMirrorBtn.setOnClickListener(new View.OnClickListener() {
            public static final int REAR_VIEW_MIRROR_FOLD = 1;
            public static final int REAR_VIEW_MIRROR_UNFOLD = 2;

            @Override
            public void onClick(View view) {
                try {
                    Class<?> clz = Class.forName("android.hardware.bydauto.doormirror.BYDAutoRearViewMirrorDevice");
                    Method getInstance = clz.getMethod("getInstance", Context.class);
                    Object obj = getInstance.invoke(clz, ReflectBydActivity.this);

                    Class<?> aClass = Class.forName("android.hardware.bydauto.AbsBYDAutoDevice");
                    Method set = aClass.getDeclaredMethod("set", int.class, int.class, int.class);
                    set.setAccessible(true);
                    set.invoke(obj, 1047, REAR_VIEW_MIRROR_FOLD, REAR_VIEW_MIRROR_FOLD);
                    Toast.makeText(ReflectBydActivity.this, "success", Toast.LENGTH_SHORT).show();
                } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    Toast.makeText(ReflectBydActivity.this, "error:" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ReflectBydActivity.this, BYDAUTO_BIGDATA_GET) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ReflectBydActivity.this, new String[]{BYDAUTO_BIGDATA_GET}, 0);
                    return;
                }
//                if (ContextCompat.checkSelfPermission(ReflectBydActivity.this, BYDAUTO_POWER_GET) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(ReflectBydActivity.this, new String[]{BYDAUTO_POWER_GET}, 0);
//                    return;
//                }
                autoBigDataDevice = BYDAutoBigDataDevice.getInstance(ReflectBydActivity.this);
//                autoPowerDevice = BYDAutoPowerDevice.getInstance(ReflectBydActivity.this);
                autoBigDataDevice.registerListener(bigDataListener);
                Toast.makeText(ReflectBydActivity.this, "register big_data success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (autoBigDataDevice != null) {
            autoBigDataDevice.registerListener(bigDataListener);
        }
//        if (autoPowerDevice != null) {
//            autoPowerDevice.registerListener(powerListener);
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (autoBigDataDevice != null) {
            autoBigDataDevice.unregisterListener(bigDataListener);
        }
//        if (autoPowerDevice != null) {
//            autoPowerDevice.unregisterListener(powerListener);
//        }
    }

    public static class DataBean {
        int event_type;
        int value_int;
        float value_float;
        double value_double;
        int[] value_array_int;
        float[] value_array_float;
        byte[] value_array_byte;

        public DataBean(int event_type, int value_int, float value_float, double value_double, int[] value_array_int, float[] value_array_float, byte[] value_array_byte) {
            this.event_type = event_type;
            this.value_int = value_int;
            this.value_float = value_float;
            this.value_double = value_double;
            this.value_array_int = value_array_int;
            this.value_array_float = value_array_float;
            this.value_array_byte = value_array_byte;
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        LayoutReflectItemBinding binding;

        public ItemViewHolder(LayoutReflectItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private final List<DataBean> dataBeanList;

        public ItemAdapter(List<DataBean> dataBeanList) {
            this.dataBeanList = dataBeanList;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutReflectItemBinding binding = LayoutReflectItemBinding.inflate(getLayoutInflater());
            return new ItemViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            DataBean dataBean = dataBeanList.get(position);

            holder.binding.eventType.setText("" + dataBean.event_type);

            holder.binding.valueInt.setText("" + dataBean.value_int);
            holder.binding.valueFloat.setText("" + dataBean.value_float);
            holder.binding.valueDouble.setText("" + dataBean.value_double);

            String str1 = dataBean.value_array_int == null ? "null" : Arrays.toString(dataBean.value_array_int);
            holder.binding.valueArrayInt.setText("" + str1);

            String str2 = dataBean.value_array_float == null ? "null" : Arrays.toString(dataBean.value_array_float);
            holder.binding.valueArrayFloat.setText("" + str2);

            String str3 = dataBean.value_array_byte == null ? "null" : StringToHex.bytesToHexString(dataBean.value_array_byte);
            holder.binding.valueArrayByte.setText("" + str3);
        }

        @Override
        public int getItemCount() {
            return dataBeanList.size();
        }
    }

    private AbsBYDAutoBigDataListener bigDataListener = new AbsBYDAutoBigDataListener() {
//        @Override
//        public void onNeedRendRegisterTable(int value) {
//            super.onNeedRendRegisterTable(value);
//        }
//
//        @Override
//        public void onWholeFrameDataChanged(byte[] data) {
//            super.onWholeFrameDataChanged(data);
//            String hex = StringToHex.bytesToHexString(data);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    respBigdataTv.setText(hex + "");
//                }
//            });
//        }

        @Override
        public void onDataEventChanged(int a, BYDAutoEventValue value) {
            super.onDataEventChanged(a, value);
            int data1 = value.intValue;
            float data2 = value.floatValue;
            double data3 = value.doubleValue;
            int[] data4 = value.intArrayValue;
            float[] data5 = value.floatArrayValue;
            byte[] data6 = value.bufferDataValue;
            DataBean dataBean = new DataBean(a, data1, data2, data3, data4, data5, data6);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mDataBeanList.add(dataBean);
                    itemAdapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onError(int a, String error) {
            super.onError(a, error);
            Toast.makeText(ReflectBydActivity.this, "code = " + a + ", msg = " + error, Toast.LENGTH_SHORT).show();
        }
    };

//    private AbsBYDAutoPowerListener powerListener = new AbsBYDAutoPowerListener() {
//        @Override
//        public void onPowerCtlStatusChanged(int r5, int r6) {
//            super.onPowerCtlStatusChanged(r5, r6);
//        }
//
//        @Override
//        public void onTpDisplayControllerChanged(int event_type, int value) {
//            super.onTpDisplayControllerChanged(event_type, value);
//        }
//
//        @Override
//        public void onTftBacklightChanged(int value) {
//            super.onTftBacklightChanged(value);
//        }
//    };

    private void testFun2() {
        BYDAutoSpeedDevice speedDevice = BYDAutoSpeedDevice.getInstance(this);
        double currentSpeed = speedDevice.getCurrentSpeed();
        KLog.e("speed = " + currentSpeed);
        AbsBYDAutoSpeedListener listener = new AbsBYDAutoSpeedListener() {
            @Override
            public void onSpeedChanged(double value) {
                super.onSpeedChanged(value);
                KLog.e("speed = " + value);
            }
        };
        speedDevice.registerListener(listener);
    }

    /**
     * byd模拟器反射接口需要的东西：（示例：BYDAutoSpeedDevice）
     * 1.AndroidManifest.xml 中声明权限，如果不声明，在 requestPermissions 不会弹权限请求窗口
     * <uses-permission android:name="android.permission.BYDAUTO_SPEED_GET" />
     * 2.需要使用 platform.keystore 签名
     * 3.registerListener可以不实现 IBYDAutoListener，但不能将 abstract class 改为 interface
     * 4.registerListener 注册的抽象类包名必须与 device 一致
     * 5.registerListener 注册的抽象类可以只写代码中需要的方法
     */
    private void testFun() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.BYDAUTO_SPEED_GET") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.BYDAUTO_SPEED_GET"}, 0);
            KLog.e("testFun: 开始请求权限");
            return;
        }
        ProgressDialog dialog = new ProgressDialog(ReflectBydActivity.this);
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class<?> clz = Class.forName("android.hardware.bydauto.speed.BYDAutoSpeedDevice");
                    Method method = clz.getMethod("getInstance", Context.class);
                    Object instance = method.invoke(null, getApplicationContext());

                    Method getCurrentSpeed = clz.getMethod("getCurrentSpeed", null);
                    Object speed = getCurrentSpeed.invoke(instance, null);
                    System.out.println();
                    KLog.e("speed =  " + speed);

                    Method registerListener = clz.getMethod("registerListener", AbsBYDAutoSpeedListener.class);
                    AbsBYDAutoSpeedListener listener = new AbsBYDAutoSpeedListener() {
                        @Override
                        public void onSpeedChanged(double value) {
//                            super.onSpeedChanged(value);
                            KLog.e("onSpeedChanged: " + value);
                        }
                    };
                    registerListener.invoke(instance, listener);
                    KLog.e("registerListener: ");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    KLog.e("ClassNotFoundException: " + e.toString());
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    KLog.e("NoSuchMethodException: " + e.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    KLog.e("IllegalAccessException: " + e.toString());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    KLog.e("InvocationTargetException: " + e.getTargetException().toString());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                });
            }
        }).start();
    }
}