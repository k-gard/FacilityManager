package com.gardikiotis.FacilityManager.models;

import java.util.List;

public class TechnicalDepartment extends Department {


    public TechnicalDepartment() {
    }

    public TechnicalDepartment(String name, String description, List<Employee> employees) {
        super(name, description, employees);
    }

}
