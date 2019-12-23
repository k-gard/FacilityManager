package com.gardikiotis.FacilityManager.repositories;

import com.gardikiotis.FacilityManager.models.Building;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends CrudRepository<Building, Long> {
}
