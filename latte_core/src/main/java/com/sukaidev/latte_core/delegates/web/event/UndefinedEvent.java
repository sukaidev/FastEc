package com.sukaidev.latte_core.delegates.web.event;

import com.sukaidev.latte_core.util.log.LatteLogger;

/**
 * Created by sukaidev on 2019/05/05.
 */
public class UndefinedEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefinedEvent",params);
        return null;
    }
}
