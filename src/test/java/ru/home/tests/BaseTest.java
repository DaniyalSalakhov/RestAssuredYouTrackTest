package ru.home.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import ru.home.specifications.Specifications;
import ru.home.utils.ConfigReader;

public abstract class BaseTest {

    protected final String baseUrl = ConfigReader.get("base.url");
    protected final String token = ConfigReader.get("token");

    @BeforeEach
    public void setUp() {
        RestAssured.requestSpecification = Specifications.requestSpecification(baseUrl, token);
    }
}
