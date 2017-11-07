package pl.booksmanagement.service;

import pl.booksmanagement.exception.UserNotFoundException;
import pl.booksmanagement.model.Book;

import java.util.List;

public interface BookService {
    Book findByBookId(Long id);
    Book findByIsbn(String isbn);
    Book findByBookAuthor(String author);
    List<Book> findAll();
    Book save(Book book);
    void saveBookForUser(String userId, Book book) throws UserNotFoundException;
}
