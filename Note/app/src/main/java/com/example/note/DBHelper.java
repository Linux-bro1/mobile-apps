package com.example.note;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="Todo.db";
    private static final int DB_VERSION=1;
    private static final String U_Name="u_name";
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
//        DB.execSQL("create Table users(username Text primary key, email Text, password Text)");
        DB.execSQL("create Table users(u_id Integer primary key autoincrement, username Text not null unique," +
                "email Text not null unique, mobile Text not null, password Text not null )");
        DB.execSQL("create Table list(id integer not null primary key autoincrement, U_Name Text not null, title Text not null, " +
                "description Text not null, foreign key ("+U_Name+") references users(username) )");
//        CREATE TABLE "users" (
//                "u_id"	INTEGER NOT NULL,
//                "username"	TEXT NOT NULL UNIQUE,
//        "email"	INTEGER NOT NULL UNIQUE,
//        "mobile"	TEXT NOT NULL UNIQUE,
//        "password"	TEXT NOT NULL,
//                PRIMARY KEY("u_id" AUTOINCREMENT)
//);
//        CREATE TABLE "list" (
//                "id"	INTEGER NOT NULL,
//                "u_name"	TEXT NOT NULL,
//                "title"	TEXT NOT NULL,
//                "description"	TEXT NOT NULL,
//                PRIMARY KEY("id" AUTOINCREMENT),
//                FOREIGN KEY("u_name") REFERENCES "users"("username")
//);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists users");
        DB.execSQL("drop Table if exists list");
    }

    public Boolean insertData(String username, String email,String mobile, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("username",username);
        content.put("email",email);
        content.put("mobile",mobile);
        content.put("password",password);
        Long result = MyDB.insert("users",null,content);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateData(String username, String email, String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("username",username);
        content.put("email",email);
        content.put("password","1234");
        MyDB.update("users",content,"username=?",new String[]{name});

        return true;
    }

    @SuppressLint("Range")
    public Cursor getData(String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        final Cursor c = MyDB.rawQuery("Select * from users where username = ?", new String[]{name});
        return c;
    }


    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if(cursor.getCount()>0){
            return true;
        }else return false;
    }
    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }else return false;
    }

}
