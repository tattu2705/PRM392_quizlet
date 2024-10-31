package com.example.projectprn392flashcard.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.data.dao.UserDAO;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtName, edtEmail, edtPhone;
    private TextView txtSignup;
    private Button btnRegister;

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.username);
        edtPassword = findViewById(R.id.password);
        edtName = findViewById(R.id.name);
        edtEmail = findViewById(R.id.email);
        edtPhone = findViewById(R.id.phone);
        txtSignup = findViewById(R.id.signupText);
        btnRegister = findViewById(R.id.btn_register);

        userDAO = new UserDAO(this);

        RegistrationHandler registrationHandler = new RegistrationHandler(userDAO, this);

        txtSignup.setOnClickListener(v -> finish());

        btnRegister.setOnClickListener(v -> registrationHandler.handleRegistration(
                edtUsername.getText().toString(),
                edtPassword.getText().toString(),
                edtName.getText().toString(),
                edtEmail.getText().toString(),
                edtPhone.getText().toString()
        ));
    }
}

class RegistrationHandler {

    private UserDAO userDAO;
    private AppCompatActivity activity;

    public RegistrationHandler(UserDAO userDAO, AppCompatActivity activity) {
        this.userDAO = userDAO;
        this.activity = activity;
    }

    public void handleRegistration(String username, String password, String name, String email, String phone) {
        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(activity, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userDAO.checkUsernameExist(username)) {
            Toast.makeText(activity, "Username already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        userDAO.insert(username, password, email, name, phone);
        Toast.makeText(activity, "Register successfully", Toast.LENGTH_SHORT).show();
        activity.finish();
    }
}