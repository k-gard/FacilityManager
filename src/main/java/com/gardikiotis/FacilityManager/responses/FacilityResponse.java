package com.gardikiotis.FacilityManager.responses;

import com.gardikiotis.FacilityManager.models.Building;
import com.gardikiotis.FacilityManager.models.Customer;
import com.gardikiotis.FacilityManager.models.Equipment;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

public class FacilityResponse {
    private long Id;
    private  String facilityName;
    private CompanyResponse ownerCompany;
    @OneToMany
    private List<BuildingResponse> buildings;
    @OneToMany
    private List<EquipmentResponse> equipmentList;

    public FacilityResponse(long id, String facilityName, CompanyResponse ownerCompany, List<BuildingResponse> buildings, List<EquipmentResponse> equipmentList) {
        Id = id;
        this.facilityName = facilityName;
        this.ownerCompany = ownerCompany;
        this.buildings = buildings;
        this.equipmentList = equipmentList;
    }
}
