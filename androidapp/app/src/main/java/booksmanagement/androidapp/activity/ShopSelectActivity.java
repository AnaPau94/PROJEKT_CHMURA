package booksmanagement.androidapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import booksmanagement.androidapp.R;
import booksmanagement.androidapp.model.BookModel;

public class ShopSelectActivity extends AppCompatActivity {

    BookModel book;
    String part1Empik = "http://www.empik.com/z-importu,20,s?q=";
    String part2Empik = "&qtype=basicForm";
    String part1Matras = "";
    String part2Matras = "";
    String part1Merlin = "";
    String part2Merlin = "";
    String part1SwiatKsiazki = "";
    String part2SwiatKsiazki = "";
    String part1Ceneo = "";
    String part2Ceneo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_select);

        //TODO dokończyć na razie na sztywno wpisany model książki
        book = new BookModel();
        book.setIsbn("9788376745473");
    }

    public void onButtonEmpikClick(View view) {
        String url = part1Empik + book.getIsbn() + part2Empik;
        openWebPage(url);
    }

    public void onButtonMatrasClick(View view) {
        //TODO dokończyć
    }

    public void onButtonMerlinClick(View view) {
        //TODO dokończyć
    }

    public void onButtonSwiatKsiazkiClick(View view) {
        //TODO dokończyć
    }

    public void onButtonCeneoClick(View view) {
        //TODO dokończyć
    }

    private void openWebPage(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
