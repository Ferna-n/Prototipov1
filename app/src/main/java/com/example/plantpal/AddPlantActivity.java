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

public class AddPlantActivity extends AppCompatActivity {

    private EditText etPlantName, etWateringFrequency;
    private Spinner spPlantType;
    private Button btnSavePlant;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        // Vincular los campos con sus IDs
        etPlantName = findViewById(R.id.et_plant_name);
        etWateringFrequency = findViewById(R.id.et_watering_frequency);
        spPlantType = findViewById(R.id.sp_plant_type);
        btnSavePlant = findViewById(R.id.btn_save_plant);
        btnBack = findViewById(R.id.btn_back);

        // Configurar el Spinner con los tipos de plantas desde strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.plant_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPlantType.setAdapter(adapter);

        // Configurar el botón de volver
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPlantActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish(); // Opcional, para cerrar esta actividad
            }
        });

        btnSavePlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados por el usuario
                String name = etPlantName.getText().toString();
                String species = spPlantType.getSelectedItem().toString();
                String wateringFrequencyText = etWateringFrequency.getText().toString();

                // Validar que los campos no estén vacíos
                if (name.isEmpty() || wateringFrequencyText.isEmpty()) {
                    Toast.makeText(AddPlantActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convertir el texto de frecuencia de riego a un entero
                try {
                    int wateringFrequency = Integer.parseInt(wateringFrequencyText);

                    // Crear una nueva planta con los datos ingresados
                    Plant newPlant = new Plant(name, species, wateringFrequency);

                    // Enviar la nueva planta de vuelta a la actividad principal
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newPlant", newPlant);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } catch (NumberFormatException e) {
                    // Manejo del error si el número no es válido
                    Toast.makeText(AddPlantActivity.this, "Frecuencia de riego debe ser un número", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
