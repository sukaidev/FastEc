package com.sukaidev.latte_core.wechat;

import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by sukaidev on 2019/03/20.
 */
public abstract class BaseWXActivity extends AppCompatActivity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这个必须写在onCreate方法中
        LatteWeChat.getInstance().getWXAPI().handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        LatteWeChat.getInstance().getWXAPI().handleIntent(getIntent(), this);
    }
}
