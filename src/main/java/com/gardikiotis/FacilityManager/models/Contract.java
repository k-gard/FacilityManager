package com.gardikiotis.FacilityManager.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @ManyToMany(mappedBy="contracts",cascade=CascadeType.MERGE)

    @OrderColumn(name="Contractors")
    private Set<Contractor> Contractors;// = new Contractor[2];
  //  @ManyToMany(cascade=CascadeType.ALL)

 //   private Contractor Contractor2 ;
    private LocalDate StartingDate ;
    private LocalDate EndingDate ;
    private long AwardAmount;

    public Set<Contractor> getContractors() {
        return Contractors;
    }

    public void setContractors(Set<Contractor> contractors) {
        this.Contractors = contractors;
    }

    public Contract(/*Contractor contractor1,*/Set<Contractor> contractors, LocalDate startingDate, LocalDate endingDate, long awardAmount) {
        Contractors = contractors;
       // Contractor2 = contractor2;
        StartingDate = startingDate;
        EndingDate = endingDate;
        AwardAmount = awardAmount;
    }
    public Contract(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /*public Contractor getContractor1() {
        return Contractor1;
    }

    public void setContractor1(Contractor contractor1) {
        Contractor1 = contractor1;
    }

    public Contractor getContractor2() {
        return Contractor2;
    }

    public void setContractor2(Contractor contractor2) {
        Contractor2 = contractor2;
    }
*/
    public LocalDate getStartingDate() {
        return StartingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        StartingDate = startingDate;
    }

    public LocalDate getEndingDate() {
        return EndingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        EndingDate = endingDate;
    }

    public long getAwardAmount() {
        return AwardAmount;
    }

    public void setAwardAmount(long awardAmount) {
        AwardAmount = awardAmount;
    }
}
