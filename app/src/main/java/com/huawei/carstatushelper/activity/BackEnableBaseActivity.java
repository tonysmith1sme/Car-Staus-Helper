package com.huawei.carstatushelper.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

public abstract class BackEnableBaseActivity extends BaseActivity {

    public abstract CharSequence setPageTitle();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            CharSequence charSequence = setPageTitle();
            if (charSequence != null) {
                if (!charSequence.toString().isEmpty()) {
                    supportActionBar.setTitle(charSequence);
                }
            }
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
