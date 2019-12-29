package com.gardikiotis.FacilityManager.mappers;

import com.gardikiotis.FacilityManager.models.Building;
import com.gardikiotis.FacilityManager.models.Equipment;
import com.gardikiotis.FacilityManager.models.Facility;
import com.gardikiotis.FacilityManager.responses.BuildingResponse;
import com.gardikiotis.FacilityManager.responses.FacilityResponse;
import com.gardikiotis.FacilityManager.responses.CompanyResponse;
import com.gardikiotis.FacilityManager.responses.EquipmentResponse;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class FacilityMapper {
    CompanyMapper companyMapper;
    BuildingMapper buildingMapper;
    EquipmentMapper equipmentMapper;
    public FacilityResponse mapFacilityToFacilityResponse(Facility facility) {
        return new FacilityResponse(
                facility.getId(),
                facility.getFacilityName(),
                companyMapper.mapCompanyToCompanyResponse(facility.getOwner().getCompany()),
                initializeBuildingResponseList(facility.getBuildings()),
                initializeEquipmentResponseList(facility.getEquipmentList())
        );
    }

private List<EquipmentResponse> initializeEquipmentResponseList(List<Equipment> equipment){
    ArrayList<EquipmentResponse> list= new ArrayList<>() ;
    for (Equipment e: equipment){
        list.add(equipmentMapper.mapEquipmentToEquipmentResponse(e));
    }
    return  list;

}

    private List<BuildingResponse> initializeBuildingResponseList(List<Building> buildings){
        ArrayList<BuildingResponse> list= new ArrayList<>() ;
        for (Building b: buildings){
            list.add(buildingMapper.mapBuildingToBuildingResponse(b));
        }
        return  list;

    }

}
