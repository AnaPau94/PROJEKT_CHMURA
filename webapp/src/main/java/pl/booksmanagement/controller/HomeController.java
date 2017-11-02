package pl.booksmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.booksmanagement.model.Book;
import pl.booksmanagement.service.BookService;

@Controller
public class HomeController {
    @Autowired
    BookService bookService;

    @RequestMapping("/")
    public String returnHomePage() {
        return "index";
    }

    @RequestMapping("/save")
    public String saveBook() {
        Book book = new Book();
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
}