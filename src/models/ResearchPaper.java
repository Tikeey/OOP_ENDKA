package models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ResearchPaper implements Serializable, Comparable<ResearchPaper> {
    private static final long serialVersionUID = 1L;

    private String title;
    private List<String> authors;
    private String journal;
    private Date publishedDate;
    private int citations;
    private String doi;
    private int pages;
    private String abstractText;
    private String keywords;

    public ResearchPaper(String title, List<String> authors, String journal, Date publishedDate, int citations, String doi, int pages, String abstractText, String keywords) {
        this.title = title;
        this.authors = authors;
        this.journal = journal;
        this.publishedDate = publishedDate;
        this.citations = citations;
        this.doi = doi;
        this.pages = pages;
        this.abstractText = abstractText;
        this.keywords = keywords;
    }

    public String getTitle() { 
        return title; 
    }
    public List<String> getAuthors() { 
        return authors; 
    }
    public String getJournal() { 
        return journal; 
    }
    public Date getPublishedDate() { 
        return publishedDate; 
    }
    public int getCitations() { 
        return citations; 
    }
    public String getDoi() { 
        return doi; 
    }
    public int getPages() { 
        return pages; 
    }
    public String getAbstractText() { 
        return abstractText; 
    }
    public String getKeywords() { 
        return keywords; 
    }

    public void setCitations(int citations) { this.citations = citations; }

    @Override
    public int compareTo(ResearchPaper other) {
        return Integer.compare(other.citations, this.citations);
    }

    @Override
    public String toString() {
        return "ResearchPaper{" +
               "\n  title='" + title + "'" +
               "\n  authors=" + authors +
               "\n  journal='" + journal + "'" +
               "\n  citations=" + citations +
               "\n  pages=" + pages +
               "\n  doi='" + doi + "'" +
               "\n  date=" + publishedDate +
               "\n}";
    }
}