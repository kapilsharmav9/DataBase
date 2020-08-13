package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    static final String DBNAME = "Android2020";
    static final String TABLE_1 = "empinfo";
    static final String COL_1 = "emp_id";
    static final String COL_2 = "emp_name";
    static final String COL_3 = "emp_profile";

    public DBhelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table '" + TABLE_1 + "'('"+COL_1+"' varchar(100),'"+COL_2+"' varchar(100) ,'"+COL_3+"' varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table '" + TABLE_1 + "'");
        onCreate(db);
    }

    public boolean InsertEMP(String id, String name, String profile) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, id);
        cv.put(COL_2, name);
        cv.put(COL_3, profile);
        long l = db.insert(TABLE_1,null,cv);
        if (l > 0) {
            return true;
        } else {
            return false;


        }

    }

    public Cursor getData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("select * from '" + TABLE_1 + "'", null);
        return cs;
    }


}
