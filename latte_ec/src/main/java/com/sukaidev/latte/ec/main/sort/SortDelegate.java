package com.sukaidev.latte.ec.main.sort;

import android.os.Bundle;
import android.view.View;

import com.sukaidev.latte.ec.R;
import com.sukaidev.latte.ec.main.sort.content.ContentDelegate;
import com.sukaidev.latte.ec.main.sort.list.VerticalListDelegate;
import com.sukaidev.latte_core.delegates.bottom.BottomItemDelegate;

import androidx.annotation.Nullable;

/**
 * Created by sukaidev on 2019/03/21.
 */
public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_list_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container, listDelegate);
        // 设置右侧第一个分类显示，默认显示分类一
        loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1), false, false);
    }
}
