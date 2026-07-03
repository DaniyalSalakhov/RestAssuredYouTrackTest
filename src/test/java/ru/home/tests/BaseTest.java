package ru.home.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import ru.home.utils.ConfigReader;

public abstract class BaseTest {

    protected String baseUrl = ConfigReader.get("base.url");
    protected String token = ConfigReader.get("token");

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = baseUrl;

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }
}
