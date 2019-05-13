package com.sukaidev.latte.ec.main.personal.order;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by sukaidev on 2019/05/12.
 */
public class OrderListCheckListener extends SimpleClickListener {

    private final OrderListDelegate mDelegate;

    OrderListCheckListener(OrderListDelegate delegate) {
        this.mDelegate = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mDelegate.getSupportDelegate().start(new OrderCommentDelegate());
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
