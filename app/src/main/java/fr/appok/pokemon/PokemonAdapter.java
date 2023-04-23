package fr.appok.pokemon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.appok.R;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private List<PokemonModel> mData;
    private int scroll = 0;

    public PokemonAdapter(List<PokemonModel> data) {
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PokemonModel element = mData.get(position);
        System.out.println(scroll);
        if(position == 0){
            holder.nameView.setText(element.getName());
            holder.typesView.setText(element.getTypes());
            holder.weightView.setText(element.getWeight());
            holder.heightView.setText(element.getHeight());
        }else if(position == 1){
            holder.typesView.setText(element.getTypes());
            holder.weightView.setText(element.getWeight());
        }

        scroll++;

        // Vérifier si la vue est déjà associée à un modèle de données
       /* if (holder.itemView.getTag() == null || holder.itemView.getTag() != element) {
            // La vue n'est pas associée à ce modèle de données, créer une nouvelle vue
            holder.nameView.setText(element.getName());
            holder.typesView.setText(element.getTypes());
            holder.weightView.setText(element.getWeight());
            holder.heightView.setText(element.getHeight());
            System.out.println(element);
            System.out.println(holder.itemView.getTag());
            // Enregistrer le modèle de données comme tag de la vue pour la réutiliser plus tard
            holder.itemView.setTag(element);
        } else {
            // La vue est déjà associée à ce modèle de données, simplement mettre à jour les données de la vue
            holder.nameView.setText(element.getName());

        }*/
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameView;
        public TextView typesView;
        public TextView weightView;
        public TextView heightView;

        public ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.nameView);
            typesView = view.findViewById(R.id.typesView);
            weightView = view.findViewById(R.id.weightView);
            heightView = view.findViewById(R.id.heightView);
        }
    }
}
