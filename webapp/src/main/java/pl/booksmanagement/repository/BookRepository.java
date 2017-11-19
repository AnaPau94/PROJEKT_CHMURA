package pl.booksmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.booksmanagement.model.Book;
import pl.booksmanagement.model.BookType;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUserId(Long userId);
    List<Book> findByUserIdAndType(Long userId, BookType type);
    Book findByBookId(Long id);
    Book findByIsbn(String isbn);
    Book findByBookAuthor(String author);
}