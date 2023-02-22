package com.telegram;

import com.telegram.controller.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BuzzcutBot extends TelegramLongPollingBot {

    public Long chatId(Update update){
        long chatId = -1;
        try {
            if (update.hasMessage()) {
                chatId =  update.getMessage().getChatId();
            } else if (update.hasCallbackQuery()) {
                chatId =  update.getCallbackQuery().getMessage().getChatId();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return chatId;
    }

    private static Controller controller = null;

    public static Controller getController() {
        return controller;
    }

    public void makeController() {
        BuzzcutBot.controller = new Controller();
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
        if(controller==null) makeController();
        getController().getStart(update);
        getController().RoshanScan(update);
    }

}
