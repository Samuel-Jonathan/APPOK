package fr.appok.pokemon;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import fr.appok.R;
import fr.appok.pokemon.requests.PokemonRequest;
import fr.appok.pokemon.requests.RequestPokemonEvolution;
import fr.appok.pokemon.requests.RequestURLPokemons;

public class PokemonActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pokemon_activity);

        draw();

    }

    private void draw(){
        Bundle extras = getIntent().getExtras();

        //Récupère le nom du pokémon
        String name = extras.get("name").toString();

        ImageView imageView = findViewById(R.id.pokemonImage);

        TextView nameView = findViewById(R.id.nameText);

        nameView.setText(name);

        TextView typesText = findViewById(R.id.typesText);
        TextView weightText = findViewById(R.id.weightText);
        TextView heightText = findViewById(R.id.heightText);

        List<ProgressBar> progressBars = new ArrayList<>();

        ProgressBar hpProgressBar = findViewById(R.id.hpProgressBar);
        ProgressBar attackProgressBar = findViewById(R.id.attackProgressBar);
        ProgressBar defenseProgressBar = findViewById(R.id.defenseProgressBar);
        ProgressBar specialAttackProgressBar = findViewById(R.id.specialAttackProgressBar);
        ProgressBar specialDefenseProgressBar = findViewById(R.id.specialDefenseProgressBar);
        ProgressBar speedProgressBar = findViewById(R.id.speedProgressBar);

        progressBars.add(hpProgressBar);
        progressBars.add(attackProgressBar);
        progressBars.add(defenseProgressBar);
        progressBars.add(specialAttackProgressBar);
        progressBars.add(specialDefenseProgressBar);
        progressBars.add(speedProgressBar);

        List<TextView> valuesText = new ArrayList<>();

        TextView hpValue = findViewById(R.id.hpValue);
        TextView attackValue = findViewById(R.id.attackValue);
        TextView defenseValue = findViewById(R.id.defenseValue);
        TextView specialAttackValue = findViewById(R.id.specialAttackValue);
        TextView specialDefenseValue = findViewById(R.id.specialDefenseValue);
        TextView speedValue = findViewById(R.id.speedValue);

        valuesText.add(hpValue);
        valuesText.add(attackValue);
        valuesText.add(defenseValue);
        valuesText.add(specialAttackValue);
        valuesText.add(specialDefenseValue);
        valuesText.add(speedValue);


        TextView evolutionText = findViewById(R.id.evolutionText);

        ImageView firstEvolution = findViewById(R.id.firstEvolution);
        ImageView secondEvolution = findViewById(R.id.secondEvolution);
        ImageView thirdEvolution = findViewById(R.id.thirdEvolution);

        ImageView firstArrow = findViewById(R.id.firstArrow);
        ImageView secondArrow = findViewById(R.id.secondArrow);

        List<ImageView> evolutions = new ArrayList<>();
        evolutions.add(firstEvolution);
        evolutions.add(secondEvolution);
        evolutions.add(thirdEvolution);


        // Appel de l'API en arrière-plan
        new PokemonRequest("https://pokeapi.co/api/v2/pokemon/"+name,
                typesText, weightText, heightText,
                progressBars, valuesText, evolutionText, evolutions,
                firstArrow, secondArrow).execute();

        new RequestURLPokemons("https://pokeapi.co/api/v2/pokemon/"+name, imageView).execute();

        events(firstEvolution, secondEvolution, thirdEvolution);

    }

    private void events(ImageView firstEvolution, ImageView secondEvolution, ImageView thirdEvolution){

        firstEvolution.setOnClickListener(e->{
            Intent intent = new Intent(PokemonActivity.this, PokemonActivity.class);

            intent.putExtra("name", RequestPokemonEvolution.e1);
            startActivity(intent);
        });

        secondEvolution.setOnClickListener(e->{
            Intent intent = new Intent(PokemonActivity.this, PokemonActivity.class);

            intent.putExtra("name", RequestPokemonEvolution.e2);
            startActivity(intent);
        });

        thirdEvolution.setOnClickListener(e->{
            Intent intent = new Intent(PokemonActivity.this, PokemonActivity.class);

            intent.putExtra("name", RequestPokemonEvolution.e3);
            startActivity(intent);
        });
    }


}
