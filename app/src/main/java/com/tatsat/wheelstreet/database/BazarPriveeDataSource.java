package com.tatsat.wheelstreet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

public class BazarPriveeDataSource {
    private static final String LOGTAG = "BazarPriveeDS";
    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;
    private static final String[] allColumns = {BazarPriveeDBOpenHelper.COLUMN_ITEM_ID,
            BazarPriveeDBOpenHelper.COLUMN_ITEM_NAME,
            BazarPriveeDBOpenHelper.COLUMN_ITEM_Age,
            BazarPriveeDBOpenHelper.COLUMN_ITEM_Email,
            BazarPriveeDBOpenHelper.COLUMN_ITEM_Gender,
            BazarPriveeDBOpenHelper.COLUMN_ITEM_Mobilenumber

    };

    public BazarPriveeDataSource(Context context) {
        dbHelper = new BazarPriveeDBOpenHelper(context);
    }

    public void open() {
        Log.i(LOGTAG, "Database opened");
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        Log.i(LOGTAG, "Database closed");
        dbHelper.close();
    }


    public void create(String Id, String Name,String age,String email,String gender,String mobilenumber) {

        String query = "select * from " + BazarPriveeDBOpenHelper.TABLE_CART_ITEMS + " where " + BazarPriveeDBOpenHelper.COLUMN_ITEM_ID + "='" + Id + "'";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.close();
            ContentValues values = new ContentValues();
            values.put(BazarPriveeDBOpenHelper.COLUMN_ITEM_ID, Id);
            // values.put(BazarPriveeDBOpenHelper.COLUMN_ITEM_DEALER_ID, autoSevaCartItemDetail.getItemDealerID());
            values.put(BazarPriveeDBOpenHelper.COLUMN_ITEM_NAME, Name);
            values.put(BazarPriveeDBOpenHelper.COLUMN_ITEM_Age, age);
            values.put(BazarPriveeDBOpenHelper.COLUMN_ITEM_Email, email);
            values.put(BazarPriveeDBOpenHelper.COLUMN_ITEM_Gender, gender);
            values.put(BazarPriveeDBOpenHelper.COLUMN_ITEM_Mobilenumber, mobilenumber);

            long insertid = database.update(BazarPriveeDBOpenHelper.TABLE_CART_ITEMS, values, BazarPriveeDBOpenHelper.COLUMN_ITEM_ID + "='" + Id + "'", null);

            // autoSevaCartItemDetail.setDbID(insertid);

        } else {
            cursor.close();
            ContentValues values = new ContentValues();

            values.put(BazarPriveeDBOpenHelper.COLUMN_ITEM_ID, Id);
            //values.put(BazarPriveeDBOpenHelper.COLUMN_ITEM_DEALER_ID, autoSevaCartItemDetail.getItemDealerID());
            values.put(BazarPriveeDBOpenHelper.COLUMN_ITEM_NAME, Name);
            values.put(BazarPriveeDBOpenHelper.COLUMN_ITEM_Age, age);
            values.put(BazarPriveeDBOpenHelper.COLUMN_ITEM_Email, email);
            values.put(BazarPriveeDBOpenHelper.COLUMN_ITEM_Gender, gender);
            values.put(BazarPriveeDBOpenHelper.COLUMN_ITEM_Mobilenumber, mobilenumber);


            long insertid = database.insert(BazarPriveeDBOpenHelper.TABLE_CART_ITEMS, null, values);
            //autoSevaCartItemDetail.setDbID(insertid);
        }
        //  return autoSevaCartItemDetail;
    }


    public boolean CheckDatabaseEmplty() {

        Cursor mCursor = database.rawQuery("SELECT * FROM " + BazarPriveeDBOpenHelper.TABLE_CART_ITEMS, null);
        Boolean rowExists;

        if (mCursor.moveToFirst()) {
            // DO SOMETHING WITH CURSOR
            rowExists = true;

        } else {
            // I AM EMPTY
            rowExists = false;
        }

        return rowExists;
    }

    /**
     * Delete Full Table in DB
     */
    public void deleteAllOrderItem() {
        if (database != null) {
            int deleteAllOrderItem = database.delete(BazarPriveeDBOpenHelper.TABLE_CART_ITEMS, null, null);
            Log.e("Table Delete", deleteAllOrderItem + "");
        }
    }

    /**
     * Delete Particular Item from Cart
     */
    public void deleteParticularOrderItem(String cartItemID) {
        if (database != null) {
            int deleteOrderItem = database.delete(BazarPriveeDBOpenHelper.TABLE_CART_ITEMS, BazarPriveeDBOpenHelper.COLUMN_ITEM_ID + "='" + cartItemID + "'", null);
            Log.e("Record Delete", deleteOrderItem + "");
        }
    }

    /**
     * Getting all records from Cart Table
     *
     * @return
     */
    public List<BazarPriveeDBCartItemModel> gettingAllCartDetails() {
        Cursor cursor = database.query(BazarPriveeDBOpenHelper.TABLE_CART_ITEMS, allColumns,
                null, null, null, null, null);
        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
        List<BazarPriveeDBCartItemModel> orderItemList = cursorToList(cursor);
        Log.i(LOGTAG, "Returned " + orderItemList.size() + " Size");
        return orderItemList;
    }


    private List<BazarPriveeDBCartItemModel> cursorToList(Cursor cursor) {
        List<BazarPriveeDBCartItemModel> autoSevaCartOrderItemList = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                BazarPriveeDBCartItemModel autoSevaCarttemDetail = new BazarPriveeDBCartItemModel();
                //autoSevaCarttemDetail.setDbID(cursor.getLong(cursor.getColumnIndex(BazarPriveeDBOpenHelper.COLUMN_ID)));
                autoSevaCarttemDetail.setItemID(cursor.getString(cursor.getColumnIndex(BazarPriveeDBOpenHelper.COLUMN_ITEM_ID)));
                autoSevaCarttemDetail.setItemName(cursor.getString(cursor.getColumnIndex(BazarPriveeDBOpenHelper.COLUMN_ITEM_NAME)));
                autoSevaCarttemDetail.setItemAge(cursor.getString(cursor.getColumnIndex(BazarPriveeDBOpenHelper.COLUMN_ITEM_Age)));
                autoSevaCarttemDetail.setItemEmail(cursor.getString(cursor.getColumnIndex(BazarPriveeDBOpenHelper.COLUMN_ITEM_Email)));
                autoSevaCarttemDetail.setItemGender(cursor.getString(cursor.getColumnIndex(BazarPriveeDBOpenHelper.COLUMN_ITEM_Gender)));
                autoSevaCarttemDetail.setItemMobileNumber(cursor.getString(cursor.getColumnIndex(BazarPriveeDBOpenHelper.COLUMN_ITEM_Mobilenumber)));

                autoSevaCartOrderItemList.add(autoSevaCarttemDetail);
            }
        }
        return autoSevaCartOrderItemList;
    }


    //getProductId

}
