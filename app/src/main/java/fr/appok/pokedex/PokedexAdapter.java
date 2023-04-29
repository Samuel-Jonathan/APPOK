package fr.appok.pokedex;

import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.appok.R;


public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.ViewHolder> {

    private List<PokedexModel> mData;

    private OnItemClickListener mListener;


    public PokedexAdapter(List<PokedexModel> data) {
        mData = data;
    }

    // Méthode pour créer un ViewHolder pour chaque élément de la RecyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokedex_activity, parent, false);
        view.setBackgroundColor(Color.TRANSPARENT);
        return new ViewHolder(view);
    }

    // Méthode pour lier les données aux éléments de la RecyclerView
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PokedexModel model = mData.get(position);

        // Défini l'image associée à l'élément de la RecyclerView
        holder.pokedexPokemonImage.setImageBitmap(model.getBitmap());


        // Défini le texte associé à l'élément de la RecyclerView
        holder.pokedexText.setText(model.getName());

        // Supprime la ProgressBar une fois l'image chargée
        holder.pokedexProgressBar.setVisibility(View.GONE);

        // Détection du clic sur les images
        holder.pokedexPokemonImage.setOnClickListener(view -> {

            if (mListener != null) {
                mListener.onItemClick(position, model.getName());

            }
        });

        //Trie les pokémons en fonction de l'id
        Collections.sort(mData, Comparator.comparingInt(PokedexModel::getId));
    }

    // Méthode pour initialiser l'interface OnItemClickListener
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    // Méthode pour retourner le nombre d'éléments de la RecyclerView
    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Déclaration des vues associées à chaque élément de la RecyclerView
        public ImageView pokedexPokemonImage;
        public TextView pokedexText;
        public ProgressBar pokedexProgressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            pokedexPokemonImage = itemView.findViewById(R.id.pokedexPokemonImage);
            pokedexText = itemView.findViewById(R.id.pokedexText);
            pokedexProgressBar = itemView.findViewById(R.id.pokedexProgressBar);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, String name);
    }
}