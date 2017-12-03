package pl.booksmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class UserBook implements Serializable {
//    @EmbeddedId
//    private UserBookId id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("userId")
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("bookId")
    @Id
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Enumerated(EnumType.STRING)
    private BookType type;

//    public UserBook(User user, Book book) {
//        this.user = user;
//        this.book = book;
//        this.id = new UserBookId(user.getId(), book.getBookId());
//    }
}
