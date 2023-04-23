package fr.appok.pokemon;

import android.graphics.Bitmap;

public class PokemonModel {

    private String name;
    private String types;
    private String weight;
    private String height;


    public PokemonModel(String name, String types, String weight, String height) {

        this.name = name;
        this.types = types;
        this.weight = weight;
        this.height = height;
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
}
