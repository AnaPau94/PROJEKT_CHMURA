package booksmanagement.androidapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import booksmanagement.androidapp.R;
import booksmanagement.androidapp.model.BookModel;

public class AddBookActivity extends AppCompatActivity {
    private final String addOwnedBookURL = "https://chmuraksiazki.herokuapp.com/api/book/add/owned";
    private final String addBookToBuyURL = "https://chmuraksiazki.herokuapp.com/api/book/add/buy";
    private RestTemplate restTemplate;
    private EditText title, author, isbn, publication, printDate;
    private Button addOwnedBookButton, addBookToSeacrhButton;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book_layout);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        initializeAllElements();
    }

    private void initializeAllElements(){
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

        preferences  = this.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
    }

    public void addBook(View view) {
        if(isValid()) {
            BookModel book = new BookModel();
            book.setBookAuthor(author.getText().toString());
            book.setBookTitle(title.getText().toString());
            book.setIsbn(isbn.getText().toString());
            book.setPublication(publication.getText().toString());
            book.setPrintDate(printDate.getText().toString());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization","Bearer " + preferences.getString("token",""));
            HttpEntity<BookModel> request = new HttpEntity<>(book, headers);

            restTemplate.postForObject(view.getId() == R.id.addOwnedBook ? addOwnedBookURL : addBookToBuyURL, request, BookModel.class);
            Toast.makeText(getApplicationContext(), "Book added", Toast.LENGTH_SHORT).show();
            clearAllFields();
        }
    }
    private boolean isValid() {
        if (!isValidAuthorAndTitle(author.getText().toString())) {
            author.setError("Field must be not empty [5-50]");
            return false;
        }
        if (!isValidAuthorAndTitle(title.getText().toString())) {
            title.setError("Field must be not empty [5-50]");
            return false;
        }
        if (!isValidISBN(isbn.getText().toString())) {
            isbn.setError("Field must be not empty [10-13]");
            return false;
        }
        return true;
    }

    private boolean isValidAuthorAndTitle(String toValid) {
        return toValid != null && toValid.length() > 4 && toValid.length() < 51 ?  true : false;
    }
    private boolean isValidISBN(String toValid) {
        return toValid != null && toValid.length() > 4 && toValid.length() < 51 ?  true : false;
    }

    private void clearAllFields() {
        title.setText("");
        author.setText("");
        isbn.setText("");
        publication.setText("");
        printDate.setText("");
    }
}