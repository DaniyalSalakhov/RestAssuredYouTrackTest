package ru.home.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.home.DTO.TaskDTO;
import ru.home.DTO.ProjectDTO;

import static io.restassured.RestAssured.given;

public class DeleteTaskTest extends BaseTest{
    private String taskId;

    @BeforeEach
    public void setup(){
        TaskDTO request = TaskDTO.builder()
                .project(ProjectDTO.builder().id("0-0").build())
                .summary("TaskToDelete")
                .description("testingApi")
                .build();
        taskId = given().body(request).log().all()
                .when().post("/issues")
                .then().log().all().statusCode(200)
                .extract().path("id");
    }

    @Test
    public void deleteTask() {
        given()
                .when()
                .delete("/issues/{id}", taskId)
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/issues/{id}", taskId)
                .then()
                .statusCode(404);
    }
}
