package com.encrdecrpt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public  static  final  String DBNAME="Login.db";

    public  DBHelper(Context context)
    {
        super(context,DBNAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(username TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("drop Table if exists users");
    }


    public Boolean insertdata(String name,String password)
    {
        SQLiteDatabase MyDb=this.getWritableDatabase(); //creation of DB Object
        ContentValues contentValues=new ContentValues(); //row
        contentValues.put("username",name);
        contentValues.put("password",password);

        long results=MyDb.insert("users",null,contentValues);
        if(results==-1) return  false;
        else return  true;
    }

    public Boolean checkusers(String name)
    {
        SQLiteDatabase MyDb=this.getWritableDatabase();
        Cursor cursor=MyDb.rawQuery("Select * from users where username = ?",new String[]{name});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusersdetails(String name,String pass)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from users where username = ? and password = ?",new String[]{name,pass});

        if(cursor.getCount()>0)
            return  true;
        else
            return  false;
    }
}
