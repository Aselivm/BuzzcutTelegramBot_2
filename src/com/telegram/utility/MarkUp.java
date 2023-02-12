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

    public RoshanMarkUp getRoshanMarkUp(long chat_id){
        return new RoshanMarkUp(chat_id);
    }

    public MasterMarkUp getMasterMarkUp(long chat_id){
        return new MasterMarkUp(chat_id);
    }
    public class MasterMarkUp {
        MasterMarkUp(long chat_id){
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
    }

    public class RoshanMarkUp {
        private final long chat_id;
        RoshanMarkUp(long chat_id){
            this.chat_id = chat_id;
        }
        public void roshanStart(){
            message.setChatId(chat_id);
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
            message.setChatId(chat_id);
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
