package com.sukaidev.fastec.example;


import android.os.Bundle;

import com.sukaidev.latte.ec.laucher.LauncherDelegate;
import com.sukaidev.latte_core.activities.ProxyActivity;
import com.sukaidev.latte_core.delegates.LatteDelegate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;


public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
