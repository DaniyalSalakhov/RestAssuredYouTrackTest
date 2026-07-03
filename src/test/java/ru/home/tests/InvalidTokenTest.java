package ru.home.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;

public class InvalidTokenTest extends BaseTest{

    @ParameterizedTest
    @CsvSource({"Invalid_token"})
    public void invalidToken(String token) {
        given()
                .header("Authorization", token)
                .log().all()
                .when().get("/users/me")
                .then().log().all().statusCode(401);
    }
}
