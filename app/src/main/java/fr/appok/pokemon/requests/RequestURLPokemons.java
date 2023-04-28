package fr.appok.pokemon.requests;

import android.os.AsyncTask;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import fr.appok.Request;

public class RequestURLPokemons extends AsyncTask<Void, Void, String> {


    private String url;
    private ImageView imageView;

    public RequestURLPokemons(String url, ImageView imageView) {

        this.url = url;
        this.imageView = imageView;
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

            JSONObject data2 = data.getJSONObject("sprites");
            JSONObject data3 = data2.getJSONObject("other").getJSONObject("home");
            //JSONObject data3 = data2.getJSONObject("other").getJSONObject("official-artwork");
            String url = data3.getString("front_default");

            //Récupére les images des pokémons
            RequestImagePokemon requestImagePokemons = new RequestImagePokemon(url, imageView);
            requestImagePokemons.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
