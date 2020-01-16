package com.gardikiotis.FacilityManager.responses;

import com.gardikiotis.FacilityManager.models.Contractor;

import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

public class ContractResponse {
    private long id ;
    private Set<Contractor> Contractors;// =new Contractor[2];
   // private Contractor Contractor2 ;
    private LocalDate StartingDate ;
    private LocalDate EndingDate ;
    private long AwardAmount;

    public ContractResponse(long id, Set<Contractor> contractors,/* Contractor contractor2,*/ LocalDate startingDate, LocalDate endingDate, long awardAmount) {
        this.id = id;
        Contractors = contractors;
       // Contractor2 = contractor2;
        StartingDate = startingDate;
        EndingDate = endingDate;
        AwardAmount = awardAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

 /*   public Contractor getContractor1() {
        return Contractor1;
    }

    public void setContractor1(Contractor contractor1) {
        Contractor1 = contractor1;
    }
*/

    public Set<Contractor> getContractors() {
        return Contractors;
    }

    public void setContractors(Set<Contractor> contractors) {
        Contractors = contractors;
    }



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
