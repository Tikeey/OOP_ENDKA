package models;

import java.util.Date;

public abstract class Employee extends User {
    private static final long serialVersionUID = 1L;

    private double salary;
    private String school;
    private Date hireDate;

    public Employee(int id, String login, String password, String firstName, String lastName, String email, double salary, String school) {
        super(id, login, password, firstName, lastName, email);
        this.salary = salary;
        this.school = school;
        this.hireDate = new Date();
    }

    public double getSalary() { 
        return salary; 
    }
    public String getSchool() { 
        return school; 
    }
    public Date getHireDate() { 
        return hireDate; 
    }

    public void sendMessage(User to, String text) {
        System.out.println("Message from " + getFullName() + " to " + to.getFullName() + ": " + text);
    }

    public void sendComplaint(String text) {
        System.out.println("Complaint from " + getFullName() + ": " + text);
    }
}