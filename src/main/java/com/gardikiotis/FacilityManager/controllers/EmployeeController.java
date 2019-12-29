package com.gardikiotis.FacilityManager.controllers;

import com.gardikiotis.FacilityManager.models.Employee;
import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.responses.EmployeeResponse;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {


    private EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service=service;
    }

    @GetMapping("/allEmployees")
    public ResponseEntity getAllEmployees() {
        if (service.getAllEmployees().getError()!= null) {
            return new ResponseEntity(
                    service.getAllEmployees().getError(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                service.getAllEmployees().getData(),
                null,
                HttpStatus.OK);
    }

    @PostMapping("/createEmployee")
    public ResponseEntity createEmployee(@RequestBody Employee Employee){
        GenericResponse<EmployeeResponse> response= service.createEmployee(Employee);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.CREATED);
    }

    @PatchMapping("/updateEmployee/{EmployeeId}")
    public ResponseEntity updateEmployee(@PathVariable long EmployeeId , @RequestBody Employee Employee){
        GenericResponse<EmployeeResponse> response = service.updateEmployee(EmployeeId, Employee);
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