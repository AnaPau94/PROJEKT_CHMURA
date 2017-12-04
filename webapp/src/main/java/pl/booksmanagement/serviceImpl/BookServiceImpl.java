package pl.booksmanagement.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.booksmanagement.model.Book;
import pl.booksmanagement.model.BookType;
import pl.booksmanagement.model.User;
import pl.booksmanagement.model.UserBook;
import pl.booksmanagement.model.rest.BookModel;
import pl.booksmanagement.repository.BookRepository;
import pl.booksmanagement.service.BookService;
import pl.booksmanagement.service.UserService;
import pl.booksmanagement.util.OrikaMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;
    @Autowired
    private UserService userService;
    @Autowired
    private OrikaMapper orikaMapper;


    public BookModel findByBookId(Long id) {
//        return repository.findByBookId(id);
        return null;
    }

    public BookModel findByIsbn(String isbn) {
//        return repository.findByIsbn(isbn);
        return null;
    }

    public BookModel findByBookAuthor(String author) {
        return null;
//        return repository.findByBookAuthor(author);
    }

    public List<BookModel> findAll() {
        return null;
//        return repository.findAll();
    }

    @Override
    public List<BookModel> findAllUserBooks(Long userId) {
        return userService.findAllUserBooks(userId);
    }

    public BookModel save(BookModel book) {
        Book savedBook = repository.save(orikaMapper.map(book, Book.class));
        return orikaMapper.map(savedBook, BookModel.class);
    }

    public void create(Long userId, BookModel book) {

        Book bookEntity = orikaMapper.map(book, Book.class);

        User user = userService.findUserById(userId);
        UserBook userBook = new UserBook(user, bookEntity, book.getType());
        bookEntity.getUsers().add(userBook);
        repository.save(bookEntity);
        userService.saveUser(user);
    }

    @Override
    public BookModel addOwnedBookForUser(Long userId, BookModel book) {
        return orikaMapper.map(addBookForUserByType(userId, book, BookType.OWNED), BookModel.class);
    }

    @Override
    public BookModel addBuyBookForUser(Long userId, BookModel book) {
        return orikaMapper.map(addBookForUserByType(userId, book, BookType.BUY), BookModel.class);
    }

    @Override
    public List<BookModel> getOwnedUserBooks(Long userId) {
        List<UserBook> userBooks = userService.findAllUserBookEntities(userId);
        return userBooks.stream().filter(userBook -> BookType.OWNED.equals(userBook.getType())).map(userBook -> orikaMapper.map(userBook, BookModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<BookModel> getBuyUserBooks(Long userId) {
        List<UserBook> userBooks = userService.findAllUserBookEntities(userId);
        return userBooks.stream().filter(userBook -> BookType.BUY.equals(userBook.getType())).map(userBook -> orikaMapper.map(userBook, BookModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<BookModel> getAllBooksByType(Long userId, BookType bookType) {
        List<UserBook> userBooks = userService.findAllUserBookEntities(userId);
        return userBooks.stream().filter(userBook -> bookType.equals(userBook.getType())).map(userBook -> orikaMapper.map(userBook, BookModel.class)).collect(Collectors.toList());
    }

    @Override
    public boolean changeUserBookType(Long userId, BookModel bookModel) {

        if (bookModel.getBookId() == null || bookModel.getType() == null) {

            return false;
        }

        User user = userService.findUserById(userId);

        UserBook userBook = user.getBooks().stream().filter(ub -> ub.getBook().getBookId().equals(bookModel.getBookId())).findFirst().orElse(null);
        if (userBook == null) {
            log.warn("There is no book with id {} on user {}", bookModel.getBookId(), userId);
            return false;
        }

        userBook.setType(bookModel.getType());
        userService.saveUser(user);

        return true;
    }

    public UserBook addBookForUserByType(Long userId, BookModel book, BookType bookType) {
        User user = userService.findUserById(userId);


        Book bookByIsbn = repository.findByIsbn(book.getIsbn());
        Book bookEntity = bookByIsbn != null ? bookByIsbn : orikaMapper.map(book, Book.class);

        if (book.getBookId() != null || bookByIsbn != null) {
            UserBook userBook = user.getBooks().stream().filter(ub -> ub.getBook().getBookId().equals(bookEntity.getBookId())).findFirst().orElse(null);

            if (userBook != null && userBook.getType().equals(book.getType())) {
                log.info("Book {} already exists on user {}", book.getBookId(), userId);
                return userBook;
            } else if (userBook != null) {
                userBook.setType(bookType);
                userService.saveUser(user);
                log.info("Book {} updated for user {}", book, userId);
                return userBook;
            }
        }


        UserBook userBook = new UserBook(user, bookEntity, bookType);
        bookEntity.getUsers().add(userBook);
        repository.save(bookEntity);
        userService.saveUser(user);
        log.info("Book {} with status {} added to user {}", book, bookType, userId);

        return userBook;

    }
}
