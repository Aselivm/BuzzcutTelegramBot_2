package com.telegram.controller;

import com.telegram.utility.EventTimer;
import com.telegram.utility.MarkUp;
import com.telegram.utility.RoshanStopwatch;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;

public class RoshanScanner extends Controller {

    RoshanScanner(Update update){
        scan(update);
    }

    private static final HashMap<Long, EventTimer> stopwatchById = new HashMap<>();
    private void scan(Update update) {
        if(update.hasCallbackQuery()
                &&update.getCallbackQuery().getData().equals("Stopping_the_timer")){
            stoppingTheTimer(update);
        }
        if(update.hasMessage()
                && update.getMessage().getText().equals("Roshan")){
            roshanButton(update);
        }
        if(update.hasMessage()
                && update.getMessage().getText().startsWith("Roshan timer start")){
            roshanTimerStart(update);
        }
        if(update.hasMessage()
                && update.getMessage().getText().startsWith("Show time")){
            showTime(update);
        }
    }

    private void stoppingTheTimer(Update update){
        stopwatchById.get(chatId(update))
                .getTimer()
                .cancel();
        getMessageSender().sendText(chatId(update),"Timer canceled");
        new MarkUp()
                .getMasterMarkUp(chatId(update));
    }

    private void roshanButton(Update update){
        new MarkUp()
                .getRoshanMarkUp(chatId(update))
                .roshanStart();
    }

    private void roshanTimerStart(Update update){
        stopwatchById.put(chatId(update), new RoshanStopwatch());
        new MarkUp()
                .getRoshanMarkUp(chatId(update))
                .roshanTime();
        getMessageSender()
                .sendText(chatId(update),stopwatchById.get(chatId(update))
                        .getTime());
        getMessageSender()
                .sendInlineKeyBoardMessage(chatId(update),"Press \"Stop button\" to cancel timer");
    }

    private void showTime(Update update){
        getMessageSender()
                .sendText(chatId(update),stopwatchById.get(chatId(update))
                        .getTime());
        getMessageSender()
                .sendInlineKeyBoardMessage(chatId(update),"Press \"Stop button\" to cancel timer");
    }
}
