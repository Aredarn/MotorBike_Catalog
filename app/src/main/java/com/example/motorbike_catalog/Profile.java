package com.example.motorbike_catalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    TextView UserName;
    int userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        UserName = findViewById(R.id.ProfileNameTextView);

        Intent intent = getIntent();
        if (intent != null) {
            int userId = intent.getIntExtra("userId", -1);
            if (userId != -1) {

                SharedPreferences sharedPreferences = getSharedPreferences("user_profiles", MODE_PRIVATE);
                String username = sharedPreferences.getString("username_" + userId, "");
                UserName.setText(username);
                userID = userId;
                // Proceed with displaying or working with the user data
            } else {
                Toast.makeText(this, "Hiba történt a felhasználó beolvasáskor", Toast.LENGTH_SHORT).show();
                UserName.setText("NOT FOUND");
                userID = -1;
            }
        }
    }


    public void openAddBikeMenu(View v)
    {
        if(userID != -1)
        {
            Intent addBike = new Intent(Profile.this,addBikeToProfile.class);
            addBike.putExtra("USER_ID",userID);
            startActivity(addBike);
        }
        else
        {
            Toast.makeText(this, "Nem vagy bejelentkezve", Toast.LENGTH_SHORT).show();
        }

    }
    public void openMyBikesMenu(View v)
    {
        if(userID != -1)
        {
            Intent addBike = new Intent(Profile.this,MyBikes.class);
            addBike.putExtra("USER_ID",userID);
            startActivity(addBike);
        }
        else
        {
            Toast.makeText(this, "Nem vagy bejelentkezve", Toast.LENGTH_SHORT).show();
        }

    }



}