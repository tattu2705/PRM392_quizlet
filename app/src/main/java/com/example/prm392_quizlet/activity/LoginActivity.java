package com.example.projectprn392flashcard.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprn392flashcard.MainActivity;
import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.data.dao.UserDAO;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private TextView txtRegister;

    private UserDAO userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.username);
        edtPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.loginButton);
        txtRegister = findViewById(R.id.signupText);

        userDatabase = new UserDAO(this);

        LoginHandler loginHandler = new LoginHandler(userDatabase, this);

        btnLogin.setOnClickListener(v -> loginHandler.handleLogin(
                edtUsername.getText().toString().trim(),
                edtPassword.getText().toString().trim()
        ));

        txtRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}

class LoginHandler {

    private UserDAO userDatabase;
    private AppCompatActivity activity;

    public LoginHandler(UserDAO userDatabase, AppCompatActivity activity) {
        this.userDatabase = userDatabase;
        this.activity = activity;
    }

    public void handleLogin(String username, String password) {
        if (userDatabase.checkLogin(username, password)) {
            SharedPreferences sharedPreferences = activity.getSharedPreferences("LoginState", AppCompatActivity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", true);
            int role = userDatabase.checkUserRole(username, password);
            int id = userDatabase.getUserId(username);
            editor.putInt("userId", id);
            if (role == 0) {
                editor.putString("role", "admin");
            } else {
                editor.putString("role", "user");
            }
            editor.apply();
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            activity.finish();
        } else {
            Toast.makeText(activity, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}