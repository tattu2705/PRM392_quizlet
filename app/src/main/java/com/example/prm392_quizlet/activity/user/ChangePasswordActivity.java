package com.example.projectprn392flashcard.activity.user;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.data.dao.UserDAO;
import com.example.projectprn392flashcard.data.entity.User;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText currentPasswordEditText, newPasswordEditText, confirmPasswordEditText;
    private Button changePasswordButton, cancelButton;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initializeViews();
        userDAO = new UserDAO(this);

        SharedPreferences sharedPreferences = getSharedPreferences("LoginState", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        User user = userDAO.getUserById(userId);

        changePasswordButton.setOnClickListener(v -> changePassword(user));
    }

    private void initializeViews() {
        currentPasswordEditText = findViewById(R.id.current_password);
        newPasswordEditText = findViewById(R.id.new_password);
        confirmPasswordEditText = findViewById(R.id.confirm_password);
        changePasswordButton = findViewById(R.id.btn_change);
        cancelButton = findViewById(R.id.btn_cancel);

        cancelButton.setOnClickListener(v -> finish());
    }

    private void changePassword(User user) {
        String currentPassword = currentPasswordEditText.getText().toString();
        String newPassword = newPasswordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (!currentPassword.equals(user.getPassword())) {
            Toast.makeText(this, "Current password is incorrect", Toast.LENGTH_SHORT).show();
        } else if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "New password and confirm password do not match", Toast.LENGTH_SHORT).show();
        } else {
            user.setPassword(newPassword);
            userDAO.updateUser(user);
            Toast.makeText(this, "Password has been changed", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
