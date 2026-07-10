package ru.home.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.home.DTO.TaskDTO;

public class DeleteTaskTest extends BaseTest{
    private String taskId;

    @BeforeEach
    public void setup(){
        String projectId = "0-0";
        String summary = "TaskToDelete";
        String description = "testingApi";
        TaskDTO task = tasksSteps.createTask(projectId, summary, description);
        taskId = task.getId();
    }

    @Test
    public void deleteTask() {
        tasksSteps.deleteTask(taskId);
    }
}
