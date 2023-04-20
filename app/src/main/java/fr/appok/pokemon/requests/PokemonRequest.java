package fr.appok.pokemon.requests;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokemonRequest extends AsyncTask<Void, Void, String> {


    private TextView typesView;
    private TextView weightView;
    private TextView heightView;
    private String url;


    public PokemonRequest(TextView typesView, TextView weightView, TextView heightView, String url){
        this.typesView = typesView;
        this.weightView = weightView;
        this.heightView = heightView;
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
            String json = jsonString;
            // Créer un objet JSON à partir de la chaîne JSON
            JSONObject jsonObject = new JSONObject(json);

            double weight = jsonObject.getDouble("weight");
            double height = jsonObject.getDouble("height");


            // Extraire l'array des types
            JSONArray typesArray = jsonObject.getJSONArray("types");

            StringBuilder typeBuilder = new StringBuilder();


            // Parcourir chaque élément de l'array des types et extraire le nom du type
            for (int i = 0; i < typesArray.length(); i++) {

                JSONObject typeObject = typesArray.getJSONObject(i).getJSONObject("type");
                String typeName = typeObject.getString("name");
                // Récupération du nom du pokemon dans l'objet pokemon
                typeBuilder.append(typeName).append("\n");

                typesView.setText(typeBuilder.toString());

            }

            weightView.setText(String.valueOf(weight/10));

            heightView.setText(String.valueOf(height/10));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
