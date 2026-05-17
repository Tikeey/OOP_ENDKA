package ui;

import database.Database;
import models.*;
import patterns.UserFactory;
import enums.*;
import java.util.Scanner;

public class AdminMenu {

    public static void open(Admin admin, Scanner scanner) {
        Database db = Database.getInstance();

        while (true) {
            admin.showMenu();
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addUser(db, scanner);
                    break;
                case "2":
                    removeUser(db, scanner);
                    break;
                case "3":
                    viewAllUsers(db);
                    break;
                case "4":
                    viewLogs(db);
                    break;
                case "0":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void addUser(Database db, Scanner scanner) {
        System.out.println("\n=== ADD USER ===");
        System.out.println("1. Student  2. Teacher  3. Manager  4. Admin");
        System.out.print("Type: ");
        String type = scanner.nextLine();

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        switch (type) {
            case "1":
                System.out.println("Year (FIRST/SECOND/THIRD/FOURTH): ");
                StudentYear year = StudentYear.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("School: ");
                String school = scanner.nextLine();
                db.addUser(UserFactory.createStudent(id, login, password,
                        firstName, lastName, email, year, school));
                break;
            case "2":
                System.out.print("Salary: ");
                double salary = Double.parseDouble(scanner.nextLine());
                System.out.print("School: ");
                String tSchool = scanner.nextLine();
                System.out.println("Title (TUTOR/LECTURER/SENIOR_LECTURER/PROFESSOR): ");
                TeacherTitle title = TeacherTitle.valueOf(scanner.nextLine().toUpperCase());
                db.addUser(UserFactory.createTeacher(id, login, password,
                        firstName, lastName, email, salary, tSchool, title));
                break;
            case "3":
                System.out.print("Salary: ");
                double mSalary = Double.parseDouble(scanner.nextLine());
                System.out.print("School: ");
                String mSchool = scanner.nextLine();
                System.out.println("Type (OR/DEPARTMENT/DEAN/RECTOR): ");
                ManagerType mType = ManagerType.valueOf(scanner.nextLine().toUpperCase());
                db.addUser(UserFactory.createManager(id, login, password,
                        firstName, lastName, email, mSalary, mSchool, mType));
                break;
            case "4":
                System.out.print("Salary: ");
                double aSalary = Double.parseDouble(scanner.nextLine());
                System.out.print("School: ");
                String aSchool = scanner.nextLine();
                db.addUser(UserFactory.createAdmin(id, login, password,
                        firstName, lastName, email, aSalary, aSchool));
                break;
            default:
                System.out.println("Invalid type!");
                return;
        }
        System.out.println("User added successfully!");
    }

    private static void removeUser(Database db, Scanner scanner) {
        System.out.print("Enter user ID to remove: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            db.removeUser(id);
            System.out.println("User removed!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID!");
        }
    }

    private static void viewAllUsers(Database db) {
        System.out.println("\n=== ALL USERS ===");
        for (User u : db.getUsers()) {
            System.out.println(u);
        }
    }

    private static void viewLogs(Database db) {
        System.out.println("\n=== SYSTEM LOGS ===");
        for (String log : db.getLogs()) {
            System.out.println(log);
        }
    }
}