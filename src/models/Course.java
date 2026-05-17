package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int credits;
    private List<Teacher> instructors;
    private List<Student> students;
    private List<Lesson> lessons;

    public Course(int id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.instructors = new ArrayList<>();
        this.students = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public int getId() { 
        return id; 
    }
    public String getName() { 
        return name; 
    }
    public int getCredits() { 
        return credits; 
    }
    public List<Teacher> getInstructors() { 
        return instructors; 
    }
    public List<Student> getStudents() { 
        return students; 
    }
    public List<Lesson> getLessons() { 
        return lessons; 
    }

    public void addInstructor(Teacher teacher) { 
        instructors.add(teacher); 
    }
    public void addStudent(Student student) { 
        students.add(student); 
    }
    public void addLesson(Lesson lesson) { 
        lessons.add(lesson); 
    }

    @Override
    public String toString() {
        return "Course{id=" + id + ", name='" + name + "', credits=" + credits + ", instructors=" + instructors.size() + ", students=" + students.size() + "}";
    }
}