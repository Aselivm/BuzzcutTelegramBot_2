package com.telegram.utility;

import java.util.*;

public class RoshanStopwatch extends EventTimer{
    private volatile int currentTime;

    private int minutes;
    private int seconds;

    public RoshanStopwatch(){
        this.currentTime = 11*60;
        start();
    }

    public String getTime(){
        this.minutes = currentTime/60;
        this.seconds = currentTime%60;
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
        int minutes = this.minutes - 6;
        if(currentTime<=300) return "Aegis down in: "+ minutes +":00";
        return "Aegis down in: "+ minutes +":"+ String.format("%02d", seconds);
    }

    private String roshanTimer(){
        if(currentTime<=180) return "Roshan respawns: "
                + Math.max(minutes-3,0)
                +":00"+" - "+ minutes + ":" + String.format("%02d", seconds);
        return "Roshan respawns: "
                + Math.max(minutes-3,0)
                +":"+ String.format("%02d", seconds) +" - "+ minutes + ":" + String.format("%02d", seconds);

    }

}
