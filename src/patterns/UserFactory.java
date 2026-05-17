package patterns;

import enums.ManagerType;
import enums.StudentYear;
import enums.TeacherTitle;
import models.*;

public class UserFactory {

    public static Student createStudent(int id, String login, String password, String firstName, String lastName, String email, StudentYear year, String school) {
        return new Student(id, login, password, firstName, lastName, email, year, school);
    }

    public static Teacher createTeacher(int id, String login, String password, String firstName, String lastName, String email, double salary, String school, TeacherTitle title) {
        return new Teacher(id, login, password, firstName, lastName, email, salary, school, title);
    }

    public static ResearcherTeacher createProfessor(int id, String login, String password, String firstName, String lastName, String email, double salary, String school, int hIndex) {
        return new ResearcherTeacher(id, login, password, firstName, lastName, email, salary, school, TeacherTitle.PROFESSOR, hIndex);
    }

    public static Manager createManager(int id, String login, String password, String firstName, String lastName, String email, double salary, String school, ManagerType type) {
        return new Manager(id, login, password, firstName, lastName, email, salary, school, type);
    }

    public static Admin createAdmin(int id, String login, String password, String firstName, String lastName, String email, double salary, String school) {
        return new Admin(id, login, password, firstName, lastName, email, salary, school);
    }
}