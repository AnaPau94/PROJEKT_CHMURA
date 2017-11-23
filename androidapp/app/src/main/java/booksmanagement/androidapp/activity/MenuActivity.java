package booksmanagement.androidapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import booksmanagement.androidapp.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void openAddBookActivity(View view) {
        Intent myIntent = new Intent(MenuActivity.this,
                AddBookMenuActivity.class);
        startActivity(myIntent);

    }

    public void showBooksToBuy(View view) {
        //TODO dokończyć
        Intent myIntent = new Intent(MenuActivity.this,
                BooksTabActivity.class);
        startActivity(myIntent);
    }

    public void showBooksToRead(View view) {
        //TODO dokończyć
    }

    public void showReadBooks(View view) {
        //TODO dokończyć
    }

    public void showAllMyBooks(View view) {
        //TODO dokończyć
    }

    public void showBuyBookMenu(View view) {
        Intent myIntent = new Intent(MenuActivity.this,
                BuyBookUpperPriceRangeActivity.class);
        startActivity(myIntent);
    }
}
