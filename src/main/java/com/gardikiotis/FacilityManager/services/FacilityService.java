package com.gardikiotis.FacilityManager.services;

import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.mappers.FacilityMapper;
import com.gardikiotis.FacilityManager.models.Facility;
import com.gardikiotis.FacilityManager.responses.FacilityResponse;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.repositories.FacilityRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class FacilityService {

    private FacilityMapper mapper;

    private FacilityRepository repository;

    public FacilityService(FacilityMapper mapper, FacilityRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public GenericResponse getAllFacilities(){
        try {
            Iterable<Facility> retrievedFacilities = repository.findAll();
            List<FacilityResponse> Facilities = new ArrayList<>();
            for (Facility Facility : retrievedFacilities
            ) {
                Facilities.add(mapper.mapFacilityToFacilityResponse(Facility));
            }
            if (Facilities.isEmpty()) {
                return new GenericResponse<>(new Error(0, "Error", "Nothing found"));

            }
            return new GenericResponse<>(Facilities);
        }catch (Exception e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error", "Unable to retrieve data"));
        }


    }

    public GenericResponse<Facility> getFacilityById(long id){
        try {
            Optional<Facility> fetchedFacility = repository.findById(id);
            Facility Facility= fetchedFacility.get();
            return new GenericResponse<>(Facility);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Wrong id for Facility","Id : "+id+" does not exist" ));
        }
    }


    public GenericResponse<FacilityResponse> createFacility(Facility Facility) {
        repository.save(Facility);
        return new GenericResponse<>(mapper.mapFacilityToFacilityResponse(Facility));
    }

    public GenericResponse<FacilityResponse> updateFacility(long FacilityId, Facility Facility) {
        Optional<Facility> fetchedFacility = repository.findById(FacilityId);
        if(!fetchedFacility.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong Input","Facility with id: "+FacilityId+" does not exist"));

        }
        Facility retrievedFacility= fetchedFacility.get();
        Map<String, Object> FacilityMap = new HashMap<>();
        Field[] allFields = Facility.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            try {

                if(!field.getName().equalsIgnoreCase("id")) {
                    Object value = field.get(Facility);
                    if (value != null&& !value.equals(0)) {
                        FacilityMap.put(field.getName(), value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return new GenericResponse<>(new Error(0, "Cannot Access field", "Field : "+field));
            }
            field.setAccessible(false);
        }

        FacilityMap.forEach((k, v) -> {
            // use reflection to get field k on retrievedEmployee and set it to value k
            Field field = ReflectionUtils.findField(Facility.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, retrievedFacility, v);
            field.setAccessible(false);
        });

        repository.save(retrievedFacility);
        return new GenericResponse<>(mapper.mapFacilityToFacilityResponse(retrievedFacility));
    }
}

