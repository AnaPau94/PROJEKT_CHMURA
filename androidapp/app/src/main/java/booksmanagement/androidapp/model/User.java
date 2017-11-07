package booksmanagement.androidapp.model;

public class User {
    private final String login;
    private final String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public boolean isPasswordEquals(String in) {
        boolean out = false;
        if (in.equals(password)) {
            out = true;
        }
        return out;
    }
}
