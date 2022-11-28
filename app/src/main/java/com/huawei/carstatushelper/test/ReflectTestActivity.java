package com.huawei.carstatushelper.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.carstatushelper.R;
import com.huawei.carstatushelper.databinding.ActivityReflectTestBinding;
import com.huawei.carstatushelper.util.ReflectHelper;

public class ReflectTestActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private EditText clzNameEt;
    private EditText methodNameEt;
    private EditText methodArgTypeEt;
    private EditText methodArgValueEt;
    private TextView testResultTv;
    private int newInstanceType = 0;

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
        Button testBtn = binding.testBtn;
        testResultTv = binding.testResultTv;

        testBtn.setOnClickListener(this);

//        ReflectHelper.testHelper(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.test_btn) {
            String clzName = clzNameEt.getText().toString();
            String methodName = methodNameEt.getText().toString();
            String methodArgType = methodArgTypeEt.getText().toString();
            String methodArgValue = methodArgValueEt.getText().toString();

            String invoke = ReflectHelper.invoke(this, newInstanceType, clzName, methodName, methodArgType, methodArgValue);
            testResultTv.setText(invoke);
            Toast.makeText(this, "result: " + invoke, Toast.LENGTH_SHORT).show();
        }
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