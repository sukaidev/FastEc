package com.sukaidev.latte.ec.main.sort;

import android.os.Bundle;
import android.view.View;

import com.sukaidev.latte.ec.R;
import com.sukaidev.latte_core.delegates.bottom.BottomItemDelegate;

import androidx.annotation.Nullable;

/**
 * Created by sukaidev on 2019/03/21.
 */
public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
