package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Teo on 5/20/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UsersInfo";
    private static final String TABLE_USERS= "users";

    //public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "("
                +  COLUMN_USERNAME
                + " TEXT," + COLUMN_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_USERS, null, values);
        db.close();
    }
    public User findUser(String username) {
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME+ " =  \"" + username + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setUsername(cursor.getString(0));
            user.setPassword(cursor.getString(1));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }

}
