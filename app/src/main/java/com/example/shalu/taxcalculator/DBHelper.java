package com.example.shalu.taxcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.PatternSyntaxException;

import static android.provider.Telephony.Mms.Part.TEXT;

public class DBHelper extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 2;
    static final String TABLE_NAME = "gst";
    static final String KEY_ID = "id";
    static final String ITEMS = "items";
    static final String TAX = "tax";

    private static String DATABASE_NAME = "GST.db";
    private static String DB_PATH = "";
    private SQLiteDatabase nDataBase;
    private final Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        if (Build.VERSION.SDK_INT>=17){


        }
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + ITEMS + " TEXT," + TAX + " TEXT" + ")";
        db.execSQL(QUERY);
        db.rawQuery("Insert into gst(items,Tax)values('FOOD','5')",null);
        db.rawQuery("Insert into gst(items,Tax)values('CLOTHES','10')",null);
        db.rawQuery("Insert into gst(items,Tax)values('ELECTRONICS','12')",null);
        db.rawQuery("Insert into gst(items,Tax)values('UTENCILS','5')",null);
        db.rawQuery("Insert into gst(items,Tax)values('FURNITURE','5')",null);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

}





