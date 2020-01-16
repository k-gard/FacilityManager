package com.gardikiotis.FacilityManager.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @OneToOne(mappedBy = "contractor")
    private Company company ;
    @ManyToMany(targetEntity=Contract.class,cascade=CascadeType.MERGE)
//    @JoinTable(
//            name = "Contractor_Contracts",
//            joinColumns = @JoinColumn(name = "contractor_id"),
//            inverseJoinColumns = @JoinColumn(name = "contract_id"))
//    @OrderColumn(name="Contracts")

    private Collection<Contract> contracts ; //=new Contract[2];

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

    public Collection getContracts() {
        return contracts;
    }

    public void setContracts(Collection contracts) {
        this.contracts = contracts;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
