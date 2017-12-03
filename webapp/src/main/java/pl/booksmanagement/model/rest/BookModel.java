package pl.booksmanagement.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.booksmanagement.model.BookType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookModel {
    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private String isbn;
    private String publication;
    private String printDate;

    private BookType type;
}
