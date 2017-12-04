package booksmanagement.androidapp.activity;

import android.Manifest;
import android.content.Intent;
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

public class BarcodeActivity extends AppCompatActivity {
    private EditText resultISBN;
    private Button searchBook, scanBarcode;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    IntentResult scanningResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_barcode);
        resultISBN = (EditText) findViewById(R.id.resultISBN);
        searchBook  = (Button) findViewById(R.id.scan_by_isbn);
        scanBarcode  = (Button) findViewById(R.id.button_find_by_isbn);
        checkCameraPermission();
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
}