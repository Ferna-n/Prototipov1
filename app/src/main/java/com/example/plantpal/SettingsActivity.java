package com.example.plantpal;

import android.content.Intent;
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
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchNotifications = findViewById(R.id.switch_notifications);
        ratingBar = findViewById(R.id.ratingBar);
        btnSaveSettings = findViewById(R.id.btn_save_settings);
        btnBack = findViewById(R.id.btn_back);
        buttonLogout = findViewById(R.id.button_logout);

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

        // Cerrar sesión
        buttonLogout.setOnClickListener(v -> {
            // Aquí puedes limpiar cualquier información de sesión si es necesario
            // Por ejemplo, puedes eliminar el token de acceso en SharedPreferences si lo estás usando
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear(); // Limpia todas las preferencias
            editor.apply();

            // Redirige a LoginActivity
            Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Cierra esta actividad
        });

        // Volver a la actividad anterior
        btnBack.setOnClickListener(v -> finish());
    }
}
