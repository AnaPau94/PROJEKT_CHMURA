package booksmanagement.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity  {

    private Button buttonLoginActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        buttonLoginActivity = (Button) findViewById(R.id.button_login_activity);
        buttonLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void openAddBookActivity(View view) {
        Intent myIntent = new Intent(MainActivity.this,
                AddBookActivity.class);
        startActivity(myIntent);
    }
}