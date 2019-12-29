package com.gardikiotis.FacilityManager.controllers;

import com.gardikiotis.FacilityManager.models.Facility;
import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.responses.FacilityResponse;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.services.FacilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FacilityController {


    private FacilityService service;

    public FacilityController(FacilityService service){
        this.service=service;
    }

    @GetMapping("/allFacilities")
    public ResponseEntity getAllFacilities() {
        if (service.getAllFacilities().getError()!= null) {
            return new ResponseEntity(
                    service.getAllFacilities().getError(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                service.getAllFacilities().getData(),
                null,
                HttpStatus.OK);
    }

    @PostMapping("/createFacility")
    public ResponseEntity createFacility(@RequestBody Facility Facility){
        GenericResponse<FacilityResponse> response= service.createFacility(Facility);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.CREATED);
    }

    @PatchMapping("/updateFacility/{FacilityId}")
    public ResponseEntity updateFacility(@PathVariable long FacilityId , @RequestBody Facility Facility){
        GenericResponse<FacilityResponse> response = service.updateFacility(FacilityId, Facility);
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

