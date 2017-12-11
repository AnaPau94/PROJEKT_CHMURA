package booksmanagement.androidapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import booksmanagement.androidapp.R;
import booksmanagement.androidapp.model.BookModel;

public class ShopSelectActivity extends AppCompatActivity {

    BookModel book;
    String part1Empik = "http://www.empik.com/ksiazki,31,s?q=";
    String part1Merlin = "https://merlin.pl/catalog/?q=";
    String part1SwiatKsiazki = "https://www.swiatksiazki.pl/catalogsearch/result/?q=";
    String part1Ceneo = "https://www.ceneo.pl/Ksiazki;szukaj-"; //ISBN wyszuiwanie nie dziaa. Szukanie wedug tytułu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_select);

        //TODO dokończyć na razie na sztywno wpisany model książki
        book = new BookModel();
        book.setIsbn("9788376745473");
        book.setBookTitle("Doktorzy z piekła rodem");
    }

    public void onButtonEmpikClick(View view) {
        String url = part1Empik + book.getIsbn();
        openWebPage(url);
    }

    public void onButtonMatrasClick(View view) {
        Toast.makeText(this, R.string.matras_error, Toast.LENGTH_LONG).show();
    }

    public void onButtonMerlinClick(View view) {
        String url = part1Merlin + book.getIsbn();
        openWebPage(url);
    }

    public void onButtonSwiatKsiazkiClick(View view) {
        String url = part1SwiatKsiazki + book.getIsbn();
        openWebPage(url);
    }

    public void onButtonCeneoClick(View view) {
        String url = part1Ceneo + book.getBookTitle();
        openWebPage(url);
    }

    private void openWebPage(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
