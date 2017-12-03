package pl.booksmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.booksmanagement.model.BookType;
import pl.booksmanagement.model.User;
import pl.booksmanagement.model.UserBook;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findById(Long id);

    @Query("SELECT u.books FROM User u WHERE u.id = :id")
    List<UserBook> findUserBooksByUserId(@Param("id") Long id);
//
//    @Query("SELECT u.books FROM User u WHERE u.id = :id and u.type = :bookType")
//    Set<UserBook> findUserBooksByUserIdAndType(@Param("id") Long id, @Param("bookType") BookType bookType);
}
