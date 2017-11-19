package pl.booksmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "books")
@Data
@JsonIgnoreProperties("userId")
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

    @Enumerated(EnumType.STRING)
    private BookType type;

    private Long userId;
}