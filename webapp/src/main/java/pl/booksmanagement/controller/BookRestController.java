package pl.booksmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.booksmanagement.exception.UserNotFoundException;
import pl.booksmanagement.model.Book;
import pl.booksmanagement.model.rest.BookModel;
import pl.booksmanagement.service.BookService;
import pl.booksmanagement.service.UserService;

import java.security.Principal;
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
    public ResponseEntity<?> createNewBook (@RequestBody BookModel book, Principal principal) {

//        Book savedBook = bookService.save(book);
        bookService.create(userService.getAuthUserId(principal), book);



        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUserBooks (Principal principal) {
        List<BookModel> result = new ArrayList<>();
        result.addAll(bookService.findAllUserBooks(userService.getAuthUserId(principal)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/all/owned", method = RequestMethod.GET)
    public ResponseEntity<?> getAllOwnedBooks (Principal principal) {
        List<BookModel> result = new ArrayList<>();
        result.addAll(bookService.getOwnedUserBooks(userService.getAuthUserId(principal)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/all/buy", method = RequestMethod.GET)
    public ResponseEntity<?> getAllToBuyBooks (Principal principal) {
        List<BookModel> result = new ArrayList<>();
        result.addAll(bookService.getBuyUserBooks(userService.getAuthUserId(principal)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/add/owned", method = RequestMethod.POST)
    public ResponseEntity<?> addOwnedBook(@RequestBody BookModel book, Principal principal) {
        BookModel bookModel = bookService.addOwnedBookForUser(userService.getAuthUserId(principal), book);
        return new ResponseEntity<>("created", HttpStatus.OK);
    }

    @RequestMapping(value = "/add/buy", method = RequestMethod.POST)
    public ResponseEntity<?> createBookToBuy(@RequestBody BookModel book, Principal principal) {

        bookService.addBuyBookForUser(userService.getAuthUserId(principal), book);

        return new ResponseEntity<>("created", HttpStatus.OK);
    }

    @RequestMapping(value = "/remove/buy", method = RequestMethod.POST)
    public ResponseEntity<?> removeBookToBuy(@RequestBody Book book, Principal principal) {

        return new ResponseEntity<>("created", HttpStatus.OK);
    }

    @RequestMapping(value = "/remove/owned", method = RequestMethod.POST)
    public ResponseEntity<?> removeOwnedBook(@RequestBody Book book, Principal principal) {

        return new ResponseEntity<>("created", HttpStatus.OK);
    }
}
