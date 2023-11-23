package com.example.motorbike_catalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyBikes extends AppCompatActivity {

    RecyclerView bikeListView;
    BikeModelDAO bikeModelDAO;
    MyBikesAdapter bikeCursorAdapter;
    Cursor bikeCursor;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bikes);
        bikeListView = findViewById(R.id.bikeListView);

        Intent intent = getIntent();
        if (intent != null) {
            int userId = intent.getIntExtra("USER_ID", -1);
            if (userId != -1) {
                userID = userId;
            } else {
                Toast.makeText(this, "Hiba történt a felhasználó beolvasáskor", Toast.LENGTH_SHORT).show();
                userID = -1;
            }
        }

        bikeModelDAO = new BikeModelDAO(this);

        bikeCursor = bikeModelDAO.getBikesCursorForUser(this, userID);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        bikeListView.setLayoutManager(layoutManager);

        bikeCursorAdapter = new MyBikesAdapter(this, bikeCursor, userID);
        bikeListView.setAdapter(bikeCursorAdapter);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bikeCursor != null && !bikeCursor.isClosed()) {
            bikeCursor.close();
        }
    }
}

