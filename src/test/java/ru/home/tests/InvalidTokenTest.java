package ru.home.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class InvalidTokenTest extends BaseTest{

    @ParameterizedTest
    @CsvSource({"Invalid_token"})
    public void invalidToken(String token) {
        usersSteps.getUserByInvalidToken(token);
    }
}
