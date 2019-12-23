package com.gardikiotis.FacilityManager.controllers;

import com.gardikiotis.FacilityManager.models.Customer;
import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.responses.CustomerResponse;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {


    private CustomerService service;

    public CustomerController(CustomerService service){
        this.service=service;
    }

    @GetMapping("/allCustomers")
    public ResponseEntity getAllCustomers() {
        if (service.getAllCustomers().getError()!= null) {
            return new ResponseEntity(
                    service.getAllCustomers().getError(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                service.getAllCustomers().getData(),
                null,
                HttpStatus.OK);
    }

    @PostMapping("/createCustomer")
    public ResponseEntity createCustomer(@RequestBody Customer Customer){
        GenericResponse<CustomerResponse> response= service.createCustomer(Customer);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.CREATED);
    }

    @PatchMapping("/updateCustomer/{CustomerId}")
    public ResponseEntity updateCustomer(@PathVariable long CustomerId , @RequestBody Customer Customer){
        GenericResponse<CustomerResponse> response = service.updateCustomer(CustomerId, Customer);
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

