package com.sukaidev.latte.ec.main.personal.settings;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.sukaidev.latte.ec.R;
import com.sukaidev.latte_core.delegates.LatteDelegate;

/**
 * Created by sukaidev on 2019/05/10.
 */
public class NameDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
