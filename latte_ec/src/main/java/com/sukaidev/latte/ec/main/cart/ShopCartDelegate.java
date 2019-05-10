package com.sukaidev.latte.ec.main.cart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joanzapata.iconify.widget.IconTextView;
import com.sukaidev.latte.ec.R;
import com.sukaidev.latte.ec.R2;
import com.sukaidev.latte_core.delegates.bottom.BottomItemDelegate;
import com.sukaidev.latte_core.net.RestClient;
import com.sukaidev.latte_core.net.callback.ISuccess;
import com.sukaidev.latte_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sukaidev on 2019/05/05.
 */
public class ShopCartDelegate extends BottomItemDelegate implements ISuccess, ICartItemListener {

    private ShopCartAdapter mAdapter = null;
    // 购物车的Item总数量
    private int mCurrentCount = 0;
    // 要删除的Item的总数量
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;


    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.stub_shop_cart_empty)
    ViewStub mStubNoItem = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;

    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectAll.getTag();
        if (tag == 0) {
            //noinspection ConstantConditions
            mIconSelectAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            // 更新RecyclerView的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_select)
    void onClickRemoveSelectedItem() {
        final List<MultipleItemEntity> data = mAdapter.getData();
        // 要删除的数据
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {
                deleteEntities.add(entity);
            }
        }
        mTotalCount = deleteEntities.size();
        mCurrentCount = mAdapter.getItemCount();
        for (MultipleItemEntity entity : deleteEntities) {
            int removePosition;
            final int entityPosition = entity.getField(ShopCartItemFields.POSITION);
            if (entityPosition > mCurrentCount - 1) {
                removePosition = entityPosition - (mTotalCount - mCurrentCount);
            } else {
                removePosition = entityPosition;
            }
            if (removePosition <= mAdapter.getItemCount()) {
                mAdapter.remove(removePosition);
                mCurrentCount = mAdapter.getItemCount();
                // 更新数据
                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
            }
        }
        // 更新每个entity的position
        int i = 0;
        for (MultipleItemEntity entity : data) {
            entity.setField(ShopCartItemFields.POSITION, i++);
        }
        checkItemCount();
    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        checkItemCount();
    }

    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            if (mRecyclerView.getVisibility() == View.GONE) {
                final View stubView = mStubNoItem.inflate();
                final AppCompatTextView tvToPurchase = stubView.findViewById(R.id.tv_stub_to_purchase);
                tvToPurchase.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "去购物吧！", Toast.LENGTH_SHORT).show();
                    }
                });
                mRecyclerView.setVisibility(View.GONE);
            }
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectAll.setTag(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("shop_cart.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final ArrayList<MultipleItemEntity> data = new ShopCartDataConverter()
                .setJsonData(response)
                .convert();
        mAdapter = new ShopCartAdapter(data);
        mAdapter.setCartItemListener(this);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        checkTotalPrice();
        checkItemCount();
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        checkTotalPrice();
    }

    @SuppressLint("SetTextI18n")
    private void checkTotalPrice() {
        mTotalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText("￥" + mTotalPrice);
    }
}
