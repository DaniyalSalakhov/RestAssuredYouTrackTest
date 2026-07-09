package ru.home.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.home.DTO.UserDTO;
import ru.home.specifications.Specifications;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCurrentUserTest extends BaseTest{

    @ParameterizedTest
    @CsvSource({"admin, admin"})
    void returnCurrentUser(String login, String username){
        UserDTO user = usersSteps.getCurrentUser();

        assertEquals(login, user.getLogin());
        assertEquals(username, user.getUsername());
    }
}
