package interfaces;

import models.ResearchPaper;
import models.ResearchProject;
import java.util.Comparator;
import java.util.List;

public interface Researcher {
    int getHIndex();
    List<ResearchPaper> getResearchPapers();
    List<ResearchProject> getResearchProjects();
    void printPapers(Comparator<ResearchPaper> c);
}