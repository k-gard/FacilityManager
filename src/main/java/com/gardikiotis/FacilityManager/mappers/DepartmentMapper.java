package com.gardikiotis.FacilityManager.mappers;

import com.gardikiotis.FacilityManager.models.Department;
import com.gardikiotis.FacilityManager.responses.DepartmentResponse;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {


    public DepartmentResponse mapDepartmentToDepartmentResponse(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getDescription()
               // department.getEmployees()
        );
    }


}

