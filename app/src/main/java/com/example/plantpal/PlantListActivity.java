package com.example.plantpal;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlantListActivity extends AppCompatActivity {

    private RecyclerView rvPlants;
    private PlantAdapter plantAdapter;
    private ArrayList<Plant> plantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list);

        rvPlants = findViewById(R.id.rv_plants);

        plantList = new ArrayList<>();
        plantAdapter = new PlantAdapter(plantList);

        rvPlants.setLayoutManager(new LinearLayoutManager(this));
        rvPlants.setAdapter(plantAdapter);

        // Obtener la lista actualizada de plantas
        if (getIntent().getSerializableExtra("plantList") != null) {
            plantList = (ArrayList<Plant>) getIntent().getSerializableExtra("plantList");
            plantAdapter.updatePlantList(plantList);  // Actualizamos la lista en el adapter
        }
    }
}
