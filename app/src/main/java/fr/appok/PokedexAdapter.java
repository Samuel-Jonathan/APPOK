package fr.appok;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


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
        return new ViewHolder(view);
    }

    // Méthode pour lier les données aux éléments de la RecyclerView
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PokedexModel model = mData.get(position);

        // Défini l'image associée à l'élément de la RecyclerView
        holder.imageView.setImageBitmap(model.getBitmap());

        // Défini le texte associé à l'élément de la RecyclerView
        holder.textView.setText(model.getName());

        // Supprime la ProgressBar une fois l'image chargée
        holder.progressBar.setVisibility(View.GONE);

        // Défini la couleur de fond du TextView
        holder.textView.setBackgroundColor(Color.RED);

        // Détection du clic sur les images
        holder.imageView.setOnClickListener(view -> {

            if (mListener != null) {
                mListener.onItemClick(position);
                // Récupération de la TextView associée à l'ImageView cliqué
                TextView associatedTextView = ((ViewGroup) view.getParent()).findViewById(R.id.namePokemon);
                //Récupère le nom du pokémon
                System.out.println(associatedTextView.getText());
            }
        });
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
            imageView = itemView.findViewById(R.id.pokemon);
            textView = itemView.findViewById(R.id.namePokemon);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}