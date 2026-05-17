package models;

import exceptions.NotResearcherException;
import interfaces.Researcher;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResearchProject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String topic;
    private List<ResearchPaper> publishedPapers;
    private List<ProjectMembership> participants;

    public ResearchProject(String topic) {
        this.topic = topic;
        this.publishedPapers = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public String getTopic() { 
        return topic; 
    }
    public List<ResearchPaper> getPublishedPapers() { 
        return publishedPapers; 
    }
    public List<ProjectMembership> getParticipants() { 
        return participants; 
    }

    public void addPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    public void addMember(Object candidate, enums.ParticipantRole role)
            throws NotResearcherException {
        if (!(candidate instanceof Researcher)) {
            throw new NotResearcherException(
                candidate + " is not a Researcher and cannot join the project!"
            );
        }
        participants.add(new ProjectMembership((Researcher) candidate, this, role));
    }

    @Override
    public String toString() {
        return "ResearchProject{topic='" + topic + "', papers=" +
               publishedPapers.size() + ", participants=" + participants.size() + "}";
    }
}