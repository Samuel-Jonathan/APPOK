package fr.appok;

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


        ListView pokedex = findViewById(R.id.pokedex);

        
        // Appel de l'API en arrière-plan
        new PokedexRequest(this, pokedex, "https://pokeapi.co/api/v2/pokemon?limit=151").execute();


    }

}
