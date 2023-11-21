package com.example.motorbike_catalog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.motorbike_catalog.databinding.ActivityBikeModellsBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BikeModells extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ModelAdapter adapter;
    private List<BikeModel> bikeModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        String Manufacturer = intent.getExtras().getString("MANUFACTURER_NAME");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_modells);

        recyclerView = findViewById(R.id.bikeModelRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bikeModelList = new ArrayList<>();

        // Initialize your database helper and DAO
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        BikeModelDAO bikeModelDAO = new BikeModelDAO(this);

        try {
            databaseHelper.createDatabase();
            bikeModelDAO.open();

            // Fetch manufacturers from the database
            Cursor cursor = bikeModelDAO.getAllBikeModels(Manufacturer);
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
                    bikeModelList.add(new BikeModel(modelName, manufacturer, series, horsepower, weight, torque,
                            firstProductionYear, lastProductionYear, ccm, noCylinders, noGears, cooling,
                            engineType, fuelSize, fuelSystem,topSpeed,photoURL));
                } while (cursor.moveToNext());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bikeModelDAO.close();
        }

        adapter = new ModelAdapter(bikeModelList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ModelAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(String bikeName) {
                // Show a toast with the manufacturer's name
                Toast.makeText(BikeModells.this, "BikeName: " + bikeName, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BikeModells.this,BikeDetail.class);

                // Add any extra data to the intent if needed
                intent.putExtra("MODEL_NAME", bikeName);
                startActivity(intent);
            }
        });
    }
}