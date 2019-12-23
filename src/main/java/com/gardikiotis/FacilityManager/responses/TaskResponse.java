package com.gardikiotis.FacilityManager.responses;

public class TaskResponse {
    private long id;
    private String description;
    private String category;

    public TaskResponse(long id, String description, String category) {
        this.id = id;
        this.description = description;
        this.category = category;
    }
}
