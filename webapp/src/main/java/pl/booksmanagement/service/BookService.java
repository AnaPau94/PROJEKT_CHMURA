package pl.booksmanagement.service;

import pl.booksmanagement.model.Book;

import java.util.List;

public interface BookService {
    Book findByBookId(Long id);
    Book findByIsbn(String isbn);
    Book findByBookAuthor(String author);
    List<Book> findAll();
    void save(Book book);
}
