package com.example.plantpal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchNotifications;
    private RatingBar ratingBar;
    private Button btnSaveSettings, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchNotifications = findViewById(R.id.switch_notifications);
        ratingBar = findViewById(R.id.ratingBar);
        btnSaveSettings = findViewById(R.id.btn_save_settings);
        btnBack = findViewById(R.id.btn_back);

        // Recuperar configuraciones de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE);
        switchNotifications.setChecked(sharedPreferences.getBoolean("notifications_enabled", false));
        ratingBar.setRating(sharedPreferences.getFloat("app_rating", 0));

        // Listener para el RatingBar
        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            if (fromUser) {
                Toast.makeText(SettingsActivity.this, "¡Gracias por calificarnos!", Toast.LENGTH_SHORT).show();
            }
        });

        btnSaveSettings.setOnClickListener(v -> {
            boolean notificationsEnabled = switchNotifications.isChecked();
            float rating = ratingBar.getRating();

            // Guardar en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("notifications_enabled", notificationsEnabled);
            editor.putFloat("app_rating", rating);
            editor.apply();

            // Mostrar un mensaje
            Toast.makeText(SettingsActivity.this, notificationsEnabled ? "Notificaciones activadas" : "Notificaciones desactivadas", Toast.LENGTH_SHORT).show();
        });

        btnBack.setOnClickListener(v -> finish()); // Regresar a la actividad anterior
    }
}
