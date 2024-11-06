package com.example.plantpal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;



public class ModifyPlantActivity extends AppCompatActivity {

    private EditText etPlantName, etPlantType, etPlantDescription, etPlantDays; // Agregar campo para días
    private Button btnSave;
    private Plant plant;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_plant);

        etPlantName = findViewById(R.id.et_plant_name);
        etPlantType = findViewById(R.id.et_plant_type);
        etPlantDescription = findViewById(R.id.et_plant_description); // Campo para descripción
        etPlantDays = findViewById(R.id.et_plant_days); // Campo para días
        btnSave = findViewById(R.id.btn_save);

        databaseHelper = new DatabaseHelper(this);
        plant = (Plant) getIntent().getSerializableExtra("plant");

        // Rellenar campos con datos existentes
        etPlantName.setText(plant.getName());
        etPlantType.setText(plant.getType());
        etPlantDescription.setText(plant.getDescription());
        etPlantDays.setText(String.valueOf(plant.getDays())); // Suponiendo que tienes un método getDays()

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plant.setName(etPlantName.getText().toString());
                plant.setType(etPlantType.getText().toString());
                plant.setDescription(etPlantDescription.getText().toString());
                plant.setDays(Integer.parseInt(etPlantDays.getText().toString())); // Actualizar días

                if (databaseHelper.updatePlant(plant)) {
                    Toast.makeText(ModifyPlantActivity.this, "Planta actualizada", Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("updatedPlant", plant);
                    setResult(RESULT_OK, returnIntent); // Devolver la planta actualizada
                    finish();
                } else {
                    Toast.makeText(ModifyPlantActivity.this, "Error al actualizar planta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
