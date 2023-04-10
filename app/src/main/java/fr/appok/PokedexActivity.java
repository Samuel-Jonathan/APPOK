package fr.appok;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class PokedexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokedex_activity);


        ListView listePokemons = findViewById(R.id.pokedex);

        
        // Appel de l'API en arrière-plan
        new PokedexRequest(this, listePokemons, "https://pokeapi.co/api/v2/pokemon?limit=151").execute();

        listePokemons.setOnItemClickListener((adapterView, view, position, id) -> {
            String name = (String) adapterView.getItemAtPosition(position);

            Intent intent = new Intent(PokedexActivity.this, PokemonActivity.class).putExtra("name", name);
            startActivity(intent);

        });

    }

}
