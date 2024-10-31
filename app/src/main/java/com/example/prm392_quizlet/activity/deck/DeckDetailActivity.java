package com.example.projectprn392flashcard.activity.deck;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.activity.card.CreateCardActivity;
import com.example.projectprn392flashcard.adapter.CardRecycleViewAdapter;
import com.example.projectprn392flashcard.data.dao.*;
import com.example.projectprn392flashcard.data.entity.*;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class DeckDetailActivity extends AppCompatActivity {

    private Deck currentDeck;
    private TextView deckNameTextView, deckDescriptionTextView;
    private List<Card> cardList;
    private DeckDAO deckDatabase;
    private CardDAO cardDatabase;
    private RecyclerView cardRecyclerView;
    private CardRecycleViewAdapter cardAdapter;
    private Button learnButton;
    private ImageView starImageView;
    private DeckStarDAO deckStarDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_detail);

        initializeViews();

        Intent intent = getIntent();
        if (intent.getSerializableExtra("deck") != null) {
            currentDeck = (Deck) intent.getSerializableExtra("deck");
        }

        populateDeckDetails();
        setupRecyclerView();

        SharedPreferences sharedPreferences = getSharedPreferences("LoginState", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);

        setupStarButton(userId);
        setupLearnButton();
    }

    private void initializeViews() {
        deckNameTextView = findViewById(R.id.tv_deck_name);
        deckDescriptionTextView = findViewById(R.id.tv_deck_description);
        cardRecyclerView = findViewById(R.id.rv_card_list);
        starImageView = findViewById(R.id.iv_star);
        learnButton = findViewById(R.id.bt_learn_deck);
        deckDatabase = new DeckDAO(this);
        cardDatabase = new CardDAO(this);
        deckStarDatabase = new DeckStarDAO(this);
    }

    private void populateDeckDetails() {
        deckNameTextView.setText(currentDeck.getName());
        deckDescriptionTextView.setText(currentDeck.getDescription());
    }

    private void setupRecyclerView() {
        cardList = cardDatabase.getCardByDeckId(currentDeck.getId());
        cardAdapter = new CardRecycleViewAdapter(this, cardList);
        cardRecyclerView.setAdapter(cardAdapter);
        cardRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void setupStarButton(int userId) {
        if (deckStarDatabase.getStar(currentDeck.getId(), userId) == 1) {
            starImageView.setImageResource(R.drawable.baseline_star_24);
        } else {
            starImageView.setImageResource(R.drawable.baseline_star_border_24);
        }

        starImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleStarStatus(userId);
            }
        });
    }

    private void toggleStarStatus(int userId) {
        int currentStarStatus = deckStarDatabase.getStar(currentDeck.getId(), userId);
        if (currentStarStatus == 0) {
            deckStarDatabase.update(currentDeck.getId(), userId, 1);
            starImageView.setImageResource(R.drawable.baseline_star_24);
        } else if (currentStarStatus == -1) {
            deckStarDatabase.insert(currentDeck.getId(), userId, 1);
            starImageView.setImageResource(R.drawable.baseline_star_24);
        } else {
            deckStarDatabase.update(currentDeck.getId(), userId, 0);
            starImageView.setImageResource(R.drawable.baseline_star_border_24);
        }
    }

    private void setupLearnButton() {
        learnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLearnDeckActivity();
            }
        });
    }

    private void startLearnDeckActivity() {
        Intent learnIntent = new Intent(DeckDetailActivity.this, LearnDeckActivity.class);
        learnIntent.putExtra("DECK_ID", (long) currentDeck.getId());
        startActivity(learnIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.deck_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int userId = getSharedPreferences("LoginState", MODE_PRIVATE).getInt("userId", 0);
        if (item.getItemId() == R.id.action_edit_deck) {
            handleEditDeckOption(userId);
            return true;
        } else if (item.getItemId() == R.id.action_delete_deck) {
            confirmDeleteDeck(userId);
            return true;
        } else if (item.getItemId() == R.id.action_add_card) {
            handleAddCardOption(userId);
            return true;
        }
        return super.onOptionsItemSelected(item);

//        switch (item.getItemId()) {
//            case R.id.action_edit_deck:
//                handleEditDeckOption(userId);
//                return true;
//            case R.id.action_delete_deck:
//                confirmDeleteDeck(userId);
//                return true;
//            case R.id.action_add_card:
//                handleAddCardOption(userId);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }

    }

    private void handleEditDeckOption(int userId) {
        if (currentDeck.getUser_id() != userId) {
            Toast.makeText(DeckDetailActivity.this, "You can't edit this deck", Toast.LENGTH_SHORT).show();
        } else {
            Intent editIntent = new Intent(DeckDetailActivity.this, EditDeckActivity.class);
            editIntent.putExtra("deck", currentDeck);
            startActivity(editIntent);
        }
    }

    private void confirmDeleteDeck(int userId) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Deck")
                .setMessage("Are you sure you want to delete this deck?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        handleDeleteDeck(userId);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void handleDeleteDeck(int userId) {
        if (currentDeck.getUser_id() != userId) {
            Toast.makeText(DeckDetailActivity.this, "You can't delete this deck", Toast.LENGTH_SHORT).show();
        } else {
            deckDatabase.deleteDeck(currentDeck.getId());
            finish();
        }
    }

    private void handleAddCardOption(int userId) {
        if (currentDeck.getUser_id() != userId) {
            Toast.makeText(DeckDetailActivity.this, "You can't edit this deck", Toast.LENGTH_SHORT).show();
        } else {
            Intent createCardIntent = new Intent(DeckDetailActivity.this, CreateCardActivity.class);
            createCardIntent.putExtra("DECK_ID", (long) currentDeck.getId());
            startActivity(createCardIntent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshDeckDetails();
    }

    private void refreshDeckDetails() {
        cardList = cardDatabase.getCardByDeckId(currentDeck.getId());
        cardAdapter.setList(cardList);
        currentDeck = deckDatabase.getDeckById(currentDeck.getId());
        deckNameTextView.setText(currentDeck.getName());
        deckDescriptionTextView.setText(currentDeck.getDescription());
        cardAdapter.notifyDataSetChanged();
    }
}
