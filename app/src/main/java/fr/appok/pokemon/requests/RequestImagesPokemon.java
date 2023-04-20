package fr.appok.pokemon.requests;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import fr.appok.Request;

public class RequestImagesPokemon extends AsyncTask<Void, Void, Bitmap> {

    // Déclaration des variables
    private String url;
    private ImageView imageView;

    // Constructeur
    public RequestImagesPokemon(String url, ImageView imageView){
        this.url = url;
        this.imageView = imageView;
    }

    // Méthode qui va récupérer l'image de manière asynchrone
    @Override
    protected Bitmap doInBackground(Void... voids) {
        return Request.requestBitmap(url);
    }

    // Méthode appelée quand la tâche asynchrone est terminée
    @Override
    protected void onPostExecute(Bitmap result) {
        if (result != null) {

            imageView.setImageBitmap(result);

        }
    }
}
