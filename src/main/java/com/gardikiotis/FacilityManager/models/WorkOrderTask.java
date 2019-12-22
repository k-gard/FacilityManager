package com.gardikiotis.FacilityManager.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
public class WorkOrderTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany
    private List<Employee> employeeList;
    @OneToMany
    private List<Subcontractor> subcontractorList;
    @OneToOne
    private Task task;
    private Date startingTime;
    private Date endingTime;
    private int hourDuration;

    public WorkOrderTask(List<Employee> employeeList, List<Subcontractor> subcontractorList, Task task, Date startingTime, Date endingTime, int hourDuration) {
        this.employeeList = employeeList;
        this.subcontractorList = subcontractorList;
        this.task = task;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.hourDuration = hourDuration;
    }




    public List<Subcontractor> getSubcontractorList() {
        return subcontractorList;
    }

    public void setSubcontractorList(List<Subcontractor> subcontractorList) {
        this.subcontractorList = subcontractorList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public Date getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }

    public int getHourDuration() {
        return hourDuration;
    }

    public void setHourDuration(int hourDuration) {
        this.hourDuration = hourDuration;
    }
}
