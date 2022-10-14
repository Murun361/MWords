package com.labs.MWords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_Helper extends SQLiteOpenHelper {
    public static final String DBNAME="login.db";

    public DB_Helper(@Nullable Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table user(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists user ");
    }
    public boolean insertData(String username, String password){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result=MyDB.insert("user",null, contentValues);
        if(result==-1){
            return false;
        }
        else
            return true;
    }
    public boolean check_username(String username){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from user where username=?",new String[] {username});
        if(cursor.getCount()>0){
            String Uname=cursor.toString();
            return true;
        }
        else
            return false;
    }
    public boolean check_username_password(String username, String password){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from user where username=? and password=?",new String[] {username, password});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }

}
