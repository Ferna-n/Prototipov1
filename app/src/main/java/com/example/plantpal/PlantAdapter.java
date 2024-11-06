package com.example.plantpal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {

    private ArrayList<Plant> plantList;
    private OnPlantClickListener listener;
    private Context context;

    public interface OnPlantClickListener {
        void onPlantDelete(Plant plant);
        void onPlantModify(Plant plant);
    }

    public PlantAdapter(ArrayList<Plant> plantList, Context context, OnPlantClickListener listener) {
        this.plantList = plantList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public PlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant, parent, false);
        return new PlantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlantViewHolder holder, int position) {
        Plant plant = plantList.get(position);

        holder.nameTextView.setText("Planta: " + plant.getName());
        holder.typeTextView.setText("Tipo: " + plant.getType()); // Mostrar el tipo
        holder.descriptionTextView.setText("Descripción: " + plant.getDescription()); // Mostrar la descripción
        holder.daysTextView.setText("Días: " + plant.getDays());

        holder.modifyButton.setOnClickListener(v -> listener.onPlantModify(plant));
        holder.deleteButton.setOnClickListener(v -> listener.onPlantDelete(plant));
    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }

    public static class PlantViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, typeTextView, descriptionTextView, daysTextView;
        Button modifyButton, deleteButton;

        public PlantViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_view_plant_name);
            typeTextView = itemView.findViewById(R.id.text_view_type);
            descriptionTextView = itemView.findViewById(R.id.text_view_description);
            daysTextView = itemView.findViewById(R.id.text_view_days);
            modifyButton = itemView.findViewById(R.id.button_modify);
            deleteButton = itemView.findViewById(R.id.button_delete);
        }
    }
}
