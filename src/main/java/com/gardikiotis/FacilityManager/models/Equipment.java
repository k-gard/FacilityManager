package com.gardikiotis.FacilityManager.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String category;
    private String description;
    private MaintenaceFrequencyType frequencyType;
    private int maintenanceFrequency;

    public Equipment(String category, String description, MaintenaceFrequencyType frequencyType, int maintenanceFrequency) {
        this.category = category;
        this.description = description;
        this.frequencyType = frequencyType;
        this.maintenanceFrequency = maintenanceFrequency;
    }

    public Equipment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MaintenaceFrequencyType getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(MaintenaceFrequencyType frequencyType) {
        this.frequencyType = frequencyType;
    }

    public int getMaintenanceFrequency() {
        return maintenanceFrequency;
    }

    public void setMaintenanceFrequency(int maintenanceFrequency) {
        this.maintenanceFrequency = maintenanceFrequency;
    }
}
