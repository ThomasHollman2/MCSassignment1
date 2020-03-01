package com.example.weekend1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDatabase extends SQLiteOpenHelper {
    public UserDatabase(@Nullable Context context) {
        super(context, "Users", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + UserTable.TABLE_NAME +
                        " (" + UserTable._ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        UserTable.USER_NAME +
                        " VARCHAR(255)," +
                        UserTable.USER_COUNTRY +
                        " VARCHAR(255)," +
                        UserTable.USER_GENDER +
                        " VARCHAR(255)," +
                        UserTable.USER_DOB +
                        " VARCHAR(255))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
