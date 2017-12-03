package pl.booksmanagement.service;

import pl.booksmanagement.model.User;
import pl.booksmanagement.model.UserBook;
import pl.booksmanagement.model.rest.BookModel;

import java.security.Principal;
import java.util.List;

public interface UserService {
    boolean isUserLoginAvailable(String login);
    void createExampleUser();
    boolean createNewUser(User u);
    User findUserByUsername(String username);
    User findUserById(Long id);
    void saveUser(User u);

    Long getAuthUserId(Principal principal);

    List<BookModel> findAllUserBooks(Long userId);
    List<UserBook> findAllUserBookEntities(Long userId);
}
