package com.telegram.utility;

import com.telegram.BuzzcutBot;

import java.util.*;

public class Stopwatch extends BuzzcutBot {

    private final Timer timer = new Timer();
    private final long chat_id;
    private volatile long currentTime;
    private String minutes;

    private String seconds;

    public Stopwatch(long chat_id, int minutes, int seconds){
        this.chat_id = chat_id;
        this.currentTime = (minutes* 60L)+seconds;
        start();
    }

    public Timer getTimer() {
        return this.timer;
    }

    public String getTime(){
        minutes = String.valueOf(currentTime/60);
        seconds = String.valueOf(currentTime%60<10? "0"+currentTime%60:currentTime%60);
        return aegisTimer() + "\n" + roshanTimer();
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("count");
                if (currentTime-- <= 0) {
                    timer.cancel();
                }
            }
        } ,1000, 1000);
    }

    private String aegisTimer(){
        int min = Integer.parseInt(minutes) - 6;
        if(currentTime<=300) return "Aegis down in: "+ min+":00";
        return "Aegis down in: "+ min+":"+ seconds;
    }

    private String roshanTimer(){
        if(currentTime<=180) return "Roshan respawns: "
                + Math.max(Integer.parseInt(minutes)-3,0)
                +":00"+" - "+ minutes + ":" + seconds;
        return "Roshan respawns: "
                + Math.max(Integer.parseInt(minutes)-3,0)
                +":"+seconds+" - "+ minutes + ":" + seconds;

    }

}
