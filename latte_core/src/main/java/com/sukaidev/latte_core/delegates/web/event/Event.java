package com.sukaidev.latte_core.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.sukaidev.latte_core.delegates.web.WebDelegate;

/**
 * Created by sukaidev on 2019/05/05.
 */
public abstract class Event implements IEvent {

    private Context mContext = null;
    private String mAction = null;
    private WebDelegate mDelegate = null;
    private String mUrl = null;
    private WebView mWebView = null;

    public Context getContext() {
        return mContext;
    }

    public Event setContext(Context context) {
        this.mContext = context;
        return this;
    }

    public WebView getWebView() {
        return mDelegate.getWebView();
    }

    public String getAction() {
        return mAction;
    }

    public Event setAction(String action) {
        this.mAction = action;
        return this;
    }

    public WebDelegate getDelegate() {
        return mDelegate;
    }

    public Event setDelegate(WebDelegate delegate) {
        this.mDelegate = delegate;
        return this;
    }

    public String getUrl() {
        return mUrl;
    }

    public Event setUrl(String url) {
        this.mUrl = url;
        return this;
    }
}
