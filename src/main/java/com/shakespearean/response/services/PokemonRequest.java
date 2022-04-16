package com.shakespearean.response.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonRequest {

    public PokemonRequest(){

    }

    public String[] getPokemonbyName(String name){
        String uri = "https://pokeapi.co/api/v2/pokemon-species/" + name;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> response = restTemplate.exchange(uri, HttpMethod.GET, null, JsonNode.class);
        JsonNode map = response.getBody();

        String description =  map.get("flavor_text_entries").get(0).get("flavor_text").asText();
        description = description.replace("\n", " ");
        description = description.replace("\f", " ");
        String habitat = map.get("habitat").asText();
        String is_legendary = map.get("is_legendary").asText();

        String[] returnStr = {name, description, habitat, is_legendary};
        return returnStr;
    }
}
