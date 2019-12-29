package com.gardikiotis.FacilityManager.controllers;

import com.gardikiotis.FacilityManager.models.Building;
import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.responses.BuildingResponse;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.services.BuildingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BuildingController {


    private BuildingService service;

    public BuildingController(BuildingService service){
        this.service=service;
    }

    @GetMapping("/allBuildings")
    public ResponseEntity getAllBuildings() {
        if (service.getAllBuildings().getError()!= null) {
            return new ResponseEntity(
                    service.getAllBuildings().getError(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                service.getAllBuildings().getData(),
                null,
                HttpStatus.OK);
    }

    @PostMapping("/createBuilding")
    public ResponseEntity createBuilding(@RequestBody Building Building){
        GenericResponse<BuildingResponse> response= service.createBuilding(Building);
        if(response.getError()!=null){
            return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.getData(),null,HttpStatus.CREATED);
    }

    @PatchMapping("/updateBuilding/{BuildingId}")
    public ResponseEntity updateBuilding(@PathVariable long BuildingId , @RequestBody Building Building){
        GenericResponse<BuildingResponse> response = service.updateBuilding(BuildingId, Building);
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