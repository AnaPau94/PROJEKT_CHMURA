package pl.booksmanagement.service;

import pl.booksmanagement.model.rest.BookModel;

import java.util.List;

public interface BookService {
    BookModel findByBookId(Long id);
    BookModel findByIsbn(String isbn);
    BookModel findByBookAuthor(String author);
    List<BookModel> findAll();
    List<BookModel> findAllUserBooks(Long userId);
    BookModel save(BookModel book);
    BookModel addOwnedBookForUser(Long userId, BookModel book);
    BookModel addBuyBookForUser(Long userId, BookModel book);
    List<BookModel> getOwnedUserBooks(Long userId);
    List<BookModel> getBuyUserBooks(Long userId);
    void create(Long userId, BookModel book);
}
