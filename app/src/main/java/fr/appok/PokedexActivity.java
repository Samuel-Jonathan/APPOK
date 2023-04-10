package fr.appok;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PokedexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokedex_activity);

        // Appel de l'API en arrière-plan
        new PokedexRequest("https://pokeapi.co/api/v2/pokemon?limit=151").execute();


    }

}
