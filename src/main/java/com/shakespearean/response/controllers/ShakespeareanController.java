package com.shakespearean.response.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shakespearean.response.services.PokemonRequest;
import com.shakespearean.response.services.PokemonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * The ShakespeareanController, given a pokemon name, returns a "shakespearean" response.
 */
@RestController
public class ShakespeareanController {


    @Autowired
    private PokemonRequest pokemonRequest;

    // Get all pokemon responses
    @GetMapping("/pokemon")
    void getAllPokemon() {
        return;
    }

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

    // Get particular pokemon response
    @GetMapping("/pokemon/translated/{name}")
    String getTranslatedPokemon(@PathVariable String name) throws JsonProcessingException {
        String[] res = pokemonRequest.getPokemonbyName(name);

        RestTemplate restTemplate2 = new RestTemplate();
        String shakespeareanUri = "https://api.funtranslations.com/translate/shakespeare.json?text="+ URLEncoder.encode(res[1], StandardCharsets.UTF_8);
        ResponseEntity<JsonNode> resShakespeare = restTemplate2.exchange(shakespeareanUri, HttpMethod.POST, null, JsonNode.class);
        JsonNode map2 = resShakespeare.getBody();
        String descriptionTranslated = map2.get("contents").get("translated").asText();

        PokemonResponse pokemonResponse = new PokemonResponse(res[0], descriptionTranslated, res[2], res[3]);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(pokemonResponse);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /*
    @PostMapping("/pokemon")
    void submitPokemon(@RequestBody pokemon){
        return;
    }
    */

    @DeleteMapping("/pokemon/{id}")
    void deletePokemon(){
        // code
    }

    @PutMapping("/pokemon/{id}")
    void updatePokemon(){
        //code
    }

}
