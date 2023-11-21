package com.example.motorbike_catalog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class BikeDetail extends AppCompatActivity {

    TextView BikeName,HorsePower,TopSpeed,CCM,Manufacturer,ProdYears, EngineType, Weight;
    SeekBar HorsePowerSeekBar, CCMSeekbar, TopSpeedSeekBar;
    ImageView BikeImage;
    private ModelAdapter adapter;
    BikeModel bikeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_detail);

        Intent intent = getIntent();
        String bikeName = intent.getExtras().getString("MODEL_NAME");

        //TEXTVIEW
         BikeName = findViewById(R.id.bikeName);
         HorsePower = findViewById(R.id.HorsePower);
         CCM = findViewById(R.id.ccmValue);
         Manufacturer = findViewById(R.id.manufacturerValue);
         ProdYears =findViewById(R.id.prodYearValue);
         EngineType = findViewById(R.id.typeValue);
         Weight = findViewById(R.id.weightValue);
         TopSpeed = findViewById(R.id.topSpeedValue);

         // SEEKBAR
        HorsePowerSeekBar = findViewById(R.id.HorsePowerSeekbar);
        CCMSeekbar = findViewById(R.id.ccmSeekBar);
        TopSpeedSeekBar = findViewById(R.id.topSpeedSeekBar);

        //Photo
        BikeImage = findViewById(R.id.bikeImage);

        //DATA FETCH
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        BikeModelDAO bikeModelDAO = new BikeModelDAO(this);


        try {
            databaseHelper.createDatabase();
            bikeModelDAO.open();



            Cursor cursor = bikeModelDAO.getBikeModel(bikeName);
            if (cursor.moveToFirst()) {
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
                @SuppressLint("Range") int topSpeed = cursor.getInt(cursor.getColumnIndex("topSpeed"));
                @SuppressLint("Range") String photoURL = cursor.getString(cursor.getColumnIndex("photoURL"));

                // Create a BikeModel object with the retrieved data
                bikeModel = new BikeModel(modelName, manufacturer, series, horsepower, weight, torque,
                        firstProductionYear, lastProductionYear, ccm, noCylinders, noGears, cooling,
                        engineType, fuelSize, fuelSystem,topSpeed,photoURL);
                // Use the bikeModel as needed, for example, add it to a list or perform actions
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            bikeModelDAO.close();
        }

        BikeName.setText(bikeModel.getName() + "");

        HorsePower.setText(bikeModel.getHorsepower() + "lóerő");
        HorsePowerSeekBar.setEnabled(false);
        HorsePowerSeekBar.setProgress(bikeModel.getHorsepower());

        CCM.setText(bikeModel.getCcm() + "ccm");
        CCMSeekbar.setEnabled(false);
        CCMSeekbar.setProgress(bikeModel.getCcm());

        TopSpeed.setText(bikeModel.getTopSpeed() + "kmh");
        TopSpeedSeekBar.setEnabled(false);
        TopSpeedSeekBar.setProgress(bikeModel.getTopSpeed());

        Manufacturer.setText(bikeModel.getManufacturer());
        ProdYears.setText(bikeModel.getFirstProductionYear() + " -- " + bikeModel.getLastProductionYear());

        Weight.setText(bikeModel.getWeight() + "kg");

        if (bikeModel.getPhotoURL() != null) {
            String url = bikeModel.getPhotoURL();
           Picasso.get().load(url).into(BikeImage);
        }

        EngineType.setText(bikeModel.getEngineType() + " " + bikeModel.getNoCylinders());





    }
}