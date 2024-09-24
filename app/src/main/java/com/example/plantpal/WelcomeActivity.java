package com.example.plantpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    private Button btnAddPlant, btnViewPlants;
    private ArrayList<Plant> plantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnAddPlant = findViewById(R.id.btn_add_plant);
        btnViewPlants = findViewById(R.id.btn_view_plants);

        // Inicializamos la lista de plantas si no existe
        if (getIntent().getSerializableExtra("plantList") != null) {
            plantList = (ArrayList<Plant>) getIntent().getSerializableExtra("plantList");
        } else {
            plantList = new ArrayList<>();
        }

        // Navegar a la pantalla para agregar plantas
        btnAddPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, AddPlantActivity.class);
                intent.putExtra("plantList", plantList);  // Pasamos la lista de plantas
                startActivityForResult(intent, 1);
            }
        });

        // Navegar a la lista de plantas
        btnViewPlants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, PlantListActivity.class);
                intent.putExtra("plantList", plantList);  // Pasamos la lista de plantas
                startActivity(intent);
            }
        });
    }

    // Actualizamos la lista de plantas cuando regrese de AddPlantActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            plantList = (ArrayList<Plant>) data.getSerializableExtra("plantList");
        }
    }
}
