package com.example.motorbike_catalog;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public Cursor getAllBikeModels() {
        return database.query("bikemodel", null, null, null, null, null, null);
    }

    public Cursor getBikeModel(String modelName) {
        String[] projection = null; // null means return all columns
        String selection = "name = ?";
        String[] selectionArgs = { modelName };

        return database.query("bikemodel", projection, selection, selectionArgs, null, null, null);
    }

    public Cursor getBikesCursorForUser(Context context, int userId) {
        SharedPreferences preferences = context.getSharedPreferences("BikeData", Context.MODE_PRIVATE);
        String userKey = String.valueOf(userId);
        String savedBikes = preferences.getString(userKey, "");

        if (savedBikes.isEmpty()) {
            return null;
        }

        String[] bikeNames = savedBikes.split(",");

        StringBuilder selectionBuilder = new StringBuilder();
        selectionBuilder.append("name IN (");
        for (int i = 0; i < bikeNames.length; i++) {
            selectionBuilder.append("?");
            if (i < bikeNames.length - 1) {
                selectionBuilder.append(",");
            }
        }
        selectionBuilder.append(")");

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        return database.query(
                "bikemodel",
                null,
                selectionBuilder.toString(),
                bikeNames,
                null,
                null,
                null
        );
    }
}
