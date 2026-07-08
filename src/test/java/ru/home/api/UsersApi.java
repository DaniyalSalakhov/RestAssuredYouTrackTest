package ru.home.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UsersApi {
    public Response getCurrentUser() {
        return RestAssured.given()
                .queryParam("fields", "login,name")
                .when().get("/users/me");
    }

    public Response invalidToken(String token) {
        return RestAssured.given()
                .header("Authorization", token)
                .when().get("/users/me");
    }
}
