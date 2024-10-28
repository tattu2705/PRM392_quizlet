package com.example.projectprn392flashcard.activity.card;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.data.dao.CardDAO;
import com.example.projectprn392flashcard.data.entity.Card;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class EditCardActivity extends AppCompatActivity {

    private EditText frontEditText, backEditText;
    private Button saveButton, cancelButton;
    private Card currentCard;
    private CardDAO cardDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);

        initializeViews();

        Intent intent = getIntent();
        if (intent.getSerializableExtra("card") != null) {
            currentCard = (Card) intent.getSerializableExtra("card");
        }
        System.out.println(currentCard.toString());

        populateCardData();

        setupButtonListeners();
    }

    private void initializeViews() {
        frontEditText = findViewById(R.id.et_deck_name);
        backEditText = findViewById(R.id.et_deck_description);
        saveButton = findViewById(R.id.btn_save);
        cancelButton = findViewById(R.id.btn_cancel);
        cardDatabase = new CardDAO(this);
    }

    private void populateCardData() {
        if (currentCard != null) {
            frontEditText.setText(currentCard.getFront());
            backEditText.setText(currentCard.getBack());
        }
    }

    private void setupButtonListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCard();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveCard() {
        if (currentCard != null) {
            currentCard.setFront(frontEditText.getText().toString());
            currentCard.setBack(backEditText.getText().toString());
            cardDatabase.updateCard(currentCard);
            finish();
        }
    }
}

