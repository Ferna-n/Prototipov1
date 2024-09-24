package com.example.plantpal;

import java.io.Serializable;

public class Plant implements Serializable {
    private String name;
    private String species;
    private String wateringFrequency;
    private String type;  // Tipo de planta

    public Plant(String name, String species, String wateringFrequency, String type) {
        this.name = name;
        this.species = species;
        this.wateringFrequency = wateringFrequency;
        this.type = type;  // Nuevo campo
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getWateringFrequency() {
        return wateringFrequency;
    }

    public void setWateringFrequency(String wateringFrequency) {
        this.wateringFrequency = wateringFrequency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name + " (" + species + ", Tipo: " + type + ")";
    }
}
