package ru.home.steps;

import io.qameta.allure.Step;
import ru.home.DTO.UserDTO;
import ru.home.api.UsersApi;
import ru.home.specifications.Specifications;

public class UsersSteps {
    private UsersApi usersApi;

    public UsersSteps() {
        this.usersApi = new UsersApi();
    }

    @Step("Получить информацию о текущем пользователе")
    public UserDTO getCurrentUser() {
        return usersApi.getCurrentUser()
                .then().spec(Specifications.response200())
                .extract().as(UserDTO.class);
    }

    @Step("Попытка получить информацию о текущем пользователе с недействительным токеном '{token}'")
    public void getUserByInvalidToken(String token) {
        usersApi.invalidToken(token)
                .then().spec(Specifications.response401());
    }
}
