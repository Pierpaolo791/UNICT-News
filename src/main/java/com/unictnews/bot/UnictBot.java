package com.unictnews.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnictBot extends TelegramLongPollingBot{

    private final String token; 
    
    public UnictBot(String token) {
    this.token = token; 
    }
    
    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getBotUsername() {
        return "UNICTBot";
    }
    
}
