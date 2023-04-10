package fr.appok;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokedexRequest extends AsyncTask<Void, Void, String> {

    private String url;

    public PokedexRequest(String url){
        this.url = url;
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

                // Récupération du nom du pokemon dans l'objet pokemon
                String name = pokemonObject.getString("name");

                // Affichage du nom du pokemon dans la console
                System.out.println(name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
