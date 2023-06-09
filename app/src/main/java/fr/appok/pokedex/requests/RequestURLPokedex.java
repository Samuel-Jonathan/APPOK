package fr.appok.pokedex.requests;

import android.media.Image;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import fr.appok.pokedex.PokedexAdapter;
import fr.appok.Request;

public class RequestURLPokedex extends AsyncTask<Void, Void, String> {


    private PokedexAdapter pokedexAdapter;
    private String name;
    private String url;
    private ProgressBar pokedexProgressBar;



    public RequestURLPokedex(PokedexAdapter pokedexAdapter, String name, String url, ProgressBar pokedexProgressBar){
        this.pokedexAdapter = pokedexAdapter;
        this.name = name;
        this.url = url;
        this.pokedexProgressBar = pokedexProgressBar;
    }


    @Override
    protected String doInBackground(Void... voids) {
        return Request.requestString(url);
    }

    @Override
    protected void onPostExecute(String jsonString) {
        super.onPostExecute(jsonString);
        try {

            JSONObject data = new JSONObject(jsonString);

            int id = data.getInt("id");
            JSONObject data2 = data.getJSONObject("sprites");
            JSONObject data3 = data2.getJSONObject("other").getJSONObject("home");
            String url = data3.getString("front_default");

            //Récupére les images des pokémons
            RequestImagesPokedex requestImagesPokemons = new RequestImagesPokedex(pokedexAdapter,name,id,url,pokedexProgressBar);
            requestImagesPokemons.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
