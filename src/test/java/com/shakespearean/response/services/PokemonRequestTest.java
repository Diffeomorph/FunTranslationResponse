package com.shakespearean.response.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PokemonRequestTest {

    @Autowired
    PokemonRequest pokemonRequestTest;

    @Test
    void testPokemonRequest(){
        String[] actual = pokemonRequestTest.getPokemonbyName("charizard");
        String[] expected = new String[]{"charizard","Spits fire that is hot enough to melt boulders. Known to cause forest fires unintentionally.","","false"};
        assertArrayEquals(actual, expected);
    }

}
