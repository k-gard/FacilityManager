package com.gardikiotis.FacilityManager.services;

import com.gardikiotis.FacilityManager.responses.Error;
import com.gardikiotis.FacilityManager.mappers.EmployeeMapper;
import com.gardikiotis.FacilityManager.models.Employee;
import com.gardikiotis.FacilityManager.responses.EmployeeResponse;
import com.gardikiotis.FacilityManager.responses.GenericResponse;
import com.gardikiotis.FacilityManager.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class EmployeeService {

    private EmployeeMapper mapper;

    private EmployeeRepository repository;

    public EmployeeService(EmployeeMapper mapper, EmployeeRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public GenericResponse getAllEmployees(){
        try {
            Iterable<Employee> retrievedEmployees = repository.findAll();
            List<EmployeeResponse> Employees = new ArrayList<>();
            for (Employee Employee : retrievedEmployees
            ) {
                Employees.add(mapper.mapEmployeeToEmployeeResponse(Employee));
            }
            if (Employees.isEmpty()) {
                return new GenericResponse<>(new Error(0, "Error", "Nothing found"));

            }
            return new GenericResponse<>(Employees);
        }catch (Exception e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error", "Unable to retrieve data"));
        }


    }

    public GenericResponse<Employee> getEmployeeById(long id){
        try {
            Optional<Employee> fetchedEmployee = repository.findById(id);
            Employee Employee= fetchedEmployee.get();
            return new GenericResponse<>(Employee);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Wrong id for Employee","Id : "+id+" does not exist" ));
        }
    }


    public GenericResponse<EmployeeResponse> createEmployee(Employee Employee) {
        repository.save(Employee);
        return new GenericResponse<>(mapper.mapEmployeeToEmployeeResponse(Employee));
    }

    public GenericResponse<EmployeeResponse> updateEmployee(long EmployeeId, Employee Employee) {
        Optional<Employee> fetchedEmployee = repository.findById(EmployeeId);
        if(!fetchedEmployee.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong Input","Employee with id: "+EmployeeId+" does not exist"));

        }
        Employee retrievedEmployee= fetchedEmployee.get();
        Map<String, Object> EmployeeMap = new HashMap<>();
        Field[] allFields = Employee.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            try {

                if(!field.getName().equalsIgnoreCase("id")) {
                    Object value = field.get(Employee);
                    if (value != null&& !value.equals(0)) {
                        EmployeeMap.put(field.getName(), value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return new GenericResponse<>(new Error(0, "Cannot Access field", "Field : "+field));
            }
            field.setAccessible(false);
        }

        EmployeeMap.forEach((k, v) -> {
            // use reflection to get field k on retrievedEmployee and set it to value k
            Field field = ReflectionUtils.findField(Employee.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, retrievedEmployee, v);
            field.setAccessible(false);
        });

        repository.save(retrievedEmployee);
        return new GenericResponse<>(mapper.mapEmployeeToEmployeeResponse(retrievedEmployee));
    }
}

