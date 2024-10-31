package com.example.projectprn392flashcard.activity.user;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.data.dao.UserDAO;
import com.example.projectprn392flashcard.data.entity.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;


public class AccountInfoActivity extends AppCompatActivity {
    private TextView usernameTextView, nameTextView, emailTextView, phoneTextView;
    private Button editProfileButton, changePasswordButton, cancelButton;
    private UserDAO userDAO;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        initializeViews();
        initializeUser();

        editProfileButton.setOnClickListener(v -> startActivity(new Intent(this, EditProfileActivity.class)));
        changePasswordButton.setOnClickListener(v -> startActivity(new Intent(this, ChangePasswordActivity.class)));
        cancelButton.setOnClickListener(v -> finish());
    }

    private void initializeViews() {
        usernameTextView = findViewById(R.id.usernameTextView);
        nameTextView = findViewById(R.id.name);
        emailTextView = findViewById(R.id.emailTextView);
        phoneTextView = findViewById(R.id.phone);
        editProfileButton = findViewById(R.id.btn_edit_profile);
        changePasswordButton = findViewById(R.id.btn_change_password);
        cancelButton = findViewById(R.id.btn_cancel);
        userDAO = new UserDAO(this);
    }

    private void initializeUser() {
        SharedPreferences sharedPreferences = getSharedPreferences("LoginState", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        user = userDAO.getUserById(userId);

        usernameTextView.setText(user.getUsername());
        nameTextView.setText(user.getName());
        emailTextView.setText(user.getEmail());
        phoneTextView.setText(user.getPhone());
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeUser();
    }
}
