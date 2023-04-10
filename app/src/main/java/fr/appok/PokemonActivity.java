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
        System.out.println(nom);
        TextView name = findViewById(R.id.name);
        name.setText(nom);

    }


}
