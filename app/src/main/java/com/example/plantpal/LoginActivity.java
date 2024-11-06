package com.example.plantpal;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private CheckBox chkShowPassword;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        chkShowPassword = findViewById(R.id.chk_show_password);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnRegister = findViewById(R.id.btn_register);
        Button btnExit = findViewById(R.id.btn_exit);

        dbHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(v -> loginUser());
        btnRegister.setOnClickListener(v -> registerUser());
        btnExit.setOnClickListener(v -> {
            // Cerrar la actividad actual y salir de la aplicación
            finishAffinity(); // Cierra todas las actividades en la pila y sale de la aplicación
        });

        chkShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT);
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            etPassword.setSelection(etPassword.getText().length());
        });
    }

    private void loginUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (dbHelper.checkUser(username, password)) {
            // Login exitoso
            Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show();

            // Redirigir a WelcomeActivity
            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish(); // Cierra LoginActivity para no volver atrás
        } else {
            // Login fallido
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    private void registerUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Verificar si el nombre de usuario ya está tomado
        if (dbHelper.isUsernameTaken(username)) {
            Toast.makeText(this, "El nombre de usuario ya está tomado", Toast.LENGTH_SHORT).show();
            return; // Salir del método si el nombre de usuario ya existe
        }

        if (dbHelper.addUser(username, password)) {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
        }
    }
}