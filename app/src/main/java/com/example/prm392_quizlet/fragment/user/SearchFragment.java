package com.example.projectprn392flashcard.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.adapter.DeckFullRecycleViewAdapter;
import com.example.projectprn392flashcard.data.dao.DeckDAO;
import com.example.projectprn392flashcard.data.entity.Deck;

import java.util.List;

public class SearchFragment extends Fragment {

    private EditText etSearchDeck;
    private Button btnSearch;
    private RecyclerView recyclerViewResults;
    private DeckDAO deckRepository;
    private DeckFullRecycleViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setupDeckRepository();
        setupSearchButtonListener();
    }

    private void initializeViews(View view) {
        etSearchDeck = view.findViewById(R.id.search_deck);
        btnSearch = view.findViewById(R.id.search_button);
        recyclerViewResults = view.findViewById(R.id.search_results);
    }

    private void setupDeckRepository() {
        deckRepository = new DeckDAO(getContext());
    }

    private void setupSearchButtonListener() {
        btnSearch.setOnClickListener(v -> performSearch());
    }

    private void performSearch() {
        String deckName = etSearchDeck.getText().toString();
        List<Deck> decks = deckRepository.getDeckByName(deckName);

        if (decks != null && !decks.isEmpty()) {
            adapter = new DeckFullRecycleViewAdapter(getContext(), decks);
            recyclerViewResults.setAdapter(adapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            recyclerViewResults.setLayoutManager(layoutManager);
        } else {

        }
    }
}
