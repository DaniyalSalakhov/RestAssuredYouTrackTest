package ru.home.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.home.DTO.TaskDTO;
import ru.home.DTO.ProjectDTO;
import ru.home.api.TasksApi;
import ru.home.factory.TaskFactory;
import ru.home.specifications.Specifications;

public class DeleteTaskTest extends BaseTest{
    private String taskId;
    private TasksApi tasksApi = new TasksApi();

    @BeforeEach
    public void setup(){
        String projectId = "0-0";
        String summary = "TaskToDelete";
        String description = "testingApi";
        TaskDTO response = tasksApi.createTask(TaskFactory.createTask(projectId, summary, description))
                .then().spec(Specifications.response200())
                .extract().as(TaskDTO.class);
        taskId = response.getId();
    }

    @Test
    public void deleteTask() {
        tasksApi.deleteTask(taskId).then().spec(Specifications.response200());
        tasksApi.getTask(taskId).then().spec(Specifications.response404());
    }
}
