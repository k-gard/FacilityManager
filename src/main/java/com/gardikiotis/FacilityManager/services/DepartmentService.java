package com.gardikiotis.FacilityManager.services;

import com.gardikiotis.FacilityManager.mappers.DepartmentMapper;
import com.gardikiotis.FacilityManager.models.Company;
import com.gardikiotis.FacilityManager.responses.CompanyResponse;
import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.models.Department;
import com.gardikiotis.FacilityManager.responses.DepartmentResponse;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;


import java.lang.reflect.Field;
import java.util.*;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper mapper;

    @Autowired
    private DepartmentRepository repository;


    public GenericResponse<List<DepartmentResponse>> getAllDepartments(){
        try {
            Iterable<Department> retrievedDepartments = repository.findAll();
            List<DepartmentResponse> departments = new ArrayList<>();
            for (Department department : retrievedDepartments) {
                departments.add(mapper.mapDepartmentToDepartmentResponse(department));
            }
            if (departments.isEmpty()) {
                return new GenericResponse<>(new Error(0, "Empty Repository", "Please add Department"));
            }
            return new GenericResponse<>(departments);
        } catch(Exception e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error", "Unable to retrieve data"));
        }
    }
    public GenericResponse<DepartmentResponse> updateDepartment(long departmentId, Department department) {
        Optional<Department> fetchedDepartment = repository.findById(departmentId);
        if(!fetchedDepartment.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong Input","Department with id: "+departmentId+" does not exist"));

        }
        Department retrievedDepartment= fetchedDepartment.get();
        Map<String, Object> departmentMap = new HashMap<>();
        Field[] allFields = department.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            try {

                if(!field.getName().equalsIgnoreCase("id")) {
                    Object value = field.get(department);
                    if (value != null&& !value.equals(0)) {
                        departmentMap.put(field.getName(), value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return new GenericResponse<>(new Error(0, "Cannot Access field", "Field : "+field));
            }
            field.setAccessible(false);
        }

        departmentMap.forEach((k, v) -> {
            // use reflection to get field k on retrievedEmployee and set it to value k
            Field field = ReflectionUtils.findField(Department.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, retrievedDepartment, v);
            field.setAccessible(false);
        });

        repository.save(retrievedDepartment);
        return new GenericResponse<>(mapper.mapDepartmentToDepartmentResponse(retrievedDepartment));
    }
    public GenericResponse<DepartmentResponse> createDepartment(Department department) {
        repository.save(department);
        return new GenericResponse<>(mapper.mapDepartmentToDepartmentResponse(department));
    }

}

