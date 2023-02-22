package com.telegram.controller;
import com.telegram.BuzzcutBot;
import com.telegram.utility.MarkUp;
import org.telegram.telegrambots.meta.api.objects.Update;


public class Controller extends BuzzcutBot {

    public void getStart(Update update){
            if(update.hasMessage()
                    && update.getMessage().getText().startsWith("/start")){
                new MarkUp()
                        .getMasterMarkUp(chatId(update));
        }
    }

    public void RoshanScan(Update update) {
        new RoshanScanner(update);
    }

    public MessageSender getMessageSender() {
        return new MessageSender();
    }
}
