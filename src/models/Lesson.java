package models;

import enums.LessonType;
import java.io.Serializable;
import java.util.Date;

public class Lesson implements Serializable {
    private static final long serialVersionUID = 1L;

    private LessonType type;
    private Course course;
    private Teacher teacher;
    private Date date;
    private String room;

    public Lesson(LessonType type, Course course, Teacher teacher, Date date, String room) {
        this.type = type;
        this.course = course;
        this.teacher = teacher;
        this.date = date;
        this.room = room;
    }

    public LessonType getType() { return type; }
    public Course getCourse() { return course; }
    public Teacher getTeacher() { return teacher; }
    public Date getDate() { return date; }
    public String getRoom() { return room; }

    @Override
    public String toString() {
        return "[" + type + "] " + course.getName() + " | Room: " + room + " | " + date;
    }
}