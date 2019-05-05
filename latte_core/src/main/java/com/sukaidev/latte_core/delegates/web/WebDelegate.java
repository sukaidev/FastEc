package com.sukaidev.latte_core.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.sukaidev.latte_core.delegates.LatteDelegate;
import com.sukaidev.latte_core.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by sukaidev on 2019/05/05.
 */
public abstract class WebDelegate extends LatteDelegate {

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAvailable = false;

    public WebDelegate() {

    }

    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference = new WeakReference<WebView>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                mWebView.addJavascriptInterface(LatteWebInterface.create(this), "latte");
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("Initializer is null!");
            }
        }
    }

    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WebView is null!");
        } else {
            return mIsWebViewAvailable ? mWebView : null;
        }
    }

    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("Url is null!");
        } else {
            return mUrl;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView = null;
        }
    }
}