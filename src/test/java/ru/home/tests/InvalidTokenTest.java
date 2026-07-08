package ru.home.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.home.api.UsersApi;
import ru.home.specifications.Specifications;

public class InvalidTokenTest extends BaseTest{

    private UsersApi usersApi = new UsersApi();

    @ParameterizedTest
    @CsvSource({"Invalid_token"})
    public void invalidToken(String token) {
        usersApi.invalidToken(token)
                .then().spec(Specifications.response401());
    }
}
