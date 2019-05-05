package com.sukaidev.latte_core.delegates.web.event;

import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by sukaidev on 2019/05/05.
 */
public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        if (getAction().equals("test")) {
            getWebView().post(new Runnable() {
                final WebView webView = getWebView();

                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
