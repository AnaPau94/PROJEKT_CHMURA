package pl.booksmanagement.util;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;
import pl.booksmanagement.model.Book;
import pl.booksmanagement.model.UserBook;
import pl.booksmanagement.model.rest.BookModel;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class OrikaMapper {
    private MapperFactory mapperFactory;

    public OrikaMapper() {
        mapperFactory = new DefaultMapperFactory.Builder().build();
    }

    @PostConstruct
    public void init() {
        mapperFactory
                .classMap(Book.class, BookModel.class)
                .exclude("users")
                .byDefault()
                .register();
        mapperFactory
                .classMap(UserBook.class, BookModel.class)
                .exclude("users")
                .fieldAToB("book.bookId", "bookId")
                .fieldAToB("book.bookTitle", "bookTitle")
                .fieldAToB("book.bookAuthor", "bookAuthor")
                .fieldAToB("book.isbn", "isbn")
                .fieldAToB("book.publication", "publication")
                .fieldAToB("book.printDate", "printDate")
                .byDefault()
                .register();
    }

    public <RETURN, IN> RETURN map(IN in, Class<RETURN> returnClass) {
        return mapperFactory.getMapperFacade().map(in, returnClass);
    }

    public <RETURN, IN> List<RETURN> mapAsList(Iterable<IN> in, Class<RETURN> returnClass) {
        return mapperFactory.getMapperFacade().mapAsList(in, returnClass);
    }

    public <RETURN, IN> void map(IN in, RETURN returnObj) {
        mapperFactory.getMapperFacade().map(in, returnObj);
    }

}
