package com.example.motorbike_catalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBikeInfo(View v)
    {
        Intent bikeInfo = new Intent(this, BikeDetail.class);
        this.startActivity(bikeInfo);
    }

    public void openManufacturers(View v)
    {
        Intent manuF = new Intent(this, manufacturers.class);
        this.startActivity(manuF);
    }

    public void openProfile(View v)
    {
        Intent profile = new Intent(this,New_Profile.class);
        this.startActivity(profile);
    }

    public void openTop10(View v)
    {
        Intent profile = new Intent(this,top10_model.class);
        this.startActivity(profile);
    }

}