package ru.home.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.home.DTO.TaskDTO;
import ru.home.DTO.ProjectDTO;
import ru.home.api.TasksApi;
import ru.home.specifications.Specifications;

public class DeleteTaskTest extends BaseTest{
    private String taskId;
    private TasksApi tasksApi;

    @BeforeEach
    public void setup(){
        tasksApi = new TasksApi();
        TaskDTO request = TaskDTO.builder()
                .project(ProjectDTO.builder().id("0-0").build())
                .summary("TaskToDelete")
                .description("testingApi")
                .build();
        TaskDTO response = tasksApi.createTask(request).then().spec(Specifications.response200())
                .extract().as(TaskDTO.class);
        taskId = response.getId();
    }

    @Test
    public void deleteTask() {
        tasksApi.deleteTask(taskId).then().spec(Specifications.response200());
        tasksApi.getTask(taskId).then().spec(Specifications.response404());
    }
}
