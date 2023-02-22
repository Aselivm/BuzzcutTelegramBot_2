package com.telegram.utility;

import com.telegram.BuzzcutBot;

import java.util.Timer;

public abstract class EventTimer extends BuzzcutBot {
    protected Integer integerSeconds(int currentTime){
        return currentTime%60;
    }

    protected Integer integerMinutes(int currentTime){
        return currentTime/60;
    }

    protected String stringSeconds(int currentTime){
        return String.valueOf(currentTime%60<10? "0"+currentTime%60:currentTime%60);
    }

    protected String stringMinutes(int currentTime){
        return String.valueOf(currentTime/60);
    }
    public Timer getTimer() {
        return new Timer();
    }
    public String getTime() {
        return null;
    }
    public void start(){}

}
