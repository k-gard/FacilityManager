package com.gardikiotis.FacilityManager.mappers;

import com.gardikiotis.FacilityManager.models.Building;
import com.gardikiotis.FacilityManager.responses.BuildingResponse;
import org.springframework.stereotype.Component;

@Component
public class BuildingMapper {
    public BuildingResponse mapBuildingToBuildingResponse(Building building){
        return new BuildingResponse(
                building.getId(),
                building.getName(),
                building.getBuildingType()
        );
    }
}
