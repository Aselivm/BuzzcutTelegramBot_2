import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;

public class BuzzcutBot extends TelegramLongPollingBot {

    public void sendText(long chat_id,String text){
        SendMessage message = new SendMessage();
        message.setChatId(chat_id);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "BuzzcutQcTestBot";
    }

    @Override
    public String getBotToken() {
        return "5963590377:AAGL0m7LkSJuP1dN9pjHjE7cRO8JSXzop0Y";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()&& update.getMessage().getText().equals("Roshan died")){
            Thread thread = new Thread(new TimerClass(update.getMessage().getChatId(),11,0));
            thread.start();
        }
        if(update.hasMessage()&& update.getMessage().getText().equals("Show time")){
            TimerClass timerClass = new TimerClass();
            try {
                timerClass.sendTime(update);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}