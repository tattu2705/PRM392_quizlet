package com.example.projectprn392flashcard.activity.feedback;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.data.dao.FeedbackDAO;
import com.example.projectprn392flashcard.data.entity.Feedback;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SendFeedbackActivity extends AppCompatActivity {

    private EditText titleEditText, contentEditText;
    private Button submitButton, cancelButton;
    private FeedbackDAO feedbackDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);

        initializeViews();
        setupSubmitButton();
        setupCancelButton();
    }

    private void initializeViews() {
        titleEditText = findViewById(R.id.et_title);
        contentEditText = findViewById(R.id.contentEditText);
        submitButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
        feedbackDAO = new FeedbackDAO(this);
    }

    private void setupSubmitButton() {
        SharedPreferences sharedPreferences = getSharedPreferences("LoginState", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();
                String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

                Feedback feedback = new Feedback(title, content, userId, currentDate, "");
                feedbackDAO.addFeedback(feedback);
                Toast.makeText(SendFeedbackActivity.this, "Feedback sent", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setupCancelButton() {
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
