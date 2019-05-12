package com.sukaidev.latte.ec.main.personal.settings;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.sukaidev.latte.ec.main.personal.list.ListBean;
import com.sukaidev.latte_core.delegates.LatteDelegate;

/**
 * Created by sukaidev on 2019/05/12.
 */
public class SettingsClickListener extends SimpleClickListener {

    private final LatteDelegate mDelegate;

    public SettingsClickListener(LatteDelegate delegate) {
        this.mDelegate = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id) {
            case 1:
                // 这是消息推送的开关
                break;
            case 2:
                // 这是关于页面
                mDelegate.getSupportDelegate().start(bean.getDelegate());
                break;
            default:
                break;
        }
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
