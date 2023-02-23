package com.telegram.utility;

import com.telegram.BuzzcutBot;

import java.util.Timer;

public abstract class EventTimer extends BuzzcutBot {
    private Timer timer = null;
    public Timer getTimer() {
        if(timer == null) {
            timer = new Timer();
        }
        return timer;
    }
    public abstract String getTime();

    public abstract void start();

}
