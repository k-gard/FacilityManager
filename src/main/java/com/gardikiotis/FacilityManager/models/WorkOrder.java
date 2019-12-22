package com.gardikiotis.FacilityManager.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany
    private List<Employee> employees;
    @OneToMany
    private List<Subcontractor> subcontractors;
    @OneToOne
    private Facility facility;
    @OneToOne
    private Building building;
    @OneToMany
    private List<WorkOrderTask> workOrderTasks;
    private long cost;

    public WorkOrder(List<Employee> employees, List<Subcontractor> subcontractors, Facility facility, Building building, List<WorkOrderTask> workOrderTasks, long cost) {
        this.employees = employees;
        this.subcontractors = subcontractors;
        this.facility = facility;
        this.building = building;
        this.workOrderTasks = workOrderTasks;
        this.cost = cost;
    }

    public WorkOrder() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Subcontractor> getSubcontractors() {
        return subcontractors;
    }

    public void setSubcontractors(List<Subcontractor> subcontractors) {
        this.subcontractors = subcontractors;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<WorkOrderTask> getWorkOrderTasks() {
        return workOrderTasks;
    }

    public void setWorkOrderTasks(List<WorkOrderTask> workOrderTasks) {
        this.workOrderTasks = workOrderTasks;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }
}
