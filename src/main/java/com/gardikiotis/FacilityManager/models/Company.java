package com.gardikiotis.FacilityManager.models;

import javax.persistence.*;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String name ;
    private long afm;
    private String addressStreet ;
    private String addressNumber ;
    private String addressPostCode ;
    private long phoneNumber ;
    private String email;
    @OneToOne
    @JoinTable(name = "Company_contractor",
            joinColumns =
                    { @JoinColumn(name = "company_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "contractor_id", referencedColumnName = "id") })
    Contractor contractor;

    public Company(String name, long afm, String addressStreet, String addressNumber, String addressPostCode, long phoneNumber, String email) {
        this.name = name;
        this.afm = afm;
        this.addressStreet = addressStreet;
        this.addressNumber = addressNumber;
        this.addressPostCode = addressPostCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Company() {
    }

    public Company(String name, long afm) {
        this.name = name;
        this.afm = afm;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
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

    public long getAfm() {
        return afm;
    }

    public void setAfm(long afm) {
        this.afm = afm;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressPostCode() {
        return addressPostCode;
    }

    public void setAddressPostCode(String addressPostCode) {
        this.addressPostCode = addressPostCode;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
