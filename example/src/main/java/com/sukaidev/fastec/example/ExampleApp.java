package com.sukaidev.fastec.example;

import android.app.Application;

import com.sukaidev.latte_core.app.Latte;

/**
 * Created by sukaidev on 2019/01/16.
 */
public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1")
                .configure();
    }
}
