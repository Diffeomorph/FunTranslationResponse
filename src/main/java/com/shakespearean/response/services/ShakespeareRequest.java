package com.shakespearean.response.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class ShakespeareRequest {

    public ShakespeareRequest(){

    }

    public String getShakespeareanTranslation(String description){
        RestTemplate restTemplate2 = new RestTemplate();
        String shakespeareanUri = "https://api.funtranslations.com/translate/shakespeare.json?text="+ URLEncoder.encode(description, StandardCharsets.UTF_8);
        ResponseEntity<JsonNode> resShakespeare = restTemplate2.exchange(shakespeareanUri, HttpMethod.POST, null, JsonNode.class);
        JsonNode map2 = resShakespeare.getBody();
        String descriptionTranslated = map2.get("contents").get("translated").asText();
        return descriptionTranslated;
    }

}

