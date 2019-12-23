package com.gardikiotis.FacilityManager.mappers;

import com.gardikiotis.FacilityManager.models.Task;
import com.gardikiotis.FacilityManager.responses.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskResponse mapTaskToTaskResponse(Task task) {
        return  new TaskResponse(
                task.getId(),
                task.getDescription(),
                task.getCategory()
        );
    }
}
