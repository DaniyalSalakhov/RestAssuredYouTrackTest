package ru.home.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.home.DTO.ProjectDTO;
import ru.home.DTO.TaskDTO;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UploadFileTest extends BaseTest {
    private String taskId;

    @BeforeEach
    public void setup() {
        TaskDTO request = TaskDTO.builder()
                .project(ProjectDTO.builder().id("0-0").build())
                .summary("UploadFileTest")
                .description("testingApi")
                .build();
        taskId = given().body(request).log().all()
                .when().post("/issues")
                .then().log().all().statusCode(200)
                .extract().path("id");
    }

    @ParameterizedTest
    @CsvSource({"src/test/resources/files, uploadTestFile.txt"})
    public void uploadFile(String path, String fileName) {
        File file = new File(path, fileName);
        given().contentType("multipart/form-data")
                .multiPart("upload1",  file)
                .queryParam("fields", "id,name")
                .log().all()
                .when().post("/issues/{id}/attachments", taskId)
                .then().log().all().statusCode(200)
                .body("[0].name", equalTo(fileName));
    }

    @AfterEach
    public void shutdown() {
        given()
                .when()
                .delete("/issues/{id}", taskId)
                .then()
                .statusCode(200);
    }
}
