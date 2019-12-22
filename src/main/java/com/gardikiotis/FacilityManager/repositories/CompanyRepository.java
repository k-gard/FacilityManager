package com.gardikiotis.FacilityManager.repositories;

import com.gardikiotis.FacilityManager.models.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface CompanyRepository extends CrudRepository<Company, Long> {

}
