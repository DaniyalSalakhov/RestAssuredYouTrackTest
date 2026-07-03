package ru.home.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CurrentUser extends BaseTest{

    @ParameterizedTest
    @CsvSource({"admin, admin"})
    void returnCurrentUser(String login, String username){
        given().queryParam("fields","login,name").log().all()
                .when().get("/users/me")
                .then().log().all().statusCode(200).body("login", equalTo(login))
                .body("name", equalTo(username));
    }
}
