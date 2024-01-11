package com.example.motorbike_catalog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyBikesAdapter extends RecyclerView.Adapter<MyBikesAdapter.BikeViewHolder> {

    private Cursor cursor;
    private Context context;
    private int userID;

    public MyBikesAdapter(Context context, Cursor cursor, int userID) {
        this.cursor = cursor;
        this.context = context;
        this.userID = userID;
    }

    public MyBikesAdapter()
    {}

    @NonNull
    @Override
    public BikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_bike_item, parent, false);
        return new BikeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BikeViewHolder holder, int position) {
        if (cursor != null && cursor.moveToPosition(position)) {
            String modelName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String manufacturer = cursor.getString(cursor.getColumnIndexOrThrow("manufactuer"));
            int horsepower = cursor.getInt(cursor.getColumnIndexOrThrow("horsepower"));

            holder.textViewModelName.setText(modelName);
            holder.textViewManufacturer.setText("Manufacturer: " + manufacturer);
            holder.textViewHorsepower.setText("Horsepower: " + horsepower);

            holder.buttonDelete.setOnClickListener(view -> {
                String bikeToDelete = holder.textViewModelName.getText().toString();
                deleteBikeFromSharedPreferences(userID, bikeToDelete);
                notifyItemRemoved(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Motor törölve!!!", Toast.LENGTH_SHORT).show();
            });

            holder.itemView.setOnClickListener(v -> {
                int bikeNameColumnIndex = cursor.getColumnIndexOrThrow("name");
                String bikeName = cursor.getString(bikeNameColumnIndex);

                // Open BikeDetail activity with the bike's name
                Intent intent = new Intent(context, BikeDetail.class);
                intent.putExtra("MODEL_NAME", bikeName);
                context.startActivity(intent);
            });
        }
    }


    @Override
    public int getItemCount() {
        return cursor != null ? cursor.getCount() : 0;
    }

    public static class BikeViewHolder extends RecyclerView.ViewHolder {
        TextView textViewModelName;
        TextView textViewManufacturer;
        TextView textViewHorsepower;
        Button buttonDelete;

        public BikeViewHolder(View itemView) {
            super(itemView);
            textViewModelName = itemView.findViewById(R.id.textViewModelName);
            textViewManufacturer = itemView.findViewById(R.id.textViewManufacturer);
            textViewHorsepower = itemView.findViewById(R.id.textViewHorsepower);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        cursor = newCursor;
        notifyDataSetChanged();
    }
    private void deleteBikeFromSharedPreferences(int userID, String bikeName) {
        SharedPreferences preferences = context.getSharedPreferences("BikeData", Context.MODE_PRIVATE);
        String userKey = String.valueOf(userID);

        String savedBikes = preferences.getString(userKey, "");
        if (!savedBikes.isEmpty()) {
            String[] bikeArray = savedBikes.split(",");
            List<String> bikesList = new ArrayList<>(Arrays.asList(bikeArray));

            if (bikesList.remove(bikeName)) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(userKey, TextUtils.join(",", bikesList));
                editor.apply();
            }
        }
    }

}

