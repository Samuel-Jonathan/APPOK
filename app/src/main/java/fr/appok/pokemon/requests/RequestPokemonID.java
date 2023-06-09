package fr.appok.pokemon.requests;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RequestPokemonID extends AsyncTask<Void, Void, String> {


    private String url;
    private TextView evolutionText;
    private List<ImageView> evolutions;
    private ImageView firstArrow;
    private ImageView secondArrow;


    public RequestPokemonID(String url, TextView evolutionText, List<ImageView> evolutions, ImageView firstArrow, ImageView secondArrow) {
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
            JSONObject jsonResponse = new JSONObject(jsonString);
            JSONObject evolutionChain = jsonResponse.getJSONObject("evolution_chain");

            //Récupère l'url des évolutions
            String url = evolutionChain.getString("url");


            RequestPokemonEvolution requestPokemonEvolution = new RequestPokemonEvolution(url,evolutionText, evolutions, firstArrow, secondArrow);
            requestPokemonEvolution.execute();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
