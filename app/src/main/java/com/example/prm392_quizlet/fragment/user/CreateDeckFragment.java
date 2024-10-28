package com.example.projectprn392flashcard.fragment.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.activity.card.CreateCardActivity;
import com.example.projectprn392flashcard.data.dao.DeckDAO;
import com.example.projectprn392flashcard.data.entity.Deck;

public class CreateDeckFragment extends Fragment {

    private EditText etDeckName, etDeckDescription;
    private Button btnCreateDeck;
    private DeckDAO deckDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_deck, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setupDeckDAO();
        setupListeners();
    }

    private void initializeViews(View view) {
        etDeckName = view.findViewById(R.id.et_deck_name);
        etDeckDescription = view.findViewById(R.id.et_deck_description);
        btnCreateDeck = view.findViewById(R.id.btn_add_deck);
    }

    private void setupDeckDAO() {
        deckDAO = new DeckDAO(getContext());
    }

    private void setupListeners() {
        btnCreateDeck.setOnClickListener(v -> {
            String deckName = etDeckName.getText().toString();
            String deckDescription = etDeckDescription.getText().toString();

            if (isValidInput(deckName, deckDescription)) {
                int userId = getUserIdFromPreferences();
                Deck deck = new Deck(deckName, deckDescription, userId, 0);
                long deckId = deckDAO.insertDeck(deck);
                navigateToCreateCardActivity(deckId);
                clearInputFields();
            } else {
                showInvalidInputMessage();
            }
        });
    }

    private boolean isValidInput(String deckName, String deckDescription) {
        return !deckName.isEmpty() && !deckDescription.isEmpty();
    }

    private int getUserIdFromPreferences() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("LoginState", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("userId", 0);
    }

    private void navigateToCreateCardActivity(long deckId) {
        Intent intent = new Intent(getActivity(), CreateCardActivity.class);
        intent.putExtra("DECK_ID", deckId);
        startActivity(intent);
    }

    private void clearInputFields() {
        etDeckName.setText("");
        etDeckDescription.setText("");
    }

    private void showInvalidInputMessage() {
        // You can use Toast or Dialog to show an invalid input message
        // Example:
        Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
    }
}
