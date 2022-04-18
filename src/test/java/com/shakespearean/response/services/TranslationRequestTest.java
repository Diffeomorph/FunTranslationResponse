package com.shakespearean.response.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TranslationRequestTest {

    @Autowired
    TranslationRequest translationRequestTest;

    @Test
    void testShakespeareRequest(){
        String actual = translationRequestTest.getTranslation("shakespeare", "Spits fire that is hot enough to melt boulders. Known to cause forest fires unintentionally.");
        String expected = "Spits fire yond is hot enow to melt boulders. Known to cause forest fires unintentionally.";
        assertEquals(actual, expected);
    }

    @Test
    void testYodaRequest(){
        String actual = translationRequestTest.getTranslation("yoda", "Spits fire that is hot enough to melt boulders. Known to cause forest fires unintentionally.");
        String expected = "Fire that is hot enough to melt boulders,  spits.Unintentionally,  known to cause forest fires.";
        assertEquals(expected, actual);
    }

}