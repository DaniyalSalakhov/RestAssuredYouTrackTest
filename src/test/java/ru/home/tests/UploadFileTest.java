package ru.home.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.home.DTO.TaskFileDTO;
import ru.home.factory.TaskFactory;
import ru.home.specifications.Specifications;
import java.io.File;
import static org.junit.Assert.assertEquals;

public class UploadFileTest extends BaseTest {
    private String taskId;

    @BeforeEach
    public void setup() {
        String projectId = "0-0";
        String summary = "UploadFileTest";
        String description = "testingApi";
        taskId = tasksApi.createTask(TaskFactory.createTask(projectId, summary, description))
                .then().spec(Specifications.response200())
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
