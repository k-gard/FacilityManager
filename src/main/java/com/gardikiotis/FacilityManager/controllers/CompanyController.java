package com.gardikiotis.FacilityManager.controllers;

import com.gardikiotis.FacilityManager.models.Company;
import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.responses.CompanyResponse;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {


    private CompanyService service;

    public CompanyController(CompanyService service){
        this.service=service;
    }

    @GetMapping("/allCompanies")
    public ResponseEntity getAllCompanies() {
        if (service.getAllCompanies().getError()!= null) {
            return new ResponseEntity(
                    service.getAllCompanies().getError(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                service.getAllCompanies().getData(),
                null,
                HttpStatus.OK);
    }

    @PostMapping("/createCompany")
    public ResponseEntity createCompany(@RequestBody Company company){
        GenericResponse<CompanyResponse> response= service.createCompany(company);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.CREATED);
    }

    @PatchMapping("/updateCompany/{companyId}")
    public ResponseEntity updateCompany(@PathVariable long companyId , @RequestBody Company company){
        GenericResponse<CompanyResponse> response = service.updateCompany(companyId, company);
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

