package booksmanagement.androidapp;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import booksmanagement.androidapp.model.Book;

public class MainActivity extends AppCompatActivity {
    private final static String addBookURL = "http://192.168.43.60:8080/api/book/create";
    private RestTemplate restTemplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Dodanie książki
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        Book book = new Book();
        book.setBookAuthor("Henryk Sienkiewicz");
        book.setBookTitle("From Mobile");
        book.setIsbn("1234567891234");
        restTemplate.postForObject(addBookURL, book, Book.class);
    }
}
