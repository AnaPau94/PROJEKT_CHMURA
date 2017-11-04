package pl.booksmanagement.service;

public interface UserService {
    boolean isUserLoginAvailable(String login);

    void createExampleUser();
}
