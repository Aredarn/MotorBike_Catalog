package com.example.motorbike_catalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class top10_model extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Top10ModelAdapter adapter;
    private List<BikeModel> bikeModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10_model);


        recyclerView = findViewById(R.id.topBikes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bikeModelList = new ArrayList<>();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        BikeModelDAO bikeModelDAO = new BikeModelDAO(this);

        try {
            databaseHelper.createDatabase();
            bikeModelDAO.open();

            Cursor cursor = bikeModelDAO.getTop10ClickedBikeModels();
            if (((Cursor) cursor).moveToFirst()) {
                do {
                    @SuppressLint("Range") String modelName = cursor.getString(cursor.getColumnIndex("name"));
                    @SuppressLint("Range") String manufacturer = cursor.getString(cursor.getColumnIndex("manufactuer"));
                    @SuppressLint("Range") String series = cursor.getString(cursor.getColumnIndex("series"));
                    @SuppressLint("Range") int horsepower = cursor.getInt(cursor.getColumnIndex("horsepower"));
                    @SuppressLint("Range") int weight = cursor.getInt(cursor.getColumnIndex("weight"));
                    @SuppressLint("Range") int torque = cursor.getInt(cursor.getColumnIndex("torque"));
                    @SuppressLint("Range") int firstProductionYear = cursor.getInt(cursor.getColumnIndex("firstProductionYear"));
                    @SuppressLint("Range") int lastProductionYear = cursor.getInt(cursor.getColumnIndex("lastProductionYear"));
                    @SuppressLint("Range") int ccm = cursor.getInt(cursor.getColumnIndex("ccm"));
                    @SuppressLint("Range") int noCylinders = cursor.getInt(cursor.getColumnIndex("noCylinders"));
                    @SuppressLint("Range") int noGears = cursor.getInt(cursor.getColumnIndex("noGears"));
                    @SuppressLint("Range") String cooling = cursor.getString(cursor.getColumnIndex("cooling"));
                    @SuppressLint("Range") String engineType = cursor.getString(cursor.getColumnIndex("engineType"));
                    @SuppressLint("Range") int fuelSize = cursor.getInt(cursor.getColumnIndex("fuelSize"));
                    @SuppressLint("Range") String fuelSystem = cursor.getString(cursor.getColumnIndex("fuelSystem"));
                    @SuppressLint("Range") int topSpeed = cursor.getInt(cursor.getColumnIndex("fuelSize"));
                    @SuppressLint("Range") String photoURL = cursor.getString(cursor.getColumnIndex("photoURL"));
                    @SuppressLint("Range") int clicks = cursor.getInt(cursor.getColumnIndex("clicks"));
                    bikeModelList.add(new BikeModel(modelName, manufacturer, series, horsepower, weight, torque,
                            firstProductionYear, lastProductionYear, ccm, noCylinders, noGears, cooling,
                            engineType, fuelSize, fuelSystem,topSpeed,photoURL,clicks));
                } while (cursor.moveToNext());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bikeModelDAO.close();
        }

        adapter = new Top10ModelAdapter(bikeModelList);
        recyclerView.setAdapter(adapter);
    }
}