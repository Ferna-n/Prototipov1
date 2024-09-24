package com.example.plantpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddPlantActivity extends AppCompatActivity {

    private EditText etPlantName, etPlantSpecies, etWateringFrequency;
    private Button btnSavePlant;
    private Spinner spPlantType; // Spinner para tipo de planta
    private ArrayList<Plant> plantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        etPlantName = findViewById(R.id.et_plant_name);
        etWateringFrequency = findViewById(R.id.et_watering_frequency);
        spPlantType = findViewById(R.id.sp_plant_type); // Spinner de tipo de planta
        btnSavePlant = findViewById(R.id.btn_save_plant);

        // Configuramos el Spinner con los tipos de plantas
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.plant_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPlantType.setAdapter(adapter);

        if (getIntent().getSerializableExtra("plantList") != null) {
            plantList = (ArrayList<Plant>) getIntent().getSerializableExtra("plantList");
        } else {
            plantList = new ArrayList<>();
        }

        btnSavePlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etPlantName.getText().toString();
                String species = etPlantSpecies.getText().toString();
                String wateringFrequency = etWateringFrequency.getText().toString();
                String plantType = spPlantType.getSelectedItem().toString(); // Tipo de planta seleccionado

                if (!name.isEmpty() && !species.isEmpty() && !wateringFrequency.isEmpty()) {
                    Plant newPlant = new Plant(name, species, wateringFrequency, plantType);
                    plantList.add(newPlant);

                    Intent intent = new Intent();
                    intent.putExtra("plantList", plantList);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(AddPlantActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
