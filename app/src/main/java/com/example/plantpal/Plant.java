package com.example.plantpal;

import java.io.Serializable;

public class Plant implements Serializable {
    private int id;
    private String name;
    private String type;
    private String description;
    private int days;

    // Constructor, getters y setters

    public Plant(int id, String name, String type, String description, int days) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.days = days;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}

