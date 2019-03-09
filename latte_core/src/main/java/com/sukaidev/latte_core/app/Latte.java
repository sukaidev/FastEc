package com.sukaidev.latte_core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by sukaidev on 2019/01/16.
 */
public final class Latte {

    public static Configurator init(Context context){
        getConfiguration().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
}

    public static HashMap<Object,Object> getConfiguration(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext(){
        return (Context) Latte.getConfiguration().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
