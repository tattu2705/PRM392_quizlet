package com.example.projectprn392flashcard.activity.deck;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.data.dao.DeckDAO;
import com.example.projectprn392flashcard.data.entity.Deck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;



public class EditDeckActivity extends AppCompatActivity {

    private EditText deckNameEditText, deckDescriptionEditText;
    private Button saveButton, cancelButton;
    private Deck currentDeck;
    private DeckDAO deckDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_deck);

        initializeViews();
        setupDeckDetails();
        setupButtonListeners();
    }

    private void initializeViews() {
        deckNameEditText = findViewById(R.id.et_deck_name);
        deckDescriptionEditText = findViewById(R.id.et_deck_description);
        saveButton = findViewById(R.id.btn_save);
        cancelButton = findViewById(R.id.btn_cancel);
        deckDatabase = new DeckDAO(this);
    }

    private void setupDeckDetails() {
        Intent intent = getIntent();
        if (intent.getSerializableExtra("deck") != null) {
            currentDeck = (Deck) intent.getSerializableExtra("deck");
        }

        deckNameEditText.setText(currentDeck.getName());
        deckDescriptionEditText.setText(currentDeck.getDescription());
    }

    private void setupButtonListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDeck();
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveDeck() {
        currentDeck.setName(deckNameEditText.getText().toString());
        currentDeck.setDescription(deckDescriptionEditText.getText().toString());
        deckDatabase.updateDeck(currentDeck);
    }
}
