package pl.booksmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.booksmanagement.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookId(Long id);
    Book findByIsbn(String isbn);
    Book findByBookAuthor(String author);
}