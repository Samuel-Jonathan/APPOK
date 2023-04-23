package fr.appok.pokemon;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

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

        ImageView imageView = findViewById(R.id.imageView);

        TextView nameView = findViewById(R.id.nameView);

        nameView.setText(name);

        TextView typesView = findViewById(R.id.typesView);
        TextView weightView = findViewById(R.id.weightView);
        TextView heightView = findViewById(R.id.heightView);
        ProgressBar hpView = findViewById(R.id.hpProgressBar);
        ProgressBar attackView = findViewById(R.id.attackProgressBar);
        ProgressBar defenseView = findViewById(R.id.defenseProgressBar);





        /*LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        RecyclerView recyclerView = findViewById(R.id.pokemon);
        recyclerView.setLayoutManager(layoutManager);

        PokemonAdapter adapter = new PokemonAdapter(data);
        recyclerView.setAdapter(adapter);*/


        // Appel de l'API en arrière-plan
        new PokemonRequest("https://pokeapi.co/api/v2/pokemon/"+name, typesView, weightView, heightView, hpView, attackView, defenseView).execute();

        new RequestURLPokemons("https://pokeapi.co/api/v2/pokemon/"+name, imageView).execute();



    }


}
