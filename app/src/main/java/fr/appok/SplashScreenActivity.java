package fr.appok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifImageView;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIMEOUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);


        //Affiche le splash screen pendant 5 secondes
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), PokedexActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN_TIMEOUT);

        drawPikachu();


    }

    private void drawPikachu(){
        GifImageView pikachu = findViewById(R.id.pikachu);

        // Créer une animation de translation qui déplace l'image de 100 pixels vers la droite
        TranslateAnimation animation = new TranslateAnimation(0, 600, 0, 0);
        animation.setDuration(5000); // Durée de l'animation en millisecondes (5 secondes)

        // Démarrer l'animation
        pikachu.startAnimation(animation);
    }
}