package fr.appok.pokemon;

import android.graphics.Bitmap;
import android.widget.ProgressBar;

public class PokemonModel {

    private String name;
    private String types;
    private String weight;
    private String height;
    private Bitmap image;


    public PokemonModel(String name, String types, String weight, String height, Bitmap image) {

        this.name = name;
        this.types = types;
        this.weight = weight;
        this.height = height;
        this.image = image;
    }


    public PokemonModel(){

    }

    public String getName() {
        return name;
    }

    public String getTypes() {
        return types;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public Bitmap getImage() {
        return image;
    }
}
