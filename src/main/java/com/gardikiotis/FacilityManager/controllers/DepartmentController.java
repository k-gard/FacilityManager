package com.gardikiotis.FacilityManager.controllers;

import com.gardikiotis.FacilityManager.responses.DepartmentResponse;
import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.models.Department;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class DepartmentController {

    private DepartmentService service;

    public DepartmentController(DepartmentService service){
        this.service=service;
    }

    @GetMapping("/allDepartments")
    public ResponseEntity getAllDepartments() {
        if (service.getAllDepartments().getError()!= null) {
            return new ResponseEntity(
                    service.getAllDepartments().getError(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                service.getAllDepartments().getData(),
                null,
                HttpStatus.OK);
    }

    @PostMapping("/createDepartment")
    public ResponseEntity createDepartment(@RequestBody Department Department){
        GenericResponse<DepartmentResponse> response= service.createDepartment(Department);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.CREATED);
    }

    @PatchMapping("/updateDepartment/{DepartmentId}")
    public ResponseEntity updateDepartment(@PathVariable long DepartmentId , @RequestBody Department Department){
        GenericResponse<DepartmentResponse> response = service.updateDepartment(DepartmentId, Department);
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
