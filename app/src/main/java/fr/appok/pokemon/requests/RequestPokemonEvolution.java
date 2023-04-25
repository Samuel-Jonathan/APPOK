package fr.appok.pokemon.requests;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestPokemonEvolution extends AsyncTask<Void, Void, String> {


    private String url;
    private TextView evolutionText;

    public RequestPokemonEvolution(String url, TextView evolutionText) {
        this.url = url;
        this.evolutionText = evolutionText;
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
            while (evolvesTo.length() > 0) {
                JSONObject nextEvolvesTo = evolvesTo.getJSONObject(0);
                evolutionText.setText(name.append(" -> ").append(nextEvolvesTo.getJSONObject("species").getString("name")));
                evolvesTo = nextEvolvesTo.getJSONArray("evolves_to");
            }
            System.out.println(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
