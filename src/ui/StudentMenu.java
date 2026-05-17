package ui;

import database.Database;
import exceptions.MaxCreditsException;
import exceptions.MaxFailsException;
import models.*;
import java.util.List;
import java.util.Scanner;

public class StudentMenu {

    public static void open(Student student, Scanner scanner) {
        Database db = Database.getInstance();

        while (true) {
            student.showMenu();
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAvailableCourses(db);
                    break;
                case "2":
                    registerForCourse(student, db, scanner);
                    break;
                case "3":
                    viewMarks(student);
                    break;
                case "4":
                    System.out.println(student.getTranscript());
                    break;
                case "5":
                    rateTeacher(student, db, scanner);
                    break;
                case "0":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void viewAvailableCourses(Database db) {
        System.out.println("\n=== AVAILABLE COURSES ===");
        for (Course c : db.getCourses()) {
            System.out.println(c);
        }
    }

    private static void registerForCourse(Student student, Database db, Scanner scanner) {
        viewAvailableCourses(db);
        System.out.print("Enter course ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Course course = db.findCourseById(id);

            if (course == null) {
                System.out.println("Course not found!");
                return;
            }

            if (student.getCredits() + course.getCredits() > 21) {
                throw new MaxCreditsException(
                    "Cannot register! Max 21 credits allowed. " +
                    "Current: " + student.getCredits() +
                    ", Course: " + course.getCredits()
                );
            }

            course.addStudent(student);
            student.getCourses().add(course);
            student.addCredits(course.getCredits());

            Mark mark = new Mark(student, course);
            student.getTranscript().addMark(mark);

            System.out.println("Successfully registered for: " + course.getName());
            db.log(student.getFullName() + " registered for " + course.getName());

        } catch (MaxCreditsException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID!");
        }
    }

    private static void viewMarks(Student student) {
        System.out.println("\n=== MY MARKS ===");
        List<Mark> marks = student.getTranscript().getMarks();
        if (marks.isEmpty()) {
            System.out.println("No marks yet.");
            return;
        }
        for (Mark m : marks) {
            System.out.println(m);
        }
        System.out.println("GPA: " + String.format("%.2f", student.getTranscript().getGPA()));
    }

    private static void rateTeacher(Student student, Database db, Scanner scanner) {
        System.out.println("\n=== RATE TEACHER ===");
        System.out.print("Enter teacher ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            User user = db.findUserById(id);
            if (user instanceof Teacher) {
                System.out.print("Rating (1-5): ");
                int rating = Integer.parseInt(scanner.nextLine());
                if (rating < 1 || rating > 5) {
                    System.out.println("Rating must be between 1 and 5!");
                    return;
                }
                System.out.println("Rated " + user.getFullName() + ": " + rating + "/5");
                db.log(student.getFullName() + " rated " + user.getFullName() + ": " + rating);
            } else {
                System.out.println("Teacher not found!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID!");
        }
    }
}