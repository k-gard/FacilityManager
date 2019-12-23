package com.gardikiotis.FacilityManager.repositories;

import com.gardikiotis.FacilityManager.models.Contractor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorRepository extends CrudRepository<Contractor, Long> {
}
