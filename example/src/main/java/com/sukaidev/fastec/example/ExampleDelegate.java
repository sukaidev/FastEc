package com.sukaidev.fastec.example;

import android.os.Bundle;

import android.view.View;

import com.sukaidev.latte_core.delegates.LatteDelegate;

import androidx.annotation.Nullable;

/**
 * Created by sukaidev on 2019/01/18.
 */
public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
