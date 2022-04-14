package com.shakespearean.response.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shakespearean.response.services.PokemonDescription;
import com.shakespearean.response.services.PokemonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * The ShakespeareanController, given a pokemon name, returns a "shakespearean" response.
 */
@RestController
public class ShakespeareanController {


    //@Autowired
    //private PokemonDescription pokemonDescription;

    // Get all pokemon responses
    @GetMapping("/pokemon")
    void getAllPokemon() {
        return;
    }

    // Get particular pokemon response
    @GetMapping("/pokemon/{name}")
    String printPokemon(@PathVariable String name) throws JsonProcessingException {
        String uri = "https://pokeapi.co/api/v2/pokemon-species/" + name;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response = restTemplate.exchange(uri, HttpMethod.GET, null, JsonNode.class);
        JsonNode map = response.getBody();

        String description =  map.get("flavor_text_entries").get(0).get("flavor_text").asText();
        String habitat = map.get("habitat").asText();
        String is_legendary = map.get("is_legendary").asText();

        PokemonResponse pokemonResponse = new PokemonResponse(name, description, habitat, is_legendary);

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
    String printTranslatedPokemon(@PathVariable String name) throws JsonProcessingException {
        String uri = "https://pokeapi.co/api/v2/pokemon-species/" + name;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response = restTemplate.exchange(uri, HttpMethod.GET, null, JsonNode.class);
        JsonNode map = response.getBody();

        String description =  map.get("flavor_text_entries").get(0).get("flavor_text").asText();
        String shakespeareanUri = "https://api.funtranslations.com/translate/shakespeare.json?text="+description;
        ResponseEntity<JsonNode> responseEntityStr = restTemplate.postForEntity(shakespeareanUri, null, JsonNode.class);

        System.out.println(responseEntityStr);

        String habitat = map.get("habitat").asText();
        String is_legendary = map.get("is_legendary").asText();

        PokemonResponse pokemonResponse = new PokemonResponse(name, description, habitat, is_legendary);

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
