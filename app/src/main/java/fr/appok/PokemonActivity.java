package fr.appok;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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


        // Appel de l'API en arrière-plan
        new PokemonRequest(this, types, "https://pokeapi.co/api/v2/pokemon/"+nom).execute();



    }


}
