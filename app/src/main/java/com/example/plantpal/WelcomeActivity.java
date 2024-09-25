package com.example.plantpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    private Button btnAddPlant, btnViewPlants,btnCareGuide;
    private ArrayList<Plant> plantList;  // Lista de plantas agregadas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnAddPlant = findViewById(R.id.btn_add_plant);
        btnViewPlants = findViewById(R.id.btn_view_plants);
        btnCareGuide = findViewById(R.id.btn_care_guide); // Nuevo botón

        plantList = new ArrayList<>();  // Inicializar la lista de plantas

        // Botón para agregar plantas
        btnAddPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, AddPlantActivity.class);
                startActivityForResult(intent, 1);  // Lanzar AddPlantActivity
            }
        });

        // Botón para ver la lista de plantas
        btnViewPlants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, PlantListActivity.class);
                intent.putExtra("plantList", plantList);  // Pasar la lista de plantas
                startActivity(intent);  // Lanzar PlantListActivity
            }
        });

        Button btnSettings = findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        btnCareGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, PlantCareGuideActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Recuperar la planta agregada desde AddPlantActivity
            Plant newPlant = (Plant) data.getSerializableExtra("newPlant");
            plantList.add(newPlant);  // Añadir la nueva planta a la lista
        }
    }

}
