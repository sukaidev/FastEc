package com.sukaidev.latte.ec.detail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.alibaba.fastjson.JSONObject;
import com.sukaidev.latte.ec.R;
import com.sukaidev.latte.ec.R2;
import com.sukaidev.latte_core.delegates.LatteDelegate;

import butterknife.BindView;

/**
 * Created by sukaidev on 2019/05/15.
 */
public class GoodsInfoDelegate extends LatteDelegate {

    @BindView(R2.id.tv_goods_info_title)
    AppCompatTextView mTvGoodsTitle = null;
    @BindView(R2.id.tv_goods_info_desc)
    AppCompatTextView mTvGoodsDesc = null;
    @BindView(R2.id.tv_goods_info_price)
    AppCompatTextView mTvGoodsPrice = null;

    private static final String ARG_GOODS_DATA = "ARG_GOODS_DATA";
    private JSONObject mData = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            final String goodsData = args.getString("ARG_GOODS_DATA");
            mData = JSONObject.parseObject(goodsData);
        } else {
            throw new NullPointerException("Goods id can't be null!");
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_info;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        final String name = mData.getString("name");
        final String desc = mData.getString("description");
        final double price = mData.getDouble("price");

        mTvGoodsTitle.setText(name);
        mTvGoodsDesc.setText(desc);
        mTvGoodsPrice.setText(String.valueOf(price));

    }

    static GoodsDetailDelegate create(@NonNull String goodsId) {
        final Bundle args = new Bundle();
        args.putString(ARG_GOODS_DATA, goodsId);
        final GoodsDetailDelegate detailDelegate = new GoodsDetailDelegate();
        detailDelegate.setArguments(args);
        return detailDelegate;
    }
}
