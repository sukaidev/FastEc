package com.sukaidev.latte_core.delegates.web;

import com.alibaba.fastjson.JSON;

/**
 * Created by sukaidev on 2019/05/05.
 */
public class LatteWebInterface {

    private final WebDelegate DELEGATE;


    private LatteWebInterface(WebDelegate delegate) {
        DELEGATE = delegate;
    }

    static LatteWebInterface create(WebDelegate delegate) {
        return new LatteWebInterface(delegate);
    }

    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        return null;
    }
}
