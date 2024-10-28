package com.example.projectprn392flashcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprn392flashcard.activity.AdminHomeActivity;
import com.example.projectprn392flashcard.activity.LoginActivity;
import com.example.projectprn392flashcard.activity.UserHomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("LoginState", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        String role = sharedPreferences.getString("role", "");

        if (isLoggedIn) {
            navigateToHomeActivity(role);
        } else {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }

    private void navigateToHomeActivity(String role) {
        Intent intent;
        if (role.equals("admin")) {
            intent = new Intent(MainActivity.this, AdminHomeActivity.class);
        } else {
            intent = new Intent(MainActivity.this, UserHomeActivity.class);
        }
        startActivity(intent);
        finish();
    }
}