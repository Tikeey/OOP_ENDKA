package models;

import enums.ManagerType;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    private static final long serialVersionUID = 1L;

    private ManagerType managerType;

    public Manager(int id, String login, String password, String firstName, String lastName, String email, double salary, String school, ManagerType managerType) {
        super(id, login, password, firstName, lastName, email, salary, school);
        this.managerType = managerType;
    }

    public ManagerType getManagerType() { return managerType; }

    @Override
    public void showMenu() {
        System.out.println("=== MANAGER MENU ===");
        System.out.println("1. Approve course registration");
        System.out.println("2. Add course for registration");
        System.out.println("3. Assign teacher to course");
        System.out.println("4. Generate report");
        System.out.println("5. Manage news");
        System.out.println("6. View students by GPA");
        System.out.println("7. View students by name");
        System.out.println("0. Logout");
    }
}