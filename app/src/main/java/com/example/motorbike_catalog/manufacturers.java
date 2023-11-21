package com.example.motorbike_catalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class manufacturers extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ManufacturerAdapter adapter;
    private List<Manufacturer> manufacturerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturers);

        recyclerView = findViewById(R.id.manufacturerRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        manufacturerList = new ArrayList<>();

        // Initialize your database helper and DAO
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        ManufacturerDAO manufacturerDAO = new ManufacturerDAO(this);

        try {
            databaseHelper.createDatabase();
            manufacturerDAO.open();

            // Fetch manufacturers from the database
            Cursor cursor = manufacturerDAO.getAllManufacturers();
            if (((Cursor) cursor).moveToFirst()) {
                do {
                    @SuppressLint("Range") String manufacturerName = cursor.getString(cursor.getColumnIndex("Name"));
                    @SuppressLint("Range") String location = cursor.getString(cursor.getColumnIndex("Location"));
                    @SuppressLint("Range") int foundationYear = cursor.getInt(cursor.getColumnIndex("FoundationYear"));
                    manufacturerList.add(new Manufacturer(manufacturerName,location,foundationYear));
                } while (cursor.moveToNext());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            manufacturerDAO.close();
        }

        adapter = new ManufacturerAdapter(manufacturerList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ManufacturerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String manufacturerName) {
                // Show a toast with the manufacturer's name
                //Toast.makeText(manufacturers.this, "Manufacturer: " + manufacturerName, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(manufacturers.this, BikeModells.class);

                // Add any extra data to the intent if needed
                intent.putExtra("MANUFACTURER_NAME", manufacturerName);
                startActivity(intent);
            }
        });
    }

}
