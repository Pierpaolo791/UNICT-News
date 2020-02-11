
package com.unictnews.domain;

public class News {
    private String title;
    private String content;
    private String link; 

    public News() {
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = formatContent(content);
    }
    
    private String formatContent(String content) {
        if (content.length() > 200) content =  content.substring(0,200) + "...\nApri il link per leggere l'intero contenuto";
        return content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    @Override
    public String toString() {
        return "<b>[ "+title+" ]</b>\n" +
                link + "\n\n" +
                content;
    }
    
}
