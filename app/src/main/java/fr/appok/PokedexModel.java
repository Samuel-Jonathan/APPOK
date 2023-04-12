package fr.appok;

import android.graphics.Bitmap;

public class PokedexModel {

    private String name;
    private Bitmap bitmap;
    private int id;



    public PokedexModel(String name, Bitmap bitmap, int id) {
        this.name = name;
        this.bitmap = bitmap;
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getId() {
        return id;
    }
}
