package fr.appok.pokedex.requests;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import fr.appok.pokedex.PokedexActivity;
import fr.appok.pokedex.PokedexAdapter;
import fr.appok.pokedex.PokedexModel;
import fr.appok.Request;

public class RequestImagesPokedex extends AsyncTask<Void, Void, Bitmap> {

    // Déclaration des variables
    private PokedexAdapter pokedexAdapter;
    private String name;
    private int id;
    private String url;
    private ProgressBar progressBar;

    // Constructeur
    public RequestImagesPokedex(PokedexAdapter pokedexAdapter, String name, int id, String url, ProgressBar progressBar){
        this.pokedexAdapter = pokedexAdapter;
        this.name = name;
        this.id = id;
        this.url = url;
        this.progressBar = progressBar;
    }

    // Méthode qui va récupérer l'image de manière asynchrone
    @Override
    protected Bitmap doInBackground(Void... voids) {
        return Request.requestBitmap(url);
    }

    // Méthode appelée quand la tâche asynchrone est terminée
    @Override
    protected void onPostExecute(Bitmap result) {
        if (result != null) {

            // Ajout du nom et de l'image du Pokémon dans la liste de données
            PokedexActivity.data.add(new PokedexModel(name,  result, id));

            progressBar.setProgress(progressBar.getProgress()+1);

            System.out.println(progressBar.getProgress());



            // Notification de l'adaptateur pour indiquer que les données ont été modifiées
            pokedexAdapter.notifyDataSetChanged();

            // Si toutes les image ont été chargées, on masque la barre de progression
            if(PokedexActivity.data.size() >= 151){
                PokedexActivity.listePokemons.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }
}
