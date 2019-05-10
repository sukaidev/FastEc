package com.sukaidev.latte.ec.main.personal;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sukaidev.latte.ec.R;
import com.sukaidev.latte.ec.R2;
import com.sukaidev.latte.ec.main.personal.list.ListAdapter;
import com.sukaidev.latte.ec.main.personal.list.ListBean;
import com.sukaidev.latte.ec.main.personal.list.ListItemType;
import com.sukaidev.latte_core.delegates.bottom.BottomItemDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by sukaidev on 2019/05/08.
 */
public class PersonalDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setText("收货地址")
                .build();

        ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        // 设置RecyclerVIew
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
    }
}
