
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerClass extends BuzzcutBot implements Runnable {

    TimerClass(long chat_id, int minutes, int seconds){
        this.chat_id = chat_id;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    TimerClass(){}
    private long chat_id;
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
        long time = 0;
        FileInputStream fis;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(String.valueOf(update.getMessage().getChatId()));
            ois = new ObjectInputStream(fis);
            time = ois.readLong();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            assert ois != null;
            ois.close();
        }
        String text = aegisTimer(time) + "\n" + roshanTimer(time);
        sendText(update.getMessage().getChatId(),text);
    }

    @Override
    public void run() {
        currentTime = (minutes* 60L)+seconds;
        Timer timer = new Timer();
        File file = new File(String.valueOf(chat_id));
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                FileOutputStream fos;
                ObjectOutputStream oos = null;
                try {
                    fos = new FileOutputStream(file);
                    oos = new ObjectOutputStream(fos);
                    oos.writeLong(currentTime);
                    System.out.println("Current time: " + currentTime);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }finally {
                    try {
                        assert oos != null;
                        oos.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (--currentTime == 0) {
                    timer.cancel();
                }
            }
        } ,1000, 1000);
    }
}
