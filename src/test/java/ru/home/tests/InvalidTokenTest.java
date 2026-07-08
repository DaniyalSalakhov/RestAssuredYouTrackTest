package ru.home.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.home.specifications.Specifications;

public class InvalidTokenTest extends BaseTest{

    @ParameterizedTest
    @CsvSource({"Invalid_token"})
    public void invalidToken(String token) {
        usersApi.invalidToken(token)
                .then().spec(Specifications.response401());
    }
}
