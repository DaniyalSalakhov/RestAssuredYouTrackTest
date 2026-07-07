package ru.home.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.home.DTO.ProjectDTO;
import ru.home.DTO.TaskDTO;
import ru.home.DTO.TaskFileDTO;
import ru.home.api.TasksApi;
import ru.home.specifications.Specifications;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class UploadFileTest extends BaseTest {
    private String taskId;
    private TasksApi  tasksApi;

    @BeforeEach
    public void setup() {
        tasksApi = new TasksApi();
        TaskDTO request = TaskDTO.builder()
                .project(ProjectDTO.builder().id("0-0").build())
                .summary("UploadFileTest")
                .description("testingApi")
                .build();
        taskId = tasksApi.createTask(request).then().spec(Specifications.response200())
                .extract().path("id");
    }

    @ParameterizedTest
    @CsvSource({"src/test/resources/files, uploadTestFile.txt"})
    public void uploadFile(String path, String fileName) {
        File file = new File(path, fileName);
        TaskFileDTO[] files = tasksApi.uploadFile(taskId, file).then().spec(Specifications.response200())
                .extract().as(TaskFileDTO[].class);
        assertEquals(fileName, files[0].getName());
    }

    @AfterEach
    public void shutdown() {
        tasksApi.deleteTask(taskId).then().spec(Specifications.response200());
    }
}
