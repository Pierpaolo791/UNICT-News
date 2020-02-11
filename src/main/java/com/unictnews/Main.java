package com.unictnews;

import com.unictnews.bot.UnictBot;
import com.unictnews.scraper.Scraper;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Devi inserire il token e il canale @canale");
            return;
        }
        String token = args[0];
        String canale = args[1];
        
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        UnictBot unictBot = new UnictBot(token);
        try {
            botsApi.registerBot(unictBot);
        } catch (TelegramApiRequestException ex) {
            System.out.println("Errore nel registrare il bot");
        }
        new Timer().scheduleAtFixedRate(new Scraper(unictBot,canale), 0, 1000*10);
    }
}
