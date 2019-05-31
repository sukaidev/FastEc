package com.sukaidev.fastec.example;

import android.app.Application;

import androidx.annotation.Nullable;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mob.MobSDK;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.sukaidev.fastec.example.event.ShareEvent;
import com.sukaidev.latte.ec.database.DatabaseManager;
import com.sukaidev.latte.ec.icon.FontEcModule;
import com.sukaidev.latte_core.app.Latte;
import com.sukaidev.fastec.example.event.TestEvent;
import com.sukaidev.latte_core.ui.callback.CallbackManager;
import com.sukaidev.latte_core.ui.callback.CallbackType;
import com.sukaidev.latte_core.ui.callback.IGlobalCallback;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by sukaidev on 2019/01/16.
 */
public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("https://www.sukaidev.top/api/FastEC/")
//                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .withWeChatAppId("wx9e44cf1508070533")
                .withWeChatAppSecret("987b5d78c449e1787cfe0ad1e43887a1")
                .withJavaScriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                .withWebEvent("share", new ShareEvent())
                .configure();
        DatabaseManager.getInstance().init(this);

        // 极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        // ShareSDK
        MobSDK.init(this);

        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, args -> {
                    if (JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                        // 开启极光推送
                        JPushInterface.setDebugMode(true);
                        JPushInterface.init(Latte.getApplicationContext());
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, args -> {
                    if (!JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                        // 开启极光推送
                        JPushInterface.stopPush(Latte.getApplicationContext());
                    }
                });
    }
}
