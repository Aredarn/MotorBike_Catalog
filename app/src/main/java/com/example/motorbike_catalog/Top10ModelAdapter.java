package com.example.motorbike_catalog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.motorbike_catalog.BikeModel;


import java.util.List;

public class Top10ModelAdapter extends RecyclerView.Adapter<Top10ModelAdapter.BikeModelViewHolder> {
    private List<BikeModel> top10BikeModels;
    private OnItemClickListener itemClickListener;

    public Top10ModelAdapter(List<BikeModel> top10BikeModels) {
        this.top10BikeModels = top10BikeModels;
    }

    @NonNull
    @Override
    public BikeModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topbike_item, parent, false);
        return new BikeModelViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return top10BikeModels.size();
    }

    public static class BikeModelViewHolder extends RecyclerView.ViewHolder {
        TextView textViewModelName, textViewManufacturer, textViewHorsepower, textClicks;

        public BikeModelViewHolder(View itemView) {
            super(itemView);
            textViewModelName = itemView.findViewById(R.id.textViewModelName);
            textViewManufacturer = itemView.findViewById(R.id.textViewManufacturer);
            textViewHorsepower = itemView.findViewById(R.id.textViewHorsepower);
            textClicks = itemView.findViewById(R.id.clickCount);
        }
    }

    // Interface for item click events
    public interface OnItemClickListener {
        void onItemClick(String modelName);
    }

    // Set the click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull BikeModelViewHolder holder, int position) {
        BikeModel bikeModel = top10BikeModels.get(position);
        holder.textViewModelName.setText(bikeModel.getManufacturer() + " " + bikeModel.getName());
        holder.textViewManufacturer.setText("Gyártó: " + bikeModel.getManufacturer());
        holder.textViewHorsepower.setText("Lóerő: " + bikeModel.getHorsepower());
        holder.textClicks.setText("Kattintások: " + bikeModel.getClicks());

        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(bikeModel.getName());
            }
        });
    }
}
