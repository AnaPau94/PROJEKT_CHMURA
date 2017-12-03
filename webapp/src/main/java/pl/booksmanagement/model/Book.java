package pl.booksmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @NotEmpty
    @Size(max=50)
    private String bookTitle;
    @NotEmpty
    @Size(max=50)
    private String bookAuthor;
    @NotEmpty
    @Size(min=10, max=13)
    private String isbn;
    @Size(max=50)
    private String publication;
    private String printDate;

    @OneToMany(
            mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserBook> users = new ArrayList<>();

    private Long userId;
}