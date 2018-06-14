package com.tatsat.wheelstreet.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rahul on 04-07-2015.
 */
public class BazarPriveeDBOpenHelper extends SQLiteOpenHelper {
    private static final String LOGTAG = "DatabaseDB";

    public static final String DATABASE_NAME = "Test.db";
    private static final int DATABASE_VERSION = 1;

    // Cart Table
    public static final String TABLE_CART_ITEMS = "tbl_cart_items";
    public static final String COLUMN_ITEM_ID = "item_id";
    public static final String COLUMN_ITEM_NAME = "item_name";
    public static final String COLUMN_ITEM_Age = "item_age";
    public static final String COLUMN_ITEM_Email = "item_email";
    public static final String COLUMN_ITEM_Gender = "item_gender";
    public static final String COLUMN_ITEM_Mobilenumber = "item_mobilenumber";




    // Cart Table Create
    private static final String TABLE_CART_ITEMS_CREATE = "CREATE TABLE " + TABLE_CART_ITEMS
            + "(" + COLUMN_ITEM_ID + " TEXT primary key, "
            + COLUMN_ITEM_NAME + " TEXT, "
            + COLUMN_ITEM_Age + " TEXT, "
            + COLUMN_ITEM_Email + " TEXT, "
            + COLUMN_ITEM_Gender + " TEXT, "
            + COLUMN_ITEM_Mobilenumber + " TEXT)";



    public BazarPriveeDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CART_ITEMS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART_ITEMS);
        onCreate(db);
    }


}
