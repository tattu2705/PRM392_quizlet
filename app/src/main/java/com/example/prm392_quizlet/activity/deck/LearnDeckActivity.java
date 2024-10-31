
package com.example.projectprn392flashcard.activity.deck;

import com.example.projectprn392flashcard.R;
import com.example.projectprn392flashcard.data.dao.CardDAO;
import com.example.projectprn392flashcard.data.entity.Card;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import java.util.List;

public class LearnDeckActivity extends AppCompatActivity {
    private AnimatorSet rightOutAnimator;
    private AnimatorSet leftInAnimator;

    private TextView cardTextView, progressTextView;
    private Button nextButton, prevButton;
    private ImageView backButton;
    private FrameLayout cardFrameLayout;
    private CardView cardView;
    private List<Card> cardList;
    private int currentCardIndex = 0;
    private boolean isFrontVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_deck);

        initializeViews();
        loadDeckCards();
        setupCardFlipAnimations();
        setupButtonListeners();
        updateUI();
    }

    private void initializeViews() {
        cardTextView = findViewById(R.id.cardTextView);
        progressTextView = findViewById(R.id.progressTextView);
        nextButton = findViewById(R.id.nextButton);
        prevButton = findViewById(R.id.prevButton);
        cardView = findViewById(R.id.cardView);
        cardFrameLayout = findViewById(R.id.cardFrameLayout);
        backButton = findViewById(R.id.backButton);
    }

    private void loadDeckCards() {
        long deckId = getIntent().getLongExtra("DECK_ID", -1);
        CardDAO cardDAO = new CardDAO(this);
        cardList = cardDAO.getCardByDeckId((int)deckId);
    }

    private void setupCardFlipAnimations() {
        rightOutAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.out_animation);
        leftInAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.in_animation);
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        cardView.setCameraDistance(scale);

        cardFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
                updateCardText();
            }
        });
    }

    private void setupButtonListeners() {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextCard();
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreviousCard();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateUI() {
        if (!cardList.isEmpty()) {
            updateCardText();
            updateProgressText();
        }
    }

    private void flipCard() {
        if (isFrontVisible) {
            rightOutAnimator.setTarget(cardFrameLayout);
            leftInAnimator.setTarget(cardFrameLayout);
            rightOutAnimator.start();
            leftInAnimator.start();
            isFrontVisible = false;
        } else {
            rightOutAnimator.setTarget(cardFrameLayout);
            leftInAnimator.setTarget(cardFrameLayout);
            rightOutAnimator.start();
            leftInAnimator.start();
            isFrontVisible = true;
        }
    }

    private void updateCardText() {
        Card currentCard = cardList.get(currentCardIndex);
        if (isFrontVisible) {
            cardTextView.setText(currentCard.getFront());
        } else {
            cardTextView.setText(currentCard.getBack());
        }
    }

    private void updateProgressText() {
        String progress = (currentCardIndex + 1) + "/" + cardList.size();
        progressTextView.setText(progress);
    }

    private void showNextCard() {
        if (currentCardIndex < cardList.size() - 1) {
            currentCardIndex++;
            isFrontVisible = true;
            updateCardText();
            updateProgressText();
        }
    }

    private void showPreviousCard() {
        if (currentCardIndex > 0) {
            currentCardIndex--;
            isFrontVisible = true;
            updateCardText();
            updateProgressText();
        }
    }
}

