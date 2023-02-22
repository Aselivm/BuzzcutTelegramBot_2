package com.telegram.utility;

import java.util.*;

public class RoshanStopwatch extends EventTimer{
    private volatile int currentTime;

    public RoshanStopwatch(){
        this.currentTime = 11*60;
        start();
    }

    public String getTime(){
        return aegisTimer() + "\n" + roshanTimer();
    }

    public void start() {
        getTimer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("count");
                if (decrementAndGet(currentTime) <= 0) {
                    getTimer().cancel();
                }
            }
        } ,1000, 1000);
    }

    private Integer decrementAndGet(int currentTime){
        return this.currentTime = --currentTime;
    }

    private String aegisTimer(){
        int min = integerMinutes(currentTime) - 6;
        if(currentTime<=300) return "Aegis down in: "+ min+":00";
        return "Aegis down in: "+ min+":"+ stringSeconds(currentTime);
    }



}
