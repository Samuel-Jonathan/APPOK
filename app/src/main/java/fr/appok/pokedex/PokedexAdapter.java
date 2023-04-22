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
        holder.imageView.setImageBitmap(model.getBitmap());

        System.out.println(PokedexActivity.data.size() );

        // Défini le texte associé à l'élément de la RecyclerView
        holder.textView.setText(model.getName());

        // Supprime la ProgressBar une fois l'image chargée
        holder.progressBar.setVisibility(View.GONE);

        // Détection du clic sur les images
        holder.imageView.setOnClickListener(view -> {

            if (mListener != null && PokedexActivity.data.size() >= 151) {
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
        public ImageView imageView;
        public TextView textView;
        public ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagePokedex);
            textView = itemView.findViewById(R.id.namePokemon);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, String name);
    }
}