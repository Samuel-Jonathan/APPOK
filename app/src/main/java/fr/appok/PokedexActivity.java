package fr.appok;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.appok.pokedex.requests.RequestAllPokemons;

public class PokedexActivity extends AppCompatActivity {

    private RecyclerView listePokemons;

    // Liste des données des Pokémon
    public static List<PokedexModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokedex_activity);

        // Récupération de la vue RecyclerView
        listePokemons = findViewById(R.id.listePokemons);

        // Récupération de la barre de progression
        ProgressBar progressBar = findViewById(R.id.progressBar);

        // Initialisation de l'adaptateur avec les données des Pokémon
        PokedexAdapter adapter = new PokedexAdapter(PokedexActivity.data);

        // Récupération de la liste des Pokémon
        getPokemons(listePokemons, progressBar, adapter);

        adapter.setOnItemClickListener(position -> {


        });

    }

    // Méthode qui va récupérer la liste des Pokémon de manière asynchrone
    private void getPokemons(RecyclerView listePokemons, ProgressBar progressBar, PokedexAdapter adapter){

        // Lancement de la tâche asynchrone pour récupérer la liste des Pokémon
        new RequestAllPokemons(adapter, "https://pokeapi.co/api/v2/pokemon?limit=151", progressBar).execute();

        // Configuration de la vue RecyclerView
        listePokemons.setAdapter(adapter);
        int spacing = -1200;
        listePokemons.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = spacing;
            }
        });
        listePokemons.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
