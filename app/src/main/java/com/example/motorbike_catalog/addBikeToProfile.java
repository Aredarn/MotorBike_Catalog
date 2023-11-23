package com.example.motorbike_catalog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class addBikeToProfile extends AppCompatActivity {


    private List<Manufacturer> manufacturerList;
    private List<BikeModel> bikeModelList;
    private Spinner spinnerManufacturers;
    private Spinner spinnerModels;
    private Button addToSelfButton;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        userID = intent.getExtras().getInt("USER_ID");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bike_to_profile);

        spinnerManufacturers = findViewById(R.id.spinnerManufacturers);
        spinnerModels = findViewById(R.id.spinnerModels);


        manufacturerList = new ArrayList<>();
        bikeModelList = new ArrayList<>();

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


        BikeModelDAO bikeModelDAO = new BikeModelDAO(this);
        try {
            databaseHelper.createDatabase();
            bikeModelDAO.open();
            Cursor cursor = bikeModelDAO.getAllBikeModels();
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
                            engineType, fuelSize, fuelSystem,topSpeed,photoURL,0));
                } while (cursor.moveToNext());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bikeModelDAO.close();
        }


        List<String> manufacturerNames = new ArrayList<>();
        List<String> modelNames = new ArrayList<>();

        for (Manufacturer manufacturer : manufacturerList) {
            manufacturerNames.add(manufacturer.getName());
        }

        for (BikeModel model : bikeModelList) {
            modelNames.add(model.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, manufacturerNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerManufacturers.setAdapter(adapter);

        addToSelfButton = findViewById(R.id.addToSelf); // Replace with your button ID
        addToSelfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBike();
            }
        });

        spinnerManufacturers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedManufacturer = manufacturerNames.get(position);
                List<String> filteredModels = new ArrayList<>();


                for (BikeModel model : bikeModelList) {
                    if (model.getManufacturer().equals(selectedManufacturer)) {
                        filteredModels.add(model.getName());
                    }
                }

                ArrayAdapter<String> modelsAdapter = new ArrayAdapter<>(addBikeToProfile.this, android.R.layout.simple_spinner_item, filteredModels);
                modelsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerModels.setAdapter(modelsAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addBike() {
        Spinner spinnerModels = findViewById(R.id.spinnerModels);
        String selectedBike = spinnerModels.getSelectedItem().toString();

        int userId = userID;

        SharedPreferences preferences = getSharedPreferences("BikeData", Context.MODE_PRIVATE);
        String userBikes = preferences.getString(String.valueOf(userId), "");

        if (!userBikes.isEmpty()) {
            // Split the string into individual bikes
            String[] userBikeArray = userBikes.split(",");
            List<String> userBikeList = new ArrayList<>(Arrays.asList(userBikeArray));

            if (!userBikeList.contains(selectedBike)) {
                userBikeList.add(selectedBike);

                // Join the bikes back into a comma-separated string
                userBikes = TextUtils.join(",", userBikeList);

                // Store the updated bikes for this user
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(String.valueOf(userId), userBikes);
                editor.apply();

                Toast.makeText(this, "Katalógushoz adva: " + selectedBike, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ez a motor már létezik a katalógusodba", Toast.LENGTH_SHORT).show();
            }
        } else {
            // If the user doesn't have any bikes yet, simply add the selected bike
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(String.valueOf(userId), selectedBike);
            editor.apply();
            Toast.makeText(this, "Katalógushoz adva: " + selectedBike, Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isBikeAlreadyAdded(int userId, String bikeName) {
        SharedPreferences preferences = getSharedPreferences("BikeData", Context.MODE_PRIVATE);
        String storedBike = preferences.getString(String.valueOf(userId), "");
        return storedBike.equals(bikeName);
    }


}