package models;

public class Admin extends Employee {
    private static final long serialVersionUID = 1L;

    public Admin(int id, String login, String password, String firstName, String lastName, String email, double salary, String school) {
        super(id, login, password, firstName, lastName, email, salary, school);
    }

    @Override
    public void showMenu() {
        System.out.println("=== ADMIN MENU ===");
        System.out.println("1. Add user");
        System.out.println("2. Remove user");
        System.out.println("3. Update user");
        System.out.println("4. View logs");
        System.out.println("0. Logout");
    }
}