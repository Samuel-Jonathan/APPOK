package fr.appok.pokemon;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.appok.R;
import fr.appok.pokemon.requests.PokemonRequest;
import fr.appok.pokemon.requests.RequestURLPokemons;

public class PokemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_activity);

        Bundle extras = getIntent().getExtras();

        //Récupère le nom du pokémon
        String nom = extras.get("name").toString();

        TextView name = findViewById(R.id.name);
        name.setText(nom);


        TextView types = findViewById(R.id.types);

        TextView weight = findViewById(R.id.weight);

        TextView height = findViewById(R.id.height);

        ImageView imageView = findViewById(R.id.image);


        // Appel de l'API en arrière-plan
        new PokemonRequest(types,weight,height, "https://pokeapi.co/api/v2/pokemon/"+nom).execute();
        new RequestURLPokemons("https://pokeapi.co/api/v2/pokemon/"+nom, imageView).execute();



    }


}
