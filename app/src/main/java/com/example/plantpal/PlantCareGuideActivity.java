package com.example.plantpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PlantCareGuideActivity extends AppCompatActivity {

    private TextView tvCareGuide;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_care_guide);

        tvCareGuide = findViewById(R.id.tv_care_guide);
        btnBack = findViewById(R.id.btn_back);

        // Aquí puedes agregar la información sobre el cuidado de plantas
        String careGuideText = "Guía de Cuidado de Plantas\n\n" +
                "1. Riego: Asegúrate de regar tus plantas regularmente. " +
                "La frecuencia depende del tipo de planta y del clima. Generalmente, se recomienda regar una vez a la semana.\n\n" +
                "2. Luz: La mayoría de las plantas requieren luz natural. " +
                "Colócalas en un lugar donde reciban luz indirecta, pero evita la luz solar directa, que puede quemar las hojas.\n\n" +
                "3. Suelo: Utiliza un sustrato adecuado según el tipo de planta. " +
                "Las plantas suculentas, por ejemplo, necesitan un suelo bien drenado.\n\n" +
                "4. Fertilización: Aplica fertilizante cada cierto tiempo, especialmente durante la temporada de crecimiento. " +
                "Sigue las instrucciones del producto para no sobrefertilizar.\n\n" +
                "5. Poda: Recorta las hojas muertas y las ramas dañadas para fomentar un crecimiento saludable.\n\n" +
                "6. Plagas: Revisa regularmente tus plantas en busca de plagas y enfermedades. " +
                "Si encuentras alguna, actúa rápidamente con un tratamiento adecuado.\n\n" +
                "Recuerda que cada planta tiene sus necesidades específicas. ¡Investiga y cuida de tus plantas con amor!";

        tvCareGuide.setText(careGuideText);

        // Configurar el botón de volver
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantCareGuideActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish(); // Opcional, para cerrar esta actividad
            }
        });
    }
}