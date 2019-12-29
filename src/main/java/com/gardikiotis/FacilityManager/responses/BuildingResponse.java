package com.gardikiotis.FacilityManager.responses;

public class BuildingResponse {
    private long id;
    private String name;
    private String buildingType;

    public BuildingResponse(long id, String name, String buildingType) {
        this.id = id;
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
