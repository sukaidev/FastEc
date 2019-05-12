package com.sukaidev.latte.ec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.sukaidev.latte.ec.R;
import com.sukaidev.latte.ec.R2;
import com.sukaidev.latte_core.delegates.LatteDelegate;
import com.sukaidev.latte_core.ui.callback.CallbackManager;
import com.sukaidev.latte_core.ui.callback.CallbackType;
import com.sukaidev.latte_core.ui.callback.IGlobalCallback;
import com.sukaidev.latte_core.widget.AutoPhotoLayout;
import com.sukaidev.latte_core.widget.StarLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sukaidev on 2019/05/12.
 * 订单评论页面.
 */
public class OrderCommentDelegate extends LatteDelegate {

    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout = null;

    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout = null;

    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit() {
        Toast.makeText(getContext(), "评分："+mStarLayout.getStarCount(), Toast.LENGTH_LONG).show();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(@Nullable Uri args) {
                        mAutoPhotoLayout.onCropTarget(args);
                    }
                });
    }
}
