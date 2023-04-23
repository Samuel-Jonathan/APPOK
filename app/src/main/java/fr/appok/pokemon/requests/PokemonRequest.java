package fr.appok.pokemon.requests;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import fr.appok.pokemon.PokemonActivity;
import fr.appok.pokemon.PokemonAdapter;
import fr.appok.pokemon.PokemonModel;

public class PokemonRequest extends AsyncTask<Void, Void, String> {


    private String url;
    private TextView typesView;
    private TextView weightView;
    private TextView heightView;
    private ProgressBar hpView;
    private ProgressBar attackView;
    private ProgressBar defenseView;


    public PokemonRequest(String url, TextView typesView, TextView weightView, TextView heightView, ProgressBar hpView, ProgressBar attackView, ProgressBar defenseView) {
        this.url = url;
        this.typesView = typesView;
        this.weightView = weightView;
        this.heightView = heightView;
        this.hpView = hpView;
        this.attackView = attackView;
        this.defenseView = defenseView;
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
            String json = jsonString;
            // Créer un objet JSON à partir de la chaîne JSON
            JSONObject jsonObject = new JSONObject(json);

            double weight = jsonObject.getDouble("weight");
            double height = jsonObject.getDouble("height");


            // Extraire l'array des types
            JSONArray typesArray = jsonObject.getJSONArray("types");

            // Extraire le tableau "stats" de l'objet JSONObject
            JSONArray statsArray = jsonObject.getJSONArray("stats");

            StringBuilder types = new StringBuilder();


            // Parcourir chaque élément de l'array des types et extraire le nom du type
            for (int i = 0; i < typesArray.length(); i++) {
                if(i >= 1) types.append(", ");
                JSONObject typeObject = typesArray.getJSONObject(i).getJSONObject("type");
                String type = typeObject.getString("name");
                // Récupération du nom du pokemon dans l'objet pokemon
                types.append(type);
            }



            // Boucle à travers le tableau "stats" pour extraire le nom de chaque statistique
            for (int i = 0; i < statsArray.length(); i++) {
                // Extraire l'objet JSONObject pour la statistique courante
                JSONObject statObject = statsArray.getJSONObject(i);

                // Extraire l'objet JSONObject "stat" pour la statistique courante
                JSONObject nameObject = statObject.getJSONObject("stat");


                // Extraire la valeur de "base_stat" pour la statistique courante
                int baseStat = statObject.getInt("base_stat");
                if(i == 0){
                    hpView.setProgress(baseStat);
                }else if(i == 1){
                    attackView.setProgress(baseStat);
                }else if(i == 2){
                    defenseView.setProgress(baseStat);
                }



            }




            typesView.setText("Types : " + types);
            weightView.setText("Weight : " + weight);
            heightView.setText("Height : " + height);





        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
