package com.gardikiotis.FacilityManager.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Building {
    @Id
    private long id;
    private String name;
    private String buildingType;

    public Building(String name, String buildingType) {
        this.name = name;
        this.buildingType = buildingType;
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

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }
}
