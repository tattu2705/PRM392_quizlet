package com.example.projectprn392flashcard.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectprn392flashcard.data.SQLiteHelper;
import com.example.projectprn392flashcard.data.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private SQLiteHelper db;
    private SQLiteDatabase sqLiteDatabase;

    public UserDAO(Context context) {
        this.db = new SQLiteHelper(context);
    }

    public int getUserId(String username) {
        sqLiteDatabase = db.getReadableDatabase();
        String query = "SELECT id FROM user WHERE username = ?";
        Cursor rs = sqLiteDatabase.rawQuery(query, new String[]{username});
        int userId = -1;
        if (rs != null && rs.moveToFirst()) {
            userId = rs.getInt(0);
        }
        if (rs != null) {
            rs.close();
        }
        sqLiteDatabase.close();
        return userId;
    }

    public User getUserById(int id) {
        sqLiteDatabase = db.getReadableDatabase();
        String query = "SELECT * FROM user WHERE id = ?";
        Cursor rs = sqLiteDatabase.rawQuery(query, new String[]{String.valueOf(id)});
        User user = null;
        if (rs != null && rs.moveToFirst()) {
            String username = rs.getString(1);
            String password = rs.getString(2);
            String email = rs.getString(3);
            String name = rs.getString(4);
            String phone = rs.getString(5);
            int role = rs.getInt(6);
            int status = rs.getInt(7);
            user = new User(id, username, password, email, name, phone, role, status);
        }
        if (rs != null) {
            rs.close();
        }
        sqLiteDatabase.close();
        return user;
    }

    public boolean checkLogin(String username, String password) {
        sqLiteDatabase = db.getReadableDatabase();
        String query = "SELECT * FROM user WHERE username = ? AND password = ? AND status = 1";
        Cursor rs = sqLiteDatabase.rawQuery(query, new String[]{username, password});
        boolean isLoggedIn = rs != null && rs.moveToFirst();
        if (rs != null) {
            rs.close();
        }
        sqLiteDatabase.close();
        return isLoggedIn;
    }

    public int checkUserRole(String username, String password) {
        sqLiteDatabase = db.getReadableDatabase();
        String query = "SELECT role FROM user WHERE username = ? AND password = ?";
        Cursor rs = sqLiteDatabase.rawQuery(query, new String[]{username, password});
        int role = -1;
        if (rs != null && rs.moveToFirst()) {
            role = rs.getInt(0);
        }
        if (rs != null) {
            rs.close();
        }
        sqLiteDatabase.close();
        return role;
    }

    public boolean updateUser(User user) {
        sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        contentValues.put("email", user.getEmail());
        contentValues.put("name", user.getName());
        contentValues.put("phone", user.getPhone());
        contentValues.put("role", user.getRole());
        contentValues.put("status", user.getStatus());
        int rowsAffected = sqLiteDatabase.update("user", contentValues, "id = ?", new String[]{String.valueOf(user.getId())});
        sqLiteDatabase.close();
        return rowsAffected > 0;
    }

    public List<User> searchUser(String key) {
        List<User> userList = new ArrayList<>();
        sqLiteDatabase = db.getReadableDatabase();
        String query = "SELECT * FROM user WHERE username LIKE ? OR email LIKE ? OR phone LIKE ? OR name LIKE ?";
        Cursor rs = sqLiteDatabase.rawQuery(query, new String[]{"%" + key + "%", "%" + key + "%", "%" + key + "%", "%" + key + "%"});
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String username = rs.getString(1);
            String password = rs.getString(2);
            String email = rs.getString(3);
            String name = rs.getString(4);
            String phone = rs.getString(5);
            int role = rs.getInt(6);
            int status = rs.getInt(7);
            userList.add(new User(id, username, password, email, name, phone, role, status));
        }
        if (rs != null) {
            rs.close();
        }
        sqLiteDatabase.close();
        return userList;
    }

    public void insert(String username, String password, String email, String name, String phone) {
        sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("role", 1);
        contentValues.put("status", 1);
        sqLiteDatabase.insert("user", null, contentValues);
        sqLiteDatabase.close();
    }

    public boolean checkUsernameExist(String username) {
        sqLiteDatabase = db.getReadableDatabase();
        String query = "SELECT * FROM user WHERE username = ?";
        Cursor rs = sqLiteDatabase.rawQuery(query, new String[]{username});
        boolean exists = rs != null && rs.moveToFirst();
        if (rs != null) {
            rs.close();
        }
        sqLiteDatabase.close();
        return exists;
    }
}
