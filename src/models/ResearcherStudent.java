package models;

import enums.StudentYear;
import interfaces.Researcher;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResearcherStudent extends Student implements Researcher {
    private static final long serialVersionUID = 1L;

    private int hIndex;
    private List<ResearchPaper> papers;
    private List<ResearchProject> projects;

    public ResearcherStudent(int id, String login, String password, String firstName, String lastName, String email, StudentYear year, String school, int hIndex) {
        super(id, login, password, firstName, lastName, email, year, school);
        this.hIndex = hIndex;
        this.papers = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    @Override
    public int getHIndex() { 
        return hIndex; 
    }

    @Override
    public List<ResearchPaper> getResearchPapers() { 
        return papers; 
    }

    @Override
    public List<ResearchProject> getResearchProjects() { 
        return projects; 
    }

    public void addPaper(ResearchPaper paper) { 
        papers.add(paper); 
    }
    public void addProject(ResearchProject project) { 
        projects.add(project); 
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        papers.stream()
              .sorted(c)
              .forEach(System.out::println);
    }
}