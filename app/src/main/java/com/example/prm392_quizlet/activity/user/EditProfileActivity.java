package com.example.projectprn392flashcard.activity.user;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.data.dao.UserDAO;
import com.example.projectprn392flashcard.data.entity.User;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;



public class EditProfileActivity extends AppCompatActivity {
    private EditText usernameEditText, nameEditText, emailEditText, phoneEditText;
    private Button saveButton, cancelButton;
    private UserDAO userDAO;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        initializeViews();
        userDAO = new UserDAO(this);

        SharedPreferences sharedPreferences = getSharedPreferences("LoginState", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        user = userDAO.getUserById(userId);

        populateUserDetails();

        saveButton.setOnClickListener(v -> saveUserProfile());
        cancelButton.setOnClickListener(v -> finish());
    }

    private void initializeViews() {
        usernameEditText = findViewById(R.id.username);
        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.phone);
        saveButton = findViewById(R.id.btn_save);
        cancelButton = findViewById(R.id.btn_cancel);
    }

    private void populateUserDetails() {
        usernameEditText.setText(user.getUsername());
        nameEditText.setText(user.getName());
        emailEditText.setText(user.getEmail());
        phoneEditText.setText(user.getPhone());
    }

    private void saveUserProfile() {
        String usernameStr = usernameEditText.getText().toString();
        String nameStr = nameEditText.getText().toString();
        String emailStr = emailEditText.getText().toString();

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            emailEditText.setError("Invalid email");
            return;
        }

        String phoneStr = phoneEditText.getText().toString();

        user.setUsername(usernameStr);
        user.setName(nameStr);
        user.setEmail(emailStr);
        user.setPhone(phoneStr);

        userDAO.updateUser(user);
        finish();
    }
}


