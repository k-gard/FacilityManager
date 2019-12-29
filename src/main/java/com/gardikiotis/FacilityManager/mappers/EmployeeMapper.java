package com.gardikiotis.FacilityManager.mappers;

import com.gardikiotis.FacilityManager.models.Employee;
import com.gardikiotis.FacilityManager.responses.EmployeeResponse;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public EmployeeResponse mapEmployeeToEmployeeResponse(Employee employee){
        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getUser(),
                employee.getDepartment(),
                employee.getEmployeeType(),
                employee.getCostPerHour()
        );

    }
}
