package ru.home.factory;

import ru.home.DTO.ProjectDTO;
import ru.home.DTO.TaskDTO;

public class TaskFactory {
    public static TaskDTO createTask(String projectId, String summary, String description){
        return TaskDTO.builder()
                .project(ProjectDTO.builder().id(projectId).build())
                .summary(summary)
                .description(description)
                .build();
    }
}
