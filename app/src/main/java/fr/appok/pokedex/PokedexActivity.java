package fr.appok.pokedex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.appok.pokemon.PokemonActivity;
import fr.appok.R;
import fr.appok.pokedex.requests.RequestAllPokedex;

public class PokedexActivity extends AppCompatActivity {


    public static RecyclerView listePokemons;

    // Liste des données des Pokémon
    public static List<PokedexModel> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokedex_activity);

        // Charger l'image de fond
        Drawable background = getResources().getDrawable(R.drawable.test);

        // Obtenir la taille de l'écran
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        // Redimensionner l'image de fond
        Bitmap bitmap = ((BitmapDrawable)background).getBitmap();
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);

        // Définir le background pour l'activité
        getWindow().setBackgroundDrawable(new BitmapDrawable(getResources(), resizedBitmap));

        // Récupération de la vue RecyclerView
        listePokemons = findViewById(R.id.listePokemons);

        // Récupération de la barre de progression
        ProgressBar progressBar = findViewById(R.id.progressBar);

        // Initialisation de l'adaptateur avec les données des Pokémon
        PokedexAdapter adapter = new PokedexAdapter(PokedexActivity.data);

        // Récupération de la liste des Pokémon
        getPokemons(listePokemons, progressBar, adapter);

        adapter.setOnItemClickListener((position, name) -> {

                Intent intent = new Intent(PokedexActivity.this, PokemonActivity.class);

                intent.putExtra("name", name);
                startActivity(intent);

        });



    }

    // Méthode qui va récupérer la liste des Pokémon de manière asynchrone
    private void getPokemons(RecyclerView listePokemons, ProgressBar progressBar, PokedexAdapter adapter){

        // Lancement de la tâche asynchrone pour récupérer la liste des Pokémon
        new RequestAllPokedex(adapter, "https://pokeapi.co/api/v2/pokemon?limit=151",progressBar).execute();

        // Configuration de la vue RecyclerView
        listePokemons.setAdapter(adapter);
        int spacing = -1200;
        listePokemons.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = spacing;
            }
        });
        listePokemons.setLayoutManager(new GridLayoutManager(this, 3));
    }


}
