package com.sukaidev.latte_core.ui.recycler;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sukaidev.latte_core.R;

import java.util.List;

/**
 * Created by sukaidev on 2019/05/02.
 */
public class MultipleRecyclerViewAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder> implements BaseQuickAdapter.SpanSizeLookup {


    protected MultipleRecyclerViewAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    public static MultipleRecyclerViewAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclerViewAdapter(data);
    }

    public static MultipleRecyclerViewAdapter create(DataConverter converter) {
        return new MultipleRecyclerViewAdapter(converter.convert());
    }

    private void init() {
        // 设置不同的布局
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        // 设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        // 多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {

    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
        return getData().get(i).getField(MultipleFields.SPAN_SIZE);
    }
}
