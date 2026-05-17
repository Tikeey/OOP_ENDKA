package ui;

import database.Database;
import models.*;
import java.util.List;
import java.util.Scanner;

public class TeacherMenu {

    public static void open(Teacher teacher, Scanner scanner) {
        Database db = Database.getInstance();

        while (true) {
            teacher.showMenu();
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewCourses(teacher);
                    break;
                case "2":
                    putMark(teacher, db, scanner);
                    break;
                case "3":
                    viewStudents(teacher);
                    break;
                case "4":
                    sendMessage(teacher, db, scanner);
                    break;
                case "0":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void viewCourses(Teacher teacher) {
        System.out.println("\n=== MY COURSES ===");
        if (teacher.getCourses().isEmpty()) {
            System.out.println("No courses assigned.");
            return;
        }
        for (Course c : teacher.getCourses()) {
            System.out.println(c);
        }
    }

    private static void putMark(Teacher teacher, Database db, Scanner scanner) {
        System.out.println("\n=== PUT MARK ===");
        System.out.print("Enter student ID: ");
        try {
            int studentId = Integer.parseInt(scanner.nextLine());
            User user = db.findUserById(studentId);

            if (!(user instanceof Student)) {
                System.out.println("Student not found!");
                return;
            }

            Student student = (Student) user;
            System.out.print("Enter course ID: ");
            int courseId = Integer.parseInt(scanner.nextLine());
            Course course = db.findCourseById(courseId);

            if (course == null) {
                System.out.println("Course not found!");
                return;
            }

            // Ищем оценку студента по курсу
            Mark mark = null;
            for (Mark m : student.getTranscript().getMarks()) {
                if (m.getCourse().getId() == courseId) {
                    mark = m;
                    break;
                }
            }

            if (mark == null) {
                System.out.println("Student is not registered for this course!");
                return;
            }

            System.out.print("Attestation 1 (0-100): ");
            mark.setAttestation1(Double.parseDouble(scanner.nextLine()));
            System.out.print("Attestation 2 (0-100): ");
            mark.setAttestation2(Double.parseDouble(scanner.nextLine()));
            System.out.print("Final exam (0-100): ");
            mark.setFinalExam(Double.parseDouble(scanner.nextLine()));

            if (!mark.isPassed()) {
                mark.incrementFailCount();
                System.out.println("Student FAILED this course. Fail count: " + mark.getFailCount());
            }

            student.setGpa(student.getTranscript().getGPA());
            System.out.println("Mark saved! Total: " + String.format("%.2f", mark.getTotal()));
            db.log(teacher.getFullName() + " put mark for " + student.getFullName());

        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    private static void viewStudents(Teacher teacher) {
        System.out.println("\n=== MY STUDENTS ===");
        for (Course course : teacher.getCourses()) {
            System.out.println("Course: " + course.getName());
            for (Student s : course.getStudents()) {
                System.out.println("  - " + s.getFullName() + " | GPA: " + s.getGpa());
            }
        }
    }

    private static void sendMessage(Teacher teacher, Database db, Scanner scanner) {
        System.out.println("\n=== SEND MESSAGE ===");
        System.out.print("Enter recipient ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            User recipient = db.findUserById(id);
            if (recipient == null) {
                System.out.println("User not found!");
                return;
            }
            System.out.print("Message: ");
            String text = scanner.nextLine();
            teacher.sendMessage(recipient, text);
            db.log(teacher.getFullName() + " sent message to " + recipient.getFullName());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID!");
        }
    }
}