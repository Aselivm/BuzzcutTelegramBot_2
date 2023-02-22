package com.telegram.utility;

import com.telegram.BuzzcutBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MarkUp extends BuzzcutBot {
    private final SendMessage message = new SendMessage();
    private ReplyKeyboardMarkup keyboardMarkup;
    private List<KeyboardRow> keyboard;
    private KeyboardRow row;

    public RoshanMarkUp getRoshanMarkUp(long chatId){
        return new RoshanMarkUp(chatId);
    }

    public void getMasterMarkUp(long chat_id){
        message.setChatId(chat_id);
        message.setText("Choose option");
        keyboardMarkup = new ReplyKeyboardMarkup();
        keyboard = new ArrayList<>();
        row = new KeyboardRow();
        row.add("Roshan");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public class RoshanMarkUp {
        private final long chatId;
        RoshanMarkUp(long chatId){
            this.chatId = chatId;
        }
        public void roshanStart(){
            message.setChatId(chatId);
            message.setText("Roshan keyboard");
            keyboardMarkup = new ReplyKeyboardMarkup();
            keyboard = new ArrayList<>();
            row = new KeyboardRow();
            row.add("Roshan timer start");
            keyboard.add(row);
            keyboardMarkup.setKeyboard(keyboard);
            message.setReplyMarkup(keyboardMarkup);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        public void roshanTime(){
            message.setChatId(chatId);
            message.setText("Roshan timer started");
            keyboardMarkup = new ReplyKeyboardMarkup();
            keyboard = new ArrayList<>();
            row = new KeyboardRow();
            row.add("Show time");
            keyboard.add(row);
            keyboardMarkup.setKeyboard(keyboard);
            message.setReplyMarkup(keyboardMarkup);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
