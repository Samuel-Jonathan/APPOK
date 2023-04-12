package fr.appok.pokedex.requests;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import fr.appok.PokedexAdapter;
import fr.appok.Request;

public class RequestURLPokemons extends AsyncTask<Void, Void, String> {


    private PokedexAdapter pokedexAdapter;
    private String name;
    private String url;
    private ProgressBar progressBar;


    public RequestURLPokemons(PokedexAdapter pokedexAdapter, String name, String url, ProgressBar progressBar){
        this.pokedexAdapter = pokedexAdapter;
        this.name = name;
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

            JSONObject data = new JSONObject(jsonString);
            JSONObject data2 = data.getJSONObject("sprites");
            JSONObject data3 = data2.getJSONObject("other").getJSONObject("official-artwork");
            String url = data3.getString("front_default");

            //Récupére les images des pokémons
            new RequestImagesPokemons(pokedexAdapter,name,url, progressBar).execute();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
