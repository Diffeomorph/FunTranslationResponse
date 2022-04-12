package com.shakespearean.response.controllers;

import com.shakespearean.response.services.PokemonDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The ShakespeareanController, given a pokemon name, returns a "shakespearean" response.
 */
@RestController
public class ShakespeareanController {


    @Autowired
    private PokemonDescription pokemonDescription;

    // Get all pokemon responses
    @GetMapping("/pokemon")
    void getAllPokemon() {
        return;
    }

    // Get particular pokemon response
    @GetMapping("/pokemon/{id}")
    void printPokemon(@PathVariable int id) {

        return;
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
