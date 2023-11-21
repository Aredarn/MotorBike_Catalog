package com.example.motorbike_catalog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.BikeModelViewHolder> {
    private List<BikeModel> bikeModels;
    private OnItemClickListener itemClickListener;

    public ModelAdapter(List<BikeModel> bikeModels) {
        this.bikeModels = bikeModels;
    }

    @NonNull
    @Override
    public BikeModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bike_model_item, parent, false);
        return new BikeModelViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return bikeModels.size();
    }

    public static class BikeModelViewHolder extends RecyclerView.ViewHolder {
        TextView textViewModelName, textViewManufacturer, textViewHorsepower;

        public BikeModelViewHolder(View itemView) {
            super(itemView);
            textViewModelName = itemView.findViewById(R.id.textViewModelName);
            textViewManufacturer = itemView.findViewById(R.id.textViewManufacturer);
            textViewHorsepower = itemView.findViewById(R.id.textViewHorsepower);
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
        BikeModel bikeModel = bikeModels.get(position);
        holder.textViewModelName.setText(bikeModel.getManufacturer() + " " + bikeModel.getName());
        holder.textViewManufacturer.setText("Gyártó: " + bikeModel.getManufacturer());
        holder.textViewHorsepower.setText("Lóerő: " + bikeModel.getHorsepower());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(bikeModel.getName());
                }
            }
        });
    }
}

