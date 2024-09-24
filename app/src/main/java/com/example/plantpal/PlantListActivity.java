package com.example.plantpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PlantListActivity extends AppCompatActivity {

    private ListView lvPlants;
    private Button btnAddPlant;
    private ArrayList<Plant> plantList;
    private ArrayAdapter<Plant> plantAdapter;

    private static final int ADD_PLANT_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list);

        lvPlants = findViewById(R.id.lv_plants);
        btnAddPlant = findViewById(R.id.btn_add_plant);

        // Inicializamos la lista y el adaptador
        plantList = new ArrayList<>();
        plantAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, plantList);
        lvPlants.setAdapter(plantAdapter);

        // Listener para agregar plantas
        btnAddPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantListActivity.this, AddPlantActivity.class);
                startActivityForResult(intent, ADD_PLANT_REQUEST_CODE);
            }
        });

        // Listener para ver los detalles de la planta seleccionada
        lvPlants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PlantListActivity.this, PlantDetailActivity.class);
                intent.putExtra("plant", plantList.get(position));
                startActivity(intent);
            }
        });
    }

    // Método para recibir los datos desde AddPlantActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PLANT_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<Plant> updatedPlantList = (ArrayList<Plant>) data.getSerializableExtra("plantList");
            if (updatedPlantList != null) {
                plantList.clear();
                plantList.addAll(updatedPlantList); // Actualizamos la lista
                plantAdapter.notifyDataSetChanged(); // Refrescamos la vista
            }
        }
    }
}



