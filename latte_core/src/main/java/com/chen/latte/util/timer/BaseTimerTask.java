package com.chen.latte.util.timer;

import java.util.TimerTask;

public class BaseTimerTask extends TimerTask {

    TimeListener mTimeListener = null;

    public BaseTimerTask(TimeListener timeListener) {
        mTimeListener = timeListener;
    }

    @Override
    public void run() {
        if (mTimeListener != null){
            mTimeListener.ontimer();
        }
    }
}
