package com.sukaidev.latte_core.ui.refresh;

import com.sukaidev.latte_core.app.Latte;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Created by sukaidev on 2019/03/21.
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout refresh_layout) {
        this.REFRESH_LAYOUT = refresh_layout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }
}
