package models;

import java.io.Serializable;

public class Mark implements Serializable {
    private static final long serialVersionUID = 1L;

    private Student student;
    private Course course;
    private double attestation1;
    private double attestation2;
    private double finalExam;
    private int failCount;

    public Mark(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.attestation1 = 0;
        this.attestation2 = 0;
        this.finalExam = 0;
        this.failCount = 0;
    }

    public Student getStudent() { 
        return student; 
    }
    public Course getCourse() { 
        return course; 
    }
    public double getAttestation1() { 
        return attestation1; 
    }
    public double getAttestation2() { 
        return attestation2; 
    }
    public double getFinalExam() { 
        return finalExam; 
    }
    public int getFailCount() { 
        return failCount; 
    }

    public void setAttestation1(double attestation1) { 
        this.attestation1 = attestation1; 
    }
    public void setAttestation2(double attestation2) { 
        this.attestation2 = attestation2; 
    }
    public void setFinalExam(double finalExam) { 
        this.finalExam = finalExam; 
    }

    public double getTotal() {
        return attestation1 * 0.3 + attestation2 * 0.3 + finalExam * 0.4;
    }

    public boolean isPassed() {
        return getTotal() >= 50;
    }

    public void incrementFailCount() {
        failCount++;
    }

    @Override
    public String toString() {
        return "Mark{course=" + course.getName() +
               ", att1=" + attestation1 +
               ", att2=" + attestation2 +
               ", final=" + finalExam +
               ", total=" + String.format("%.2f", getTotal()) +
               ", passed=" + isPassed() + "}";
    }
}