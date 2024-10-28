package com.example.projectprn392flashcard.data.entity;

import java.io.Serializable;

public class Card implements Serializable {
    private int id;
    private String front;
    private String back;
    private int isLearned;
    private int deck_id;

    public Card() {
    }

    public Card(int id, String front, String back, int isLearned, int deckId) {
        this.id = id;
        this.front = front;
        this.back = back;
        this.isLearned = isLearned;
        this.deck_id = deckId;

    }

    public Card(String front, String back, int deckId) {
        this.front = front;
        this.back = back;
        this.deck_id= deckId;
        this.isLearned= 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public int getIsLearned() {
        return isLearned;
    }

    public void setIsLearned(int isLearned) {
        this.isLearned = isLearned;
    }

    public int getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(int deck_id) {
        this.deck_id = deck_id;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", front='" + front + '\'' +
                ", back='" + back + '\'' +
                ", isLearned=" + isLearned +
                ", deck_id=" + deck_id +
                '}';
    }
}
