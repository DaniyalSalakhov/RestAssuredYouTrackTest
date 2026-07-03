package ru.home.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.home.DTO.TaskDTO;
import ru.home.DTO.ProjectDTO;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateTaskTest extends BaseTest {

    private String taskId;

    @ParameterizedTest
    @CsvSource({"0-0, Test, TestingAPI"})
    public void createTask(String projectId, String summary, String description) {
        TaskDTO request = TaskDTO.builder()
                .project(ProjectDTO.builder().id(projectId).build())
                .summary(summary)
                .description(description)
                .build();
        taskId = given().body(request).log().all()
                .when().post("/issues")
                .then().log().all().statusCode(200)
                .extract().path("id");

        assertNotNull(taskId);
    }

    @AfterEach
    void shutDown() {
        System.out.println(taskId);
        given()
                .when()
                .delete("/issues/{id}", taskId)
                .then()
                .statusCode(200);
    }
}
