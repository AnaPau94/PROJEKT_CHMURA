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
import android.widget.EditText;

public class MainActivity extends Activity  {

    EditText editTextLoginLogin;
    EditText editTextLoginPassword;
    Button buttonLoginCreateAccount;
    Button buttonLoginSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeAllElements();
    }

    private void initializeAllElements() {
        editTextLoginLogin = (EditText) findViewById(R.id.editText_login_login);
        editTextLoginPassword = (EditText) findViewById(R.id.editText_login_password);
        buttonLoginCreateAccount = (Button) findViewById(R.id.button_login_create_account);
        buttonLoginCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        buttonLoginSignIn = (Button) findViewById(R.id.button_sign_in);
        buttonLoginSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInMethod(editTextLoginLogin.getText().toString(), editTextLoginPassword.getText().toString());
            }
        });
    }

    private void signInMethod(String login, String password) {
        boolean isOk = true;

        if (isOk) {
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }
}