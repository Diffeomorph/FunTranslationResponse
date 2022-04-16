package com.shakespearean.response.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.jupiter.api.Assertions.*;

class PokemonRequestTest {

    @Autowired
    PokemonRequest pokemonRequestTest;

    @BeforeEach
    public void setUp() throws Exception {
        pokemonRequestTest = new PokemonRequest();
    }

    @Test
    void testPokemonRequest(){
        String[] actual = pokemonRequestTest.getPokemonbyName("charizard");
        String[] expected = new String[]{"charizard","Spits fire that is hot enough to melt boulders. Known to cause forest fires unintentionally.","","false"};
        assertArrayEquals(actual, expected);
    }

}
