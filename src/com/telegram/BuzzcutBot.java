package com.telegram;

import com.telegram.controller.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BuzzcutBot extends TelegramLongPollingBot {

    private static Controller controller = null;

    public static Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        BuzzcutBot.controller = controller;
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
        if(controller==null) setController(new Controller());
        getController().getStart(update);
        getController().getRoshanController(update);
    }

}
