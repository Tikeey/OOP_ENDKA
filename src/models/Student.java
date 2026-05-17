package models;

import enums.StudentYear;
import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private static final long serialVersionUID = 1L;

    private StudentYear year;
    private String school;
    private int credits;
    private double gpa;
    private List<Course> courses;
    private Transcript transcript;

    public Student(int id, String login, String password, String firstName, String lastName, String email, StudentYear year, String school) {
        super(id, login, password, firstName, lastName, email);
        this.year = year;
        this.school = school;
        this.credits = 0;
        this.gpa = 0.0;
        this.courses = new ArrayList<>();
        this.transcript = new Transcript(this);
    }

    public StudentYear getYear() { 
        return year; 
    }
    public String getSchool() { 
        return school; 
    }
    public int getCredits() { 
        return credits; 
    }
    public double getGpa() { 
        return gpa; 
    }
    public List<Course> getCourses() { 
        return courses; 
    }
    public Transcript getTranscript() { 
        return transcript; 
    }

    public void setGpa(double gpa) { 
        this.gpa = gpa; 
    }
    public void addCredits(int c) { 
        this.credits += c; 
    }

    @Override
    public void showMenu() {
        System.out.println("=== STUDENT MENU ===");
        System.out.println("1. View available courses");
        System.out.println("2. Register for course");
        System.out.println("3. View my marks");
        System.out.println("4. View transcript");
        System.out.println("5. Rate teacher");
        System.out.println("0. Logout");
    }
}