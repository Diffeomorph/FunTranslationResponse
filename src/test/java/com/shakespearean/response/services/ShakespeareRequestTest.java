package com.shakespearean.response.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ShakespeareRequestTest {

    @Autowired
    ShakespeareRequest shakespeareRequestTest;

    @BeforeEach
    public void setUp() throws Exception {
        shakespeareRequestTest = new ShakespeareRequest();
    }

    @Test
    void testShakespeareRequest(){
        String actual = shakespeareRequestTest.getShakespeareanTranslation("Spits fire that is hot enough to melt boulders. Known to cause forest fires unintentionally.");
        String expected = ;
        assertEquals(actual, expected);
    }

}