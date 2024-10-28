package com.example.projectprn392flashcard.fragment.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.activity.deck.AllDeckActivity;
import com.example.projectprn392flashcard.adapter.DeckRecycleViewAdapter;
import com.example.projectprn392flashcard.data.dao.DeckDAO;
import com.example.projectprn392flashcard.data.dao.DeckStarDAO;
import com.example.projectprn392flashcard.data.entity.Deck;

import java.util.List;

public class UserHomeFragment extends Fragment {

    private DeckRecycleViewAdapter adapterDecks, adapterStarDecks, adapterMyDecks;
    private RecyclerView recyclerViewDecks, recyclerViewStarDecks, recyclerViewMyDecks;
    private TextView btnViewAllDecks, btnViewAllStarDecks, btnViewAllMyDecks;
    private DeckDAO deckDAO;
    private DeckStarDAO deckStarDAO;
    private List<Deck> deckList, starDeckList, myDeckList;
    private int userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setupRepositories();
        setupAdapters();
        setupRecyclerViews();
        setupButtonListeners();
        fetchData();
    }

    private void initializeViews(View view) {
        recyclerViewDecks = view.findViewById(R.id.rcDeck);
        recyclerViewStarDecks = view.findViewById(R.id.rcStarDeck);
        recyclerViewMyDecks = view.findViewById(R.id.rcMyDeck);
        btnViewAllDecks = view.findViewById(R.id.viewAllDecks);
        btnViewAllStarDecks = view.findViewById(R.id.viewAllStarDecks);
        btnViewAllMyDecks = view.findViewById(R.id.viewAllMyDecks);
    }

    private void setupRepositories() {
        deckDAO = new DeckDAO(getContext());
        deckStarDAO = new DeckStarDAO(getContext());
    }

    private void setupAdapters() {
        adapterDecks = new DeckRecycleViewAdapter(getContext(), deckList);
        adapterStarDecks = new DeckRecycleViewAdapter(getContext(), starDeckList);
        adapterMyDecks = new DeckRecycleViewAdapter(getContext(), myDeckList);
    }

    private void setupRecyclerViews() {
        recyclerViewDecks.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewDecks.setAdapter(adapterDecks);

        recyclerViewStarDecks.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewStarDecks.setAdapter(adapterStarDecks);

        recyclerViewMyDecks.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewMyDecks.setAdapter(adapterMyDecks);
    }

    private void setupButtonListeners() {
        btnViewAllDecks.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AllDeckActivity.class);
            intent.putExtra("type", "all");
            startActivity(intent);
        });

        btnViewAllStarDecks.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AllDeckActivity.class);
            intent.putExtra("type", "star");
            startActivity(intent);
        });

        btnViewAllMyDecks.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AllDeckActivity.class);
            intent.putExtra("type", "my");
            startActivity(intent);
        });
    }

    private void fetchData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LoginState", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", 0);

        deckList = deckDAO.getAll();
        starDeckList = deckStarDAO.getDeckByStar(userId, 1);
        myDeckList = deckDAO.getDeckByUserId(userId); // Assuming you have this method

        adapterDecks.setList(deckList);
        adapterStarDecks.setList(starDeckList);
        adapterMyDecks.setList(myDeckList);

        adapterDecks.notifyDataSetChanged();
        adapterStarDecks.notifyDataSetChanged();
        adapterMyDecks.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData();
    }
}







//package com.example.projectprn392flashcard.fragment.user;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.projectprn392flashcard.R;
//import com.example.projectprn392flashcard.activity.deck.AllDeckActivity;
//import com.example.projectprn392flashcard.adapter.DeckRecycleViewAdapter;
//import com.example.projectprn392flashcard.data.dao.DeckDAO;
//import com.example.projectprn392flashcard.data.dao.DeckStarDAO;
//import com.example.projectprn392flashcard.data.entity.Deck;
//
//import java.util.List;
//
//public class UserHomeFragment extends Fragment {
//
//    private DeckRecycleViewAdapter adapterDecks, adapterStarDecks;
//    private RecyclerView recyclerViewDecks, recyclerViewStarDecks;
//    private TextView btnViewAllDecks, btnViewAllStarDecks;
//    private DeckDAO deckDAO;
//    private DeckStarDAO deckStarDAO;
//    private List<Deck> deckList, starDeckList;
//    private int userId;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_user_home, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        initializeViews(view);
//        setupRepositories();
//        setupAdapters();
//        setupRecyclerViews();
//        setupButtonListeners();
//        fetchData();
//    }
//
//    private void initializeViews(View view) {
//        recyclerViewDecks = view.findViewById(R.id.rcDeck);
//        recyclerViewStarDecks = view.findViewById(R.id.rcStarDeck);
//        btnViewAllDecks = view.findViewById(R.id.viewAllDecks);
//        btnViewAllStarDecks = view.findViewById(R.id.viewAllStarDecks);
//    }
//
//    private void setupRepositories() {
//        deckDAO = new DeckDAO(getContext());
//        deckStarDAO = new DeckStarDAO(getContext());
//    }
//
//    private void setupAdapters() {
//        adapterDecks = new DeckRecycleViewAdapter(getContext(), deckList);
//        adapterStarDecks = new DeckRecycleViewAdapter(getContext(), starDeckList);
//    }
//
//    private void setupRecyclerViews() {
//        recyclerViewDecks.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        recyclerViewDecks.setAdapter(adapterDecks);
//
//        recyclerViewStarDecks.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        recyclerViewStarDecks.setAdapter(adapterStarDecks);
//    }
//
//    private void setupButtonListeners() {
//        btnViewAllDecks.setOnClickListener(v -> {
//            Intent intent = new Intent(getContext(), AllDeckActivity.class);
//            intent.putExtra("type", "all");
//            startActivity(intent);
//        });
//
//        btnViewAllStarDecks.setOnClickListener(v -> {
//            Intent intent = new Intent(getContext(), AllDeckActivity.class);
//            intent.putExtra("type", "star");
//            startActivity(intent);
//        });
//    }
//
//    private void fetchData() {
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LoginState", Context.MODE_PRIVATE);
//        userId = sharedPreferences.getInt("userId", 0);
//
//        deckList = deckDAO.getAll();
//        starDeckList = deckStarDAO.getDeckByStar(userId, 1);
//
//        adapterDecks.setList(deckList);
//        adapterStarDecks.setList(starDeckList);
//
//        adapterDecks.notifyDataSetChanged();
//        adapterStarDecks.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        fetchData();
//    }
//}
