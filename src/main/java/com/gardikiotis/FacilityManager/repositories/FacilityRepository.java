package com.gardikiotis.FacilityManager.repositories;

import com.gardikiotis.FacilityManager.models.Facility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends CrudRepository<Facility, Long> {
}
