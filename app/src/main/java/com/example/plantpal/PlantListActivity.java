package com.example.plantpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlantListActivity extends AppCompatActivity {

    private RecyclerView rvPlants;
    private PlantAdapter plantAdapter;
    private ArrayList<Plant> plantList;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list);

        rvPlants = findViewById(R.id.rv_plants);

        plantList = new ArrayList<>();
        plantAdapter = new PlantAdapter(plantList);
        btnBack = findViewById(R.id.btn_back);

        rvPlants.setLayoutManager(new LinearLayoutManager(this));
        rvPlants.setAdapter(plantAdapter);

        // Obtener la lista actualizada de plantas
        if (getIntent().getSerializableExtra("plantList") != null) {
            plantList = (ArrayList<Plant>) getIntent().getSerializableExtra("plantList");
            plantAdapter.updatePlantList(plantList);  // Actualizamos la lista en el adapter
        }

        // Configurar el botón de volver
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantListActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish(); // Opcional, para cerrar esta actividad
            }
        });
    }
}
