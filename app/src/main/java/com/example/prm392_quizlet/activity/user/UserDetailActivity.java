package com.example.projectprn392flashcard.activity.user;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.data.dao.UserDAO;
import com.example.projectprn392flashcard.data.entity.User;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;


public class UserDetailActivity extends AppCompatActivity {

    private TextView usernameTextView, nameTextView, emailTextView, phoneTextView;
    private Button resetPasswordButton, deleteAccountButton, cancelButton;
    private User user;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        initializeViews();
        userDAO = new UserDAO(this);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("user") != null) {
            user = (User) intent.getSerializableExtra("user");
        }

        populateUserDetails();

        resetPasswordButton.setOnClickListener(v -> resetPassword());
        deleteAccountButton.setOnClickListener(v -> deleteAccount());
        cancelButton.setOnClickListener(v -> finish());
    }

    private void initializeViews() {
        usernameTextView = findViewById(R.id.usernameTextView);
        nameTextView = findViewById(R.id.name);
        emailTextView = findViewById(R.id.emailTextView);
        phoneTextView = findViewById(R.id.phone);
        resetPasswordButton = findViewById(R.id.btn_reset_password);
        deleteAccountButton = findViewById(R.id.btn_delete_account);
        cancelButton = findViewById(R.id.btn_cancel);
    }

    private void populateUserDetails() {
        if (user != null) {
            usernameTextView.setText(user.getUsername());
            nameTextView.setText(user.getName());
            emailTextView.setText(user.getEmail());
            phoneTextView.setText(user.getPhone());
        }
    }

    private void resetPassword() {
        user.setPassword("123456");
        userDAO.updateUser(user);
        Toast.makeText(this, "Password reset successful", Toast.LENGTH_SHORT).show();
    }

    private void deleteAccount() {
        user.setStatus(0);
        userDAO.updateUser(user);
        Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}


