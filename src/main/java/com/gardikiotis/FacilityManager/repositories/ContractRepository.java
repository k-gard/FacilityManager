package com.gardikiotis.FacilityManager.repositories;

import com.gardikiotis.FacilityManager.models.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long> {
}
