package pl.booksmanagement.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.booksmanagement.exception.UserNotFoundException;
import pl.booksmanagement.model.Book;
import pl.booksmanagement.model.BookType;
import pl.booksmanagement.model.User;
import pl.booksmanagement.repository.BookRepository;
import pl.booksmanagement.service.BookService;
import pl.booksmanagement.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
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

    @Override
    public List<Book> findAllUserBooks(Long userId) {
        return repository.findByUserId(userId);
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    public void addOwnedBookForUser(Long userId, Book book) {
        if (book.getBookId() != null) {
           log.warn("add already exists book");
           return;
        }

        book.setType(BookType.OWNED);
        book.setUserId(userId);
        repository.save(book);

    }

    @Override
    public void addBuyBookForUser(Long userId, Book book) {
        if (book.getBookId() != null) {
            log.warn("add already exists book");
            return;
        }

        book.setType(BookType.BUY);
        book.setUserId(userId);
        repository.save(book);
    }

    @Override
    public List<Book> getOwnedUserBooks(Long userId) {
        return repository.findByUserIdAndType(userId, BookType.OWNED);
    }

    @Override
    public List<Book> getBuyUserBooks(Long userId) {
        return repository.findByUserIdAndType(userId, BookType.BUY);
    }


}
