package com.shakespearean.response.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ShakespeareRequestTest {

    @Autowired
    ShakespeareRequest shakespeareRequestTest;

    @Test
    void testShakespeareRequest(){
        String actual = shakespeareRequestTest.getShakespeareanTranslation("Spits fire that is hot enough to melt boulders. Known to cause forest fires unintentionally.");
        String expected = "Spits fire yond is hot enow to melt boulders. Known to cause forest fires unintentionally.";
        assertEquals(actual, expected);
    }

}