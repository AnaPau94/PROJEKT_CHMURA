package pl.booksmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.booksmanagement.model.Book;
import pl.booksmanagement.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createNewBook (@RequestBody Book book) {
        bookService.save(book);
        return new ResponseEntity<>("created", HttpStatus.OK);
    }
}
