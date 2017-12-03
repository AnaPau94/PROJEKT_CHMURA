package pl.booksmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.booksmanagement.model.rest.BookModel;
import pl.booksmanagement.service.BookService;
import pl.booksmanagement.service.UserService;

@Controller
public class HomeController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String returnHomePage() {
        return "index";
    }

    @RequestMapping("/save")
    public String saveBook() {
        BookModel book = new BookModel();
        book.setBookAuthor("Henryk Sienkiewicz");
        book.setBookTitle("Potop");
        book.setIsbn("1234567891234");
        bookService.save(book);
        return "index";
    }

    @RequestMapping("/getAllBooks")
    public String getAllBooks(Model model) {
        model.addAttribute("booksList", bookService.findAll());
        return "books";
    }

    @RequestMapping("/users/username/{username}")
    public String getUserInfo(Model model, @PathVariable("username") String username) {
        model.addAttribute("user", userService.findUserByUsername(username));
        return "users";
    }

    @RequestMapping("/users/id/{id}")
    public String getUserInfo(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "users";
    }
}