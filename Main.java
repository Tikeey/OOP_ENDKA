import database.Database;
import models.User;
import patterns.LoggerDecorator;
import ui.MainMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database db = Database.getInstance();

        if (db.getUsers().isEmpty()) {
            DataInitializer.init(db);
        }

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║   UNIVERSITY SYSTEM v1.0     ║");
        System.out.println("╚══════════════════════════════╝");

        while (true) {
            System.out.println("\n1. Login");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("0")) {
                db.save();
                System.out.println("Goodbye!");
                break;
            } else if (choice.equals("1")) {
                System.out.print("Login: ");
                String login = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();

                User user = db.authenticate(login, password);

                if (user == null) {
                    System.out.println("Invalid login or password!");
                } else {
                    System.out.println("Welcome, " + user.getFullName() + "!");
                    LoggerDecorator loggedUser = new LoggerDecorator(user);
                    MainMenu.open(loggedUser, scanner);
                }
            } else {
                System.out.println("Invalid choice!");
            }
        }

        scanner.close();
    }
}