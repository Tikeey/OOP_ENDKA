package ui;

import models.*;
import patterns.LoggerDecorator;
import java.util.Scanner;

public class MainMenu {

    public static void open(LoggerDecorator user, Scanner scanner) {
        User wrapped = user.getWrappedUser();

        if (wrapped instanceof Admin) {
            AdminMenu.open((Admin) wrapped, scanner);
        } else if (wrapped instanceof Manager) {
            ManagerMenu.open((Manager) wrapped, scanner);
        } else if (wrapped instanceof Teacher) {
            TeacherMenu.open((Teacher) wrapped, scanner);
        } else if (wrapped instanceof Student) {
            StudentMenu.open((Student) wrapped, scanner);
        } else {
            System.out.println("Unknown role!");
        }
    }
}