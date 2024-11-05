package com.example.plantpal;

import android.os.Parcel;
import android.os.Parcelable;

public class Plant implements Parcelable {
    private int id;
    private String name;
    private String type;
    private int days;

    // Constructor
    public Plant(int id, String name, String type, int days) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.days = days;
    }

    // Constructor para Parcelable
    protected Plant(Parcel in) {
        id = in.readInt();
        name = in.readString();
        type = in.readString();
        days = in.readInt();
    }

    // Implementación de Parcelable
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(type);
        parcel.writeInt(days);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Plant> CREATOR = new Creator<Plant>() {
        @Override
        public Plant createFromParcel(Parcel in) {
            return new Plant(in);
        }

        @Override
        public Plant[] newArray(int size) {
            return new Plant[size];
        }
    };

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getDays() {
        return days;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
