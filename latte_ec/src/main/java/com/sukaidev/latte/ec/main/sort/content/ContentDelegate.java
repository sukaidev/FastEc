package com.sukaidev.latte.ec.main.sort.content;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.sukaidev.latte.ec.R;
import com.sukaidev.latte.ec.R2;
import com.sukaidev.latte_core.delegates.LatteDelegate;
import com.sukaidev.latte_core.net.RestClient;
import com.sukaidev.latte_core.net.callback.ISuccess;

import java.util.List;

import butterknife.BindView;

/**
 * Created by sukaidev on 2019/05/04.
 */
public class ContentDelegate extends LatteDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData = null;

    @BindView(R2.id.rv_content_list)
    RecyclerView mRecyclerView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    private void initData() {
        RestClient.builder()
                .url("sort_content_list.php?contentId=" + mContentId)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mData = new SectionDataConverter().convert(response);
                        final SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content, R.layout.item_section_header, mData);
                        mRecyclerView.setAdapter(sectionAdapter);
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }
}
