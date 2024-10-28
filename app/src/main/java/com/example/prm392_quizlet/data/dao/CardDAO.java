package com.example.projectprn392flashcard.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectprn392flashcard.data.SQLiteHelper;
import com.example.projectprn392flashcard.data.entity.Card;

import java.util.ArrayList;
import java.util.List;

public class CardDAO {
    SQLiteHelper db;
    SQLiteDatabase sqLiteDatabase;

    public CardDAO(Context context) {
        this.db = new SQLiteHelper(context);
        sqLiteDatabase = db.getWritableDatabase();
    }

    //insert card
    public long insertCard(Card card){
        sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("front", card.getFront());
        contentValues.put("back", card.getBack());
        contentValues.put("is_learning", card.getIsLearned());
        contentValues.put("deck_id", card.getDeck_id());
        long result = sqLiteDatabase.insert("card", null, contentValues);
        return result;
    }

    //update card
    public void updateCard(Card card){
        sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("front", card.getFront());
        contentValues.put("back", card.getBack());
        contentValues.put("is_learning", card.getIsLearned());
        contentValues.put("deck_id", card.getDeck_id());
        sqLiteDatabase.update("card", contentValues, "id = ?", new String[]{String.valueOf(card.getId())});
    }

    public List<Card> getCardByDeckId(int deckId){
        sqLiteDatabase= db.getReadableDatabase();
        List<Card> list= new ArrayList<>();
        String query = "SELECT * FROM card WHERE deck_id = "+deckId;
        try (android.database.Cursor cursor = sqLiteDatabase.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Card card = new Card();
                    card.setId(cursor.getInt(0));
                    card.setFront(cursor.getString(1));
                    card.setBack(cursor.getString(2));
                    card.setDeck_id(cursor.getInt(3));
                    card.setIsLearned(cursor.getInt(4));
                    list.add(card);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }
//  public void updateFeedback(int feedbackId, String feedback){
//        sqLiteDatabase = db.getWritableDatabase();
//        String query = "UPDATE feedback SET feedback = '"+feedback+"' WHERE id = "+feedbackId;
//        sqLiteDatabase.execSQL(query);
//    }
//
//    public void getAllFeedback(){
//        sqLiteDatabase = db.getReadableDatabase();
//        String query = "SELECT * FROM feedback";
//        sqLiteDatabase.execSQL(query);
//    }
//
//    public void getFeedbackById(int feedbackId){
//        sqLiteDatabase = db.getReadableDatabase();
//        String query = "SELECT * FROM feedback WHERE id = "+feedbackId;
//        sqLiteDatabase.execSQL(query);
//    }
    public List<Card> getAllCard(){
        sqLiteDatabase= db.getReadableDatabase();
        List<Card> list= new ArrayList<>();
        String query = "SELECT * FROM card";
        try (android.database.Cursor cursor = sqLiteDatabase.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Card card = new Card();
                    card.setId(cursor.getInt(0));
                    card.setFront(cursor.getString(1));
                    card.setBack(cursor.getString(2));
                    card.setDeck_id(cursor.getInt(3));
                    card.setIsLearned(cursor.getInt(4));
                    list.add(card);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    public void deleteCard(int cardId){
        sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.delete("card", "id = ?", new String[]{String.valueOf(cardId)});
    }


}

