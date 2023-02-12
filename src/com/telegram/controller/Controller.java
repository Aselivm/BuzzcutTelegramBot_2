package com.telegram.controller;

import com.telegram.BuzzcutBot;
import com.telegram.utility.MarkUp;
import com.telegram.utility.Stopwatch;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller extends BuzzcutBot {
    private static final HashMap<Long, Stopwatch> stopwatchById = new HashMap<>();
    public void getStart(Update update){
        new Start(update);
    }
    public void getRoshanController(Update update) {new RoshanController(update);}

    public MessageSender getMessageSender() {
        return new MessageSender();
    }

    static class MessageSender extends  Controller {

        private SendMessage message = new SendMessage();
        public void sendText(long chat_id, String text) {
            message = new SendMessage();
            message.setChatId(chat_id);
            message.setText(text);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

        public void sendInlineKeyBoardMessage(long chat_id, String text) {
            message = new SendMessage();
            message.setChatId(chat_id);
            message.setText(text);
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText("Stop button");
            inlineKeyboardButton.setCallbackData("Stopping_the_timer");
            rowInline.add(inlineKeyboardButton);
            rowsInline.add(rowInline);
            markupInline.setKeyboard(rowsInline);
            message.setReplyMarkup(markupInline);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class Start extends Controller{
        Start(Update update){
            if(update.hasMessage()&& update.getMessage().getText().startsWith("/start")){
                new MarkUp().getMasterMarkUp(update.getMessage().getChatId());
            }
        }
    }

    private class RoshanController{
        RoshanController(Update update){
            if(update.hasCallbackQuery()&&update.getCallbackQuery().getData().equals("Stopping_the_timer")){
                stopwatchById.get(update.getCallbackQuery().getMessage().getChatId()).getTimer().cancel();
                getMessageSender().sendText(update.getCallbackQuery().getMessage().getChatId(),"Timer canceled");
                new MarkUp().getMasterMarkUp(update.getCallbackQuery().getMessage().getChatId());
            }
            if(update.hasMessage()&& update.getMessage().getText().equals("Roshan")){
                new MarkUp().getRoshanMarkUp(update.getMessage().getChatId()).roshanStart();
            }
            if(update.hasMessage()&& update.getMessage().getText().startsWith("Roshan timer start")){
                stopwatchById.put(update.getMessage().getChatId(), new Stopwatch(update.getMessage().getChatId(),11,0));
                new MarkUp().getRoshanMarkUp(update.getMessage().getChatId()).roshanTime();
                getMessageSender().sendText(update.getMessage().getChatId(),stopwatchById.get(update.getMessage().getChatId()).getTime());
                getMessageSender().sendInlineKeyBoardMessage(update.getMessage().getChatId(),"Press \"Stop button\" to cancel timer");
            }
            if(update.hasMessage()&& update.getMessage().getText().startsWith("Show time")){
                getMessageSender().sendText(update.getMessage().getChatId(),stopwatchById.get(update.getMessage().getChatId()).getTime());
                getMessageSender().sendInlineKeyBoardMessage(update.getMessage().getChatId(),"Press \"Stop button\" to cancel timer");
            }
        }
    }
}
