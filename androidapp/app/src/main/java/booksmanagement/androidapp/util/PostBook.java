package booksmanagement.androidapp.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import booksmanagement.androidapp.model.BookModel;

/**
 * Created by Anna Bielinska-Slot on 12/4/2017.
 */

public class PostBook {
    private BookModel book;
    private String token;
    private final String addOwnedBookURL = "https://chmuraksiazki.herokuapp.com/api/book/add/owned";
    private final String addBookToBuyURL = "https://chmuraksiazki.herokuapp.com/api/book/add/buy";
    private RestTemplate restTemplate;
    private boolean isOwned = false;

    public PostBook(BookModel book, String token, boolean isOwned) {
        this.book = book;
        this.token = token;
        this.isOwned = isOwned;

        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public void postBook() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer " + token);
        HttpEntity<BookModel> request = new HttpEntity<>(book, headers);

        if (isOwned) {
            restTemplate.postForObject(addOwnedBookURL, request, BookModel.class);
        } else {
            restTemplate.postForObject(addBookToBuyURL, request, BookModel.class);
        }
    }
}
