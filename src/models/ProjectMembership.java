package models;

import enums.ParticipantRole;
import interfaces.Researcher;
import java.io.Serializable;

public class ProjectMembership implements Serializable {
    private static final long serialVersionUID = 1L;

    private Researcher researcher;
    private ResearchProject project;
    private ParticipantRole role;

    public ProjectMembership(Researcher researcher, ResearchProject project, ParticipantRole role) {
        this.researcher = researcher;
        this.project = project;
        this.role = role;
    }

    public Researcher getResearcher() { 
        return researcher; 
    }
    public ResearchProject getProject() { 
        return project; 
    }
    public ParticipantRole getRole() { 
        return role; 
    }

    @Override
    public String toString() {
        return "Membership{researcher=" + researcher +
               ", role=" + role +
               ", project=" + project.getTopic() + "}";
    }
}