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
    private OnItemClickListener itemClickListener;

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
    public int getItemCount() {
        return manufacturers.size();
    }

    public static class ManufacturerViewHolder extends RecyclerView.ViewHolder {
        TextView textViewManufacturer,textViewLocation,textViewYear;

        public ManufacturerViewHolder(View itemView) {
            super(itemView);
            textViewManufacturer = itemView.findViewById(R.id.textViewManufacturer);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            textViewYear = itemView.findViewById(R.id.textViewYear);
        }
    }


    // Interface for item click events
    public interface OnItemClickListener {
        void onItemClick(String manufacturerName);
    }

    // Set the click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }


    @Override
    public void onBindViewHolder(@NonNull ManufacturerViewHolder holder, int position) {
        Manufacturer manufacturer = manufacturers.get(position);
        holder.textViewManufacturer.setText(manufacturer.getName());
        holder.textViewLocation.setText("Ország: " +manufacturer.getLocation());
        holder.textViewYear.setText("Alapítási év: "+manufacturer.getFoundationYear());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(manufacturer.getName());
                }
            }
        });
    }
}
