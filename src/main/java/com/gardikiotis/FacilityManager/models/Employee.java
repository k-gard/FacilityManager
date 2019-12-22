package com.gardikiotis.FacilityManager.models;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    @OneToOne
    private User user;
    @ManyToOne
    private Department department;
    private String employeeType;
    private double costPerHour;

    public Employee(String firstName, String lastName, User user, Department department, String employeeType, double costPerHour) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.department = department;
        this.employeeType = employeeType;
        this.costPerHour = costPerHour;
    }

    public Employee() {
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(double costPerHour) {
        this.costPerHour = costPerHour;
    }

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

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }
}
