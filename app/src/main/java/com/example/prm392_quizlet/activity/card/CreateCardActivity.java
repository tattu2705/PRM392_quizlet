package com.example.projectprn392flashcard.activity.card;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.adapter.CardRecycleViewAdapter;
import com.example.projectprn392flashcard.data.dao.CardDAO;
import com.example.projectprn392flashcard.data.dao.DeckDAO;
import com.example.projectprn392flashcard.data.entity.Card;
import com.example.projectprn392flashcard.data.entity.Deck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class CreateCardActivity extends AppCompatActivity {
    private TextView deckNameTextView;
    private EditText frontEditText, backEditText;
    private Button createCardButton, doneButton;
    private RecyclerView cardRecyclerView;
    private CardDAO cardDAO;
    private CardRecycleViewAdapter adapter;
    private long deckId = 0;
    private DeckDAO deckDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);

        initializeViews();

        Intent intent = getIntent();
        if (intent.hasExtra("DECK_ID")) {
            deckId = intent.getLongExtra("DECK_ID", 0);
        }

        Deck deck = deckDAO.getDeckById((int) deckId);
        deckNameTextView.setText(deck.getName());

        setupRecyclerView();
        setupButtons();
    }

    private void initializeViews() {
        deckNameTextView = findViewById(R.id.tv_deck_name);
        frontEditText = findViewById(R.id.et_card_front);
        backEditText = findViewById(R.id.et_card_back);
        createCardButton = findViewById(R.id.btn_create_card);
        doneButton = findViewById(R.id.btn_done);
        cardRecyclerView = findViewById(R.id.rcCard);
        cardDAO = new CardDAO(this);
        deckDAO = new DeckDAO(this);
    }

    private void setupRecyclerView() {
        adapter = new CardRecycleViewAdapter(this, cardDAO.getCardByDeckId((int) deckId));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        cardRecyclerView.setLayoutManager(layoutManager);
        cardRecyclerView.setAdapter(adapter);
    }

    private void setupButtons() {
        createCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCard();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void createCard() {
        String front = frontEditText.getText().toString();
        String back = backEditText.getText().toString();
        cardDAO.insertCard(new Card(front, back, (int) deckId));
        adapter = new CardRecycleViewAdapter(this, cardDAO.getCardByDeckId((int) deckId));
        cardRecyclerView.setAdapter(adapter);
        frontEditText.setText("");
        backEditText.setText("");
    }
}