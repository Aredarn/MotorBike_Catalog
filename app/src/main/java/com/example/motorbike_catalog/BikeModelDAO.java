package com.example.motorbike_catalog;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BikeModelDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public BikeModelDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Cursor getAllBikeModels(String manufacturer) {
        String[] projection = null; // null means return all columns
        String selection = "manufactuer = ?";
        String[] selectionArgs = { manufacturer };

        return database.query("bikemodel", projection, selection, selectionArgs, null, null, null);
    }

    public Cursor getBikeModel(String modelName) {
        String[] projection = null; // null means return all columns
        String selection = "name = ?";
        String[] selectionArgs = { modelName };

        return database.query("bikemodel", projection, selection, selectionArgs, null, null, null);
    }



    // Add other CRUD methods as needed
}
