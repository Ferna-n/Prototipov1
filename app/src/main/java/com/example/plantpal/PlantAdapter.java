package com.example.plantpal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {

    private ArrayList<Plant> plantList;

    public PlantAdapter(ArrayList<Plant> plantList) {
        this.plantList = plantList;
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant, parent, false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        Plant plant = plantList.get(position);
        holder.tvPlantName.setText(plant.getName());
        holder.tvPlantSpecies.setText(plant.getSpecies());
    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }

    public void updatePlantList(ArrayList<Plant> newPlantList) {
        plantList.clear();
        plantList.addAll(newPlantList);
        notifyDataSetChanged();
    }

    class PlantViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlantName, tvPlantSpecies;

        public PlantViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlantName = itemView.findViewById(R.id.tv_plant_name);
            tvPlantSpecies = itemView.findViewById(R.id.tv_plant_species);
        }
    }
}
