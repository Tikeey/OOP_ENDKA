package models;

import enums.ComplaintStatus;
import java.io.Serializable;
import java.util.Date;

public class Complaint implements Serializable {
    private static final long serialVersionUID = 1L;

    private User from;
    private String text;
    private Date date;
    private ComplaintStatus status;
    private Manager signedBy;

    public Complaint(User from, String text) {
        this.from = from;
        this.text = text;
        this.date = new Date();
        this.status = ComplaintStatus.PENDING;
    }

    public User getFrom() { 
        return from; 
    }
    public String getText() { 
        return text; 
    }
    public Date getDate() { 
        return date; 
    }
    public ComplaintStatus getStatus() { 
        return status; 
    }
    public Manager getSignedBy() { 
        return signedBy; 
    }

    public void sign(Manager manager) {
        this.signedBy = manager;
        this.status = ComplaintStatus.UNDER_REVIEW;
    }

    public void resolve() { 
        this.status = ComplaintStatus.RESOLVED; 
    }
    public void reject() { 
        this.status = ComplaintStatus.REJECTED; 
    }

    @Override
    public String toString() {
        return "Complaint{from=" + from.getFullName() +
               ", status=" + status +
               ", text='" + text + "'" +
               ", date=" + date + "}";
    }
}