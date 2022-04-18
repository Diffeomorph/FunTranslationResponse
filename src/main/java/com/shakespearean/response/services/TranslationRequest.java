package com.shakespearean.response.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * ShakespeareRequest, given some text, returns a "shakespearean" translation.
 */
@Service
public class TranslationRequest {

    public TranslationRequest(){

    }

    public String getTranslation(String type, String description){
        RestTemplate restTemplate2 = new RestTemplate();
        String translationUri;
        if (type.toLowerCase() == "shakespeare"){
            translationUri = "https://api.funtranslations.com/translate/shakespeare.json?text="+ URLEncoder.encode(description, StandardCharsets.UTF_8);
        } else if (type.toLowerCase() == "yoda"){
            translationUri = "https://api.funtranslations.com/translate/yoda.json?text="+ URLEncoder.encode(description, StandardCharsets.UTF_8);
        } else {
            return description;
        }
        ResponseEntity<JsonNode> resShakespeare = restTemplate2.exchange(translationUri, HttpMethod.POST, null, JsonNode.class);
        JsonNode map2 = resShakespeare.getBody();
        String descriptionTranslated = map2.get("contents").get("translated").asText();
        return descriptionTranslated;
    }

}

