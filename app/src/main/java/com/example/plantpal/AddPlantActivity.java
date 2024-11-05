package com.example.plantpal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log; // Agrega para depuración
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddPlantActivity extends AppCompatActivity {
    private EditText editTextPlantName;
    private EditText editTextPlantType;
    private Button buttonAddPlant;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        editTextPlantName = findViewById(R.id.editTextPlantName);
        editTextPlantType = findViewById(R.id.editTextPlantType);
        buttonAddPlant = findViewById(R.id.buttonAddPlant);
        buttonBack = findViewById(R.id.buttonBack);

        buttonAddPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlant();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddPlantActivity.this, WelcomeActivity.class));
                finish();
            }
        });
    }

    private void addPlant() {
        String name = editTextPlantName.getText().toString();
        String type = editTextPlantType.getText().toString();
        int days = 15;

        if (!name.isEmpty() && !type.isEmpty()) {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            try {
                if (databaseHelper.addPlant(name, type, days)) {
                    Toast.makeText(this, "Planta agregada exitosamente", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddPlantActivity.this, PlantListActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Error al agregar planta", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("AddPlantActivity", "Error al agregar planta: " + e.getMessage());
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            } finally {
                databaseHelper.close();
            }
        } else {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}