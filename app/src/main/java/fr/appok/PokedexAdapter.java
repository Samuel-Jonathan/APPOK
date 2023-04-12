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

    public PokedexAdapter(List<PokedexModel> data) {
        mData = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokedex_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PokedexModel model = mData.get(position);

        holder.imageView.setImageBitmap(model.getBitmap());
        holder.textView.setText(model.getName());
        holder.progressBar.setVisibility(View.GONE);

        holder.textView.setBackgroundColor(Color.RED);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
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
}
