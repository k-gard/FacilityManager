package com.gardikiotis.FacilityManager.responses;

import com.gardikiotis.FacilityManager.models.Department;
import com.gardikiotis.FacilityManager.models.User;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class EmployeeResponse {
    private long id;
    private String firstName;
    private String lastName;

    private User user;

    private Department department;
    private String employeeType;
    private double costPerHour;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(double costPerHour) {
        this.costPerHour = costPerHour;
    }

    public EmployeeResponse(long id, String firstName, String lastName, User user, Department department, String employeeType, double costPerHour) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.department = department;
        this.employeeType = employeeType;
        this.costPerHour = costPerHour;



    }
}
