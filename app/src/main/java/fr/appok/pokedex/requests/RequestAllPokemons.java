package fr.appok.pokedex.requests;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import fr.appok.PokedexAdapter;
import fr.appok.Request;

public class RequestAllPokemons extends AsyncTask<Void, Void, String> {


    private PokedexAdapter pokedexAdapter;
    private String url;
    private ProgressBar progressBar;

    public RequestAllPokemons(PokedexAdapter pokedexAdapter, String url, ProgressBar progressBar){
        this.pokedexAdapter = pokedexAdapter;
        this.url = url;
        this.progressBar = progressBar;
    }


    @Override
    protected String doInBackground(Void... voids) {
        Request.requestString(url);
        return null;
    }

    @Override
    protected void onPostExecute(String jsonString) {
        super.onPostExecute(jsonString);

        try {
            // Création de l'objet JSONObject pour le fichier JSON reçu
            JSONObject jsonObject = new JSONObject(jsonString);

            // Récupération du tableau "results" dans le fichier JSON
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            // Boucle pour récupérer chaque nom de pokemon dans le tableau
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                // Récupération de chaque objet pokemon dans le tableau
                JSONObject pokemonObject = jsonArray.getJSONObject(i);

                // Récupère le nom du pokémon
                String name = pokemonObject.getString("name");

                // Requête pour récupérer les url des images des pokémons
                new RequestURLPokemons(pokedexAdapter, name, "https://pokeapi.co/api/v2/pokemon/"+name, progressBar).execute();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
