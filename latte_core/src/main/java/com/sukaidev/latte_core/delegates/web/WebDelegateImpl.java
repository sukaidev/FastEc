package com.sukaidev.latte_core.delegates.web;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.sukaidev.latte_core.delegates.web.chromeclient.WebChromeClientImpl;
import com.sukaidev.latte_core.delegates.web.client.WebViewClientImpl;
import com.sukaidev.latte_core.delegates.web.route.RouteKeys;
import com.sukaidev.latte_core.delegates.web.route.Router;

/**
 * Created by sukaidev on 2019/05/05.
 */
public class WebDelegateImpl extends WebDelegate implements IWebViewInitializer {

    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }


    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) {
            // 用原生的方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
