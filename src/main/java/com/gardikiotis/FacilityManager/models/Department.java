package com.gardikiotis.FacilityManager.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class Department {
    @Id
    private long id;
    private String name ;
    private String description ;
    @OneToMany
    private List<Employee> employees;

    Department(String name, String description, List<Employee> employees) {
        this.name = name;
        this.description = description;
        this.employees = employees;
    }

    Department() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
