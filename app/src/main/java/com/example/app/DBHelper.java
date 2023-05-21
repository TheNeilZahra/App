package com.example.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNMAME = "login.db";

    public DBHelper(Context context) {
        super(context, "login.db", null, 1);
    }

    //Creates the table Users
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE users(email TEXT PRIMARY KEY, name TEXT, password TEXT, points INTEGER DEFAULT 0)");
    }

    //Drops the table if it is an old version
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
        onCreate(MyDB);
    }

    //Inserts the the table data
    public Boolean insertData(String name, String email, String password, int points) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("points", points);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //Checks the relative Email Value
    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[] {email});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //Checks if the email value and password values match an entry in the table
    public Boolean checkemailpassword(String email, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", new String[] { email, password });
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //Retrieves the name of the user with the corresponding email and password
    public String getRelativeName(String email, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT name FROM users WHERE email = ? AND password = ?", new String[] {email, password});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            cursor.close();
            return name;
        } else {
            cursor.close();
            return null;
        }
    }

    //Adds points to the account
    public void addPoints(String email, int points) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("points", points);
        MyDB.update("users", contentValues, "email = ?", new String[] { email });
    }

    //Removes points from the account
    public void removePoints(String email, int points) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        int currentPoints = getPoints(email);
        if (currentPoints != -1) {
            int newPoints = Math.max(0, currentPoints - points);
            ContentValues contentValues = new ContentValues();
            contentValues.put("points", newPoints);
            MyDB.update("users", contentValues, "email = ?", new String[] { email });
        }
    }

    //Gets the amount of points the account has
    public int getPoints(String email) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT points FROM users WHERE email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            int points = cursor.getInt(cursor.getColumnIndexOrThrow("points"));
            cursor.close();
            return points;
        } else {
            cursor.close();
            return -1; // or any other value to indicate no points found
        }
    }

}
