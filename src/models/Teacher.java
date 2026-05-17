package models;

import enums.TeacherTitle;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends Employee {
    private static final long serialVersionUID = 1L;

    private TeacherTitle title;
    private List<Course> courses;

    public Teacher(int id, String login, String password, String firstName, String lastName, String email, double salary, String school, TeacherTitle title) {
        super(id, login, password, firstName, lastName, email, salary, school);
        this.title = title;
        this.courses = new ArrayList<>();
    }

    public TeacherTitle getTitle() { 
        return title; 
    }
    public List<Course> getCourses() { 
        return courses; 
    }
    public void addCourse(Course course) { 
        courses.add(course); 
    }

    public boolean isProfessor() {
        return title == TeacherTitle.PROFESSOR;
    }

    @Override
    public void showMenu() {
        System.out.println("=== TEACHER MENU ===");
        System.out.println("1. View my courses");
        System.out.println("2. Put mark");
        System.out.println("3. View students");
        System.out.println("4. Send message");
        System.out.println("0. Logout");
    }
}