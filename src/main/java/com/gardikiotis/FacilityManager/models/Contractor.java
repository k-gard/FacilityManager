package com.gardikiotis.FacilityManager.models;

import javax.persistence.*;

@Entity
public abstract class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @OneToOne
    private Company company ;

    Contractor(Company company) {
        this.company = company;
    }


    Contractor() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
