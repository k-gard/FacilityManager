package com.gardikiotis.FacilityManager.mappers;

import com.gardikiotis.FacilityManager.models.Department;
import com.gardikiotis.FacilityManager.models.Employee;
import com.gardikiotis.FacilityManager.responses.DepartmentResponse;
import com.gardikiotis.FacilityManager.responses.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentMapper {
    @Autowired
    private EmployeeMapper mapper;

    public DepartmentResponse mapDepartmentToDepartmentResponse(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getDescription(),
                initEmployeeResponseList(department)
        );
    }

    private List<EmployeeResponse> initEmployeeResponseList(Department department) {
        List<EmployeeResponse> employeeResponseList=new ArrayList<>();
    for (Employee e : department.getEmployees()){
        employeeResponseList.add(mapper.mapEmployeeToEmployeeResponse(e));
    }
    return employeeResponseList;
    }


}

