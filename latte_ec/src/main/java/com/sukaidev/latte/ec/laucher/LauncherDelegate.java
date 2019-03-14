package com.sukaidev.latte.ec.laucher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.sukaidev.latte.ec.R;
import com.sukaidev.latte.ec.R2;
import com.sukaidev.latte_core.app.AccountManager;
import com.sukaidev.latte_core.app.IUserChecker;
import com.sukaidev.latte_core.delegates.LatteDelegate;
import com.sukaidev.latte_core.ui.launcher.ILauncherListener;
import com.sukaidev.latte_core.ui.launcher.OnLauncherFinishTag;
import com.sukaidev.latte_core.ui.launcher.ScrollLauncherTag;
import com.sukaidev.latte_core.util.storage.LattePreference;
import com.sukaidev.latte_core.util.timer.BaseTimerTask;
import com.sukaidev.latte_core.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sukaidev on 2019/03/10.
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener {

    private Timer mTimer = null;

    private int mCount = 5;

    private ILauncherListener mILauncherListener = null;

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_laucher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    // 判断是否显示滑动启动页
    private void checkIsShowScroll(){
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else {
            // 检查用户是否登录了APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null){
                        mILauncherListener.onLauncherFinished(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null){
                        mILauncherListener.onLauncherFinished(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
