package fr.appok.pokemon;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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


    // Liste des données des Pokémon
    public static List<PokemonModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pokemon_activity);

        Bundle extras = getIntent().getExtras();

        //Récupère le nom du pokémon
        String name = extras.get("name").toString();


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        RecyclerView recyclerView = findViewById(R.id.pokemon);
        recyclerView.setLayoutManager(layoutManager);
        PokemonAdapter adapter = new PokemonAdapter(data);
        recyclerView.setAdapter(adapter);




        // Appel de l'API en arrière-plan
        new PokemonRequest(adapter,"https://pokeapi.co/api/v2/pokemon/"+name, name).execute();

        //new RequestURLPokemons("https://pokeapi.co/api/v2/pokemon/"+name, imageView).execute();

    }


}
