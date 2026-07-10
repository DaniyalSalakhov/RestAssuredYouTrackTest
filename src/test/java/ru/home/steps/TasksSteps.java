package ru.home.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.home.DTO.TaskDTO;
import ru.home.DTO.TaskFileDTO;
import ru.home.api.TasksApi;
import ru.home.factory.TaskFactory;
import ru.home.specifications.Specifications;

import java.io.File;

public class TasksSteps {
    private final TasksApi tasksApi;

    public TasksSteps() {
        tasksApi = new TasksApi();
    }

    @Step("Создать задачу в проекте '{projectId}' с названием '{summary}' и описанием '{description}'")
    public TaskDTO createTask(String projectId, String summary, String description) {
        TaskDTO task = TaskFactory.createTask(projectId, summary, description);
        return tasksApi.createTask(task)
                .then().spec(Specifications.response200())
                .extract().as(TaskDTO.class);
    }

    @Step("Найти задачу по id '{taskId}'")
    public TaskDTO getTaskById(String taskId) {
        Response response = tasksApi.getTaskById(taskId);
        if(response.getStatusCode() == 200) {
            return response.then().spec(Specifications.response200())
                    .extract().as(TaskDTO.class);
        } else if (response.getStatusCode() == 404) {
            response.then().spec(Specifications.response404());
            return null;
        }
        throw new AssertionError("Unexpected response status: " + response.getStatusCode());
    }

    @Step("Загрузить файл '{file}' в задачу с id '{taskId}'")
    public TaskFileDTO[] uploadFile(String taskId, File file) {
        return tasksApi.uploadFile(taskId, file)
                .then().spec(Specifications.response200())
                .extract().as(TaskFileDTO[].class);
    }

    @Step("Удалить задачу по id '{id}'")
    public void deleteTask(String taskId) {
        tasksApi.deleteTask(taskId).then().spec(Specifications.response200());
    }
}
