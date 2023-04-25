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
import java.util.List;

public class PokemonRequest extends AsyncTask<Void, Void, String> {


    private String url;
    private TextView typesText;
    private TextView weightText;
    private TextView heightText;
    private ProgressBar hpProgressBar;
    private ProgressBar attackProgressBar;
    private ProgressBar defenseProgressBar;
    private ProgressBar specialAttackProgressBar;
    private ProgressBar specialDefenseProgressBar;
    private ProgressBar speedProgressBar;
    private TextView evolutionText;
    private List<ImageView> evolutions;
    private ImageView firstArrow;
    private ImageView secondArrow;

    public PokemonRequest(String url, TextView typesText, TextView weightText,
                          TextView heightText, ProgressBar hpProgressBar, ProgressBar attackProgressBar,
                          ProgressBar defenseProgressBar, ProgressBar specialAttackProgressBar, ProgressBar specialDefenseProgressBar,
                          ProgressBar speedProgressBar, TextView evolutionText, List<ImageView> evolutions,
                          ImageView firstArrow, ImageView secondArrow) {
        this.url = url;
        this.typesText = typesText;
        this.weightText = weightText;
        this.heightText = heightText;
        this.hpProgressBar = hpProgressBar;
        this.attackProgressBar = attackProgressBar;
        this.defenseProgressBar = defenseProgressBar;
        this.specialAttackProgressBar = specialAttackProgressBar;
        this.specialDefenseProgressBar = specialDefenseProgressBar;
        this.speedProgressBar = speedProgressBar;
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
            // Créer un objet JSON à partir de la chaîne JSON
            JSONObject jsonObject = new JSONObject(jsonString);

            int id = jsonObject.getInt("id");
            double weight = jsonObject.getDouble("weight");
            double height = jsonObject.getDouble("height");


            // Extraire l'array des types
            JSONArray typesArray = jsonObject.getJSONArray("types");

            // Extraire le tableau "stats" de l'objet JSONObject
            JSONArray statsArray = jsonObject.getJSONArray("stats");

            StringBuilder types = new StringBuilder();


            // Parcourir chaque élément de l'array des types et extraire le nom du type
            for (int i = 0; i < typesArray.length(); i++) {
                if (i >= 1) types.append(", ");
                JSONObject typeObject = typesArray.getJSONObject(i).getJSONObject("type");
                String type = typeObject.getString("name");
                // Récupération du nom du pokemon dans l'objet pokemon
                types.append(type);
            }


            // Boucle à travers le tableau "stats" pour extraire le nom de chaque statistique
            for (int i = 0; i < statsArray.length(); i++) {
                // Extraire l'objet JSONObject pour la statistique courante
                JSONObject statObject = statsArray.getJSONObject(i);


                // Extraire la valeur de "base_stat" pour la statistique courante
                int baseStat = statObject.getInt("base_stat");

                switch (i){
                    case 0:
                        hpProgressBar.setProgress(baseStat);
                        break;
                    case 1:
                        attackProgressBar.setProgress(baseStat);
                        break;
                    case 2:
                        defenseProgressBar.setProgress(baseStat);
                        break;
                    case 3:
                        specialAttackProgressBar.setProgress(baseStat);
                        break;
                    case 4:
                        specialDefenseProgressBar.setProgress(baseStat);
                        break;
                    case 5:
                        speedProgressBar.setProgress(baseStat);
                        break;
                }


                typesText.setText("Types : " + types);
                weightText.setText("Weight : " + weight);
                heightText.setText("Height : " + height);

            }

            new RequestPokemonID("https://pokeapi.co/api/v2/pokemon-species/"+id, evolutionText, evolutions, firstArrow, secondArrow).execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
