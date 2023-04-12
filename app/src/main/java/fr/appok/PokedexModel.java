package fr.appok;

import android.graphics.Bitmap;

public class PokedexModel {

    private String name;
    private Bitmap bitmap;



    public PokedexModel(String name, Bitmap bitmap) {
        this.name = name;
        this.bitmap = bitmap;
    }



    public String getName() {
        return name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
