package com.example.plantpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyPlantActivity extends AppCompatActivity {

    private EditText editTextName, editTextDays;
    private Plant plant;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_plant);

        editTextName = findViewById(R.id.edit_text_name);
        editTextDays = findViewById(R.id.edit_text_days);

        databaseHelper = new DatabaseHelper(this);

        // Recuperar la planta desde el Intent
        plant = (Plant) getIntent().getSerializableExtra("plant");
        if (plant != null) {
            editTextName.setText(plant.getName());
            editTextDays.setText(String.valueOf(plant.getDays()));
        }

        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String daysString = editTextDays.getText().toString();

            int days = 0;
            if (!daysString.isEmpty()) {
                days = Integer.parseInt(daysString);
            }

            plant.setName(name);
            plant.setDays(days);
            databaseHelper.updatePlant(plant); // Actualizar la planta en la base de datos

            Intent resultIntent = new Intent();
            resultIntent.putExtra("modifiedPlant", plant);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
