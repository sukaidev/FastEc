package com.sukaidev.latte_core.delegates.web;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;


/**
 * Created by sukaidev on 2019/05/05.
 */
public class WebViewInitializer {

    public WebView createWebView(WebView webView) {
        WebView.setWebContentsDebuggingEnabled(true);
        // 禁止横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        // 禁止纵向滚动
        webView.setVerticalScrollBarEnabled(false);
        // 允许截图
        webView.setDrawingCacheEnabled(true);
        // 屏蔽长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        // 初始化WebSettings
        final WebSettings settings = webView.getSettings();
        final String ua = settings.getUserAgentString();
        // 设置UserAgent用于App身份识别
        settings.setUserAgentString(ua + "Latte");
        // 隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        // 禁止缩放
        settings.setSupportZoom(false);
        // 文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        // 缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        return webView;
    }

}
