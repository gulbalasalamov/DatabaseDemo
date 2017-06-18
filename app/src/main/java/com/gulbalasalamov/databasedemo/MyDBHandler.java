package com.gulbalasalamov.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "urunDB.db";
    private static final String TABLE_URUNLER = "urunler";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_URUNADI = "urunadi";
    private static final String COLUMN_URUNMIKTARI = "urunmiktari";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_URUNLER_TABLE = "CREATE TABLE " + TABLE_URUNLER +
                "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_URUNADI
                + " TEXT," + COLUMN_URUNMIKTARI + " INTEGER" + ");";
        db.execSQL(CREATE_URUNLER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_URUNLER);
        onCreate(db);
    }

    public void urunEkle(Urun urun) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_URUNADI, urun.getUrunAdi());
        values.put(COLUMN_URUNMIKTARI, urun.getUrunMiktari());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_URUNLER, null, values);
        db.close();
    }

    public Urun urunBul(String urunadi) {
        String query = "Select * FROM " + TABLE_URUNLER +
                " WHERE " + COLUMN_URUNADI + " =  \"" + urunadi + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Urun urun = new Urun();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            urun.setID(Integer.parseInt(cursor.getString(0)));
            urun.setUrunAdi(cursor.getString(1));
            urun.setUrunMiktari(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            urun = null;
        }
        db.close();
        return urun;
    }

    public boolean urunSil(String urunadi) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_URUNLER +
                " WHERE " + COLUMN_URUNADI + " =  \"" + urunadi + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Urun urun = new Urun();
        if (cursor.moveToFirst()) {
            urun.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_URUNLER, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(urun.getID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}
