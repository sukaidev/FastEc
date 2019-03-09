package com.sukaidev.latte_core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by sukaidev on 2019/01/16.
 */
public final class Latte {

    public static Configurator init(Context context){
        getConfiguratons().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
}

    public static HashMap<String,Object> getConfiguratons(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext(){
        return (Context) Latte.getConfiguratons().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
