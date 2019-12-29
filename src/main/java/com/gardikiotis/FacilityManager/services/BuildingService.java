package com.gardikiotis.FacilityManager.services;

import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.mappers.BuildingMapper;
import com.gardikiotis.FacilityManager.models.Building;
import com.gardikiotis.FacilityManager.responses.BuildingResponse;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.repositories.BuildingRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class BuildingService {

    private BuildingMapper mapper;

    private BuildingRepository repository;

    public BuildingService(BuildingMapper mapper, BuildingRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public GenericResponse getAllBuildings(){
        try {
            Iterable<Building> retrievedBuildings = repository.findAll();
            List<BuildingResponse> Buildings = new ArrayList<>();
            for (Building Building : retrievedBuildings
            ) {
                Buildings.add(mapper.mapBuildingToBuildingResponse(Building));
            }
            if (Buildings.isEmpty()) {
                return new GenericResponse<>(new Error(0, "Error", "Nothing found"));

            }
            return new GenericResponse<>(Buildings);
        }catch (Exception e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error", "Unable to retrieve data"));
        }


    }

    public GenericResponse<Building> getBuildingById(long id){
        try {
            Optional<Building> fetchedBuilding = repository.findById(id);
            Building Building= fetchedBuilding.get();
            return new GenericResponse<>(Building);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Wrong id for Building","Id : "+id+" does not exist" ));
        }
    }


    public GenericResponse<BuildingResponse> createBuilding(Building Building) {
        repository.save(Building);
        return new GenericResponse<>(mapper.mapBuildingToBuildingResponse(Building));
    }

    public GenericResponse<BuildingResponse> updateBuilding(long BuildingId, Building Building) {
        Optional<Building> fetchedBuilding = repository.findById(BuildingId);
        if(!fetchedBuilding.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong Input","Building with id: "+BuildingId+" does not exist"));

        }
        Building retrievedBuilding= fetchedBuilding.get();
        Map<String, Object> BuildingMap = new HashMap<>();
        Field[] allFields = Building.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            try {

                if(!field.getName().equalsIgnoreCase("id")) {
                    Object value = field.get(Building);
                    if (value != null&& !value.equals(0)) {
                        BuildingMap.put(field.getName(), value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return new GenericResponse<>(new Error(0, "Cannot Access field", "Field : "+field));
            }
            field.setAccessible(false);
        }

        BuildingMap.forEach((k, v) -> {
            // use reflection to get field k on retrievedEmployee and set it to value k
            Field field = ReflectionUtils.findField(Building.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, retrievedBuilding, v);
            field.setAccessible(false);
        });

        repository.save(retrievedBuilding);
        return new GenericResponse<>(mapper.mapBuildingToBuildingResponse(retrievedBuilding));
    }
}



