package ru.home.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.home.api.UsersApi;
import ru.home.specifications.Specifications;

import static io.restassured.RestAssured.given;

public class InvalidTokenTest extends BaseTest{

    private UsersApi usersApi;

    @BeforeEach
    void setup(){
        usersApi = new UsersApi();
    }

    @ParameterizedTest
    @CsvSource({"Invalid_token"})
    public void invalidToken(String token) {
        usersApi.invalidToken(token)
                .then().spec(Specifications.response401());
    }
}
