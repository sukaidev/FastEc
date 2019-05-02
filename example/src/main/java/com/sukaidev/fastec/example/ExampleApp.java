package com.sukaidev.fastec.example;

import android.app.Application;
import android.os.Debug;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.sukaidev.latte.ec.database.DatabaseManager;
import com.sukaidev.latte.ec.icon.FontEcModule;
import com.sukaidev.latte_core.app.Latte;
import com.sukaidev.latte_core.net.interceptors.DebugInterceptor;



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
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .configure();
        DatabaseManager.getInstance().init(this);
    }
}
