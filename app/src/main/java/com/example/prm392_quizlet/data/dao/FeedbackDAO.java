package com.example.projectprn392flashcard.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectprn392flashcard.data.SQLiteHelper;
import com.example.projectprn392flashcard.data.entity.Feedback;

import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {
    SQLiteHelper db;
    SQLiteDatabase sqLiteDatabase;

    public FeedbackDAO(Context context) {
        this.db = new SQLiteHelper(context);
        sqLiteDatabase = db.getWritableDatabase();
    }

    public void insertFeedback(String feedback){
        sqLiteDatabase = db.getWritableDatabase();
        String query = "INSERT INTO feedback (feedback) VALUES ('"+feedback+"')";
        sqLiteDatabase.execSQL(query);
    }

    public void deleteFeedback(int feedbackId){
        sqLiteDatabase = db.getWritableDatabase();
        String query = "DELETE FROM feedback WHERE id = "+feedbackId;
        sqLiteDatabase.execSQL(query);
    }

    public void updateFeedback(int feedbackId, String feedback){
        sqLiteDatabase = db.getWritableDatabase();
        String query = "UPDATE feedback SET feedback = '"+feedback+"' WHERE id = "+feedbackId;
        sqLiteDatabase.execSQL(query);
    }

    public void getAllFeedback(){
        sqLiteDatabase = db.getReadableDatabase();
        String query = "SELECT * FROM feedback";
        sqLiteDatabase.execSQL(query);
    }

    public void getFeedbackById(int feedbackId){
        sqLiteDatabase = db.getReadableDatabase();
        String query = "SELECT * FROM feedback WHERE id = "+feedbackId;
        sqLiteDatabase.execSQL(query);
    }

    //add
    public void addFeedback(Feedback f){
        ContentValues values= new ContentValues();
        values.put("title", f.getTitle());
        values.put("content", f.getContent());
        values.put("user_id", f.getUser_id());
        values.put("created_at", f.getCreated_at());
        values.put("update_at", f.getUpdate_at());
        SQLiteDatabase st= db.getWritableDatabase();
        st.insert("feedback", null, values);
    }

    public List<Feedback> getAll(){
        List<Feedback> list = new ArrayList<>();
        SQLiteDatabase st= db.getReadableDatabase();
        Cursor rs= st.query("feedback", null, null, null, null, null, null);
        while (rs!= null && rs.moveToNext()){
            int id= rs.getInt(0);
            int user_id= rs.getInt(1);
            String title= rs.getString(2);
            String content= rs.getString(3);
            String created_at= rs.getString(4);
            String update_at= rs.getString(5);
            list.add(new Feedback(id,title,content, user_id, created_at, update_at));
        }
        return list;
    }

    public List<Feedback> getFeedbackByUserId(int userId){
        List<Feedback> list= new ArrayList<>();
        String query = "SELECT * FROM feedback WHERE user_id = "+userId;
        try (Cursor cursor = sqLiteDatabase.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Feedback feedback = new Feedback();
                    feedback.setId(cursor.getInt(0));
                    feedback.setTitle(cursor.getString(1));
                    feedback.setContent(cursor.getString(2));
                    feedback.setUser_id(cursor.getInt(3));
                    feedback.setCreated_at(cursor.getString(4));
                    feedback.setUpdate_at(cursor.getString(5));
                    list.add(feedback);
                } while (cursor.moveToNext());
            }
        }
        return list;
    }

    public String getUsernameOfFeedback(int feedbackId){
        String query = "SELECT user.name FROM feedback INNER JOIN user ON feedback.user_id = user.id WHERE feedback.id = "+feedbackId;
        try (Cursor cursor = sqLiteDatabase.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                return cursor.getString(0);
            }
        }
        return null;
    }

}
//  public void getFeedbackById(int feedbackId){
//        sqLiteDatabase = db.getReadableDatabase();
//        String query = "SELECT * FROM feedback WHERE id = "+feedbackId;
//        sqLiteDatabase.execSQL(query);
//    }