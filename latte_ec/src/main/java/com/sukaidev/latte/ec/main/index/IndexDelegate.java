package com.sukaidev.latte.ec.main.index;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.sukaidev.latte.ec.R;
import com.sukaidev.latte.ec.R2;
import com.sukaidev.latte.ec.main.EcBottomDelegate;
import com.sukaidev.latte_core.delegates.bottom.BottomItemDelegate;
import com.sukaidev.latte_core.net.RestClient;
import com.sukaidev.latte_core.net.callback.ISuccess;
import com.sukaidev.latte_core.ui.callback.CallbackManager;
import com.sukaidev.latte_core.ui.callback.CallbackType;
import com.sukaidev.latte_core.ui.callback.IGlobalCallback;
import com.sukaidev.latte_core.ui.recycler.BaseDecoration;
import com.sukaidev.latte_core.ui.recycler.MultipleFields;
import com.sukaidev.latte_core.ui.recycler.MultipleItemEntity;
import com.sukaidev.latte_core.ui.refresh.RefreshHandler;
import com.sukaidev.latte_core.ui.scanner.ScannerDelegate;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sukaidev on 2019/03/21.
 */
public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;

    @OnClick(R2.id.icon_index_scan)
    void onClickScanQrCode() {
        startScanWithCheck(getParentDelegate());
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(BaseDecoration.create(getResources().getColor(R.color.app_background), 5));
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index.php");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_SCAN, new IGlobalCallback<String>() {
                    @Override
                    public void executeCallback(@Nullable String args) {
                        Toast.makeText(getContext(), args, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
