package fr.appok.pokemon;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.appok.R;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private List<PokemonModel> mData;

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

        if(position == 0){
            holder.nameView.setText(element.getName());
            holder.typesView.setText(element.getTypes());
            holder.weightView.setText(element.getWeight());
            holder.heightView.setText(element.getHeight());
            holder.imageView.setImageBitmap(element.getImage());

        }else if(position == 1){
            holder.attackView.setVisibility(View.VISIBLE);
            holder.defenseView.setVisibility(View.VISIBLE);
            holder.attackView.setBackgroundColor(Color.BLACK);
            holder.defenseView.setBackgroundColor(Color.RED);
        }

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
        public ImageView imageView;
        public ProgressBar attackView;
        public ProgressBar defenseView;


        public ViewHolder(View view) {
            super(view);
          /*  nameView = view.findViewById(R.id.nameView);
            typesView = view.findViewById(R.id.typesView);
            weightView = view.findViewById(R.id.weightView);
            heightView = view.findViewById(R.id.heightView);
            imageView = view.findViewById(R.id.imageView);
            attackView = view.findViewById(R.id.attackView);
            defenseView = view.findViewById(R.id.defense);*/
        }
    }
}
