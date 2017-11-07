package pl.booksmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.booksmanagement.exception.UserNotFoundException;
import pl.booksmanagement.model.Book;
import pl.booksmanagement.service.BookService;

@RestController
@RequestMapping("/api/book")
@Slf4j
public class BookRestController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createNewBook (@RequestBody Book book, @RequestParam("u") String userId) {
        if (StringUtils.isBlank(userId)) {
            bookService.save(book);
        } else if (StringUtils.isNotBlank(userId)) {
            try {
                bookService.saveBookForUser(userId, book);
            } catch (UserNotFoundException e) {
                log.error("Error", e);
                return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>("created", HttpStatus.OK);
    }
}
