package pl.booksmanagement.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 64)
    private String username;
    private String password;
    private String email;

    @OneToMany
    @JoinTable(
            name="user_owned_books",
            joinColumns = @JoinColumn( name="id"),
            inverseJoinColumns = @JoinColumn( name="book_id")
    )
    private List<Book> ownedBooks ;


    @OneToMany
    @JoinTable(
            name="user_books_to_buy",
            joinColumns = @JoinColumn( name="id"),
            inverseJoinColumns = @JoinColumn( name="book_id")
    )
    private List<Book>  booksToBuy;
}
