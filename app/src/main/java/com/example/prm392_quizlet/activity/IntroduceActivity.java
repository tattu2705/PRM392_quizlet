package com.example.projectprn392flashcard.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprn392flashcard.R;

public class IntroduceActivity extends AppCompatActivity {

    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);

        cancelButton = findViewById(R.id.cancelButton);

        cancelButton.setOnClickListener(v -> finish());
    }
}