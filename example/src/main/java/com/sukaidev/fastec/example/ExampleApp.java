package com.sukaidev.fastec.example;

import android.app.Application;
import android.os.Debug;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.sukaidev.latte_core.app.Latte;
import com.sukaidev.latte_core.net.interceptors.DebugInterceptor;

/**
 * Created by sukaidev on 2019/01/16.
 */
public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withApiHost("http://10.0.2.2")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}
