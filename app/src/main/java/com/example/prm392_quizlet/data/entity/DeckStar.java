package com.example.projectprn392flashcard.data.entity;

public class DeckStar {
    private int id;
    private int deckId;
    private int userId;
    private int star;

    public DeckStar(int id, int deckId, int userId, int star) {
        this.id = id;
        this.deckId = deckId;
        this.userId = userId;
        this.star = star;
    }

    public DeckStar(int deckId, int userId, int star) {
        this.deckId = deckId;
        this.userId = userId;
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public int getDeckId() {
        return deckId;
    }

    public int getUserId() {
        return userId;
    }

    public int getStar() {
        return star;
    }
}
