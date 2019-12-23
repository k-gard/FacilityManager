package com.gardikiotis.FacilityManager.responses;

import com.gardikiotis.FacilityManager.models.Company;
import com.gardikiotis.FacilityManager.models.Facility;

import javax.persistence.*;
import java.util.List;

public class CustomerResponse {

    private long id;
    private List<FacilityResponse> facilityList;
    private CompanyResponse company ;

    public CustomerResponse(long id,/*List<FacilityResponse> facilityList,*/ CompanyResponse company) {
        this.id = id;
       // this.facilityList=facilityList;
        this.company = company;
    }
}
