package patterns;

import database.Database;
import models.User;

public class LoggerDecorator extends User {
    private static final long serialVersionUID = 1L;

    private User wrappedUser;

    public LoggerDecorator(User user) {
        super(user.getId(), user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail());
        this.wrappedUser = user;
    }

    public void log(String action) {
        Database.getInstance().log("User [" + wrappedUser.getFullName() + "]: " + action);
    }

    @Override
    public void showMenu() {
        log("opened menu");
        wrappedUser.showMenu();
    }

    @Override
    public String getFullName() {
        return wrappedUser.getFullName();
    }

    public User getWrappedUser() {
        return wrappedUser;
    }
}