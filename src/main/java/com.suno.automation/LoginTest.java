package com.suno.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LoginTest {
    private final String BASE_URL = "https://suno.com/api/v1";
    private String token;

    @Test
    public void testLogin() {
        RestAssured.baseURI = BASE_URL;

        String email = "testuser@example.com";
        String password = "TestPassword123";

        Response response = given()
                .header("Content-Type", "application/json")
                .body("{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }")
                .post("/auth/login");

        Assertions.assertEquals(200, response.getStatusCode(), "Login failed");
        token = response.jsonPath().getString("token");
        System.out.println("Login successful, token: " + token);
    }

    public String getToken() {
        return token;
    }
}
