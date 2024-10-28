package com.example.projectprn392flashcard.data.entity;

import java.io.Serializable;

public class Feedback implements Serializable {
    private int id;
    private String title;
    private String content;
    private int user_id;
    private String created_at;
    private String update_at;

    public Feedback() {
    }

    public Feedback(int id, String title, String content, int user_id, String created_at, String update_at) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user_id = user_id;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    public Feedback(String title, String content, int user_id, String created_at, String update_at) {
        this.title = title;
        this.content = content;
        this.user_id = user_id;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }
}
//  public void getFeedbackById(int feedbackId){
//        sqLiteDatabase = db.getReadableDatabase();
//        String query = "SELECT * FROM feedback WHERE id = "+feedbackId;
//        sqLiteDatabase.execSQL(query);
//    }