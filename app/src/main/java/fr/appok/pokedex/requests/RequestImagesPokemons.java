package fr.appok.pokedex.requests;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;

import fr.appok.PokedexActivity;
import fr.appok.PokedexAdapter;
import fr.appok.PokedexModel;
import fr.appok.Request;

public class RequestImagesPokemons extends AsyncTask<Void, Void, Bitmap> {

    // Déclaration des variables
    private PokedexAdapter pokedexAdapter;
    private String name;
    private String url;
    private ProgressBar progressBar;

    // Constructeur
    public RequestImagesPokemons(PokedexAdapter pokedexAdapter, String name, String url, ProgressBar progressBar){
        this.pokedexAdapter = pokedexAdapter;
        this.name = name;
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
            PokedexActivity.data.add(new PokedexModel(name,  result));
            // Notification de l'adaptateur pour indiquer que les données ont été modifiées
            pokedexAdapter.notifyDataSetChanged();

            // Si au moins une image a été récupérée, on masque la barre de progression
            if(PokedexActivity.data.size() >= 1){
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }
}
