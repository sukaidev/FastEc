package com.sukaidev.latte.ec.main.sort.list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.sukaidev.latte.ec.R;
import com.sukaidev.latte_core.delegates.LatteDelegate;

/**
 * Created by sukaidev on 2019/05/04.
 */
public class VerticalListDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
