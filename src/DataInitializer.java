import database.Database;
import enums.*;
import models.*;
import patterns.UserFactory;

import java.util.Arrays;
import java.util.Date;

public class DataInitializer {

    public static void init(Database db) {
        System.out.println("Initializing test data...");

        // Admin
        Admin admin = UserFactory.createAdmin(1, "admin", "admin123", "Alice", "Admin", "admin@uni.edu", 150000, "IT");
        db.addUser(admin);

        // Manager
        Manager manager = UserFactory.createManager(2, "manager", "manager123", "Bob", "Manager", "manager@uni.edu", 120000, "IT", ManagerType.OR);
        db.addUser(manager);

        // Professor (всегда Researcher)
        ResearcherTeacher professor = UserFactory.createProfessor(3, "prof", "prof123", "Carol", "Smith", "carol@uni.edu", 200000, "IT", 5);
        db.addUser(professor);

        // Teacher
        Teacher teacher = UserFactory.createTeacher(4, "teacher", "teacher123", "Dave", "Jones", "dave@uni.edu", 100000, "IT", TeacherTitle.SENIOR_LECTURER);
        db.addUser(teacher);

        // Students
        Student student1 = UserFactory.createStudent(5, "student1", "student123", "Eve", "Brown", "eve@uni.edu", StudentYear.SECOND, "IT");
        Student student2 = UserFactory.createStudent(6, "student2", "student123", "Frank", "White", "frank@uni.edu", StudentYear.FOURTH, "IT");
        db.addUser(student1);
        db.addUser(student2);

        // Courses
        Course oop = new Course(1, "OOP", 5);
        Course math = new Course(2, "Math", 4);
        oop.addInstructor(professor);
        oop.addInstructor(teacher);
        math.addInstructor(teacher);
        db.addCourse(oop);
        db.addCourse(math);

        // ResearchPaper
        ResearchPaper paper = new ResearchPaper(
                "Deep Learning in Education",
                Arrays.asList("Carol Smith", "Dave Jones"),
                "IEEE", new Date(), 42, "10.1109/abc", 12,
                "Abstract text here", "AI, Education"
        );
        professor.addPaper(paper);
        db.addPaper(paper);

        // ResearchProject
        ResearchProject project = new ResearchProject("AI in University Systems");
        try {
            project.addMember(professor, ParticipantRole.LEAD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        db.addProject(project);

        System.out.println("Test data loaded!");
    }
}