package com.example.motorbike_catalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class manufacturers extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ManufacturerAdapter adapter;
    private List<Manufacturer> manufacturerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturers);

        recyclerView = findViewById(R.id.manufacturerRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        manufacturerList = new ArrayList<>();
        manufacturerList.add(new Manufacturer("Honda"));
        manufacturerList.add(new Manufacturer("Yamaha"));
        manufacturerList.add(new Manufacturer("Kawasaki"));
        manufacturerList.add(new Manufacturer("Suzuki"));

        adapter = new ManufacturerAdapter(manufacturerList);
        recyclerView.setAdapter(adapter);
    }
}