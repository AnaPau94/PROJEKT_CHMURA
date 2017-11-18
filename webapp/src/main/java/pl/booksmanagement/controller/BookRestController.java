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
import pl.booksmanagement.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@Slf4j
public class BookRestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createNewBook (@RequestBody Book book) {

        bookService.save(book);

        return new ResponseEntity<>("created", HttpStatus.OK);
    }

    @RequestMapping(value = "/all/owned", method = RequestMethod.POST)
    public ResponseEntity<?> getAllUserBooks (@RequestParam("u") String userId) {
        userService.findUserById(Long.valueOf(userId));
        List<Book> result = new ArrayList<>();
        try {
            result.addAll(bookService.getOwnedUserBooks(userId));
        } catch (UserNotFoundException e) {
            log.error("Error", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/add/owned", method = RequestMethod.POST)
    public ResponseEntity<?> addOwnedBook(@RequestBody Book book, @RequestParam("u") String userId) {
        if (StringUtils.isBlank(userId)) {
            bookService.save(book);
        } else if (StringUtils.isNotBlank(userId)) {
            try {
                bookService.addOwnedBookForUser(userId, book);
            } catch (UserNotFoundException e) {
                log.error("Error", e);
                return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>("created", HttpStatus.OK);
    }

    @RequestMapping(value = "/add/buy", method = RequestMethod.POST)
    public ResponseEntity<?> createBookToBuy(@RequestBody Book book, @RequestParam("u") String userId) {
        if (StringUtils.isBlank(userId)) {
            bookService.save(book);
        } else if (StringUtils.isNotBlank(userId)) {
            try {
                bookService.addBuyBookForUser(userId, book);
            } catch (UserNotFoundException e) {
                log.error("Error", e);
                return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>("created", HttpStatus.OK);
    }

    @RequestMapping(value = "/remove/buy", method = RequestMethod.POST)
    public ResponseEntity<?> removeBookToBuy(@RequestBody Book book, @RequestParam("u") String userId) {

        return new ResponseEntity<>("created", HttpStatus.OK);
    }

    @RequestMapping(value = "/remove/owned", method = RequestMethod.POST)
    public ResponseEntity<?> removeOwnedBook(@RequestBody Book book, @RequestParam("u") String userId) {

        return new ResponseEntity<>("created", HttpStatus.OK);
    }
}
