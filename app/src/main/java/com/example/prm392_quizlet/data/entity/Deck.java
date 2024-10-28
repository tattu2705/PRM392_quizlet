package com.example.projectprn392flashcard.data.entity;

import java.io.Serializable;

public class Deck implements Serializable {
    private int id;
    private String name;
    private String description;
    private int user_id;

    private int star;

    public Deck() {
    }

    public Deck(int id, String name, String description, int user_id, int start) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user_id = user_id;
        this.star = start;
    }

    public Deck(String name, String description, int user_id, int start) {
        this.name = name;
        this.description = description;
        this.user_id = user_id;
        this.star = start;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int start) {
        this.star = start;
    }

}
