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

}