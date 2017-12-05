package booksmanagement.androidapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import booksmanagement.androidapp.R;
import booksmanagement.androidapp.model.BookModel;
import booksmanagement.androidapp.util.ISBNFinderUtil;
import booksmanagement.androidapp.util.PostBook;

public class SearchByISBNActivity extends AppCompatActivity {

    EditText editText;
    Long isbnNumber;
    BookModel book;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_isbn);
        preferences  = this.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
    }

    public void onButtonAddToBuyClick(View view) {
        routineAction();
        PostBook postBookObject = new PostBook(book, preferences.getString("token", ""), false);
        postBookObject.postBook();
    }

    public void onButtonAddToLibraryClick(View view) {
        routineAction();
        PostBook postBookObject = new PostBook(book, preferences.getString("token", ""), true);
        postBookObject.postBook();
    }

    private void routineAction() {
        editText = (EditText) findViewById(R.id.editText_isbn_number);
        if (checkInputData()) {
            isbnSearchOnWebsite();
        } else {
            showUserInputErrorInfo();
        }
    }

    private void isbnSearchOnWebsite() {
        ISBNFinderUtil finder = new ISBNFinderUtil(isbnNumber);
        if (finder.find()) {
            book = finder.getBook();
        } else {
            showISBNFinderError();
        }
    }

    private void showISBNFinderError() {
        Toast.makeText(this, getText(R.string.isbn_find_error_info), Toast.LENGTH_LONG).show();
    }

    private void showUserInputErrorInfo() {
        Toast.makeText(this, getText(R.string.isbn_error_info), Toast.LENGTH_LONG).show();
    }

    private boolean checkInputData() {
        boolean out = false;
        String inputData = editText.getText().toString();

        if (inputData.length() > 1) {
            isbnNumber = Long.decode(inputData);

            if (isbnNumber.compareTo(999999999L) == 1 && isbnNumber.compareTo(10000000000L) == -1) {
                out = true;
            } else {
                if (isbnNumber.compareTo(999999999999L) == 1 && isbnNumber.compareTo(10000000000000L) == -1) {
                    out = true;
                }
            }
        }
        return out;
    }
}
