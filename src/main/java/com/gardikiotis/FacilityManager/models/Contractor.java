package com.gardikiotis.FacilityManager.models;

import javax.persistence.*;

@Entity
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @OneToOne
    private Company company ;

    public Contractor(Company company) {
        this.company = company;
    }


    public Contractor() {
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
