package com.gardikiotis.FacilityManager.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subcontractor extends Contractor {
    @Id
    private long id;
    public Subcontractor(Company company) {
        super(company);
    }

    public Subcontractor() {
        super();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
