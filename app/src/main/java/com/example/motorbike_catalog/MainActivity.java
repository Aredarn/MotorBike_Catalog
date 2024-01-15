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
        setSupportActionBar(findViewById(R.id.my_toolbar));
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
        Intent profile = new Intent(this,Profile.class);
        this.startActivity(profile);
    }

    public void openTopBikes(View v)
    {
        //Intent topBikes = new Intent(this,top)
    }

}