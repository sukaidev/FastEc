package com.sukaidev.latte.ec.main.personal;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.sukaidev.latte.ec.R;
import com.sukaidev.latte_core.delegates.bottom.BottomItemDelegate;

/**
 * Created by sukaidev on 2019/05/08.
 */
public class PersonalDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
