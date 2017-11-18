package pl.booksmanagement.service;

import pl.booksmanagement.model.User;

import java.security.Principal;

public interface UserService {
    boolean isUserLoginAvailable(String login);
    void createExampleUser();
    boolean createNewUser(User u);
    User findUserByUsername(String username);
    User findUserById(Long id);
    void saveUser(User u);

    Long getAuthUserId(Principal principal);
}
