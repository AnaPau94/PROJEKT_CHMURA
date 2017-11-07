package pl.booksmanagement.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.booksmanagement.exception.UserNotFoundException;
import pl.booksmanagement.model.Book;
import pl.booksmanagement.model.User;
import pl.booksmanagement.repository.BookRepository;
import pl.booksmanagement.service.BookService;
import pl.booksmanagement.service.UserService;

import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository repository;

    @Autowired
    private UserService userService;

    public Book findByBookId(Long id) {
        return repository.findByBookId(id);
    }
    public Book findByIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }
    public Book findByBookAuthor(String author) {
        return repository.findByBookAuthor(author);
    }
    public List<Book> findAll() {
        return repository.findAll();
    }
    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    public void saveBookForUser(String userId, Book book) throws UserNotFoundException {
        if (book.getBookId() != null) {
           log.warn("add already exists book");
           return;
        }

        if (StringUtils.isBlank(userId)) {
            throw new UserNotFoundException("User id cannot be null!");
        }

        User user = userService.findUserById(Long.valueOf(userId));
        if (user == null) {
            throw new UserNotFoundException("User with id=" + userId + " not found!");
        }

        Book savedBook = repository.save(book);
        user.getOwnedBooks().add(savedBook);
        userService.saveUser(user);
    }


}
