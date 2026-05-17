package ui;

import database.Database;
import models.*;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ManagerMenu {

    public static void open(Manager manager, Scanner scanner) {
        Database db = Database.getInstance();

        while (true) {
            manager.showMenu();
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    approveCourseRegistration(db, scanner);
                    break;
                case "2":
                    addCourseForRegistration(db, scanner);
                    break;
                case "3":
                    assignTeacher(db, scanner);
                    break;
                case "4":
                    generateReport(db);
                    break;
                case "5":
                    manageNews(manager, db, scanner);
                    break;
                case "6":
                    viewStudentsByGPA(db);
                    break;
                case "7":
                    viewStudentsByName(db);
                    break;
                case "0":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void approveCourseRegistration(Database db, Scanner scanner) {
        System.out.println("\n=== APPROVE REGISTRATION ===");
        System.out.print("Student ID: ");
        try {
            int sId = Integer.parseInt(scanner.nextLine());
            System.out.print("Course ID: ");
            int cId = Integer.parseInt(scanner.nextLine());

            User user = db.findUserById(sId);
            Course course = db.findCourseById(cId);

            if (user instanceof Student && course != null) {
                System.out.println("Registration approved: " +
                        user.getFullName() + " → " + course.getName());
                db.log("Manager approved: " + user.getFullName() + " for " + course.getName());
            } else {
                System.out.println("Student or course not found!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    private static void addCourseForRegistration(Database db, Scanner scanner) {
        System.out.println("\n=== ADD COURSE ===");
        System.out.print("Course ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Course name: ");
            String name = scanner.nextLine();
            System.out.print("Credits: ");
            int credits = Integer.parseInt(scanner.nextLine());

            Course course = new Course(id, name, credits);
            db.addCourse(course);
            System.out.println("Course added: " + name);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    private static void assignTeacher(Database db, Scanner scanner) {
        System.out.println("\n=== ASSIGN TEACHER ===");
        System.out.print("Teacher ID: ");
        try {
            int tId = Integer.parseInt(scanner.nextLine());
            System.out.print("Course ID: ");
            int cId = Integer.parseInt(scanner.nextLine());

            User user = db.findUserById(tId);
            Course course = db.findCourseById(cId);

            if (user instanceof Teacher && course != null) {
                course.addInstructor((Teacher) user);
                ((Teacher) user).addCourse(course);
                System.out.println("Assigned " + user.getFullName() + " to " + course.getName());
                db.log("Assigned teacher " + user.getFullName() + " to " + course.getName());
            } else {
                System.out.println("Teacher or course not found!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    private static void generateReport(Database db) {
        System.out.println("\n=== ACADEMIC REPORT ===");
        List<User> students = db.getUsers().stream()
                .filter(u -> u instanceof Student)
                .collect(Collectors.toList());

        System.out.println("Total students: " + students.size());

        double avgGpa = students.stream()
                .mapToDouble(u -> ((Student) u).getGpa())
                .average()
                .orElse(0.0);

        System.out.println("Average GPA: " + String.format("%.2f", avgGpa));

        for (User u : students) {
            Student s = (Student) u;
            System.out.println("  " + s.getFullName() +
                    " | GPA: " + String.format("%.2f", s.getGpa()) +
                    " | Credits: " + s.getCredits());
        }
    }

    private static void manageNews(Manager manager, Database db, Scanner scanner) {
        System.out.println("\n=== MANAGE NEWS ===");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Content: ");
        String content = scanner.nextLine();

        News news = new News(title, content, manager.getFullName());
        db.addNews(news);
        System.out.println("News published!");
    }

    private static void viewStudentsByGPA(Database db) {
        System.out.println("\n=== STUDENTS BY GPA ===");
        db.getUsers().stream()
                .filter(u -> u instanceof Student)
                .map(u -> (Student) u)
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .forEach(s -> System.out.println(
                        s.getFullName() + " | GPA: " + String.format("%.2f", s.getGpa())));
    }

    private static void viewStudentsByName(Database db) {
        System.out.println("\n=== STUDENTS BY NAME ===");
        db.getUsers().stream()
                .filter(u -> u instanceof Student)
                .map(u -> (Student) u)
                .sorted(Comparator.comparing(Student::getFullName))
                .forEach(s -> System.out.println(s.getFullName()));
    }
}