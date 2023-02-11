
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.*;
import java.util.*;

public class Stopwatch extends BuzzcutBot{
    private long currentTime;

    private String minutes;

    private String seconds;

    Stopwatch(int minutes, int seconds){
        this.currentTime = (minutes* 60L)+seconds;
    }

    public void sendTime(Update update) throws IOException {
        minutes = String.valueOf(currentTime/60);
        seconds = String.valueOf(currentTime%60<10? "0"+currentTime%60:currentTime%60);
        String text = aegisTimer() + "\n" + roshanTimer();
        sendText(update.getMessage().getChatId(),text);
    }

    public void start() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("count");
                if (--currentTime == 0) {
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
