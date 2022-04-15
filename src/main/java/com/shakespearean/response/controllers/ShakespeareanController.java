package com.shakespearean.response.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shakespearean.response.services.PokemonResponse;
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


    //@Autowired
    //private PokemonDescription pokemonDescription;

    // Get all pokemon responses
    @GetMapping("/pokemon")
    void getAllPokemon() {
        return;
    }

    // Get particular pokemon response
    @GetMapping("/pokemon/{name}")
    String getPokemon(@PathVariable String name) throws JsonProcessingException {
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
    String getTranslatedPokemon(@PathVariable String name) throws JsonProcessingException {
        String uri = "https://pokeapi.co/api/v2/pokemon-species/" + name;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response = restTemplate.exchange(uri, HttpMethod.GET, null, JsonNode.class);
        JsonNode map = response.getBody();

        String description =  map.get("flavor_text_entries").get(0).get("flavor_text").asText();
        String shakespeareanUri = "https://api.funtranslations.com/translate/shakespeare.json?text="+ URLEncoder.encode(description, StandardCharsets.UTF_8);
        ResponseEntity<JsonNode> res = restTemplate.exchange(shakespeareanUri, HttpMethod.POST, null, JsonNode.class);
        JsonNode map2 = res.getBody();
        String descriptionTranslated = map2.get("contents").get("translated").asText();

        String habitat = map.get("habitat").asText();
        String is_legendary = map.get("is_legendary").asText();

        PokemonResponse pokemonResponse = new PokemonResponse(name, descriptionTranslated, habitat, is_legendary);

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
