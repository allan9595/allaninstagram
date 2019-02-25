package com.example.allanchang.allan_instagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        ParseUser currentUser = ParseUser.getCurrentUser();

        if(currentUser != null) {
            goMainActivity();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                login(username,password);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                signup(username, password);
            }
        });


    }

    private void signup(String username, String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    goMainActivity();
                } else {
                    Log.e(TAG, "Issue with signup");
                    e.printStackTrace();
                    return;
                }
            }
        });
    }

    private void login(String username, String password){
        //todo : go to new activity if user signned in

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with lgoin");
                    e.printStackTrace();
                    return;
                }
                goMainActivity();
            }

        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
