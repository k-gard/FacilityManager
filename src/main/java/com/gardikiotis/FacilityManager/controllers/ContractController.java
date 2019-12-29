package com.gardikiotis.FacilityManager.controllers;

import com.gardikiotis.FacilityManager.models.Contract;
import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.responses.ContractResponse;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.services.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContractController {


    private ContractService service;

    public ContractController(ContractService service){
        this.service=service;
    }

    @GetMapping("/allContracts")
    public ResponseEntity getAllContracts() {
        if (service.getAllContracts().getError()!= null) {
            return new ResponseEntity(
                    service.getAllContracts().getError(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                service.getAllContracts().getData(),
                null,
                HttpStatus.OK);
    }

    @PostMapping("/createContract")
    public ResponseEntity createContract(@RequestBody Contract Contract){
        GenericResponse<ContractResponse> response= service.createContract(Contract);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.CREATED);
    }

    @PatchMapping("/updateContract/{ContractId}")
    public ResponseEntity updateContract(@PathVariable long ContractId , @RequestBody Contract Contract){
        GenericResponse<ContractResponse> response = service.updateContract(ContractId, Contract);
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