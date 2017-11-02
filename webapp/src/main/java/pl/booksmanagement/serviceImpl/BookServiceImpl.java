package pl.booksmanagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.booksmanagement.model.Book;
import pl.booksmanagement.repository.BookRepository;
import pl.booksmanagement.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository repository;

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
    public void save(Book book) {
        repository.save(book);
    }
}
