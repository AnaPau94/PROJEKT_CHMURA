package booksmanagement.androidapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import booksmanagement.androidapp.model.Book;

public class AddBookActivity extends AppCompatActivity {
    private final static String addOwnedBookURL = "http://192.168.56.1:8080/api/book/create?u=1";
    private final static String addBookToSearchURL = "http://192.168.56.1:8080/api/book/create?u=2";
    private RestTemplate restTemplate;
    private EditText title, author, isbn, publication, printDate;
    private Button addOwnedBookButton, addBookToSeacrhButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book_layout);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        title = (EditText) findViewById(R.id.title);
        author = (EditText) findViewById(R.id.author);
        isbn = (EditText) findViewById(R.id.isbn);
        publication = (EditText) findViewById(R.id.publication);
        printDate = (EditText) findViewById(R.id.printDate);

        addOwnedBookButton  = (Button) findViewById(R.id.addOwnedBook);
        addOwnedBookButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addBook(v);
            }
        });

        addBookToSeacrhButton  = (Button) findViewById(R.id.addBookToSearch);
        addBookToSeacrhButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addBook(v);
            }
        });

        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public void addBook(View view) {
        Book book = new Book();
        book.setBookAuthor(author.getText().toString());
        book.setBookTitle(title.getText().toString());
        book.setIsbn(isbn.getText().toString());
        book.setPublication(publication.getText().toString());
        book.setPrintDate(printDate.getText().toString());

        HttpEntity<Book> request = new HttpEntity<>(book);

        restTemplate.postForObject(view.getId() == R.id.addOwnedBook ? addOwnedBookURL : addBookToSearchURL, request, Book.class);
        Toast.makeText(getApplicationContext(), "Book added", Toast.LENGTH_SHORT).show();
    }
}