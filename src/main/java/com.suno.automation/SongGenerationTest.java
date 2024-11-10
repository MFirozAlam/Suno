package com.suno.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SongGenerationTest {
    private final String BASE_URL = "https://suno.com/api/v1";
    private String token;

    @BeforeEach
    public void setup() {
        // Reuse login method to get token
        LoginTest loginTest = new LoginTest();
        loginTest.testLogin();
        token = loginTest.getToken();
    }

    @Test
    public void testGenerateSong() {
        RestAssured.baseURI = BASE_URL;
        String prompt = "Create a happy song about friendship";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{ \"prompt\": \"" + prompt + "\" }")
                .post("/songs/generate");

        Assertions.assertEquals(200, response.getStatusCode(), "Song generation failed");
        String songUrl = response.jsonPath().getString("song_url");
        System.out.println("Song generated successfully: " + songUrl);
    }
}

