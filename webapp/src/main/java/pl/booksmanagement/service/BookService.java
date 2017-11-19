package pl.booksmanagement.service;

import pl.booksmanagement.exception.UserNotFoundException;
import pl.booksmanagement.model.Book;

import java.util.List;

public interface BookService {
    Book findByBookId(Long id);
    Book findByIsbn(String isbn);
    Book findByBookAuthor(String author);
    List<Book> findAll();
    List<Book> findAllUserBooks(Long userId);
    Book save(Book book);
    void addOwnedBookForUser(Long userId, Book book);
    void addBuyBookForUser(Long userId, Book book);
    List<Book> getOwnedUserBooks(Long userId);
    List<Book> getBuyUserBooks(Long userId);
}
