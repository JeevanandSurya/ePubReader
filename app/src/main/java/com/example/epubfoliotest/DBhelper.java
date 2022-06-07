package com.example.epubfoliotest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails (name TEXT,phoneNumber Text primary key,userName TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("Drop table if exists Userdetails");
    }

    public Boolean insertuserdata(String name,String phoneNumber,String userName,String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phoneNumber", phoneNumber);
        contentValues.put("userName", userName);
        contentValues.put("password",password);

        long result = DB.insert("Userdetails",null,contentValues);
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean updateuserdata(String name,String phoneNumber,String userName,String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //  new part tryout
        if(name.length() != 0)
            contentValues.put("name", name);
        if(phoneNumber.length() != 0)
            contentValues.put("phone number",phoneNumber);
        if(userName.length() != 0)
            contentValues.put("user name",userName);
        if(password.length() != 0)
            contentValues.put("password",password);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where phoneNumber=?",new String[]{phoneNumber});
        if(cursor.getCount()>0){

            long result = DB.update("Userdetails",contentValues,"phoneNumber=?",new String[] {phoneNumber});
            if (result == -1){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }

//    public Boolean deletedata(String phoneNumber){
//        SQLiteDatabase DB = this.getWritableDatabase();
//
//        Cursor cursor = DB.rawQuery("Select * from Userdetails where phoneNumber=?",new String[]{phoneNumber});
//        if(cursor.getCount()>0){
//            long result = DB.delete("Userdetails","phoneNumber=?",new String[] {phoneNumber});
//            if (result == -1){
//                return false;
//            }
//            else{
//                return true;
//            }
//        }
//        else{
//            return false;
//        }
//    }

    public Cursor getpass(String userName){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select password from Userdetails where userName=?",new String[] {userName});
        return cursor;
    }

    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Userdetails",null);
        return cursor;
    }

//    public Cursor getdatabyyear(String yearJoined){
//        SQLiteDatabase DB = this.getWritableDatabase();
//
//        Cursor cursor = DB.rawQuery("Select * from Userdetails where yearJoined =?",new String[] {yearJoined});
//        return cursor;
//    }
}

