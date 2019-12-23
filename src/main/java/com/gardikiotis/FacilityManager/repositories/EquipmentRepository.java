package com.gardikiotis.FacilityManager.repositories;

import com.gardikiotis.FacilityManager.models.Equipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Long> {
}
