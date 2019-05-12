package com.sukaidev.fastec.example;


import android.os.Bundle;
import android.widget.Toast;

import com.sukaidev.latte.ec.laucher.LauncherDelegate;
import com.sukaidev.latte.ec.main.EcBottomDelegate;
import com.sukaidev.latte.ec.main.index.IndexDelegate;
import com.sukaidev.latte.ec.sign.ISignListener;
import com.sukaidev.latte.ec.sign.SignInDelegate;
import com.sukaidev.latte.ec.sign.SignUpDelegate;
import com.sukaidev.latte_core.activities.ProxyActivity;
import com.sukaidev.latte_core.app.Latte;
import com.sukaidev.latte_core.delegates.LatteDelegate;
import com.sukaidev.latte_core.ui.launcher.ILauncherListener;
import com.sukaidev.latte_core.ui.launcher.OnLauncherFinishTag;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import cn.jpush.android.api.JPushInterface;
import qiu.niorgai.StatusBarCompat;


public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new EcBottomDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
        startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功！请登录", Toast.LENGTH_SHORT).show();
        startWithPop(new SignInDelegate());
    }

    @Override
    public void onLauncherFinished(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户已登录", Toast.LENGTH_LONG).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户未登录", Toast.LENGTH_LONG).show();
                // 启动Fragment并且清顶
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
