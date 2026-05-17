package models;

import java.io.Serializable;

public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public User(int id, String login, String password, String firstName, String lastName, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() { 
        return id; 
    }
    public String getLogin() { 
        return login; 
    }
    public String getPassword() { 
        return password; 
    }
    public String getFirstName() { 
        return firstName; 
    }
    public String getLastName() { 
        return lastName; 
    }
    public String getEmail() { 
        return email; 
    }

    public void setPassword(String password) { 
        this.password = password; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public abstract void showMenu();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "[" + getClass().getSimpleName() + "] " + getFullName() + " (login: " + login + ")";
    }
}