package com.gardikiotis.FacilityManager.models;

import javax.persistence.*;
import java.util.List;
@Entity
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @ManyToOne
    private Customer owner;
    @OneToMany
    private List<Building> buildings;
    @OneToMany
    private List<Equipment> equipmentList;

    public Facility(Customer owner, List<Building> buildings, List<Equipment> equipmentList) {
        this.owner = owner;
        this.buildings = buildings;
        this.equipmentList = equipmentList;
    }

    public Facility() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }
}
