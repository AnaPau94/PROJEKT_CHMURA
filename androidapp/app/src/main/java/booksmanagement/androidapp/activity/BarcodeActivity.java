package booksmanagement.androidapp.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import booksmanagement.androidapp.R;
import booksmanagement.androidapp.model.BookModel;
import booksmanagement.androidapp.util.ISBNFinderUtil;
import booksmanagement.androidapp.util.PostBook;

public class BarcodeActivity extends AppCompatActivity {
    private EditText resultISBN;
    private Button searchBook, scanBarcode, addBookToMyLibrary, addBookToBuy;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    Long isbnNumber;
    BookModel book;
    SharedPreferences preferences;

    IntentResult scanningResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_barcode);
        resultISBN = (EditText) findViewById(R.id.resultISBN);
        searchBook  = (Button) findViewById(R.id.scan_by_isbn);
        scanBarcode  = (Button) findViewById(R.id.button_find_by_isbn);
        addBookToBuy = (Button) findViewById(R.id.button_find_by_barcode_add_to_buy);
        addBookToMyLibrary = (Button) findViewById(R.id.button_find_by_barcode_add_to_owned);
        checkCameraPermission();
        preferences  = this.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
    }

    public void scanBarcode(View view) {
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            resultISBN.setText(scanContent);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_CAMERA_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    scanBarcode.setEnabled(true);
                } else {
                    scanBarcode.setEnabled(false);
                }
                return;
            }
        }
    }

    public void onButtonAddToBuyClicked(View view) {
        routineAction();
        PostBook postBookObject = new PostBook(book, preferences.getString("token", ""), false);
        postBookObject.postBook();
        Toast.makeText(getApplicationContext(), "Book to buy added", Toast.LENGTH_SHORT).show();
    }

    public void onButtonAddToMyLibraryClicked(View view) {
        routineAction();
        PostBook postBookObject = new PostBook(book, preferences.getString("token", ""), true);
        postBookObject.postBook();
        Toast.makeText(getApplicationContext(), "Book added to library", Toast.LENGTH_SHORT).show();
    }

    private void routineAction() {
        resultISBN = (EditText) findViewById(R.id.resultISBN);
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
        String inputData = resultISBN.getText().toString();

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