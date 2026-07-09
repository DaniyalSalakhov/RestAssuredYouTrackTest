package ru.home.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import ru.home.api.TasksApi;
import ru.home.api.UsersApi;
import ru.home.specifications.Specifications;
import ru.home.steps.TasksSteps;
import ru.home.steps.UsersSteps;
import ru.home.utils.ConfigReader;

public abstract class BaseTest {

    protected final String baseUrl = ConfigReader.get("base.url");
    protected final String token = ConfigReader.get("token");
    protected TasksSteps tasksSteps;
    protected UsersSteps usersSteps;

    @BeforeEach
    public void setUp() {
        usersSteps = new UsersSteps();
        tasksSteps = new TasksSteps();
        RestAssured.requestSpecification = Specifications.requestSpecification(baseUrl, token);
    }
}
