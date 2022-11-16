package com.huawei.carstatushelper.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.databinding.ActivityReflectTestBinding;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectTestActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private EditText clzNameEt;
    private EditText methodNameEt;
    private TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityReflectTestBinding binding = ActivityReflectTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        clzNameEt = binding.clzNameEt;
        methodNameEt = binding.methodNameEt;
        resultTv = binding.resultTv;
        binding.testBtn.setOnClickListener(this);
        binding.constructorTypeRg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.test_btn) {
            /**
             * 测试用例：
             * String clzName = "android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice"
             * String methodName = "getAutoVIN"
             *
             * String clzName = "android.hardware.bydauto.AbsBYDAutoDevice"
             * String methodName = "get"
             *
             * String clzName = "android.hardware.bydauto.engine.BYDAutoEngineDevice"
             * String methodName = "getEngineSpeed"
             *
             * String clzName = "android.hardware.bydauto.radar.BYDAutoRadarDevice"
             * String methodName = "getAllRadarDistance"
             *
             * android.hardware.bydauto.charging.BYDAutoChargingDevice
             * getChargingRestTime
             */
            String clzName = clzNameEt.getText().toString();
            String methodName = methodNameEt.getText().toString();
            if (clzName.length() == 0 || methodName.length() == 0) {
                return;
            }
            try {
                Class<?> clz = Class.forName(clzName);
                Method method = clz.getDeclaredMethod(methodName);
                method.setAccessible(true);

                if (method.toString().contains(" static ")) {
                    Object ret = method.invoke(null);
                    resultTv.setText("" + ret);
                } else {
                    Object instant = getInstant(clz);
                    if (instant == null) {
                        Toast.makeText(this, "instance is null", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Object ret = method.invoke(instant);
                    int type = getDupleType(ret);
                    if (type == 0) {
                        int[] ints = (int[]) ret;
                        resultTv.setText("" + Arrays.toString(ints));
                    } else if (type == 1) {
                        long[] ints = (long[]) ret;
                        resultTv.setText("" + Arrays.toString(ints));
                    } else if (type == 2) {
                        float[] ints = (float[]) ret;
                        resultTv.setText("" + Arrays.toString(ints));
                    } else if (type == 3) {
                        double[] ints = (double[]) ret;
                        resultTv.setText("" + Arrays.toString(ints));
                    } else if (type == 4) {
                        boolean[] ints = (boolean[]) ret;
                        resultTv.setText("" + Arrays.toString(ints));
                    } else if (type == 5) {
                        String[] ints = (String[]) ret;
                        resultTv.setText("" + Arrays.toString(ints));
                    } else if (type == 6) {
                        Object[] ints = (Object[]) ret;
                        resultTv.setText("" + Arrays.toString(ints));
                    } else {
                        resultTv.setText("" + ret);
                    }
                }
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
                e.printStackTrace();
                Toast.makeText(this, "error:" + e, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private int getDupleType(Object obj) {
        if (obj instanceof int[]) {
            return 0;
        }
        if (obj instanceof long[]) {
            return 1;
        }
        if (obj instanceof float[]) {
            return 2;
        }
        if (obj instanceof double[]) {
            return 3;
        }
        if (obj instanceof boolean[]) {
            return 4;
        }
        if (obj instanceof String[]) {
            return 5;
        }
        if (obj instanceof Object[]) {
            return 6;
        }
        return -1;
    }

    private static final int TYPE_GET_INSTANCE = 0;
    private static final int TYPE_CONTEXT_CONSTRUCTOR = 1;
    private static final int TYPE_ZERO_CONSTRUCTOR = 2;

    public Object getInstant(Class<?> clz) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Object invoke = null;
        if (type == TYPE_GET_INSTANCE) {
            Method method = clz.getDeclaredMethod("getInstance", Context.class);
            method.setAccessible(true);
            invoke = method.invoke(null, this);
        } else if (type == TYPE_CONTEXT_CONSTRUCTOR) {
            Constructor<?> constructor = clz.getDeclaredConstructor(Context.class);
            invoke = constructor.newInstance(this);
        } else if (type == TYPE_ZERO_CONSTRUCTOR) {
            invoke = clz.newInstance();
        }
        return invoke;
    }

    int type;

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.item1_rb) {
            type = TYPE_GET_INSTANCE;
        } else if (i == R.id.item2_rb) {
            type = TYPE_CONTEXT_CONSTRUCTOR;
        } else if (i == R.id.item3_rb) {
            type = TYPE_ZERO_CONSTRUCTOR;
        }
    }
}