package com.gardikiotis.FacilityManager.mappers;

import com.gardikiotis.FacilityManager.models.Equipment;
import com.gardikiotis.FacilityManager.models.MaintenaceFrequencyType;
import com.gardikiotis.FacilityManager.responses.EquipmentResponse;
import org.springframework.stereotype.Component;

@Component
public class EquipmentMapper {


    public EquipmentResponse mapEquipmentToEquipmentResponse(Equipment equipment){
        return new EquipmentResponse(
                equipment.getId(),
                equipment.getCategory(),
                equipment.getDescription(),
                equipment.getFrequencyType(),
                equipment.getMaintenanceFrequency()
        );
    }
}
