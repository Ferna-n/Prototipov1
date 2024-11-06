package com.example.plantpal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlantListActivity extends AppCompatActivity implements PlantAdapter.OnPlantClickListener {

    private RecyclerView recyclerView;
    private PlantAdapter plantAdapter;
    private ArrayList<Plant> plantList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);
        loadPlants(); // Cargar las plantas al iniciar

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(PlantListActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        });
    }

    // Método para cargar las plantas desde la base de datos
    private void loadPlants() {
        plantList = databaseHelper.getPlants(); // Obtener plantas desde la base de datos
        plantAdapter = new PlantAdapter(plantList, this, this);
        recyclerView.setAdapter(plantAdapter);
    }


    @Override
    public void onPlantDelete(Plant plant) {
        // Eliminar planta de la base de datos
        if (databaseHelper.deletePlant(plant.getId())) {
            plantList.remove(plant);
            plantAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Planta eliminada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al eliminar la planta", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPlantModify(Plant plant) {
        // Iniciar ModifyPlantActivity para modificar la planta
        Intent intent = new Intent(PlantListActivity.this, ModifyPlantActivity.class);
        intent.putExtra("plant", plant); // Pasar el objeto Plant a ModifyPlantActivity
        startActivityForResult(intent, 2); // Iniciar ModifyPlantActivity con requestCode 2
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Plant updatedPlant = (Plant) data.getSerializableExtra("updatedPlant");
            if (updatedPlant != null) {
                // Encuentra la planta en la lista y actualízala
                for (int i = 0; i < plantList.size(); i++) {
                    if (plantList.get(i).getId() == updatedPlant.getId()) {
                        plantList.set(i, updatedPlant); // Actualizar la planta modificada
                        plantAdapter.notifyItemChanged(i); // Notificar al adaptador sobre el cambio
                        break;
                    }
                }
            }
        }
    }
}