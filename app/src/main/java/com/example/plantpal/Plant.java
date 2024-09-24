package com.example.plantpal;

import java.io.Serializable;

public class Plant implements Serializable {
    private String name;
    private String species;
    private int wateringFrequency;

    public Plant(String name, String species, int wateringFrequency) {
        this.name = name;
        this.species = species;
        this.wateringFrequency = wateringFrequency;
    }

    // Getters y setters si los necesitas
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

    public int getWateringFrequency() {
        return wateringFrequency;
    }

    public void setWateringFrequency(int wateringFrequency) {
        this.wateringFrequency = wateringFrequency;
    }

    @Override
    public String toString() {
        return name + " (" + species + ") - Riego cada " + wateringFrequency + " días";
    }
}
