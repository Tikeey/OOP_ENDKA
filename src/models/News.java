package models;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String content;
    private Date date;
    private String author;

    public News(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = new Date();
    }

    public String getTitle() { 
        return title; 
    }
    public String getContent() { 
        return content; 
    }
    public Date getDate() { 
        return date; 
    }
    public String getAuthor() { 
        return author; 
    }

    @Override
    public String toString() {
        return "=== " + title + " ===\n" + content + "\nBy: " + author + " | " + date;
    }
}