package booksmanagement.androidapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import booksmanagement.androidapp.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void openMenuActivity(View view) {
        Intent myIntent = new Intent(WelcomeActivity.this, MenuActivity.class);
        startActivity(myIntent);
    }

    public void logout(View view) {
        SharedPreferences preferences  = this.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        preferences.edit().putString("token", "").commit();
        Intent myIntent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(myIntent);
    }

    public void closeApp(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }
}
