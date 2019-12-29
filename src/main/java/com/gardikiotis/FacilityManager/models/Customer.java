package com.gardikiotis.FacilityManager.models;



import javax.persistence.*;
import java.util.List;
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany
    private List<Facility> facilityList;
    @OneToOne
    private Contractor contractor;
    @OneToOne
    private Company company;
    public Customer(Company company,List<Facility> facilities,Contractor contractor){
        this.contractor=contractor;
        this.facilityList=facilities;
        this.company=company;
    }

    public Customer(){
        super();
    }


    public long getId() {
        return id;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

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
