package fr.appok.pokemon;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.appok.R;
import fr.appok.pokedex.PokedexActivity;
import fr.appok.pokedex.PokedexAdapter;
import fr.appok.pokedex.PokedexModel;
import fr.appok.pokemon.requests.PokemonRequest;
import fr.appok.pokemon.requests.RequestURLPokemons;

public class PokemonActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_activity);

        Bundle extras = getIntent().getExtras();

        //Récupère le nom du pokémon
        String name = extras.get("name").toString();

        ImageView imageView = findViewById(R.id.image);
        TextView nameView = findViewById(R.id.name);
        TextView typesView = findViewById(R.id.types);
        TextView weightView = findViewById(R.id.weight);
        TextView heightView = findViewById(R.id.height);


        // Appel de l'API en arrière-plan
        new PokemonRequest("https://pokeapi.co/api/v2/pokemon/"+name, nameView, typesView, weightView, heightView).execute();

        new RequestURLPokemons("https://pokeapi.co/api/v2/pokemon/"+name, imageView).execute();

    }


}
