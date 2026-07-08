package ru.home.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.home.DTO.UserDTO;
import ru.home.api.UsersApi;
import ru.home.specifications.Specifications;
import static org.junit.Assert.assertEquals;

public class GetCurrentUserTest extends BaseTest{
    private UsersApi usersApi = new UsersApi();

    @ParameterizedTest
    @CsvSource({"admin, admin"})
    void returnCurrentUser(String login, String username){
        UserDTO user = usersApi.getCurrentUser()
                .then().spec(Specifications.response200()).extract().as(UserDTO.class);

        assertEquals(login, user.getLogin());
        assertEquals(username, user.getUsername());
    }
}
