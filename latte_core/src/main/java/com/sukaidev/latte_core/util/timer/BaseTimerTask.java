package com.sukaidev.latte_core.util.timer;

import java.util.TimerTask;

/**
 * Created by sukaidev on 2019/03/10.
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener iTimerListener;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.iTimerListener = iTimerListener;
    }

    @Override
    public void run() {
        if (iTimerListener != null){
            iTimerListener.onTimer();
        }
    }
}
