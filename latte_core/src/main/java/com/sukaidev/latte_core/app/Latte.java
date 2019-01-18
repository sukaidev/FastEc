package com.sukaidev.latte_core.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by sukaidev on 2019/01/16.
 */
public final class Latte {

    public static Configurator init(Context context){
        getConfiguratons().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static WeakHashMap<String,Object> getConfiguratons(){
        return Configurator.getInstance().getLatteConfigs();
    }
}
