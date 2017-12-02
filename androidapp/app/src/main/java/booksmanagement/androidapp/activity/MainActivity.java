package booksmanagement.androidapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import booksmanagement.androidapp.R;
import booksmanagement.androidapp.model.Token;
import booksmanagement.androidapp.model.User;

public class MainActivity extends Activity  {
    private EditText editTextLoginLogin;
    private EditText editTextLoginPassword;
    private Button buttonLoginCreateAccount;
    private Button buttonLoginSignIn;
    private SharedPreferences preferences;
    private final String basicAuth = "Basic " + Base64.encodeToString("my-trusted-client:secret".getBytes(), Base64.NO_WRAP);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences  = this.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);

        if(!preferences.getString("token","").equals("")) {
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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
        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        String userToken = getToken(user);

        if(userToken != null) {
            preferences.edit().putString("token", userToken).commit();
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
        }
    }

    public String getToken(User user) {
        String url = "https://chmuraksiazki.herokuapp.com/oauth/token?grant_type=password&username=" + user.getUsername() + "&password=" + user.getPassword();
        String accessToken;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", basicAuth);
        headers.setContentType(MediaType.APPLICATION_JSON);



        HttpEntity<User> request = new HttpEntity<>(user, headers);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            Token token = restTemplate.postForObject(url, request, Token.class);
            accessToken = token.getAccess_token();

        } catch (HttpClientErrorException e) {
            accessToken = null;
        }

        return accessToken;
    }

    @Override
    public void onBackPressed() {
    }
}