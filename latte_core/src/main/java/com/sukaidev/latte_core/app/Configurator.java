package com.sukaidev.latte_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by sukaidev on 2019/01/16.
 * 配置构造类.
 */
public class Configurator {

    // 存放配置信息
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    // 存放图标信息
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    // 拦截器
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }

    /**
     * 配置ApiHost
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }

    /**
     * 初始化Icon
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 配置Icon
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 配置拦截器
     * @param interceptor
     * @return
     */
    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }

    /**
     * 检查配置，保证配置完整
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigKeys> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
