package com.example.motorbike_catalog;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "motorbikes";
    private static final int DATABASE_VERSION = 1;

    private final Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // No need to create tables here, as they already exist
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }

    public void createDatabase() throws IOException {
        boolean dbExist = checkDatabase();
        if (!dbExist) {
            getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDatabase() {
        SQLiteDatabase checkDB = null;
        try {
            String dbPath = context.getDatabasePath(DATABASE_NAME).getPath();
            checkDB = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            // Database does not exist
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDatabase() throws IOException {
        InputStream input = context.getResources().openRawResource(R.raw.motorbikes);
        String outFileName = context.getDatabasePath(DATABASE_NAME).getPath();
        OutputStream output = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            ((FileOutputStream) output).write(buffer, 0, length);
        }
        output.flush();
        output.close();
        input.close();
    }

    /*
    public void incrementClicksValue(String modelName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("clicks",2);

        db.update("bikemodel",contentValues,"name = ?", new String[]{modelName});
    }*/

    public void incrementClicksValue(String modelName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Construct the SQL query to update the Clicks value by 1 for a specific model name
        String whereClause = "name=?";
        String[] whereArgs = { modelName };

        Cursor cursor = db.query("bikemodel", new String[]{"clicks"}, whereClause, whereArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int currentClicks = cursor.getInt(cursor.getColumnIndex("clicks"));
            int newClicks = currentClicks + 1;

            // Update the Clicks value for the specific model
            values.put("Clicks", newClicks);
            db.update("bikemodel", values, whereClause, whereArgs);

            cursor.close();
        }
        db.close();
    }



}



