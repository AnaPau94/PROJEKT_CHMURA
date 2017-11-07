package booksmanagement.androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import booksmanagement.androidapp.model.User;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextRegistrationLogin;
    EditText editTextRegistrationPassword;
    EditText editTextRegistrationPassword2;
    Button buttonRegistrationCreateAcoount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initializeAllElements();
    }

    private void initializeAllElements() {
        editTextRegistrationLogin = (EditText) findViewById(R.id.editText_registration_login);
        editTextRegistrationPassword = (EditText) findViewById(R.id.editText_registration_password);
        editTextRegistrationPassword2 = (EditText) findViewById(R.id.editText_registration_password2);
        buttonRegistrationCreateAcoount = (Button) findViewById(R.id.button_registration_create_account);
        buttonRegistrationCreateAcoount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAllFields()) {
                    //TODO Dokończyć rejestracje nowego usera. Przesłanie obiektu usera do serwera.
                    new User(editTextRegistrationLogin.getText().toString(), editTextRegistrationPassword.getText().toString());    //Tworzenie użytkownika
                }
            }
        });
    }

    private boolean checkAllFields() {
        boolean out = false;
        if (checkLoginField()) {
            if (checkPasswordField()) {
                if (checkPassword2Field()) {
                    out = true;
                }
            }
        }
        return out;
    }

    private boolean checkLoginField() {
        boolean out = false;
        if (editTextRegistrationLogin.getText().toString().length() > 0) {
            out = true;
        }
        return out;
    }

    private boolean checkPasswordField() {
        boolean out = false;
        if (editTextRegistrationPassword.getText().toString().length() > 0) {
            out = true;
        }
        return out;
    }

    private boolean checkPassword2Field() {
        boolean out = false;
        if (editTextRegistrationPassword2.getText().toString().length() > 0) {
            if (editTextRegistrationPassword.getText().toString().equals(editTextRegistrationPassword2.getText().toString().length())) {
                out = true;
            }
        }
        return out;
    }
}
