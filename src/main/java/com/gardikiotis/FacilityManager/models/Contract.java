package com.gardikiotis.FacilityManager.models;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @ManyToOne
    private Contractor Contractor1;
    @ManyToOne
    private Contractor Contractor2 ;
    private Date StartingDate ;
    private Date EndingDate ;
    private long AwardAmount;

    public Contract(Contractor contractor1, Contractor contractor2, Date startingDate, Date endingDate, long awardAmount) {
        Contractor1 = contractor1;
        Contractor2 = contractor2;
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

    public Contractor getContractor1() {
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

    public Date getStartingDate() {
        return StartingDate;
    }

    public void setStartingDate(Date startingDate) {
        StartingDate = startingDate;
    }

    public Date getEndingDate() {
        return EndingDate;
    }

    public void setEndingDate(Date endingDate) {
        EndingDate = endingDate;
    }

    public long getAwardAmount() {
        return AwardAmount;
    }

    public void setAwardAmount(long awardAmount) {
        AwardAmount = awardAmount;
    }
}
