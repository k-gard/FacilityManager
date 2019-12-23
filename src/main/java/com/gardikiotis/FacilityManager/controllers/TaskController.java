package com.gardikiotis.FacilityManager.controllers;

import com.gardikiotis.FacilityManager.responses.TaskResponse;
import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.models.Task;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class TaskController {

    private TaskService service;

    public TaskController(TaskService service){
        this.service=service;
    }

    @GetMapping("/allTasks")
    public ResponseEntity getAllTasks() {
        if (service.getAllTasks().getError()!= null) {
            return new ResponseEntity(
                    service.getAllTasks().getError(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                service.getAllTasks().getData(),
                null,
                HttpStatus.OK);
    }

    @PostMapping("/createTask")
    public ResponseEntity createTask(@RequestBody Task Task){
        GenericResponse<TaskResponse> response= service.createTask(Task);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.CREATED);
    }

    @PatchMapping("/updateTask/{TaskId}")
    public ResponseEntity updateTask(@PathVariable long TaskId , @RequestBody Task Task){
        GenericResponse<TaskResponse> response = service.updateTask(TaskId, Task);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.OK);
    }



    @ExceptionHandler({NumberFormatException.class})
    public ResponseEntity handleNumberFormatException(){
        return new ResponseEntity<>(new Error(0,"Wrong input type", "Id must be type long"),
                null,
                HttpStatus.BAD_REQUEST);
    }
}
