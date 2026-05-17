package models;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private User from;
    private User to;
    private String text;
    private Date date;

    public Message(User from, User to, String text) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.date = new Date();
    }

    public User getFrom() { 
        return from; 
    }
    public User getTo() { 
        return to; 
    }
    public String getText() { 
        return text; 
    }
    public Date getDate() { 
        return date; 
    }

    @Override
    public String toString() {
        return "[" + date + "] " + from.getFullName() +
               " → " + to.getFullName() + ": " + text;
    }
}