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
    private Context context;
    private OnPlantClickListener listener;

    public interface OnPlantClickListener {
        void onPlantClick(Plant plant);
        void onPlantDelete(Plant plant);
        void onPlantModify(Plant plant); // Nueva interfaz para modificar
    }

    public PlantAdapter(ArrayList<Plant> plantList, Context context, OnPlantClickListener listener) {
        this.plantList = plantList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_item, parent, false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        Plant plant = plantList.get(position);
        holder.textViewPlantName.setText(plant.getName());
        holder.textViewDays.setText("Días: " + plant.getDays()); // Mostrar días

        holder.itemView.setOnClickListener(v -> listener.onPlantClick(plant));
        holder.buttonDelete.setOnClickListener(v -> listener.onPlantDelete(plant));
        holder.buttonModify.setOnClickListener(v -> listener.onPlantModify(plant)); // Manejar modificar
    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }

    public static class PlantViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewPlantName;
        public TextView textViewDays; // Nueva vista para mostrar días
        public Button buttonDelete;
        public Button buttonModify; // Nuevo botón para modificar

        public PlantViewHolder(View itemView) {
            super(itemView);
            textViewPlantName = itemView.findViewById(R.id.text_view_plant_name);
            textViewDays = itemView.findViewById(R.id.text_view_days); // Agregar esta línea
            buttonDelete = itemView.findViewById(R.id.button_delete);
            buttonModify = itemView.findViewById(R.id.button_modify); // Inicializar botón modificar
        }
    }
}