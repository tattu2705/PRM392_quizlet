package com.example.projectprn392flashcard.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectprn392flashcard.data.SQLiteHelper;
import com.example.projectprn392flashcard.data.entity.Deck;

import java.util.ArrayList;
import java.util.List;

public class DeckStarDAO {
    SQLiteHelper db;
    SQLiteDatabase sqLiteDatabase;

    DeckDAO deckDAO;

    public DeckStarDAO(Context context) {
        this.db = new SQLiteHelper(context);
        this.deckDAO = new DeckDAO(context);
        sqLiteDatabase = db.getWritableDatabase();
    }

    public void insert(int deckId, int userId, int star){
        ContentValues contentValues = new ContentValues();
        contentValues.put("deck_id", deckId);
        contentValues.put("user_id", userId);
        contentValues.put("star", star);
        sqLiteDatabase.insert(SQLiteHelper.TABLE_DECKS_STAR, null, contentValues);
    }

    public void update(int deckId, int userId, int star){
        ContentValues contentValues = new ContentValues();
        contentValues.put("star", star);
        sqLiteDatabase.update(SQLiteHelper.TABLE_DECKS_STAR, contentValues, "deck_id = ? AND user_id = ?", new String[]{String.valueOf(deckId), String.valueOf(userId)});
    }

    public int getStar(int deckId, int userId){
        sqLiteDatabase = db.getReadableDatabase();
        String query = "SELECT star FROM deck_star WHERE deck_id = '"+deckId+"' AND user_id = '"+userId+"'";
        Cursor rs = sqLiteDatabase.rawQuery(query, null);
        if(rs!= null && rs.moveToNext()){
            return rs.getInt(0);
        }
        return -1;
    }

    public List<Deck> getDeckByStar(int userId, int star){
        sqLiteDatabase = db.getReadableDatabase();
        String query = "SELECT deck_id FROM deck_star WHERE user_id = '"+userId+"' AND star = 1";
        Cursor rs = sqLiteDatabase.rawQuery(query, null);
        List<Deck> decks = new ArrayList<>();
        while(rs!= null && rs.moveToNext()){
            int deckId= rs.getInt(0);
            Deck deck = deckDAO.getDeckById(deckId);
            decks.add(deck);
        }
        return decks;
    }
}
