package com.gardikiotis.FacilityManager.responses;


import com.gardikiotis.FacilityManager.models.Employee;

import java.util.List;

public class DepartmentResponse {
    private long id;
    private String name ;
    private String description ;
 //   private List<Employee> employees;

    public DepartmentResponse(long id, String name, String description/*, List<Employee> employees*/) {
        this.id = id;
        this.name = name;
        this.description = description;
    //    this.employees = employees;
    }
}


