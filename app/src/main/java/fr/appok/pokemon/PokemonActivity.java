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

        ImageView imageView = findViewById(R.id.pokemonImage);

        TextView nameView = findViewById(R.id.nameText);

        nameView.setText(name);

        TextView typesText = findViewById(R.id.typesText);
        TextView weightText = findViewById(R.id.weightText);
        TextView heightText = findViewById(R.id.heightText);
        ProgressBar hpProgressBar = findViewById(R.id.hpProgressBar);
        ProgressBar attackProgressBar = findViewById(R.id.attackProgressBar);
        ProgressBar defenseProgressBar = findViewById(R.id.defenseProgressBar);
        ProgressBar specialAttackProgressBar = findViewById(R.id.specialAttackProgressBar);
        ProgressBar specialDefenseProgressBar = findViewById(R.id.specialDefenseProgressBar);
        ProgressBar speedProgressBar = findViewById(R.id.speedProgressBar);


        // Appel de l'API en arrière-plan
        new PokemonRequest("https://pokeapi.co/api/v2/pokemon/"+name,
                typesText, weightText, heightText,
                hpProgressBar, attackProgressBar, defenseProgressBar,specialAttackProgressBar, specialDefenseProgressBar,speedProgressBar).execute();

        new RequestURLPokemons("https://pokeapi.co/api/v2/pokemon/"+name, imageView).execute();



    }


}
