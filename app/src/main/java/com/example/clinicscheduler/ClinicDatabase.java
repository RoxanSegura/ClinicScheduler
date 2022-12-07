package com.example.clinicscheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ClinicDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "Patient.db";
    public static final String TABLE_NAME = "Patient_Record";

    public static final String c1 = "ID";
    public static final String c2 = "FIRSTNAME";
    public static final String c3 = "LASTNAME";
    public static final String c4 = "AGE";
    public static final String c5 = "GENDER";
    public static final String c6 = "BIRTHDAY";
    public static final String c7 = "CONDITION";
    public static final String c8 = "DIAGNOSIS";
    public static final String c9 = "APPOINTMENT DATE";
    public static final String c10 = "APPOINTMENT TIME";
    SQLiteDatabase sqLiteDatabase;

    public ClinicDatabase(@Nullable Context context) { super(context, DB_NAME, null, 1); }

    //Constructor
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT, LASTNAME TEXT, AGE INTEGER, GENDER TEXT, BIRTHDAY TEXT," +
                "CONDITION TEXT, DIAGNOSIS TEXT, APPOINTMENT_DATE TEXT, APPOINTMENT_TIME TEXT)");
    }

    //Checks if the table already exists
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //Insert a new value
    public boolean insertData(String fname, String lname, String age, String gender, String birthday, String condition, String diagnosis, String appointDate, String appointTime) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(c2,fname);
        cv.put(c3,lname);
        cv.put(c4,age);
        cv.put(c5,gender);
        cv.put(c6,birthday);
        cv.put(c7,condition);
        cv.put(c8,diagnosis);
        cv.put(c9,appointDate);
        cv.put(c10,appointTime);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, cv);

        //checks if the data has been successfully inputted
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }

    // Im still gonna test if this actually works
    public Cursor getDataByID(String ID) {
        sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " where ID", null);
        return res;
    }
}
