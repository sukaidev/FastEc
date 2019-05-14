package com.sukaidev.latte_core.ui.recycler;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sukaidev.latte_core.R;
import com.sukaidev.latte_core.ui.bannner.BannerCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sukaidev on 2019/05/02.
 * 通用Adapter.
 */
public class MultipleRecyclerAdapter
        extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup, OnItemClickListener {

    // 确保初始化一次banner，防止重复加载
    private boolean mIsInitBanner = false;

    private final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();

    protected MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConverter converter) {
        return new MultipleRecyclerAdapter(converter.convert());
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
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        final String text;
        final String imageUrl;
        final AppCompatImageView imageView;
        final ArrayList<String> bannerImages;
        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = entity.getField(MultipleFields.TEXT);
                holder.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                imageView = holder.getView(R.id.img_single);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(OPTIONS)
                        .into(imageView);
                break;
            case ItemType.TEXT_IMAGE:
                text = entity.getField(MultipleFields.TEXT);
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                imageView = holder.getView(R.id.img_multiple);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(OPTIONS)
                        .into(imageView);
                holder.setText(R.id.tv_multiple_, text);
                break;
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    bannerImages = entity.getField(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }
                break;
            default:
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

    @Override
    public void onItemClick(int position) {

    }
}
