package com.sukaidev.latte_core.delegates.web.client;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sukaidev.latte_core.delegates.web.WebDelegate;
import com.sukaidev.latte_core.delegates.web.route.Router;
import com.sukaidev.latte_core.util.log.LatteLogger;

/**
 * Created by sukaidev on 2019/05/05.
 */
public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading",url);
        return Router.getInstance().handleWebUrl(DELEGATE,url);
    }
}
