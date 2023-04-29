package fr.appok;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.animation.TranslateAnimation;

import fr.appok.pokedex.PokedexActivity;
import pl.droidsonroids.gif.GifImageView;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);



        //Affiche le splash screen pendant 5 secondes
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), PokedexActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN_TIMEOUT);

        draw();


    }

    private void draw(){

        // Charger l'image de fond
        @SuppressLint("UseCompatLoadingForDrawables") Drawable background = getResources().getDrawable(R.drawable.splashscreen);

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


        GifImageView pikachu = findViewById(R.id.pikachu);

        // Créer une animation de translation qui déplace l'image de 300 pixels vers la droite
        TranslateAnimation animation = new TranslateAnimation(0, 300, 0, 0);
        animation.setDuration(SPLASH_SCREEN_TIMEOUT); // Durée de l'animation en millisecondes (3 secondes)

        // Démarrer l'animation
        pikachu.startAnimation(animation);
    }
}