package com.gardikiotis.FacilityManager.responses;

import com.gardikiotis.FacilityManager.models.MaintenaceFrequencyType;

public class EquipmentResponse {
    private long id;
    private String category;
    private String description;
    private MaintenaceFrequencyType frequencyType;
    private int maintenanceFrequency;

    public EquipmentResponse(long id, String category, String description, MaintenaceFrequencyType frequencyType, int maintenanceFrequency) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.frequencyType = frequencyType;
        this.maintenanceFrequency = maintenanceFrequency;
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
