package booksmanagement.androidapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import booksmanagement.androidapp.R;

public class AddBookMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_menu);
    }

    public void openSearchByISBNActivity(View view) {
        Intent myIntent = new Intent(AddBookMenuActivity.this,
                SearchByISBNActivity.class);
        startActivity(myIntent);
    }

    public void openSearchByBarCodeActivity(View view) {
        //TODO dokończyć
    }

    public void openAddBookActivity(View view) {
        Intent myIntent = new Intent(AddBookMenuActivity.this,
                AddBookActivity.class);
        startActivity(myIntent);
    }
}
