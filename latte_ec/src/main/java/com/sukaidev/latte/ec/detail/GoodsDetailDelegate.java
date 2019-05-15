package com.sukaidev.latte.ec.detail;

import android.animation.Animator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.joanzapata.iconify.widget.IconTextView;
import com.sukaidev.latte.ec.R;
import com.sukaidev.latte.ec.R2;
import com.sukaidev.latte_core.delegates.LatteDelegate;
import com.sukaidev.latte_core.net.RestClient;
import com.sukaidev.latte_core.net.callback.ISuccess;
import com.sukaidev.latte_core.ui.animation.BezierAnimation;
import com.sukaidev.latte_core.ui.animation.BezierUtil;
import com.sukaidev.latte_core.ui.bannner.HolderCreator;
import com.sukaidev.latte_core.util.log.LatteLogger;
import com.sukaidev.latte_core.widget.CircleTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by sukaidev on 2019/05/04.
 */
public class GoodsDetailDelegate extends LatteDelegate implements AppBarLayout.OnOffsetChangedListener, BezierUtil.AnimationListener {

    private static final String ARG_GOODS_ID = "ARG_GOODS_ID";

    private int mGoodsId = 0;

    private String mGoodsThumbUrl = null;
    private int mShopCount = 0;

    @BindView(R2.id.goods_detail_toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.tab_layout)
    TabLayout mTabLayout = null;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager = null;
    @BindView(R2.id.detail_banner)
    ConvenientBanner<String> mBanner = null;
    @BindView(R2.id.collapsing_toolbar_detail)
    CollapsingToolbarLayout mCollapsingToolbarLayout = null;
    @BindView(R2.id.app_bar_detail)
    AppBarLayout mAppBar = null;

    @BindView(R2.id.icon_goods_back)
    IconTextView mIConGoodsBack = null;
    @BindView(R2.id.tv_detail_title_text)
    AppCompatTextView mTvTitle = null;

    // 底部
    @BindView(R2.id.icon_favor)
    IconTextView mIconFavor = null;
    @BindView(R2.id.tv_shopping_cart_amount)
    CircleTextView mTvAmount = null;
    @BindView(R2.id.rl_add_shop_cart)
    RelativeLayout mRlAddShopCart = null;
    @BindView(R2.id.icon_shop_cart)
    IconTextView mIconShopCart = null;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate()
            .override(100, 100);

    @OnClick(R2.id.rl_add_shop_cart)
    void onClickAddShopCart() {
        final CircleImageView animImg = new CircleImageView(getContext());
        Glide.with(this)
                .load(mGoodsThumbUrl)
                .apply(OPTIONS)
                .into(animImg);

        BezierAnimation.addCart(this, mRlAddShopCart, mIconShopCart, animImg, this);
    }

    private void setShopCartCount(JSONObject data) {
        mGoodsThumbUrl = data.getString("thumb");
        if (mShopCount == 0) {
            mTvAmount.setVisibility(View.GONE);
        }
    }

    public static GoodsDetailDelegate create(@NonNull int goodsId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID, goodsId);
        final GoodsDetailDelegate detailDelegate = new GoodsDetailDelegate();
        detailDelegate.setArguments(args);
        return detailDelegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = this.getArguments();
        if (args != null) {
            mGoodsId = args.getInt(ARG_GOODS_ID);
            Toast.makeText(getContext(), "商品Id" + mGoodsId, Toast.LENGTH_SHORT).show();
        } else {
            throw new NullPointerException("Goods id can't be null!");
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mCollapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.app_main));
        mAppBar.addOnOffsetChangedListener(this);
        mTvAmount.setCircleBackground(Color.RED);
        initData();
        initTabLayout();
    }

    private void initTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.app_main));
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        mTabLayout.setBackgroundColor(Color.WHITE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * 得到商品信息，初始化轮播图与标题文字.
     */
    private void initData() {
        RestClient.builder()
                .url("goods_detail_data_1.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject data = JSONObject.parseObject(response).getJSONObject("data");
                        initBanner(data);
                        initGoodsInfo(data);
                        initPager(data);
                        setShopCartCount(data);
                    }
                })
                .build()
                .get();
    }

    private void initPager(JSONObject data) {
        final PagerAdapter adapter = new TabPagerAdapter(getFragmentManager(), data);
        mViewPager.setAdapter(adapter);
    }

    private void initGoodsInfo(JSONObject data) {
        final String goodsData = data.toJSONString();
        getSupportDelegate().loadRootFragment(R.id.frame_goods_info, GoodsInfoDelegate.create(goodsData));
    }

    private void initBanner(JSONObject data) {
        final JSONArray banners = data.getJSONArray("banner");
        final List<String> images = new ArrayList<>();
        final int size = banners.size();
        for (int i = 0; i < size; i++) {
            images.add(banners.getString(i));
        }
        mBanner
                .setPages(new HolderCreator(), images) // 设置资源
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus}) // 设置指示器
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL) // 设置指示器位置
                .setPageTransformer(new DefaultTransformer()) // 设置滑动动画
                .startTurning(3000) // 设置滑动间隙
                .setCanLoop(true); // 设置循环
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

    }

    @Override
    public void onAnimationEnd() {
        YoYo.with(new ScaleUpAnimation())
                .duration(500)
                .playOn(mIconShopCart);
        mShopCount++;
        mTvAmount.setVisibility(View.VISIBLE);
        mTvAmount.setText(String.valueOf(mShopCount));
        // 向服务器更新数据
        RestClient.builder()
                .url("")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        mTvAmount.setVisibility(View.VISIBLE);
//                        mTvAmount.setText(String.valueOf(mShopCount));
                    }
                })
                .params("count", mShopCount)
                .build()
                .post();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
