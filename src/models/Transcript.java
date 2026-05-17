package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transcript implements Serializable {
    private static final long serialVersionUID = 1L;

    private Student student;
    private List<Mark> marks;

    public Transcript(Student student) {
        this.student = student;
        this.marks = new ArrayList<>();
    }

    public Student getStudent() { 
        return student; 
    }
    public List<Mark> getMarks() { 
        return marks; 
    }

    public void addMark(Mark mark) {
        marks.add(mark);
    }

    public double getGPA() {
        if (marks.isEmpty()) return 0.0;
        double sum = 0;
        for (Mark mark : marks) {
            sum += mark.getTotal();
        }
        return sum / marks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== TRANSCRIPT: ").append(student.getFullName()).append(" ===\n");
        for (Mark mark : marks) {
            sb.append(mark.toString()).append("\n");
        }
        sb.append("GPA: ").append(String.format("%.2f", getGPA()));
        return sb.toString();
    }
}