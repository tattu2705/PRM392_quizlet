package com.example.projectprn392flashcard.activity.deck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.adapter.DeckFullRecycleViewAdapter;
import com.example.projectprn392flashcard.data.dao.DeckDAO;
import com.example.projectprn392flashcard.data.dao.DeckStarDAO;
import com.example.projectprn392flashcard.data.entity.Deck;

import java.util.List;

public class AllDeckActivity extends AppCompatActivity {

    private TextView titleTextView;
    private RecyclerView deckRecyclerView;
    private DeckDAO deckDatabase;
    private DeckStarDAO deckStarDatabase;
    private DeckFullRecycleViewAdapter deckAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_deck);

        initializeViews();

        SharedPreferences sharedPreferences = getSharedPreferences("LoginState", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);

        handleIntent(getIntent(), userId);
    }

    private void initializeViews() {
        titleTextView = findViewById(R.id.title);
        deckRecyclerView = findViewById(R.id.rcDeck);
        deckDatabase = new DeckDAO(this);
        deckStarDatabase = new DeckStarDAO(this);
    }

    private void handleIntent(Intent intent, int userId) {
        if (intent.getStringExtra("type") != null) {
            String type = intent.getStringExtra("type");
            List<Deck> decks;
            switch (type) {
                case "all":
                    titleTextView.setText("All Decks");
                    decks = deckDatabase.getAll();
                    break;
                case "star":
                    titleTextView.setText("Star Decks");
                    decks = deckStarDatabase.getDeckByStar(userId, 1);
                    break;
                case "my":
                    titleTextView.setText("My Decks");
                    decks = deckDatabase.getDeckByUserId(userId);
                    break;
                default:
                    decks = null;
                    break;
            }
            deckAdapter = new DeckFullRecycleViewAdapter(this, decks);
            setupRecyclerView();
        }
    }

    private void setupRecyclerView() {
        deckRecyclerView.setAdapter(deckAdapter);
        deckRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
