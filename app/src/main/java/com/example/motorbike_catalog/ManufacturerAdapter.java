package com.example.motorbike_catalog;// ManufacturerAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ManufacturerAdapter extends RecyclerView.Adapter<ManufacturerAdapter.ManufacturerViewHolder> {
    private List<Manufacturer> manufacturers;

    public ManufacturerAdapter(List<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    @NonNull
    @Override
    public ManufacturerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manufacturer_item, parent, false);
        return new ManufacturerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManufacturerViewHolder holder, int position) {
        Manufacturer manufacturer = manufacturers.get(position);
        holder.textViewManufacturer.setText(manufacturer.getName());
    }

    @Override
    public int getItemCount() {
        return manufacturers.size();
    }

    public static class ManufacturerViewHolder extends RecyclerView.ViewHolder {
        TextView textViewManufacturer;

        public ManufacturerViewHolder(View itemView) {
            super(itemView);
            textViewManufacturer = itemView.findViewById(R.id.textViewManufacturer);
        }
    }
}
