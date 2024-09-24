package com.example.plantpal;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlantDetailActivity extends AppCompatActivity {

    private TextView tvPlantName, tvPlantSpecies, tvWateringFrequency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);

        tvPlantName = findViewById(R.id.tv_plant_name);
        tvPlantSpecies = findViewById(R.id.tv_plant_species);
        tvWateringFrequency = findViewById(R.id.tv_watering_frequency);

        // Obtenemos los datos de la planta desde el Intent
        Plant plant = (Plant) getIntent().getSerializableExtra("plant");

        if (plant != null) {
            tvPlantName.setText(plant.getName());
            tvPlantSpecies.setText(plant.getSpecies());
            tvWateringFrequency.setText("Riego cada: " + plant.getWateringFrequency() + " días");
        }
    }
}
