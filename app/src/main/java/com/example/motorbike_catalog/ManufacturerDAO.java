package com.example.motorbike_catalog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ManufacturerDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public ManufacturerDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertManufacturer(String name, int foundationYear, String location) {
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("FoundationYear", foundationYear);
        values.put("Location", location);

        return database.insert("manufacturers", null, values);
    }

    public Cursor getAllManufacturers() {
        return database.query("manufacturers", null, null, null, null, null, null);
    }
}
