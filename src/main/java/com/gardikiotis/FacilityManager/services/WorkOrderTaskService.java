package com.gardikiotis.FacilityManager.services;

import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.mappers.WorkOrderTaskMapper;
import com.gardikiotis.FacilityManager.models.WorkOrderTask;
import com.gardikiotis.FacilityManager.responses.WorkOrderTaskResponse;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.repositories.WorkOrderTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class WorkOrderTaskService {

    private WorkOrderTaskMapper mapper;

    private WorkOrderTaskRepository repository;

    public WorkOrderTaskService(WorkOrderTaskMapper mapper, WorkOrderTaskRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public GenericResponse getAllWorkOrderTasks(){
        try {
            Iterable<WorkOrderTask> retrievedWorkOrderTasks = repository.findAll();
            List<WorkOrderTaskResponse> WorkOrderTasks = new ArrayList<>();
            for (WorkOrderTask WorkOrderTask : retrievedWorkOrderTasks
            ) {
                WorkOrderTasks.add(mapper.mapWorkOrderTaskToWorkOrderTaskResponse(WorkOrderTask));
            }
            if (WorkOrderTasks.isEmpty()) {
                return new GenericResponse<>(new Error(0, "Error", "Nothing found"));

            }
            return new GenericResponse<>(WorkOrderTasks);
        }catch (Exception e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error", "Unable to retrieve data"));
        }


    }

    public GenericResponse<WorkOrderTask> getWorkOrderTaskById(long id){
        try {
            Optional<WorkOrderTask> fetchedWorkOrderTask = repository.findById(id);
            WorkOrderTask WorkOrderTask= fetchedWorkOrderTask.get();
            return new GenericResponse<>(WorkOrderTask);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Wrong id for WorkOrderTask","Id : "+id+" does not exist" ));
        }
    }


    public GenericResponse<WorkOrderTaskResponse> createWorkOrderTask(WorkOrderTask WorkOrderTask) {
        repository.save(WorkOrderTask);
        return new GenericResponse<>(mapper.mapWorkOrderTaskToWorkOrderTaskResponse(WorkOrderTask));
    }

    public GenericResponse<WorkOrderTaskResponse> updateWorkOrderTask(long WorkOrderTaskId, WorkOrderTask WorkOrderTask) {
        Optional<WorkOrderTask> fetchedWorkOrderTask = repository.findById(WorkOrderTaskId);
        if(!fetchedWorkOrderTask.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong Input","WorkOrderTask with id: "+WorkOrderTaskId+" does not exist"));

        }
        WorkOrderTask retrievedWorkOrderTask= fetchedWorkOrderTask.get();
        Map<String, Object> WorkOrderTaskMap = new HashMap<>();
        Field[] allFields = WorkOrderTask.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            try {

                if(!field.getName().equalsIgnoreCase("id")) {
                    Object value = field.get(WorkOrderTask);
                    if (value != null&& !value.equals(0)) {
                        WorkOrderTaskMap.put(field.getName(), value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return new GenericResponse<>(new Error(0, "Cannot Access field", "Field : "+field));
            }
            field.setAccessible(false);
        }

        WorkOrderTaskMap.forEach((k, v) -> {
            // use reflection to get field k on retrievedEmployee and set it to value k
            Field field = ReflectionUtils.findField(WorkOrderTask.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, retrievedWorkOrderTask, v);
            field.setAccessible(false);
        });

        repository.save(retrievedWorkOrderTask);
        return new GenericResponse<>(mapper.mapWorkOrderTaskToWorkOrderTaskResponse(retrievedWorkOrderTask));
    }
}

