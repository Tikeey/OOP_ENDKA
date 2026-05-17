package database;

import models.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import interfaces.Researcher;

public class Database implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "data.ser";

    private static Database instance;

    private List<User> users;
    private List<Course> courses;
    private List<ResearchPaper> papers;
    private List<ResearchProject> projects;
    private List<News> news;
    private List<String> logs;

    private Database() {
        users = new ArrayList<>();
        courses = new ArrayList<>();
        papers = new ArrayList<>();
        projects = new ArrayList<>();
        news = new ArrayList<>();
        logs = new ArrayList<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = loadFromFile();
        }
        return instance;
    }

    public List<User> getUsers() { return users; }
    public List<Course> getCourses() { return courses; }
    public List<ResearchPaper> getPapers() { return papers; }
    public List<ResearchProject> getProjects() { return projects; }
    public List<News> getNews() { return news; }
    public List<String> getLogs() { return logs; }

    public void addUser(User user) {
        users.add(user);
        log("Added user: " + user.getFullName());
    }

    public void removeUser(int userId) {
        users.removeIf(u -> u.getId() == userId);
        log("Removed user with id: " + userId);
    }

    public User findUserByLogin(String login) {
        for (User u : users) {
            if (u.getLogin().equals(login)) return u;
        }
        return null;
    }

    public User findUserById(int id) {
        for (User u : users) {
            if (u.getId() == id) return u;
        }
        return null;
    }

    public void addCourse(Course course) {
        courses.add(course);
        log("Added course: " + course.getName());
    }

    public Course findCourseById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
    }

    public void addProject(ResearchProject project) {
        projects.add(project);
    }

    public List<Researcher> getTopCitedBySchool(String schoolName) {
        return users.stream()
                .filter(u -> u instanceof Researcher)
                .map(u -> (Researcher) u)
                .sorted(Comparator.comparingInt(Researcher::getHIndex).reversed())
                .collect(Collectors.toList());
    }

    public List<Researcher> getTopCitedByYear(int year) {
        return users.stream()
                .filter(u -> u instanceof Researcher)
                .map(u -> (Researcher) u)
                .sorted(Comparator.comparingInt(Researcher::getHIndex).reversed())
                .collect(Collectors.toList());
    }

    public void printAllResearcherPapers(Comparator<ResearchPaper> c) {
        users.stream()
                .filter(u -> u instanceof Researcher)
                .map(u -> (Researcher) u)
                .forEach(r -> {
                    System.out.println("=== " + ((User) r).getFullName() + " ===");
                    r.printPapers(c);
                });
    }

    public void addNews(News n) { news.add(n); }

    public void log(String action) {
        String entry = "[" + new java.util.Date() + "] " + action;
        logs.add(entry);
        System.out.println("LOG: " + entry);
    }

    public User authenticate(String login, String password) {
        for (User u : users) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                log("User logged in: " + u.getFullName());
                return u;
            }
        }
        return null;
    }

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(this);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static Database loadFromFile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                System.out.println("Data loaded successfully.");
                return (Database) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading data, starting fresh.");
            }
        }
        return new Database();
    }
}