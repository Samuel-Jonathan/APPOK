package fr.appok.pokemon.requests;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RequestPokemonEvolution extends AsyncTask<Void, Void, String> {


    private String url;
    private TextView evolutionText;
    private List<ImageView> evolutions;
    private ImageView firstArrow;
    private ImageView secondArrow;
    public static String e1;
    public static String e2;
    public static String e3;

    public RequestPokemonEvolution(String url, TextView evolutionText, List<ImageView> evolutions, ImageView firstArrow, ImageView secondArrow) {
        this.url = url;
        this.evolutionText = evolutionText;
        this.evolutions = evolutions;
        this.firstArrow = firstArrow;
        this.secondArrow = secondArrow;

    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            // Création de l'objet URL pour l'API
            URL urlObj = new URL(url);

            // Ouverture de la connexion à l'API
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

            // Définition de la méthode de requête GET
            con.setRequestMethod("GET");

            // Création du BufferedReader pour lire les données de l'API
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            // Variables pour lire les données de l'API
            String inputLine;
            StringBuffer response = new StringBuffer();

            // Lecture des données de l'API ligne par ligne
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Fermeture du BufferedReader
            in.close();

            // Retour des données de l'API sous forme de String
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Retour null en cas d'erreur
        return null;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onPostExecute(String jsonString) {
        super.onPostExecute(jsonString);

        try {
            JSONObject json = new JSONObject(jsonString);
            JSONObject chain = json.getJSONObject("chain");
            JSONArray evolvesTo = chain.getJSONArray("evolves_to");
            StringBuilder name = new StringBuilder(chain.getJSONObject("species").getString("name"));

            int index = 0;

            e1 = name.toString();

            if(evolvesTo.length() == 0){
                firstArrow.setVisibility(View.GONE);
                secondArrow.setVisibility(View.GONE);
                evolutions.get(0).setVisibility(View.GONE);
                evolutions.get(1).setVisibility(View.GONE);
                evolutions.get(2).setVisibility(View.GONE);
            }
            while (evolvesTo.length() > 0) {

                if(index == 0){
                    RequestURLPokemons requestURLPokemons = new RequestURLPokemons("https://pokeapi.co/api/v2/pokemon/"+name.toString(), evolutions.get(0));
                    requestURLPokemons.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }

                index++;


                JSONObject nextEvolvesTo = evolvesTo.getJSONObject(0);
                evolutionText.setText(name.append(" -> ").append(nextEvolvesTo.getJSONObject("species").getString("name")));
                evolvesTo = nextEvolvesTo.getJSONArray("evolves_to");

                RequestURLPokemons requestURLPokemons2 = new RequestURLPokemons("https://pokeapi.co/api/v2/pokemon/"+nextEvolvesTo.getJSONObject("species").getString("name"), evolutions.get(index));
                requestURLPokemons2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                switch (index) {
                    case 1:
                        firstArrow.setVisibility(View.VISIBLE);
                        e2 = nextEvolvesTo.getJSONObject("species").getString("name");
                        break;
                    case 2:
                        secondArrow.setVisibility(View.VISIBLE);
                        e3 = nextEvolvesTo.getJSONObject("species").getString("name");
                        break;
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
