package com.shakespearean.response.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shakespearean.response.services.PokemonRequest;
import com.shakespearean.response.services.PokemonResponse;
import com.shakespearean.response.services.ShakespeareRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * The ShakespeareanController, given a pokemon name, returns a "shakespearean" response.
 */
@RestController
public class ShakespeareanController {

    @Autowired
    private PokemonRequest pokemonRequest;
    private ShakespeareRequest shakespeareRequest;

    // Get particular pokemon response
    @GetMapping("/pokemon/{name}")
    String getPokemon(@PathVariable String name) throws JsonProcessingException {
        String[] res = pokemonRequest.getPokemonbyName(name);

        PokemonResponse pokemonResponse = new PokemonResponse(res[0], res[1], res[2], res[3]);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(pokemonResponse);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    // Get particular pokemon with translated response
    @GetMapping("/pokemon/translated/{name}")
    String getTranslatedPokemon(@PathVariable String name) throws JsonProcessingException {
        String[] res = pokemonRequest.getPokemonbyName(name);
        String translation = shakespeareRequest.getShakespeareanTranslation(res[1]);

        PokemonResponse pokemonTranslatedResponse = new PokemonResponse(res[0], translation, res[2], res[3]);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(pokemonTranslatedResponse);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }


}
