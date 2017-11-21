package booksmanagement.androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void openMenuActivity(View view) {
        Intent myIntent = new Intent(WelcomeActivity.this,
                MenuActivity.class);
        startActivity(myIntent);
    }
}
