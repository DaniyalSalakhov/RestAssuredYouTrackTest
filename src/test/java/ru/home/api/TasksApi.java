package ru.home.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.home.DTO.TaskDTO;

import java.io.File;

public class TasksApi {

    public Response createTask(TaskDTO task){
        return RestAssured.given()
                .body(task)
                .post("/issues");
    }

    public Response deleteTask(String taskId){
        return RestAssured.given()
                .when().delete("/issues/{id}", taskId);
    }

    public Response getTaskById(String taskId){
        return RestAssured.given()
                .when().get("/issues/{id}", taskId);
    }

    public Response uploadFile(String taskId, File file){
        return RestAssured.given().contentType("multipart/form-data")
                .multiPart("upload1", file)
                .queryParam("fields", "id,name")
                .when().post("/issues/{id}/attachments", taskId);
    }
}
