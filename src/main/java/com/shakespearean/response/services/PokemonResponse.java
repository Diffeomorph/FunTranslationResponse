package com.shakespearean.response.services;

public class PokemonResponse {
    private String name;
    private String description;
    private String habitat;
    private String isLegendary;

    public PokemonResponse(String name, String description, String habitat, String isLegendary){
        this.name = name;
        this.description = description;
        this.habitat = habitat;
        this.isLegendary = isLegendary;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public String getHabitat(){
        return this.habitat;
    }

    public String getIsLegendary(){
        return this.isLegendary;
    }

}
