package pl.booksmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.booksmanagement.model.Book;
import pl.booksmanagement.model.BookType;
import pl.booksmanagement.model.UserBook;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUserId(Long userId);
    Book findByBookId(Long id);
    Book findByIsbn(String isbn);
    Book findByBookAuthor(String author);
    List<Book> findByUsers_User_Id(Long userId);

//    @Query("SELECT b.users FROM Book b WHERE b.id = :id")
//    Set<UserBook> findUserBooksByUserId(@Param("id") Long id);
}