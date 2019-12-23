package com.gardikiotis.FacilityManager.services;

import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.mappers.TaskMapper;
import com.gardikiotis.FacilityManager.models.Task;
import com.gardikiotis.FacilityManager.responses.TaskResponse;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class TaskService {

    private TaskMapper mapper;

    private TaskRepository repository;

    public TaskService(TaskMapper mapper, TaskRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public GenericResponse getAllTasks(){
        try {
            Iterable<Task> retrievedTasks = repository.findAll();
            List<TaskResponse> tasks = new ArrayList<>();
            for (Task Task : retrievedTasks
            ) {
                tasks.add(mapper.mapTaskToTaskResponse(Task));
            }
            if (tasks.isEmpty()) {
                return new GenericResponse<>(new Error(0, "Error", "Nothing found"));

            }
            return new GenericResponse<>(tasks);
        }catch (Exception e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error", "Unable to retrieve data"));
        }


    }

    public GenericResponse<Task> getTaskById(long id){
        try {
            Optional<Task> fetchedTask = repository.findById(id);
            Task Task= fetchedTask.get();
            return new GenericResponse<>(Task);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Wrong id for Task","Id : "+id+" does not exist" ));
        }
    }


    public GenericResponse<TaskResponse> createTask(Task Task) {
        repository.save(Task);
        return new GenericResponse<>(mapper.mapTaskToTaskResponse(Task));
    }

    public GenericResponse<TaskResponse> updateTask(long TaskId, Task Task) {
        Optional<Task> fetchedTask = repository.findById(TaskId);
        if(!fetchedTask.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong Input","Task with id: "+TaskId+" does not exist"));

        }
        Task retrievedTask= fetchedTask.get();
        Map<String, Object> TaskMap = new HashMap<>();
        Field[] allFields = Task.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            try {

                if(!field.getName().equalsIgnoreCase("id")) {
                    Object value = field.get(Task);
                    if (value != null&& !value.equals(0)) {
                        TaskMap.put(field.getName(), value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return new GenericResponse<>(new Error(0, "Cannot Access field", "Field : "+field));
            }
            field.setAccessible(false);
        }

        TaskMap.forEach((k, v) -> {
            // use reflection to get field k on retrievedEmployee and set it to value k
            Field field = ReflectionUtils.findField(Task.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, retrievedTask, v);
            field.setAccessible(false);
        });

        repository.save(retrievedTask);
        return new GenericResponse<>(mapper.mapTaskToTaskResponse(retrievedTask));
    }
}

