package ru.home.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.home.DTO.TaskDTO;
import ru.home.DTO.ProjectDTO;
import ru.home.api.TasksApi;
import ru.home.specifications.Specifications;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateTaskTest extends BaseTest {

    private String taskId;
    private TasksApi tasksApi;

    @BeforeEach
    public void setup(){
        tasksApi = new TasksApi();
    }

    @ParameterizedTest
    @CsvSource({"0-0, Test, TestingAPI"})
    public void createTask(String projectId, String summary, String description) {
        TaskDTO request = TaskDTO.builder()
                .project(ProjectDTO.builder().id(projectId).build())
                .summary(summary)
                .description(description)
                .build();
        TaskDTO response = tasksApi.createTask(request).then().spec(Specifications.response200())
                .extract().as(TaskDTO.class);
        taskId = response.getId();

        assertNotNull(taskId);
    }

    @AfterEach
    void shutDown() {
        tasksApi.deleteTask(taskId).then().spec(Specifications.response200());
    }
}
