
package com.unictnews.scraper;

import com.unictnews.bot.UnictBot;
import com.unictnews.domain.News;
import com.unictnews.util.FileManager;
import java.io.IOException;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Scraper extends TimerTask{
    
    
    public final static String URL = "https://www.unict.it/";
    
    private final UnictBot bot; 
    private final String canale;
    
    public Scraper(UnictBot bot, String canale) {
        this.bot = bot;
        this.canale = canale;
    }
    
    public List<String> getNewsLink() {
        // Last 8 news
        return getDocument("news").getElementsByClass("views-field views-field-title")
                .stream()
                .map(x ->x .getElementsByTag("a").attr("href"))
                .filter(x -> !FileManager.read().contains(x))
                .collect(Collectors.toList());
    }
    
    public News getNews(String path) {
        Document doc = getDocument(path);
        News news = new News();
        news.setTitle(doc.getElementsByClass("page-title").text());
        news.setContent(doc.getElementsByClass("content").text());
        news.setLink(URL+doc.baseUri());
        FileManager.write(path);
        return news;
    }
    
    private Document getDocument(String path) {
        Document doc = null;
        try {
            doc = Jsoup.connect(URL+path).get();
        } catch (IOException ex) {
            System.err.println("Errore connessione alla pagina unict.it/news");
        }
        return doc; 
    }
    
    public List<News> getNewsNotSpammed() {
        return getNewsLink()
                    .stream()
                    .peek(x -> System.out.println(x))
                    .map(x -> getNews(x))
                    .collect(Collectors.toList());
    }
    
    @Override
    public void run() {
        List<News> news = getNewsNotSpammed();
        if (news.isEmpty()) return;
        news.stream().forEach(x -> send(x));
    }
    
    public void send(News x) {
        try {
            bot.execute(new SendMessage().enableHtml(true).setChatId(canale).setText(x.toString()));
        } catch (TelegramApiException ex) {
            System.out.println("Errore nell'invio di una news nel canale:  \n" + x);
        }
        
    }
    
}
