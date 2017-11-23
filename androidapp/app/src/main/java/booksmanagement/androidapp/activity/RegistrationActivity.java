package booksmanagement.androidapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import booksmanagement.androidapp.R;
import booksmanagement.androidapp.model.User;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextRegistrationLogin;
    EditText editTextRegistrationPassword;
    EditText editTextRegistrationPassword2;
    Button buttonRegistrationCreateAcoount;

//    private final static String url = "http://localhost:8080/api/user/create";
//    private final static String url = "https://chmuraksiazki.herokuapp.com/api/user/create";

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
                    onButtonRegisterClick();
                }
            }
        });
    }

    private void onButtonRegisterClick() {
        User user = new User();    //Tworzenie użytkownika
        user.setUsername(editTextRegistrationLogin.getText().toString());
        user.setPassword(editTextRegistrationPassword.getText().toString());

        int httpStatus = postCreateUser(user);

        Log.i("httpStatus", Integer.toString(httpStatus));

        switch (httpStatus ) {
            case HttpURLConnection.HTTP_OK:
                Toast.makeText(this, getResources().getString(R.string.congratulations), Toast.LENGTH_SHORT).show();
                //getToken(user);
                startWelcomeActivity();
                break;
            case HttpURLConnection.HTTP_CONFLICT:
                Toast.makeText(this, getResources().getString(R.string.username_is_taken), Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, getResources().getString(R.string.undefined_error), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void getToken(User user) {
        //TODO zrobić pobieranie, zapisywanie tokena
        String url = getResources().getString(R.string.url_get_token_part_1) + user.getUsername() + getResources().getString(R.string.url_get_token_part_2) + user.getPassword();


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "user: my-trusted-client password: secret");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("params", headers);
        ResponseEntity<String> response = restTemplate
                .exchange(url, HttpMethod.POST, request, String.class);

        String string = response.getBody();

        /*RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "user: my-trusted-client password: secret");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        System.out.println(result);*/
        /*try {
            URL url = new URL(getResources().getString(R.string.url_get_token_part_1) + user.getUsername() + getResources().getString(R.string.url_get_token_part_2) + user.getPassword());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Authorization", "user: my-trusted-client password: secret");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonParam;

            ClientHttpResponse =
            DataInputStream is = new DataInputStream(conn.getInputStream());
            //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
            jsonParam = new JSONObject(is.readUTF());
            Log.i("JSON", jsonParam.toString());

            is.close();

            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
            Log.i("MSG" , conn.getResponseMessage());

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    private int postCreateUser(final User user) {
        int httpStatus = HttpURLConnection.HTTP_NOT_FOUND;
        try {
            URL url = new URL(getResources().getString(R.string.url_create_user));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("username", user.getUsername());
            jsonParam.put("password", user.getPassword());

            Log.i("JSON", jsonParam.toString());
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
            os.writeBytes(jsonParam.toString());

            os.flush();
            os.close();

            httpStatus = conn.getResponseCode();
//            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
            Log.i("MSG" , conn.getResponseMessage());

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpStatus;
    }

    private void startWelcomeActivity() {
        //TODO dokończyć przejście do powitalnego okna
        Intent intent = new Intent(RegistrationActivity.this, WelcomeActivity.class);
        startActivity(intent);
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
        String pass = editTextRegistrationPassword.getText().toString();
        String pass2 = editTextRegistrationPassword2.getText().toString();
        if (pass2.length() > 0) {
            if (pass.equals(pass2)) {
                out = true;
            }
        }
        return out;
    }
}
