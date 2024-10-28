package com.example.projectprn392flashcard.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectprn392flashcard.data.SQLiteHelper;
import com.example.projectprn392flashcard.data.entity.Deck;

import java.util.ArrayList;
import java.util.List;

public class DeckDAO {
    SQLiteHelper db;
    SQLiteDatabase sqLiteDatabase;

    public DeckDAO(Context context) {
        this.db = new SQLiteHelper(context);
        sqLiteDatabase = db.getWritableDatabase();
    }

    //insert deck
    public long insertDeck(Deck deck){
        sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", deck.getName());
        contentValues.put("description", deck.getDescription());
        contentValues.put("user_id", deck.getUser_id());
        contentValues.put("star", deck.getStar());
        long result = sqLiteDatabase.insert("deck", null, contentValues);
        return result;
    }

    public Deck getDeckById(int id){
        sqLiteDatabase= db.getReadableDatabase();
        String query = "SELECT * FROM deck WHERE id = "+id;
        try (android.database.Cursor cursor = sqLiteDatabase.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                Deck deck = new Deck();
                deck.setId(cursor.getInt(0));
                deck.setName(cursor.getString(1));
                deck.setDescription(cursor.getString(2));
                deck.setStar(cursor.getInt(3));
                deck.setUser_id(cursor.getInt(4));
                return deck;
            }
        }
        return null;
    }

    //updateDeck
    public void updateDeck(Deck deck){
        sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", deck.getName());
        contentValues.put("description", deck.getDescription());
        contentValues.put("star", deck.getStar());
        sqLiteDatabase.update("deck", contentValues, "id = ?", new String[]{String.valueOf(deck.getId())});
    }

    public List<Deck> getDeckByUserId(int userId){
        sqLiteDatabase= db.getReadableDatabase();
        List<Deck> list= new ArrayList<>();
        String query = "SELECT * FROM deck WHERE user_id = "+userId;
        try (android.database.Cursor cursor = sqLiteDatabase.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Deck deck = new Deck();
                    deck.setId(cursor.getInt(0));
                    deck.setName(cursor.getString(1));
                    deck.setDescription(cursor.getString(2));
                    deck.setStar(cursor.getInt(3));
                    deck.setUser_id(cursor.getInt(4));
                    list.add(deck);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    //delete deck
    public void deleteDeck(int deckId){
        sqLiteDatabase = db.getWritableDatabase();
        //delete all card in deck
        sqLiteDatabase.delete("card", "deck_id = ?", new String[]{String.valueOf(deckId)});
        sqLiteDatabase.delete("deck", "id = ?", new String[]{String.valueOf(deckId)});
    }

    public List<Deck> getDeckByStar(int star){
        sqLiteDatabase= db.getReadableDatabase();
        List<Deck> list= new ArrayList<>();
        String query = "SELECT * FROM deck WHERE star = "+star;
        try (android.database.Cursor cursor = sqLiteDatabase.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Deck deck = new Deck();
                    deck.setId(cursor.getInt(0));
                    deck.setName(cursor.getString(1));
                    deck.setDescription(cursor.getString(2));
                    deck.setStar(cursor.getInt(3));
                    deck.setUser_id(cursor.getInt(4));
                    list.add(deck);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    public int getNumberCardInDeck(int deckId){
        sqLiteDatabase= db.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM card WHERE deck_id = "+deckId;
        try (android.database.Cursor cursor = sqLiteDatabase.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                return cursor.getInt(0);
            }
        }
        return 0;
    }

    public String getUserNameOfDeck(int deckId){
        sqLiteDatabase= db.getReadableDatabase();
        String query = "SELECT user.name FROM user, deck WHERE user.id = deck.user_id AND deck.id = "+deckId;
        try (android.database.Cursor cursor = sqLiteDatabase.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                return cursor.getString(0);
            }
        }
        return null;
    }

    public List<Deck> getAll(){
        sqLiteDatabase= db.getReadableDatabase();
        List<Deck> list= new ArrayList<>();
        String query = "SELECT * FROM deck";
        try (android.database.Cursor cursor = sqLiteDatabase.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Deck deck = new Deck();
                    deck.setId(cursor.getInt(0));
                    deck.setName(cursor.getString(1));
                    deck.setDescription(cursor.getString(2));
                    deck.setStar(cursor.getInt(3));
                    deck.setUser_id(cursor.getInt(4));
                    list.add(deck);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    public List<Deck> getDeckByName(String name){
        sqLiteDatabase= db.getReadableDatabase();
        List<Deck> list= new ArrayList<>();
        String query = "SELECT * FROM deck WHERE name LIKE '%"+name+"%'";
        try (android.database.Cursor cursor = sqLiteDatabase.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Deck deck = new Deck();
                    deck.setId(cursor.getInt(0));
                    deck.setName(cursor.getString(1));
                    deck.setDescription(cursor.getString(2));
                    deck.setStar(cursor.getInt(3));
                    deck.setUser_id(cursor.getInt(4));
                    list.add(deck);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }
}
// public void insert(int deckId, int userId, int star){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("deck_id", deckId);
//        contentValues.put("user_id", userId);
//        contentValues.put("star", star);
//        sqLiteDatabase.insert(SQLiteHelper.TABLE_DECKS_STAR, null, contentValues);
//    }
//
//    public void update(int deckId, int userId, int star){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("star", star);
//        sqLiteDatabase.update(SQLiteHelper.TABLE_DECKS_STAR, contentValues, "deck_id = ? AND user_id = ?", new String[]{String.valueOf(deckId), String.valueOf(userId)});
//    }