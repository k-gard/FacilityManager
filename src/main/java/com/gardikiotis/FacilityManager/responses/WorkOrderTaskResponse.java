package com.gardikiotis.FacilityManager.responses;

import com.gardikiotis.FacilityManager.models.Employee;
import com.gardikiotis.FacilityManager.models.Subcontractor;
import com.gardikiotis.FacilityManager.models.Task;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class WorkOrderTaskResponse {

    private long id;
    private List<Employee> employeeList;
    private List<Subcontractor> subcontractorList;
    private Task task;
    private Date startingTime;
    private Date endingTime;
    private int hourDuration;

    public WorkOrderTaskResponse(long id, List<Employee> employeeList, List<Subcontractor> subcontractorList, Task task, Date startingTime, Date endingTime, int hourDuration) {
        this.id = id;
        this.employeeList = employeeList;
        this.subcontractorList = subcontractorList;
        this.task = task;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.hourDuration = hourDuration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Subcontractor> getSubcontractorList() {
        return subcontractorList;
    }

    public void setSubcontractorList(List<Subcontractor> subcontractorList) {
        this.subcontractorList = subcontractorList;
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
