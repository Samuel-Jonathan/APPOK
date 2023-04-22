package fr.appok.pokedex.requests;

import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.appok.pokedex.PokedexAdapter;
import fr.appok.Request;

public class RequestAllPokedex extends AsyncTask<Void, Void, String> {


    private PokedexAdapter pokedexAdapter;
    private String url;
    private ProgressBar progressBar;

    public RequestAllPokedex(PokedexAdapter pokedexAdapter, String url, ProgressBar progressBar){
        this.pokedexAdapter = pokedexAdapter;
        this.url = url;
        this.progressBar = progressBar;
    }


    @Override
    protected String doInBackground(Void... voids) {
        return Request.requestString(url);
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
                new RequestURLPokedex(pokedexAdapter, name, "https://pokeapi.co/api/v2/pokemon/"+name, progressBar).execute();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
