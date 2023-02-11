
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.*;
import java.util.*;

public class Stopwatch extends BuzzcutBot{

    public static HashMap<Long, Stopwatch> id_timer = new HashMap<>();

    Stopwatch(Update update, int minutes, int seconds){
        this.update = update;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    Stopwatch(){}

    private Update update;
    private long minutes = 0;
    private long seconds = 0;
    private long currentTime = 0;

    public String aegisTimer(long time){
        int minutes = (int)((time/60)-6);
        String seconds = String.valueOf(time%60<10? "0"+time%60:time%60);
        if(time<=300) return "Aegis down in: "+ minutes+":00";
        return "Aegis down in: "+ minutes+":"+ seconds;
    }
    public String roshanTimer(long time){
        int minutes = (int)(time/60);
        String seconds = String.valueOf(time%60<10? "0"+time%60:time%60);
        if(time<=180) return "Roshan respawns: " + Math.max(minutes-3,0)+":00"+" - "+ minutes + ":" + seconds;
        return "Roshan respawns: " + Math.max(minutes-3,0)+":"+seconds+" - "+ minutes + ":" + seconds;

    }

    public void sendTime(Update update) throws IOException {
        Stopwatch stopwatch = id_timer.get(update.getMessage().getChatId());
        long time = stopwatch.currentTime;
        String text = aegisTimer(time) + "\n" + roshanTimer(time);
        sendText(update.getMessage().getChatId(),text);
    }
    public void start() {
        id_timer.put(update.getMessage().getChatId(), Stopwatch.this);
        currentTime = (minutes* 60L)+seconds;
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
}
