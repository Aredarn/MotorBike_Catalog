package com.example.motorbike_catalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class New_Profile extends AppCompatActivity {

    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);
        username = findViewById(R.id.nameEditText);
        password = findViewById(R.id.passwordEditText);
    }


    public void createNewUser(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_profiles", MODE_PRIVATE);
        int totalUsers = sharedPreferences.getInt("user_id", 0);

        String newUsername = username.getText().toString();
        String newPassword = password.getText().toString();

        int existingUserId = findUser(sharedPreferences, newUsername, newPassword);
        if (existingUserId != -1) {
            Intent profileIntent = new Intent(this, Profile.class);
            profileIntent.putExtra("userId", existingUserId);
            Toast.makeText(this, "Üdv újra itt: " + newUsername, Toast.LENGTH_SHORT).show();
            startActivity(profileIntent);

        } else {

            if (!isUsernameExists(sharedPreferences, newUsername)) {
                int userId = totalUsers + 1;

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("username_" + userId, newUsername);
                editor.putString("password_" + userId, newPassword);
                editor.putInt("user_id", userId);
                editor.apply();

                Intent profileIntent = new Intent(this, Profile.class);
                profileIntent.putExtra("userId", userId);
                Toast.makeText(this, "Új profil létrehozva!", Toast.LENGTH_SHORT).show();
                startActivity(profileIntent);

            } else {
                Toast.makeText(this, "Hibás felhasználónév vagy jelszó", Toast.LENGTH_LONG).show();
            }
        }
    }

    private int findUser(SharedPreferences sharedPreferences, String username, String password) {
        int totalUsers = sharedPreferences.getInt("user_id", 0);
        for (int userId = 1; userId <= totalUsers; userId++) {
            String storedUsername = sharedPreferences.getString("username_" + userId, "");
            String storedPassword = sharedPreferences.getString("password_" + userId, "");
            if (username.equals(storedUsername) && password.equals(storedPassword)) {
                return userId;
            }
        }
        return -1;
    }

    private boolean isUsernameExists(SharedPreferences sharedPreferences, String username) {
        int totalUsers = sharedPreferences.getInt("user_id", 0);
        for (int userId = 1; userId <= totalUsers; userId++) {
            String storedUsername = sharedPreferences.getString("username_" + userId, "");
            if (username.equals(storedUsername)) {
                return true;
            }
        }
        return false;
    }


}