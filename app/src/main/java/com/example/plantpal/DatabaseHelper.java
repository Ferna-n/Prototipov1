package com.example.plantpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.mindrot.jbcrypt.BCrypt;
import java.util.ArrayList;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "plantPal.db";
    private static final int DATABASE_VERSION = 3; // Incrementa la versión para aplicar cambios en la estructura

    // Tabla de usuarios
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // Tabla de plantas
    private static final String TABLE_PLANTS = "plants";
    private static final String COLUMN_PLANT_ID = "id";
    private static final String COLUMN_PLANT_NAME = "name";
    private static final String COLUMN_PLANT_TYPE = "type";
    private static final String COLUMN_PLANT_DAYS = "days"; // Nueva columna para los días

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de usuarios
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT NOT NULL UNIQUE, " +
                COLUMN_PASSWORD + " TEXT NOT NULL)";
        db.execSQL(createUsersTable);

        // Crear tabla de plantas
        String createPlantsTable = "CREATE TABLE " + TABLE_PLANTS + " (" +
                COLUMN_PLANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PLANT_NAME + " TEXT NOT NULL, " +
                COLUMN_PLANT_TYPE + " TEXT NOT NULL, " +
                COLUMN_PLANT_DAYS + " INTEGER NOT NULL)"; // Agrega columna para los días
        db.execSQL(createPlantsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Verifica si la tabla plants existe antes de aplicar ALTER TABLE
        try {
            if (oldVersion < 3) {
                db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PLANTS + " (" +
                        COLUMN_PLANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_PLANT_NAME + " TEXT NOT NULL, " +
                        COLUMN_PLANT_TYPE + " TEXT NOT NULL, " +
                        COLUMN_PLANT_DAYS + " INTEGER DEFAULT 0)");
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error en onUpgrade: " + e.getMessage(), e);
        }
    }

    // Métodos de gestión de usuarios
    public SQLiteDatabase getDatabase() {
        return this.getWritableDatabase();
    }

    public boolean addUser(String username, String password) {
        SQLiteDatabase db = getDatabase();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, hashedPassword);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();

        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = getDatabase();
        String query = "SELECT " + COLUMN_PASSWORD + " FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        if (cursor != null && cursor.moveToFirst()) {
            String hashedPassword = cursor.getString(0);
            boolean passwordMatch = BCrypt.checkpw(password, hashedPassword);
            cursor.close();
            db.close();
            return passwordMatch;
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return false;
    }

    public boolean isUsernameTaken(String username) {
        SQLiteDatabase db = getDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + "=?";
        Cursor cursor = null;
        boolean isTaken = false;

        try {
            cursor = db.rawQuery(query, new String[]{username});
            if (cursor.moveToFirst()) {
                int count = cursor.getInt(0);
                isTaken = count > 0;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return isTaken;
    }

    public boolean addPlant(String name, String type, int days) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLANT_NAME, name);
        values.put(COLUMN_PLANT_TYPE, type);
        values.put(COLUMN_PLANT_DAYS, days); // Agregar los días

        long result = -1;
        try {
            result = db.insert(TABLE_PLANTS, null, values);
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error al agregar planta: " + e.getMessage(), e); // Incluye toda la excepción en el log
        } finally {
            db.close(); // Cierra la conexión
        }

        return result != -1;
    }

    public ArrayList<Plant> getPlants() {
        ArrayList<Plant> plants = new ArrayList<>();
        SQLiteDatabase db = getDatabase();
        String query = "SELECT * FROM " + TABLE_PLANTS;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_PLANT_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_NAME));
                String type = cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_TYPE));
                int days = cursor.getInt(cursor.getColumnIndex(COLUMN_PLANT_DAYS));
                plants.add(new Plant(id, name, type, days));
            }
            cursor.close();
        }
        db.close();
        return plants;
    }

    public boolean deletePlant(int id) {
        SQLiteDatabase db = getDatabase();
        return db.delete(TABLE_PLANTS, COLUMN_PLANT_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    public boolean updatePlant(Plant plant) {
        SQLiteDatabase db = getDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLANT_NAME, plant.getName());
        values.put(COLUMN_PLANT_TYPE, plant.getType());
        values.put(COLUMN_PLANT_DAYS, plant.getDays());

        int rowsAffected = db.update(TABLE_PLANTS, values, COLUMN_PLANT_ID + "=?", new String[]{String.valueOf(plant.getId())});
        db.close();
        return rowsAffected > 0;
    }
}
