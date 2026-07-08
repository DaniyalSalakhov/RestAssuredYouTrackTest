package ru.home.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.home.DTO.TaskDTO;
import ru.home.factory.TaskFactory;
import ru.home.specifications.Specifications;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateTaskTest extends BaseTest {
    private String taskId;

    @ParameterizedTest
    @CsvSource({"0-0, Test, TestingAPI"})
    public void createTask(String projectId, String summary, String description) {
        TaskDTO response = tasksApi.createTask(TaskFactory.createTask(projectId, summary, description))
                .then().spec(Specifications.response200())
                .extract().as(TaskDTO.class);
        taskId = response.getId();

        assertNotNull(taskId);
    }

    @AfterEach
    void shutDown() {
        tasksApi.deleteTask(taskId).then().spec(Specifications.response200());
    }
}
