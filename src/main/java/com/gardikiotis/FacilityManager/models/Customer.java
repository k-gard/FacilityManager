package com.gardikiotis.FacilityManager.models;

import javax.persistence.*;
import java.util.List;
@Entity
public class Customer extends Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany
    private List<Facility> facilityList;
    public Customer(Company company,List<Facility> facilities){
        super(company);
        this.facilityList=facilities;
    }

    public Customer(){
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

    public List<Facility> getFacilityList() {
        return facilityList;
    }

    public void setFacilityList(List<Facility> facilityList) {
        this.facilityList = facilityList;
    }
}
