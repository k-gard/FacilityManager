package com.gardikiotis.FacilityManager.controllers;

import com.gardikiotis.FacilityManager.models.WorkOrderTask;
import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.responses.WorkOrderTaskResponse;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.services.WorkOrderTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkOrderTaskController {


    private WorkOrderTaskService service;

    public WorkOrderTaskController(WorkOrderTaskService service){
        this.service=service;
    }

    @GetMapping("/allWorkOrderTasks")
    public ResponseEntity getAllWorkOrderTasks() {
        if (service.getAllWorkOrderTasks().getError()!= null) {
            return new ResponseEntity(
                    service.getAllWorkOrderTasks().getError(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                service.getAllWorkOrderTasks().getData(),
                null,
                HttpStatus.OK);
    }

    @PostMapping("/createWorkOrderTask")
    public ResponseEntity createWorkOrderTask(@RequestBody WorkOrderTask WorkOrderTask){
        GenericResponse<WorkOrderTaskResponse> response= service.createWorkOrderTask(WorkOrderTask);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.CREATED);
    }

    @PatchMapping("/updateWorkOrderTask/{WorkOrderTaskId}")
    public ResponseEntity updateWorkOrderTask(@PathVariable long WorkOrderTaskId , @RequestBody WorkOrderTask WorkOrderTask){
        GenericResponse<WorkOrderTaskResponse> response = service.updateWorkOrderTask(WorkOrderTaskId, WorkOrderTask);
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