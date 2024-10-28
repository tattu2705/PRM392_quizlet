package com.example.projectprn392flashcard.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "flashFuse.db";
    private static final int DATABASE_VERSION = 2;
    public static final String TABLE_USERS = "user";
    public static final String TABLE_DECKS = "deck";
    public static final String TABLE_CARDS = "card";
    public static final String TABLE_FEEDBACKS = "feedback";
    public static final String TABLE_DECKS_STAR = "deck_star";

    //command
    public static final String COMMAND_CREATE_TABLE = "CREATE TABLE ";
    public static final String COMMAND_DROP_TABLE = "DROP TABLE IF EXISTS ";

    //create sql query
    public static final String CREATE_TABLE_USERS = COMMAND_CREATE_TABLE + TABLE_USERS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT NOT NULL, " +
            "password TEXT NOT NULL, " +
            "email TEXT NOT NULL, " +
            "name TEXT NOT NULL, " +
            "phone TEXT, " +
            "role INTEGER NOT NULL, " +
            "status INTEGER NOT NULL" +
            ");";
    //CREATE_TABLE_DECK
    public static final String CREATE_TABLE_DECKS = COMMAND_CREATE_TABLE + TABLE_DECKS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "description TEXT, " +
            "star INTEGER, " +
            "user_id INTEGER NOT NULL, " +
            "FOREIGN KEY(user_id) REFERENCES " + TABLE_USERS + "(id)" +
            ");";
    //create table card
    public static final String CREATE_TABLE_CARDS = COMMAND_CREATE_TABLE + TABLE_CARDS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "front TEXT NOT NULL, " +
            "back TEXT NOT NULL, " +
            "deck_id INTEGER NOT NULL, " +
            "is_learning TEXT NOT NULL, " +
            "FOREIGN KEY(deck_id) REFERENCES " + TABLE_DECKS + "(id)" +
            ");";
    //create table feedback
    public static final String CREATE_TABLE_FEEDBACKS = COMMAND_CREATE_TABLE + TABLE_FEEDBACKS + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "user_id INTEGER NOT NULL, " +
            "title TEXT NOT NULL, " +
            "content TEXT NOT NULL, " +
            "created_at TEXT NOT NULL, " +
            "updated_at TEXT NOT NULL, " +
            "FOREIGN KEY(user_id) REFERENCES " + TABLE_USERS + "(id)" +
            ");";

    //create table deck_star
    public static final String CREATE_TABLE_DECKS_STAR = COMMAND_CREATE_TABLE + TABLE_DECKS_STAR + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "user_id INTEGER NOT NULL, " +
            "deck_id INTEGER NOT NULL, " +
            "star INTEGER NOT NULL, " +
            "FOREIGN KEY(user_id) REFERENCES " + TABLE_USERS + "(id), " +
            "FOREIGN KEY(deck_id) REFERENCES " + TABLE_DECKS + "(id)" +
            ");";


    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_DECKS);
        db.execSQL(CREATE_TABLE_CARDS);
        db.execSQL(CREATE_TABLE_FEEDBACKS);
        db.execSQL(CREATE_TABLE_DECKS_STAR);

//insert data table user
        db.execSQL("INSERT INTO " + TABLE_USERS + " " + "VALUES('1','admin','admin','abc@gmail.com', 'admin', '123456789', 0, 1)");
        db.execSQL("INSERT INTO " + TABLE_USERS + " " + "VALUES('2','user1','user','abc1@gmail.com', 'user2', '1234536789', 1, 1)");
        db.execSQL("INSERT INTO " + TABLE_USERS + " " + "VALUES('3','user2','user','abc1@gmail.com', 'user3', '1234536789', 1, 1)");

        //insert data table deck
        db.execSQL("INSERT INTO " + TABLE_DECKS + " " + "VALUES('1','Deck 1','Deck 1', 0, 1)");
        db.execSQL("INSERT INTO " + TABLE_DECKS + " " + "VALUES('2','Deck 2','Deck 2', 0, 1)");
        db.execSQL("INSERT INTO " + TABLE_DECKS + " " + "VALUES('3','Deck 3','Deck 3', 0, 2)");
        db.execSQL("INSERT INTO " + TABLE_DECKS + " " + "VALUES('4','Deck 4','Deck 4', 0, 2)");
        //insert data table card
        db.execSQL("INSERT INTO " + TABLE_CARDS + " " + "VALUES('1','Front 1','Back 1', 1, 'false')");
        db.execSQL("INSERT INTO " + TABLE_CARDS + " " + "VALUES('2','Front 2','Back 2', 1, 'false')");
        db.execSQL("INSERT INTO " + TABLE_CARDS + " " + "VALUES('3','Front 3','Back 3', 2, 'false')");
        db.execSQL("INSERT INTO " + TABLE_CARDS + " " + "VALUES('4','Front 4','Back 4', 2, 'false')");
        db.execSQL("INSERT INTO " + TABLE_CARDS + " " + "VALUES('5','Front 5','Back 5', 3, 'false')");
        db.execSQL("INSERT INTO " + TABLE_CARDS + " " + "VALUES('6','Front 6','Back 6', 3, 'false')");
        db.execSQL("INSERT INTO " + TABLE_CARDS + " " + "VALUES('7','Front 7','Back 7', 4, 'false')");
        db.execSQL("INSERT INTO " + TABLE_CARDS + " " + "VALUES('8','Front 8','Back 8', 4, 'false')");
        //insert data table feedback
        db.execSQL("INSERT INTO " + TABLE_FEEDBACKS + " " + "VALUES('1','1','Feedback 1','Feedback 1', '2021-05-01', '2021-05-01')");
        db.execSQL("INSERT INTO " + TABLE_FEEDBACKS + " " + "VALUES('2','2','Feedback 2','Feedback 2', '2021-05-01', '2021-05-01')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table if exists
        db.execSQL(COMMAND_DROP_TABLE + TABLE_USERS);
        db.execSQL(COMMAND_DROP_TABLE + TABLE_DECKS);
        db.execSQL(COMMAND_DROP_TABLE + TABLE_CARDS);
        db.execSQL(COMMAND_DROP_TABLE + TABLE_FEEDBACKS);
        //recreate table
        onCreate(db);
    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
